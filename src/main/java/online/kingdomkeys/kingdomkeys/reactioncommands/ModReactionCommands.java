package online.kingdomkeys.kingdomkeys.reactioncommands;

import java.util.function.Supplier;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import online.kingdomkeys.kingdomkeys.KingdomKeys;
import online.kingdomkeys.kingdomkeys.lib.Strings;

public class ModReactionCommands {

	public static DeferredRegister<ReactionCommand> REACTION_COMMANDS = DeferredRegister.create(new ResourceLocation(KingdomKeys.MODID, "reactioncommands"), KingdomKeys.MODID);

	public static Supplier<IForgeRegistry<ReactionCommand>> registry = REACTION_COMMANDS.makeRegistry(ReactionCommand.class, RegistryBuilder::new);;

	public static final RegistryObject<ReactionCommand>
		AUTO_VALOR = REACTION_COMMANDS.register(Strings.autoValorRC, () -> new ReactionAutoForm(KingdomKeys.MODID+":"+Strings.autoValorRC, Strings.autoValor, Strings.Form_Valor)),
		AUTO_WISDOM = REACTION_COMMANDS.register(Strings.autoWisdomRC, () -> new ReactionAutoForm(KingdomKeys.MODID+":"+Strings.autoWisdomRC, Strings.autoWisdom, Strings.Form_Wisdom)),
		AUTO_LIMIT = REACTION_COMMANDS.register(Strings.autoLimitRC, () -> new ReactionAutoForm(KingdomKeys.MODID+":"+Strings.autoLimitRC, Strings.autoLimit, Strings.Form_Limit)),
		AUTO_MASTER = REACTION_COMMANDS.register(Strings.autoMasterRC, () -> new ReactionAutoForm(KingdomKeys.MODID+":"+Strings.autoMasterRC, Strings.autoMaster, Strings.Form_Master)),
		AUTO_FINAL = REACTION_COMMANDS.register(Strings.autoFinalRC, () -> new ReactionAutoForm(KingdomKeys.MODID+":"+Strings.autoFinalRC, Strings.autoFinal, Strings.Form_Final)),
		FIRE = REACTION_COMMANDS.register(new ResourceLocation(Strings.Magic_Fire).getPath(), () -> new ReactionMagic(Strings.Magic_Fire)),
		BLIZZARD = REACTION_COMMANDS.register(new ResourceLocation(Strings.Magic_Blizzard).getPath(), () -> new ReactionMagic(Strings.Magic_Blizzard)),
		WATER = REACTION_COMMANDS.register(new ResourceLocation(Strings.Magic_Water).getPath(), () -> new ReactionMagic(Strings.Magic_Water)),
		THUNDER = REACTION_COMMANDS.register(new ResourceLocation(Strings.Magic_Thunder).getPath(), () -> new ReactionMagic(Strings.Magic_Thunder))
	;
}