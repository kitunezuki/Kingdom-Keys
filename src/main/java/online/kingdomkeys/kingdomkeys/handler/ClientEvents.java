package online.kingdomkeys.kingdomkeys.handler;

import java.awt.Color;
import java.util.ArrayList;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.gui.OverlayRegistry;
import online.kingdomkeys.kingdomkeys.block.ModBlocks;
import online.kingdomkeys.kingdomkeys.block.StructureWallBlock;
import online.kingdomkeys.kingdomkeys.capability.CastleOblivionCapabilities;
import online.kingdomkeys.kingdomkeys.client.ClientSetup;
import online.kingdomkeys.kingdomkeys.config.ModConfigs;
import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.event.InputEvent.ClickInputEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.TickEvent.RenderTickEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import online.kingdomkeys.kingdomkeys.KingdomKeys;
import online.kingdomkeys.kingdomkeys.capability.IGlobalCapabilities;
import online.kingdomkeys.kingdomkeys.capability.IPlayerCapabilities;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.client.sound.ModSounds;
import online.kingdomkeys.kingdomkeys.item.KeybladeItem;
import online.kingdomkeys.kingdomkeys.item.organization.IOrgWeapon;
import online.kingdomkeys.kingdomkeys.lib.Party;
import online.kingdomkeys.kingdomkeys.lib.Strings;
import online.kingdomkeys.kingdomkeys.network.PacketHandler;
import online.kingdomkeys.kingdomkeys.network.cts.CSShotlockShot;
import online.kingdomkeys.kingdomkeys.shotlock.Shotlock;
import online.kingdomkeys.kingdomkeys.util.Utils;

public class ClientEvents {

	@SubscribeEvent
	public void onRenderTick(RenderTickEvent event) { //Lock on
		Player player = Minecraft.getInstance().player;
		if (player != null) {
			if (ModConfigs.showGuiToggle == ModConfigs.ShowType.WEAPON) {
				player.getMainHandItem().getItem();
				if (player.getMainHandItem().getItem() instanceof KeybladeItem || player.getOffhandItem().getItem() instanceof KeybladeItem || player.getMainHandItem().getItem() instanceof IOrgWeapon || player.getOffhandItem().getItem() instanceof IOrgWeapon) {
					OverlayRegistry.enableOverlay(ClientSetup.COMMAND_MENU, true);
					OverlayRegistry.enableOverlay(ClientSetup.PLAYER_PORTRAIT, true);
					OverlayRegistry.enableOverlay(ClientSetup.HP_BAR, true);
					OverlayRegistry.enableOverlay(ClientSetup.MP_BAR, true);
					OverlayRegistry.enableOverlay(ClientSetup.DRIVE_BAR, true);
					OverlayRegistry.enableOverlay(ClientSetup.SHOTLOCK, true);
				} else {
					OverlayRegistry.enableOverlay(ClientSetup.COMMAND_MENU, false);
					OverlayRegistry.enableOverlay(ClientSetup.PLAYER_PORTRAIT, false);
					OverlayRegistry.enableOverlay(ClientSetup.HP_BAR, false);
					OverlayRegistry.enableOverlay(ClientSetup.MP_BAR, false);
					OverlayRegistry.enableOverlay(ClientSetup.DRIVE_BAR, false);
					OverlayRegistry.enableOverlay(ClientSetup.SHOTLOCK, false);
				}
			}
		}

		if(InputHandler.lockOn != null && player != null) {
			if(InputHandler.lockOn.isRemoved()) {
                InputHandler.lockOn = null;
                return;	
			}
            LivingEntity target = InputHandler.lockOn;

            double dx = player.getX() - target.getX();
            double dz = player.getZ() - target.getZ();
            double dy = player.getY() - (target.getY() + (target.getBbHeight() / 2.0F)-player.getBbHeight());
            double angle = Math.atan2(dz, dx) * 180 / Math.PI;
            double pitch = Math.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * 180 / Math.PI;
            double distance = player.distanceTo(target);
            
            float rYaw = (float) Mth.wrapDegrees(angle - player.getYRot()) + 90;
            float rPitch = (float) pitch - (float) (10.0F / Math.sqrt(distance)) + (float) (distance * Math.PI / 90);
            
            float f = player.getXRot();
            float f1 = player.getYRot();
            
            player.setYRot((float)((double)player.getYRot() + (double)rYaw * 0.15D));
            player.setXRot((float)((double)player.getXRot() - (double)-(rPitch - player.getXRot()) * 0.15D));
            player.xRotO = player.getXRot() - f;
            player.yRotO += player.getYRot() - f1;

            if (player.getVehicle() != null) {
                player.getVehicle().onPassengerTurned(player);
            }

		}
	}
	
