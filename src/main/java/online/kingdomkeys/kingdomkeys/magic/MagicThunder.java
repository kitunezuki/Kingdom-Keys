package online.kingdomkeys.kingdomkeys.magic;

import net.minecraft.world.entity.player.Player;
import online.kingdomkeys.kingdomkeys.ability.Ability;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.entity.magic.ThundagaEntity;
import online.kingdomkeys.kingdomkeys.entity.magic.ThundaraEntity;
import online.kingdomkeys.kingdomkeys.entity.magic.ThundazaEntity;
import online.kingdomkeys.kingdomkeys.entity.magic.ThunderEntity;
import online.kingdomkeys.kingdomkeys.lib.Strings;
import online.kingdomkeys.kingdomkeys.reactioncommands.ReactionCommand;

public class MagicThunder extends Magic {

	public MagicThunder(String registryName, int maxLevel, boolean hasRC, String gmAbility, int order) {
		super(registryName, false, maxLevel, hasRC, gmAbility, order);
	}

	@Override
	protected void magicUse(Player player, Player caster, int level, float fullMPBlastMult) {
		float dmg = ModCapabilities.getPlayer(player).isAbilityEquipped(Strings.thunderBoost) ? getDamageMult(level) * 1.2F : getDamageMult(level);
		dmg *= fullMPBlastMult;

		switch(level) {
		case 0:
			ThunderEntity thunderController = new ThunderEntity(player.level, player, dmg);
			thunderController.setCaster(player.getUUID());
			player.level.addFreshEntity(thunderController);
			break;
		case 1:
			ThundaraEntity thundaraController = new ThundaraEntity(player.level, player, dmg);
			thundaraController.setCaster(player.getUUID());
			player.level.addFreshEntity(thundaraController);
			break;
		case 2:
			ThundagaEntity thundagaController = new ThundagaEntity(player.level, player, dmg);
			thundagaController.setCaster(player.getUUID());
			player.level.addFreshEntity(thundagaController);
			break;
		case 3:
			ThundazaEntity thundazaController = new ThundazaEntity(player.level, player, dmg);
			thundazaController.setCaster(player.getUUID());
			player.level.addFreshEntity(thundazaController);
			break;
		}
		
	}

}
