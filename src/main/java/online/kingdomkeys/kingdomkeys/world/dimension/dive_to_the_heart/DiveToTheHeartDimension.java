package online.kingdomkeys.kingdomkeys.world.dimension.dive_to_the_heart;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import online.kingdomkeys.kingdomkeys.block.ModBlocks;
import online.kingdomkeys.kingdomkeys.world.dimension.ModDimensions;

@Mod.EventBusSubscriber
public class DiveToTheHeartDimension{
    //Event Listeners//

    //Set the fog density to fade out the bottom of the platform
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void renderFog(EntityViewRenderEvent.RenderFogEvent event) {
        Level world = Minecraft.getInstance().level;
        if (world != null) {
            if (world.dimension().equals(ModDimensions.DIVE_TO_THE_HEART)) {
                RenderSystem.setShaderFogStart(0.0F);
                RenderSystem.setShaderFogEnd(30);
            }
        }
    }

    //Prevent taking damage in this dimension
    @SubscribeEvent
    public static void onHurt(LivingHurtEvent event) {
        if (event.getEntityLiving() instanceof Player) {
            if (!((Player)event.getEntityLiving()).isCreative()) {
                if (event.getEntityLiving().level.dimension().equals(ModDimensions.DIVE_TO_THE_HEART)) {
                    event.setCanceled(true);
                }
            }
        }
    }

    //Prevent player from falling off the platform
    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        if (!event.player.isCreative()) {
            if (event.player.level.dimension().equals(ModDimensions.DIVE_TO_THE_HEART)) {
                if (event.player.getY() < 10) {
                    event.player.teleportTo(0, 25, 0);
                }
            }
        }
    }

    @SubscribeEvent
    public static void breakBlock(BlockEvent.BreakEvent event) {
        if (!event.getPlayer().isCreative()) {
            if (event.getPlayer().level.dimension().equals(ModDimensions.DIVE_TO_THE_HEART)) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void placeBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getPlayer().isCreative()) {
            if (event.getWorld().dimension().equals(ModDimensions.DIVE_TO_THE_HEART)) {
                if (event.getWorld().getBlockState(event.getPos()).getBlock() == ModBlocks.pedestal.get()) {
                    if (event.getPlayer().isShiftKeyDown()) {
                        event.setCanceled(true);
                    }
                } else {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void useItem(PlayerInteractEvent.RightClickItem event) {
        if (!event.getPlayer().isCreative()) {
            if (event.getWorld().dimension().equals(ModDimensions.DIVE_TO_THE_HEART)) {
                event.setCanceled(true);
            }
        }
    }
}
