package online.kingdomkeys.kingdomkeys.magic;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import online.kingdomkeys.kingdomkeys.ability.Ability;
import online.kingdomkeys.kingdomkeys.capability.IPlayerCapabilities;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.client.sound.ModSounds;
import online.kingdomkeys.kingdomkeys.network.PacketHandler;
import online.kingdomkeys.kingdomkeys.reactioncommands.ReactionCommand;

public class MagicReflect extends Magic {

	public MagicReflect(String registryName, int maxLevel, boolean hasRC, String gmAbility, int order) {
		super(registryName, false, maxLevel, hasRC, gmAbility, order);
	}

	@Override
	protected void magicUse(Player player, Player caster, int level, float fullMPBlastMult) {
		IPlayerCapabilities playerData = ModCapabilities.getPlayer(player);
		playerData.setReflectTicks((int) (40 + (level * 5)), level);
		player.level.playSound(null, player.blockPosition(), ModSounds.reflect1.get(), SoundSource.PLAYERS, 1F, 1F);
		PacketHandler.syncToAllAround(player, playerData);
		player.swing(InteractionHand.MAIN_HAND);
	}

}