	float yaw = 0, pitch = 0;

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event) {
		IGlobalCapabilities globalData = ModCapabilities.getGlobal(event.getEntityLiving());
		if (globalData != null) {
			if (globalData.getStoppedTicks() > 0) {
				event.getEntityLiving().setXRot(pitch);
				event.getEntityLiving().setYRot(yaw);
				event.setCanceled(true);
			} else {
				yaw = event.getEntityLiving().getYRot();
				pitch = event.getEntityLiving().getXRot();
			}
		}
		
		if(event.getEntityLiving() == Minecraft.getInstance().player) {
			if(InputHandler.qrCooldown > 0) {
				InputHandler.qrCooldown -= 1;
			}
		}
		
	}
	
	@SubscribeEvent
	public void RenderEntity(RenderLivingEvent.Pre event) {
		if(event.getEntity() != null) {
			if(event.getEntity() instanceof Player) {
				Player player = (Player) event.getEntity();
				IPlayerCapabilities playerData = ModCapabilities.getPlayer(player);
				if(playerData != null) {
					// Aerial Dodge rotation
					if(playerData.getAerialDodgeTicks() > 0) {
						event.getPoseStack().mulPose(Vector3f.YP.rotationDegrees(player.tickCount*80));
					}
					
					if(playerData.getActiveDriveForm().equals(Strings.Form_Anti)) {
						player.level.addParticle(ParticleTypes.SMOKE, player.getX()+player.level.random.nextDouble() - 0.5D, player.getY()+player.level.random.nextDouble() *2D, player.getZ()+player.level.random.nextDouble() - 0.5D, 0, 0, 0);
					}
				}
			}
		}
	}
	
	public static boolean focusing = false;
	int focusingTicks = 0;
	public static double focusGaugeTemp = 100;
	double cost = 0;
	
	int cooldownTicks = 0;
	@SubscribeEvent
	public void PlayerTick(PlayerTickEvent event) {
		if (event.phase == Phase.END) {
			Minecraft mc = Minecraft.getInstance();
			if (event.player == mc.player && cooldownTicks <= 0) { // Only run this for the local client player
				focusing = mc.options.keyPickItem.isDown() && event.player.getMainHandItem() != null && Utils.getPlayerShotlock(mc.player) != null && (event.player.getMainHandItem().getItem() instanceof KeybladeItem || event.player.getMainHandItem().getItem() instanceof IOrgWeapon);
				IPlayerCapabilities playerData = ModCapabilities.getPlayer(event.player);
				if(playerData == null)
					return;
				Shotlock shotlock = Utils.getPlayerShotlock(mc.player);
				if (focusing) {
					if (focusingTicks == 0) {
						// Has started focusing
						focusGaugeTemp = playerData.getFocus();
						playerData.setShotlockEnemies(new ArrayList<Integer>());
						event.player.level.playSound(event.player, event.player.blockPosition(), ModSounds.shotlock_lockon_start.get(), SoundSource.PLAYERS, 1F, 1F);
					}
					
					if(focusingTicks == 5) {
						event.player.level.playSound(event.player, event.player.blockPosition(), ModSounds.shotlock_lockon_idle.get(), SoundSource.PLAYERS, 1F, 1F);
					}
					focusingTicks++;
					
					if(focusGaugeTemp > 0)
						focusGaugeTemp-=0.8;

					if (focusingTicks % shotlock.getCooldown() == 1 && focusGaugeTemp > 0 && playerData.getShotlockEnemies().size() < shotlock.getMaxLocks()) {
						HitResult rt = InputHandler.getMouseOverExtended(100);
						
						if (rt != null && rt instanceof EntityHitResult) {
							EntityHitResult ertr = (EntityHitResult) rt;
							Party p = ModCapabilities.getWorld(mc.level).getPartyFromMember(event.player.getUUID());
							if(ertr.getEntity() instanceof LivingEntity) {
								LivingEntity target = (LivingEntity) ertr.getEntity();
	
								if (p == null || (p.getMember(target.getUUID()) == null || p.getFriendlyFire())) { // If caster is not in a party || the party doesn't have the target in it || the party has FF on
									playerData.addShotlockEnemy(ertr.getEntity().getId());
									event.player.level.playSound(event.player, event.player.blockPosition(), ModSounds.shotlock_lockon.get(), SoundSource.PLAYERS, 1F, 1F);
									cost = playerData.getFocus() - focusGaugeTemp;
	
									if(playerData.getShotlockEnemies().size() >= shotlock.getMaxLocks()) {
										event.player.level.playSound(event.player, event.player.blockPosition(), ModSounds.shotlock_lockon_all.get(), SoundSource.PLAYERS, 1F, 1F);
									}
								}
							}
						}
					}
					
					if(mc.options.keyAttack.isDown()) {
						if (focusingTicks > 0) {
							// Has stopped shotlocking
							// Send packet to spawn entities and track enemies
							if(!playerData.getShotlockEnemies().isEmpty()) {
								playerData.remFocus(cost);
								event.player.level.playSound(event.player, event.player.blockPosition(), ModSounds.shotlock_shot.get(), SoundSource.PLAYERS, 1F, 1F);
								PacketHandler.sendToServer(new CSShotlockShot(cost, playerData.getShotlockEnemies()));
								cooldownTicks = 100;
								focusing = false;
							}
						}
						focusingTicks = 0;
						focusGaugeTemp = playerData.getFocus();
						playerData.setShotlockEnemies(new ArrayList<Integer>());
					}
				} else {
					focusingTicks = 0;
					focusGaugeTemp = playerData.getFocus();
					playerData.setShotlockEnemies(new ArrayList<Integer>());
				}
			} else {
				if(cooldownTicks > 0) {
					cooldownTicks--;
				}
			}
		}
	}
