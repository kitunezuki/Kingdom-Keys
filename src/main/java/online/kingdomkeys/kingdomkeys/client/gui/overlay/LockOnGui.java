package online.kingdomkeys.kingdomkeys.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import online.kingdomkeys.kingdomkeys.KingdomKeys;
import online.kingdomkeys.kingdomkeys.capability.IPlayerCapabilities;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.config.ModConfigs;
import online.kingdomkeys.kingdomkeys.handler.InputHandler;
import online.kingdomkeys.kingdomkeys.lib.Strings;

public class LockOnGui extends OverlayBase {
	int guiWidth = 256;
	int guiHeight = 256;

	int hpGuiWidth = 173;
	float hpBarWidth;
	float missingHpBarWidth;
	float hpPerBar;
	int hpBars;
	int currentBar;
	int oldBar;

	int hpGuiHeight = 10;
	int noborderguiwidth = 171;

	float lockOnScale;
	float hpBarScale;
	LivingEntity lastTrackedTarget;
	private float targetHealth;
	private long lastSystemTime;
	private float lastTargetHealth;

	@Override
	public void render(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
		super.render(gui, poseStack, partialTick, width, height);
		if (InputHandler.lockOn != null) {
			OverlayRegistry.enableOverlay(ForgeIngameGui.CROSSHAIR_ELEMENT, false);
		} else {
			OverlayRegistry.enableOverlay(ForgeIngameGui.CROSSHAIR_ELEMENT, true);
		}
		Player player = minecraft.player;
		IPlayerCapabilities playerData = ModCapabilities.getPlayer(player);
		if (playerData != null) {
			Entity target = InputHandler.lockOn;
			if (target == null) {
				missingHpBarWidth = 0;
				return;
			} else {
				if(player.distanceTo(target) > 35){
					InputHandler.lockOn = null;
					return;
				}

				float size = 6;

				RenderSystem.setShaderTexture(0, new ResourceLocation(KingdomKeys.MODID, "textures/gui/lockon_0.png"));

				int screenWidth = minecraft.getWindow().getGuiScaledWidth();
				int screenHeight = minecraft.getWindow().getGuiScaledHeight();

				lockOnScale = ModConfigs.lockOnIconScale/100F;
				hpBarScale = ModConfigs.lockOnHPScale/100F;

				// Icon
				poseStack.pushPose();
				{
					poseStack.translate((screenWidth / 2) - (guiWidth / 2) * lockOnScale / size - 0.5F, (screenHeight / 2) - (guiHeight / 2) * lockOnScale / size - 0.5F, 0);
					poseStack.scale(lockOnScale / size, lockOnScale / size, lockOnScale / size);
					this.blit(poseStack, 0, 0, 0, 0, guiWidth, guiHeight);

					RenderSystem.setShaderTexture(0, new ResourceLocation(KingdomKeys.MODID, "textures/gui/lockon_1.png"));
					poseStack.translate(guiWidth / 2, guiWidth / 2, 0);
					poseStack.mulPose(Vector3f.ZP.rotation((player.tickCount % 360) * ModConfigs.lockOnIconRotation / 100F));
					poseStack.translate(-guiWidth / 2, -guiWidth / 2, 0);
					this.blit(poseStack, 0, 0, 0, 0, guiWidth, guiHeight);
				}
				poseStack.popPose();

				RenderSystem.setShaderTexture(0, new ResourceLocation(KingdomKeys.MODID, "textures/gui/hpbar.png"));

				poseStack.pushPose();

				if(target != null && playerData.isAbilityEquipped(Strings.scan)) {
					poseStack.pushPose();
					{
						RenderSystem.enableBlend();
						poseStack.translate(ModConfigs.lockOnXPos, ModConfigs.lockOnYPos, 0);
						drawString(poseStack, minecraft.font, target.getName().getString(), screenWidth - minecraft.font.width(target.getName().getString()), (int) (26*hpBarScale), 0xFFFFFF);
						drawHPBar(poseStack, (LivingEntity) target);
						RenderSystem.disableBlend();
					}
					poseStack.popPose();
				}

				poseStack.scale(hpBarScale, hpBarScale, hpBarScale);
				poseStack.popPose();
			}
		}
	}

