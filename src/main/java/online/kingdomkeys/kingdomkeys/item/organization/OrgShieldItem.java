package online.kingdomkeys.kingdomkeys.item.organization;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import online.kingdomkeys.kingdomkeys.KingdomKeys;
import online.kingdomkeys.kingdomkeys.item.KKShieldItem;
import online.kingdomkeys.kingdomkeys.lib.DamageCalculation;
import online.kingdomkeys.kingdomkeys.util.Utils;

import javax.annotation.Nullable;
import java.util.List;

public class OrgShieldItem extends KKShieldItem implements IOrgWeapon {

    public OrgShieldItem()
    {
        super(new Item.Properties().group(KingdomKeys.orgWeaponsGroup).maxStackSize(1));
    }

    @Override
    public Utils.OrgMember getMember()
    {
        return Utils.OrgMember.VEXEN;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (data != null) {
            addInfoToTooltip(stack, tooltip);
        }
    }

}