/*
	@SubscribeEvent
	public void WorldRender(RenderWorldLastEvent event) {
		/*Minecraft mc = Minecraft.getInstance();
		if (mc.player != null && ModCapabilities.getPlayer(mc.player) != null) {
			IPlayerCapabilities playerData = ModCapabilities.getPlayer(mc.player);
			MatrixStack matrixStackIn = event.getMatrixStack();
			EntityRendererManager renderManager = mc.getRenderManager();

			if(playerData.getShotlockEnemies() != null) {
				for (int entID : playerData.getShotlockEnemies()) {
					Entity entityIn = mc.world.getEntityByID(entID);
					
					if (playerData.getShotlockEnemies().contains(entityIn.getEntityId())) {
						float f = entityIn.getHeight();
						matrixStackIn.push();
						{							
							ClientPlayerEntity player = mc.player;
					        double x = (double) entityIn.getPosX() - (player.lastTickPosX + (player.getPosX() - player.lastTickPosX) * (double) event.getPartialTicks());
					        double y = (double) entityIn.getPosY() - (player.lastTickPosY + (player.getPosY() - player.lastTickPosY) * (double) event.getPartialTicks());
					        double z = (double) entityIn.getPosZ() - (player.lastTickPosZ + (player.getPosZ() - player.lastTickPosZ) * (double) event.getPartialTicks());
							renderManager.textureManager.bindTexture(new ResourceLocation(KingdomKeys.MODID, "textures/gui/focus2.png"));
							
					        matrixStackIn.translate(x, y, z);
					        matrixStackIn.rotate(renderManager.getCameraOrientation());
					        matrixStackIn.translate(0,-f,-entityIn.getWidth());
					        float scale = entityIn.getHeight() / 1000;
					        matrixStackIn.scale(-scale, -scale, scale);
					        RenderSystem.enableBlend();
							blit(matrixStackIn, -128, -128, 0, 0, 256, 256);

						}
						matrixStackIn.pop();
					}
				}
			}
		}

	}*/
	
    public static void render(PoseStack matrixStack, Entity e, float partialTicks) {
        Color color = new Color(0);
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        Entity player = Minecraft.getInstance().getCameraEntity();
        double ix, iy, iz;
        double x = (double) e.getX() - (player.xOld + (player.getX() - player.xOld) * (double) partialTicks);
        double y = (double) e.getY() - (player.yOld + (player.getY() - player.yOld) * (double) partialTicks) + player.getBbHeight();
        double z = (double) e.getZ() - (player.zOld + (player.getZ() - player.zOld) * (double) partialTicks);
        ix = x;
        iy = y;
        iz = z;
        float distance = (float) Math.sqrt((x + 0.5) * (x + 0.5) + y * y + (z + 0.5) * (z + 0.5));
        float scaleDistance = 12.0f;
        float scale = 0.02666667f;
        float iconScale = (0.02666667f);//*5;
        if (distance > 12.0f) {
            int renderDistance = Minecraft.getInstance().options.renderDistance * 16;
            if (distance > (float) renderDistance) {
                float scaleFactor = (float) renderDistance /distance;
                x *= scaleFactor;
                y *= scaleFactor;
                z *= scaleFactor;
                // iy *= (MC.gameSettings.renderDistanceChunks * 16) / 18.0f;
                //     iconScale *= renderDistance/12.0F;
                scale *= (float) renderDistance / 12.0f;
            } else {
                iconScale *= (distance / scaleDistance);
                scale *= distance / 12.0f;
            }
        }

        //sound(GuiScreenKey.isSoundOn);
        renderIcon(matrixStack, e, ix, iy, iz, red, green, blue, iconScale);
        
    }
    
    private static void renderIcon(PoseStack matrix, Entity entity, double x, double y, double z, int red, int green, int blue, float scale) {
        EntityRenderDispatcher renderManager = Minecraft.getInstance().getEntityRenderDispatcher();
        scale *= 5;
        RenderSystem.setShaderTexture(0, new ResourceLocation(KingdomKeys.MODID, "textures/gui/focus2.png"));
		matrix.pushPose();
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        matrix.translate(x + 0.5, y + 3, z + 0.5);
       // RenderSystem.rotate(-renderManager.playerViewY, 0.0f, 1.0f, 0.0f);
       // RenderSystem.rotate(renderManager.playerViewX, 1.0f, 0.0f, 0.0f);
        matrix.scale(-scale, -scale, scale);
        matrix.translate(0.0f, 10.0f, 0.0f);
        matrix.scale(10.0f, 10.0f, 10.0f);
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
        
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(-0.5, -0.5, 0.0).uv(1.0F, 1.0F).color(red, green, blue, 255).endVertex();
		bufferbuilder.vertex(-0.5, 0.5, 0.0).uv(1.0F, 0.0F).color(red, green, blue, 255).endVertex();
		bufferbuilder.vertex(0.5, 0.5, 0.0).uv(0.0F, 0.0F).color(red, green, blue, 255).endVertex();
		bufferbuilder.vertex(0.5, -0.5, 0.0).uv(0.0F, 1.0F).color(red, green, blue, 255).endVertex();
		bufferbuilder.end();
		RenderSystem.enableBlend();
		BufferUploader.end(bufferbuilder);
		
		RenderSystem.depthMask(true);
		RenderSystem.enableDepthTest();
		matrix.popPose();
    }
	
	@SubscribeEvent
	public void EntityRender(RenderLivingEvent.Post<LivingEntity, EntityModel<LivingEntity>> event) {
		//Text

		/*Minecraft mc = Minecraft.getInstance();
		if (mc.player != null && ModCapabilities.getPlayer(mc.player) != null) {
			IPlayerCapabilities playerData = ModCapabilities.getPlayer(mc.player);
			//if (playerData.getShotlockEnemies() != null && playerData.getShotlockEnemies().contains(event.getEntity().getEntityId())) {
			if(true) {
				MatrixStack matrixStackIn = event.getMatrixStack();
				LivingEntity entityIn = event.getEntity();
				
				EntityRendererManager renderManager = event.getRenderer().getRenderManager();
				TranslationTextComponent displayNameIn = new TranslationTextComponent("o");
				float f = entityIn.getHeight();
				matrixStackIn.push();
				{
					matrixStackIn.translate(0.0D, (double) f, 0.0D);
					matrixStackIn.rotate(renderManager.getCameraOrientation());
					matrixStackIn.scale(-0.25F, -0.25F, 0.25F);

					Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
					FontRenderer fontrenderer = renderManager.getFontRenderer();
					float f2 = (float) (-fontrenderer.getStringPropertyWidth(displayNameIn) / 2);
					fontrenderer.drawInBatch(displayNameIn, f2, 0, 0x00FFFF, false, matrix4f, event.getBuffers(), false, 0, event.getLight());
				}
				matrixStackIn.pop();
			}
		}*/
		
		//Icon
		Minecraft mc = Minecraft.getInstance();
		if (mc.player != null && ModCapabilities.getPlayer(mc.player) != null) {
			IPlayerCapabilities playerData = ModCapabilities.getPlayer(mc.player);
			if (playerData.getShotlockEnemies() != null && playerData.getShotlockEnemies().contains(event.getEntity().getId())) {
				//if (playerData.getShotlockEnemies() != null && playerData.getShotlockEnemies().contains(event.getEntity().getEntityId())) {
				PoseStack matrixStackIn = event.getPoseStack();
				LivingEntity entityIn = event.getEntity();

				EntityRenderDispatcher renderManager = Minecraft.getInstance().getEntityRenderDispatcher();
				TranslatableComponent displayNameIn = new TranslatableComponent("o");
				float f = entityIn.getBbHeight();
				matrixStackIn.pushPose();
				{
					matrixStackIn.translate(0.0D, (double) f/2, 0.0D);
					matrixStackIn.mulPose(renderManager.cameraOrientation());
					float scale = Math.max(entityIn.getBbHeight()/2, entityIn.getBbWidth()/2)/100;

					matrixStackIn.scale(-scale, -scale, scale);
					RenderSystem.setShaderTexture(0,new ResourceLocation(KingdomKeys.MODID, "textures/gui/focus2.png"));
					blit(matrixStackIn,-128,-128,0,0,256,256);
				}
				matrixStackIn.popPose();
			}
		}
	}
	
	public void blit(PoseStack matrixStack, int x, int y, int uOffset, int vOffset, int uWidth, int vHeight) {
		blit(matrixStack, x, y, 0, (float) uOffset, (float) vOffset, uWidth, vHeight, 256, 256);
	}

	public static void blit(PoseStack matrixStack, int x, int y, int blitOffset, float uOffset, float vOffset, int uWidth, int vHeight, int textureHeight, int textureWidth) {
		innerBlit(matrixStack, x, x + uWidth, y, y + vHeight, blitOffset, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
	}

	private static void innerBlit(PoseStack matrixStack, int x1, int x2, int y1, int y2, int blitOffset, int uWidth, int vHeight, float uOffset, float vOffset, int textureWidth, int textureHeight) {
		innerBlit(matrixStack.last().pose(), x1, x2, y1, y2, blitOffset, (uOffset + 0.0F) / (float) textureWidth, (uOffset + (float) uWidth) / (float) textureWidth, (vOffset + 0.0F) / (float) textureHeight, (vOffset + (float) vHeight) / (float) textureHeight);
	}

	private static void innerBlit(Matrix4f matrix, int x1, int x2, int y1, int y2, int blitOffset, float minU, float maxU, float minV, float maxV) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(matrix, (float) x1, (float) y2, (float) blitOffset).uv(minU, maxV).endVertex();
		bufferbuilder.vertex(matrix, (float) x2, (float) y2, (float) blitOffset).uv(maxU, maxV).endVertex();
		bufferbuilder.vertex(matrix, (float) x2, (float) y1, (float) blitOffset).uv(maxU, minV).endVertex();
		bufferbuilder.vertex(matrix, (float) x1, (float) y1, (float) blitOffset).uv(minU, minV).endVertex();
		bufferbuilder.end();
		RenderSystem.enableBlend();
		BufferUploader.end(bufferbuilder);
	}
	
	@SubscribeEvent
	public void PlayerClick(ClickInputEvent event) {
		if(event.isPickBlock()) {
			Minecraft mc = Minecraft.getInstance();
			if(mc.player.getMainHandItem() != null && Utils.getPlayerShotlock(mc.player) != null && (mc.player.getMainHandItem().getItem() instanceof KeybladeItem || mc.player.getMainHandItem().getItem() instanceof IOrgWeapon)){
				event.setCanceled(true);
			}
		}	
	}

	@SubscribeEvent
	public void colourTint(ColorHandlerEvent.Block event) {
		BlockColors colours = event.getBlockColors();
		colours.register(ClientEvents::getStructureWallColour, ModBlocks.structureWall.get());
	}

	public static int getStructureWallColour(BlockState state, BlockAndTintGetter level, BlockPos pos, int tintIndex) {
		Color colour = Color.BLACK;
		CastleOblivionCapabilities.ICastleOblivionInteriorCapability cap = ModCapabilities.getCastleOblivionInterior(Minecraft.getInstance().level);
		if (cap != null) {
			if (cap.getRoomAtPos(pos) != null) {
				colour = cap.getRoomAtPos(pos).getType().getProperties().getColour();
			}
		}
		return colour.getRGB();
	}
}