	public void drawHPBar(PoseStack matrixStack, LivingEntity target) {
		int screenWidth = minecraft.getWindow().getGuiScaledWidth();

		RenderSystem.setShaderTexture(0, new ResourceLocation(KingdomKeys.MODID, "textures/gui/hpbar.png"));

		hpPerBar = ModConfigs.lockOnHpPerBar;
		float widthMultiplier = 10 * hpBarScale;

		float targetHealth = Math.min(target.getHealth(), target.getMaxHealth());

		if (target.getMaxHealth() % hpPerBar == 0) {
			hpBars = (int) (target.getMaxHealth() / hpPerBar);
		} else {
			hpBars = (int) (target.getMaxHealth() / hpPerBar) + 1;
		}

		if (targetHealth % hpPerBar == 0) {
			currentBar = (int) (targetHealth / hpPerBar);
		} else {
			currentBar = (int) (targetHealth / hpPerBar) + 1;
		}

		float firstBar =  (target.getMaxHealth() > hpPerBar ? target.getMaxHealth() % hpPerBar : target.getMaxHealth());
		float oneBar = (target.getMaxHealth() > hpPerBar ? hpPerBar : target.getMaxHealth());// (int) (target.getMaxHealth() / hpBars);

		if (targetHealth % hpPerBar == 0 && targetHealth != 0) {
			hpBarWidth = oneBar * widthMultiplier;
		} else {
			hpBarWidth = (float) ((targetHealth % hpPerBar) * widthMultiplier);
		}

		float i = (targetHealth);
		long j = Util.getMillis();
		if (i < this.targetHealth && target.invulnerableTime > 0) {
			this.lastSystemTime = j;
		} else if (i > this.targetHealth && target.invulnerableTime > 0) {
			this.lastSystemTime = j;
		}

		if ((j - this.lastSystemTime > 1000L || this.targetHealth < targetHealth)) { // If 1 second since last attack has passed update variables
			this.targetHealth = i;
			this.lastTargetHealth = i;
			this.lastSystemTime = j;
			oldBar = currentBar;
			missingHpBarWidth = 0;
		}

		//Basically get the Max of the hp bar or 0 (so weird values don't show up) and then out of that a max of that and the missing hp of the bar(so it doesn't go above the limit)
		missingHpBarWidth = targetHealth % hpPerBar == 0 ? 0 : Math.min(Math.max(((lastTargetHealth - targetHealth)),0), hpPerBar - targetHealth % hpPerBar) % hpPerBar * widthMultiplier;
		float hpBarMaxWidth, bgHPBarMaxWidth = 0;

		// Background HP width
		if (target.getMaxHealth() >= hpPerBar) {
			if(targetHealth + hpPerBar > target.getMaxHealth() && currentBar == hpBars) { //If it's first bar
				hpBarMaxWidth = (firstBar * widthMultiplier);
				bgHPBarMaxWidth = hpPerBar * widthMultiplier;
			} else if(currentBar == 1) {//If it's the last bar
				hpBarMaxWidth = (oneBar * widthMultiplier);
				bgHPBarMaxWidth = 0;
			} else { //Middle bar in entity with hp > 20
				hpBarMaxWidth = (oneBar * widthMultiplier);
				bgHPBarMaxWidth = hpPerBar * widthMultiplier;
			}
		} else { //Target has less than 20 hp
			hpBarMaxWidth = (target.getMaxHealth() % hpPerBar) * widthMultiplier;
		}

		matrixStack.pushPose();
		{
			drawHPBarBack(matrixStack, (screenWidth - hpBarMaxWidth - 4 * hpBarScale), 0 * hpBarScale, hpBarMaxWidth, hpBarScale, (screenWidth - bgHPBarMaxWidth - 4 * hpBarScale), bgHPBarMaxWidth);
			drawHPBarTop(matrixStack, (screenWidth - hpBarWidth - 2 * hpBarScale), 2 * hpBarScale, hpBarWidth, hpBarScale);
			drawDamagedHPBarTop(matrixStack, (screenWidth - hpBarWidth - missingHpBarWidth - 2 * hpBarScale), 2 * hpBarScale, missingHpBarWidth, hpBarScale, target);
			drawHPBars(matrixStack, (screenWidth - hpBarMaxWidth - 4 * hpBarScale), 0 * hpBarScale, hpBarMaxWidth, hpBarScale, target);
			drawDamagedHPBars(matrixStack, (screenWidth - hpBarMaxWidth - 4 * hpBarScale), 0 * hpBarScale, hpBarMaxWidth, hpBarScale, target);
		}
		matrixStack.popPose();
	}

