package online.kingdomkeys.kingdomkeys.item;

import com.google.common.base.Supplier;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import online.kingdomkeys.kingdomkeys.KingdomKeys;

public enum KKArmorMaterial implements ArmorMaterial {
	ORGANIZATION(KingdomKeys.MODID + ":organization", 5, new int[] { 3, 6, 8, 4 }, 420, SoundEvents.ARMOR_EQUIP_LEATHER, 1F, () -> {
		return Ingredient.of(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future.get());
	}),
	XEMNAS(KingdomKeys.MODID + ":xemnas", 5, new int[] { 3, 6, 8, 4 }, 420, SoundEvents.ARMOR_EQUIP_LEATHER, 1F, () -> {
		return Ingredient.of(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future.get());
	}),
	ANTICOAT(KingdomKeys.MODID + ":anticoat", 5, new int[] { 3, 6, 8, 4 }, 420, SoundEvents.ARMOR_EQUIP_LEATHER, 1F, () -> {
		return Ingredient.of(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future.get());
	}),
	TERRA(KingdomKeys.MODID + ":terra", 5, new int[] { 7, 9, 11, 7 }, 420, SoundEvents.ARMOR_EQUIP_DIAMOND, 3F, () -> {
		return Ingredient.of(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future.get());
	}),
	AQUA(KingdomKeys.MODID + ":aqua", 5, new int[] { 7, 9, 11, 7 }, 420, SoundEvents.ARMOR_EQUIP_DIAMOND, 3F, () -> {
		return Ingredient.of(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future.get());
	}),
	VENTUS(KingdomKeys.MODID + ":ventus", 5, new int[] { 7, 9, 11, 7 }, 420, SoundEvents.ARMOR_EQUIP_DIAMOND, 3F, () -> {
		return Ingredient.of(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future.get());
	}),
	NIGHTMAREVEN(KingdomKeys.MODID + ":nightmareventus", 5, new int[] { 7, 9, 11, 7 }, 420, SoundEvents.ARMOR_EQUIP_DIAMOND, 3F, () -> {
		return Ingredient.of(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future.get());
	}),
	ERAQUS(KingdomKeys.MODID + ":eraqus", 5, new int[] { 7, 9, 11, 7 }, 420, SoundEvents.ARMOR_EQUIP_DIAMOND, 3F, () -> {
		return Ingredient.of(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future.get());
	}),
	VANITAS(KingdomKeys.MODID + ":vanitas", 5, new int[] { 2, 5, 7, 3 }, 420, SoundEvents.ARMOR_EQUIP_DIAMOND, 3F, () -> {
		return Ingredient.of(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future.get());
	}),
	ACED(KingdomKeys.MODID + ":aced", 5, new int[] { 2, 5, 7, 3 }, 420, SoundEvents.ARMOR_EQUIP_DIAMOND, 3F, () -> {
		return Ingredient.of(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future.get());
	}),
	AVA(KingdomKeys.MODID + ":ava", 5, new int[] { 2, 5, 7, 3 }, 420, SoundEvents.ARMOR_EQUIP_DIAMOND, 3F, () -> {
		return Ingredient.of(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future.get());
	}),
	GULA(KingdomKeys.MODID + ":gula", 5, new int[] { 2, 5, 7, 3 }, 420, SoundEvents.ARMOR_EQUIP_DIAMOND, 3F, () -> {
		return Ingredient.of(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future.get());
	}),
	INVI(KingdomKeys.MODID + ":invi", 5, new int[] { 2, 5, 7, 3 }, 420, SoundEvents.ARMOR_EQUIP_DIAMOND, 3F, () -> {
		return Ingredient.of(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future.get());
	}),
	IRA(KingdomKeys.MODID + ":ira", 5, new int[] { 2, 5, 7, 3 }, 420, SoundEvents.ARMOR_EQUIP_DIAMOND, 3F, () -> {
		return Ingredient.of(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future.get());
	});
	
	
	
	//private static final int[] MAX_DAMAGE_ARRAY = new int[] { 16, 16, 16, 16 };

	String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final LazyLoadedValue<Ingredient> repairMaterial;

	KKArmorMaterial(String name, int maxDamageFactor, int[] damageReduction, int enchantability, SoundEvent sound, float toughness, Supplier<Ingredient> repairMaterialIn) {
		this.name = name;
		this.maxDamageFactor = maxDamageFactor;
		this.damageReductionAmountArray = damageReduction;
		this.enchantability = enchantability;
		this.soundEvent = sound;
		this.toughness = toughness;
		this.repairMaterial = new LazyLoadedValue<>(repairMaterialIn);
	}

	@Override
	public int getDurabilityForSlot(EquipmentSlot slotIn) {
		return -1;//MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlot slotIn) {
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.soundEvent;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairMaterial.get();
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return 0;
	}

}
