package online.kingdomkeys.kingdomkeys.client.particle;

import java.awt.Color;
import java.util.Locale;

import javax.annotation.Nonnull;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;

public class ExpParticleData implements ParticleOptions {

	public ExpParticleData(Color tint, double diameter) {
		this.tint = tint;
		this.diameter = constrainDiameterToValidRange(diameter);
	}

	public Color getTint() {
		return tint;
	}

	/**
	 * @return get diameter of particle in metres
	 */
	public double getDiameter() {
		return diameter;
	}

	@Nonnull
	@Override
	public ParticleType<?> getType() {
		return ModParticles.TYPE_EXP.get();
	}

	// write the particle information to a PacketBuffer, ready for transmission to a
	// client
	@Override
	public void writeToNetwork(FriendlyByteBuf buf) {
		buf.writeInt(tint.getRed());
		buf.writeInt(tint.getGreen());
		buf.writeInt(tint.getBlue());
		buf.writeDouble(diameter);
	}

	// used for debugging I think; prints the data in human-readable format
	@Nonnull
	@Override
	public String writeToString() {
		return String.format(Locale.ROOT, "%s %.2f %i %i %i", this.getType().getRegistryName(), diameter, tint.getRed(), tint.getGreen(), tint.getBlue());
	}

	private static double constrainDiameterToValidRange(double diameter) {
		final double MIN_DIAMETER = 0.05;
		final double MAX_DIAMETER = 1.0;
		return Mth.clamp(diameter, MIN_DIAMETER, MAX_DIAMETER);
	}

	private Color tint;
	private double diameter;

	// The DESERIALIZER is used to construct FlameParticleData from either command
	// line parameters or from a network packet

	public static final Deserializer<ExpParticleData> DESERIALIZER = new Deserializer<ExpParticleData>() {

		// parse the parameters for this particle from a /particle command
		@Nonnull
		@Override
		public ExpParticleData fromCommand(@Nonnull ParticleType<ExpParticleData> type, @Nonnull StringReader reader) throws CommandSyntaxException {
			reader.expect(' ');
			double diameter = constrainDiameterToValidRange(reader.readDouble());

			final int MIN_COLOUR = 0;
			final int MAX_COLOUR = 255;
			reader.expect(' ');
			int red = Mth.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
			reader.expect(' ');
			int green = Mth.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
			reader.expect(' ');
			int blue = Mth.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
			Color color = new Color(red, green, blue);

			return new ExpParticleData(color, diameter);
		}

		// read the particle information from a PacketBuffer after the client has
		// received it from the server
		@Override
		public ExpParticleData fromNetwork(@Nonnull ParticleType<ExpParticleData> type, FriendlyByteBuf buf) {
			// warning! never trust the data read in from a packet buffer.

			final int MIN_COLOUR = 0;
			final int MAX_COLOUR = 255;
			int red = Mth.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR);
			int green = Mth.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR);
			int blue = Mth.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR);
			Color color = new Color(red, green, blue);

			double diameter = constrainDiameterToValidRange(buf.readDouble());

			return new ExpParticleData(color, diameter);
		}
	};
}