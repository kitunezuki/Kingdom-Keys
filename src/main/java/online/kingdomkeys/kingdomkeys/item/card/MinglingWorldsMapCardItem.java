package online.kingdomkeys.kingdomkeys.item.card;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import online.kingdomkeys.kingdomkeys.util.Utils;
import online.kingdomkeys.kingdomkeys.world.dimension.castle_oblivion.system.RoomProperties;
import online.kingdomkeys.kingdomkeys.world.dimension.castle_oblivion.system.ModRoomTypes;
import online.kingdomkeys.kingdomkeys.world.dimension.castle_oblivion.system.RoomType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MinglingWorldsMapCardItem extends MapCardItem {
    public MinglingWorldsMapCardItem() {
        super(null);
    }

    @Override
    public RoomType getRoomType() {
        List<RoomType> types = ModRoomTypes.registry.get().getValues().stream().filter(roomType -> roomType.getProperties().getCategory() != RoomProperties.RoomCategory.SPECIAL).toList();
        return types.get(Utils.randomWithRange(0, types.size()-1));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TranslatableComponent("Size: ?"));
        pTooltipComponents.add(new TranslatableComponent("Enemies: ?"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

}
