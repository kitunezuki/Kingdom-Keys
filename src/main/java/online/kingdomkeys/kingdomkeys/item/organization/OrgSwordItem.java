package online.kingdomkeys.kingdomkeys.item.organization;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import online.kingdomkeys.kingdomkeys.KingdomKeys;
import online.kingdomkeys.kingdomkeys.lib.DamageCalculation;
import online.kingdomkeys.kingdomkeys.util.IExtendedReach;

public abstract class OrgSwordItem extends SwordItem implements IOrgWeapon, IExtendedReach{
    OrganizationData data = new OrganizationData();

    public OrgSwordItem() {
        super(new OrganizationItemTier(0), 0, 1, new Item.Properties().tab(KingdomKeys.orgWeaponsGroup).stacksTo(1));
    }
    @Override
	public float getReach() {
		return data.getReach();
	}
    
    @Override
    public void setDescription(String description) {
        data.description = description;
    }
    
    @Override
    public String getDesc() {
        return data.getDescription();
    }

    @Override
    public  void setOrganizationData(OrganizationData organizationData) {
        data.baseStrength = organizationData.baseStrength;
        data.baseMagic = organizationData.baseMagic;
        data.reach = organizationData.reach;
        data.description = organizationData.description;
    }
    
    @Override
    public OrganizationData getOrganizationData() {
        return data;
    }

    @Override
    public void setStrength(int str) {
        data.baseStrength = str;
    }
    
    //Get strength from the data based on level
    @Override
    public int getStrength() {
        return data.getStrength();
    }

    public void setMagic(int mag) {
        data.baseMagic = mag;
    }
    
    //Get magic from the data based on level
    @Override
    public int getMagic() {
        return data.getMagic();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
    	if(data != null) {
			tooltip.add(new TranslatableComponent(ChatFormatting.YELLOW + "" + getMember()));
			tooltip.add(new TranslatableComponent(ChatFormatting.RED + "Strength %s", getStrength() + DamageCalculation.getSharpnessDamage(stack) + " [" + DamageCalculation.getOrgStrengthDamage(Minecraft.getInstance().player, stack) + "]"));
			tooltip.add(new TranslatableComponent(ChatFormatting.BLUE + "Magic %s", getMagic() + " [" + DamageCalculation.getOrgMagicDamage(Minecraft.getInstance().player, this) + "]"));
			tooltip.add(new TranslatableComponent(ChatFormatting.WHITE + "" + ChatFormatting.ITALIC + getDesc()));
    	}
    }
}