	public void drawHPBarBack(PoseStack matrixStack, float posX, float posY, float width, float scale, float bgPosX, float bgHPBarMaxWidth) {
		RenderSystem.setShaderTexture(0, new ResourceLocation(KingdomKeys.MODID, "textures/gui/hpbar.png"));
		
		matrixStack.pushPose();
		{
			matrixStack.translate(posX, posY, 0);

			//Green bg bar render
			matrixStack.pushPose();
			{
				matrixStack.translate(bgPosX - posX, posY, 0);
				// Left Margin
				matrixStack.pushPose();
				{
					matrixStack.scale(scale, scale, 0);
					blit(matrixStack, 0, 0, 0, 0, 2, 12);
				}
				matrixStack.popPose();

				// Background
				matrixStack.pushPose(); //Empty bg (last bar)
				{
					matrixStack.translate(2*scale, 0, 0);
					matrixStack.scale(bgHPBarMaxWidth, scale, 0);
					blit(matrixStack, 0, 0, 14, 0, 1, 12);
				}
				matrixStack.popPose();

				// Right Margin
				matrixStack.pushPose();
				{
					matrixStack.translate(2 * scale + bgHPBarMaxWidth, 0, 0);
					matrixStack.scale(scale, scale, 0);
					blit(matrixStack, 0, 0, 3, 0, 2, 12);
				}
				matrixStack.popPose();
			}
			matrixStack.popPose();

			//Normal bar render
			// Left Margin
			matrixStack.pushPose();
			{
				matrixStack.scale(scale, scale, 0);
				blit(matrixStack, 0, 0, 0, 0, 2, 12);
			}
			matrixStack.popPose();

			// Background
			matrixStack.pushPose();
			{
				matrixStack.translate(2*scale, 0, 0);
				matrixStack.scale(width, scale, 0);
				blit(matrixStack, 0, 0, 2, 0, 1, 12);
			}
			matrixStack.popPose();

			// Right Margin
			matrixStack.pushPose();
			{
				matrixStack.translate(2 * scale + width, 0, 0);
				matrixStack.scale(scale, scale, 0);
				blit(matrixStack, 0, 0, 3, 0, 2, 12);
			}
			matrixStack.popPose();
			
			// HP Icon
			matrixStack.pushPose();
			{
				matrixStack.translate(width - 20*scale, 12*scale, 0);
				matrixStack.scale(scale, scale, 0);
				blit(matrixStack, 1, 0, 0, 32, 23, 12);
			}
			matrixStack.popPose();

			// HP Bars
			for (int i = 0; i < hpBars - 1; i++) {
				matrixStack.pushPose();
				{
					matrixStack.translate(width - 19*scale - (17*scale * (i + 1)), 12*scale, 0);
					matrixStack.scale(scale, scale, 0);
					blit(matrixStack, 0, 0, 0, 46, 17, 12);
				}
				matrixStack.popPose();
			}
		}
		matrixStack.popPose();

	}

	public void drawHPBarTop(PoseStack matrixStack, float posX, float posY, float width, float scale) {
		RenderSystem.setShaderTexture(0, new ResourceLocation(KingdomKeys.MODID, "textures/gui/hpbar.png"));
		// HP Bar
		matrixStack.pushPose();
		{
			matrixStack.translate(posX, posY, 0);
			matrixStack.scale(width, scale, 0);
			blit(matrixStack, 0, 0, 2, 12, 1, 8);
		}
		matrixStack.popPose();
	}
	
	private void drawHPBars(PoseStack matrixStack, float posX, float posY, float width, float scale, LivingEntity target) {
		// HP Bars
		if(target.isAlive()) {
			// HP Bars
			for (int i = 1; i < currentBar; i++) {
				matrixStack.pushPose();
				{
					matrixStack.translate(posX + width - 17 * scale - (17 * scale * i) - 2 * scale, posY + 12 * scale, 0);
					matrixStack.scale(scale, scale, 0);
					blit(matrixStack, 0, 0, 0, 60, 17, 12);
				}
				matrixStack.popPose();
			}
		}
	}
	
	private void drawDamagedHPBarTop(PoseStack matrixStack, float posX, float posY, float width, float scale, LivingEntity target) {
		RenderSystem.setShaderTexture(0, new ResourceLocation(KingdomKeys.MODID, "textures/gui/hpbar.png"));
		matrixStack.pushPose();
		{
			// HP Bar
			matrixStack.pushPose();
			{				
				matrixStack.translate(posX, posY, 0);
				matrixStack.scale(width, scale, 0);
				blit(matrixStack, 0, 0, 2, 22, 1, 8);
			}
			matrixStack.popPose();
			
		}
		matrixStack.popPose();
	}
	
	private void drawDamagedHPBars(PoseStack matrixStack, float posX, float posY, float width, float scale, LivingEntity target) {
		// HP Bars
		if(target.isAlive()) {
			for (int i = currentBar; i < oldBar; i++) {
				matrixStack.pushPose();
				{
					matrixStack.translate(posX + width - 17 * scale - (17 * scale * i) - 2 * scale, posY + 12 * scale, 0);
					matrixStack.scale(scale, scale, 0);
					blit(matrixStack, 0, 0, 17, 60, 17, 12);
				}
				matrixStack.popPose();
			}
		}
	}
}
