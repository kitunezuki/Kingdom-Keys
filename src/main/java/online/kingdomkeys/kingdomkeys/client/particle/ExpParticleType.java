package online.kingdomkeys.kingdomkeys.client.particle;

import com.mojang.serialization.Codec;

import net.minecraft.core.particles.ParticleType;

public class ExpParticleType extends ParticleType<ExpParticleData> {
	  private static boolean ALWAYS_SHOW_REGARDLESS_OF_DISTANCE_FROM_PLAYER = true;
	  public ExpParticleType() {
	    super(ALWAYS_SHOW_REGARDLESS_OF_DISTANCE_FROM_PLAYER, ExpParticleData.DESERIALIZER);
	  }

	@Override
	public Codec<ExpParticleData> codec() {
		return null;
	}
}