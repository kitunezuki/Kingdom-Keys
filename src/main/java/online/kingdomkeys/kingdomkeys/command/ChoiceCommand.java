package online.kingdomkeys.kingdomkeys.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.Util;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.ServerLifecycleHooks;
import online.kingdomkeys.kingdomkeys.KingdomKeys;
import online.kingdomkeys.kingdomkeys.capability.IPlayerCapabilities;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.lib.SoAState;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class ChoiceCommand extends BaseCommand {

    public static SuggestionProvider<CommandSourceStack> SUGGEST_CHOICES = (p_198296_0_, p_198296_1_) -> {
        List<String> list = Arrays.asList("WARRIOR", "GUARDIAN", "MYSTIC");
        try {
            String chosen = StringArgumentType.getString(p_198296_0_, "chosen");
            if (list.contains(chosen)) {
                return SharedSuggestionProvider.suggest(list.stream().filter(s -> !s.equals(chosen)).map(StringArgumentType::escapeIfRequired), p_198296_1_);
            }
        } catch (IllegalArgumentException ignored) {}
        return SharedSuggestionProvider.suggest(list.stream().map(StringArgumentType::escapeIfRequired), p_198296_1_);
    };

    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("choice").requires(source -> source.hasPermission(2));
        builder.then(Commands.argument("chosen", StringArgumentType.string()).suggests(SUGGEST_CHOICES)
                .then(Commands.argument("sacrificed", StringArgumentType.string()).suggests(SUGGEST_CHOICES)
                .then(Commands.argument("targets", EntityArgument.players())
                        .executes(ChoiceCommand::makeChoice))
                .executes(ChoiceCommand::makeChoice)));

        KingdomKeys.LOGGER.warn("Registered command " + builder.getLiteral());
        return builder;
    }

    public static SoAState stringToChoice(String choice) {
        return switch (choice) {
            case "WARRIOR" -> SoAState.WARRIOR;
            case "GUARDIAN" -> SoAState.GUARDIAN;
            case "MYSTIC" -> SoAState.MYSTIC;
            default -> SoAState.NONE;
        };
    }

    private static int makeChoice(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Collection<ServerPlayer> players = getPlayers(context, 4);
        String chosenStr = StringArgumentType.getString(context, "chosen");
        SoAState chosen = stringToChoice(chosenStr);
        String sacrificedStr = StringArgumentType.getString(context, "sacrificed");
        SoAState sacrificed = stringToChoice(sacrificedStr);
        if (chosen != SoAState.NONE && sacrificed != SoAState.NONE) {
            if (!chosen.equals(sacrificed)) {
                for (ServerPlayer target : players) {
                    IPlayerCapabilities targetData = ModCapabilities.getPlayer(target);
                    boolean noChange = false;
                    if (targetData.getSoAState() == SoAState.COMPLETE) {
                        if (targetData.getChosen() == chosen && targetData.getSacrificed() == sacrificed) {
                            noChange = true;
                        } else {
                            SoAState.applyStatsForChoices(targetData, true);
                        }
                    }
                    if (!noChange) {
                        targetData.setSoAState(SoAState.COMPLETE);
                        targetData.setSacrifice(sacrificed);
                        targetData.setChoice(chosen);
                        SoAState.applyStatsForChoices(targetData, false);
                    }
                    context.getSource().sendSuccess(new TranslatableComponent("Station of Awakening choice has been set to %s and %s for %s", chosenStr, sacrificedStr, target.getName().getString()), true);
                    target.sendMessage(new TranslatableComponent("Your Station of Awakening choice has been set to %s and %s", chosenStr, sacrificedStr), Util.NIL_UUID);
                }
            } else {
                context.getSource().sendFailure(new TranslatableComponent("CHOSEN and SACRIFICED must not be the same"));
            }
        } else {
            context.getSource().sendFailure(new TranslatableComponent("CHOSEN or SACRIFICED value is invalid"));
        }
        return 1;
    }
}
