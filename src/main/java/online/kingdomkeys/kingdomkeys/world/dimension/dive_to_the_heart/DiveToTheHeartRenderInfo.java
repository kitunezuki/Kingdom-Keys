package online.kingdomkeys.kingdomkeys.world.dimension.dive_to_the_heart;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DiveToTheHeartRenderInfo extends DimensionSpecialEffects {

    public DiveToTheHeartRenderInfo() {
        super(Float.NaN, true, SkyType.NONE, true, true);
        setSkyRenderHandler((ticks, partialTicks, matrixStack, world, mc) -> {});
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 p_230494_1_, float p_230494_2_) {
        return Vec3.ZERO;
    }

    @Override
    public boolean isFoggyAt(int p_230493_1_, int p_230493_2_) {
        return true;
    }
}
