package online.kingdomkeys.kingdomkeys.lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import online.kingdomkeys.kingdomkeys.ability.Ability;
import online.kingdomkeys.kingdomkeys.ability.ModAbilities;
import online.kingdomkeys.kingdomkeys.driveform.DriveForm;
import online.kingdomkeys.kingdomkeys.driveform.ModDriveForms;
import online.kingdomkeys.kingdomkeys.magic.ModMagics;
import online.kingdomkeys.kingdomkeys.synthesis.material.Material;

/**
 * Created by Toby on 19/07/2016.
 */
public class Utils {
	
	public static int getInt(String num) {
		int number;
		try {
			number = Integer.parseInt(num);
			return number;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Method for generating random integer between the 2 parameters, The order of
	 * min and max do not matter.
	 *
	 * @param min
	 *            minimum value that the random integer can be
	 * @param max
	 *            maximum value that the random integer can be
	 * @return a random integer
	 */
	public static int randomWithRange(int min, int max) {
		int range = Math.abs(max - min) + 1;
		return (int) (Math.random() * range) + (min <= max ? min : max);
	}

	/**
	 * Method for generating random doubles between the 2 parameters, The order of
	 * min and max do not matter.
	 *
	 * @param min
	 *            minimum value that the random double can be
	 * @param max
	 *            maximum value that the random double can be
	 * @return a random double
	 */
	public static double randomWithRange(double min, double max) {
		double range = Math.abs(max - min);
		return (Math.random() * range) + (min <= max ? min : max);
	}

	/**
	 * Method for generating random floats between the 2 parameters, The order of
	 * min and max do not matter.
	 *
	 * @param min
	 *            minimum value that the random float can be
	 * @param max
	 *            maximum value that the random float can be
	 * @return a random float
	 */
	public static float randomWithRange(float min, float max) {
		float range = Math.abs(max - min) + 1;
		return (float) (Math.random() * range) + (min <= max ? min : max);
	}
	
	/**
	 * Replacement for
	 * {@link net.minecraft.util.text.translation.I18n#translateToLocalFormatted(String, Object...)}
	 * 
	 * @param name
	 *            the unlocalized string to translate
	 * @param format
	 *            the format of the string
	 * @return the translated string
	 */
	public static String translateToLocalFormatted(String name, Object... format) {
		TranslationTextComponent translation = new TranslationTextComponent(name, format);
		return translation.getFormattedText();
	}

	/**
	 * Replacement for
	 * {@link net.minecraft.util.text.translation.I18n#translateToLocal(String)}
	 * 
	 * @param name
	 *            the unlocalized string to translate
	 * @return the translated string
	 */
	public static String translateToLocal(String name) {
		TranslationTextComponent translation = new TranslationTextComponent(name);
		return translation.getFormattedText();
	}

	public static enum OrgMember {
		XEMNAS, XIGBAR, XALDIN, VEXEN, LEXAEUS, ZEXION, SAIX, AXEL, DEMYX, LUXORD, MARLUXIA, LARXENE, ROXAS, NONE
	}
	
	
	public static int getDriveFormLevel(Map<String,int[]> map, String driveForm) {
		if(driveForm.equals(Strings.Form_Anti))
			return 7;
		return map.get(driveForm)[0];
	}
	
	public static LinkedHashMap<Material, Integer> getSortedMaterials(Map<Material, Integer> materials) {
		ArrayList<Material> list = new ArrayList<Material>();

		for(Material m : materials.keySet()) {
			list.add(m);
		}
		list.sort(Comparator.comparing(Material::getMaterialName));
		
		LinkedHashMap<Material,Integer> map = new LinkedHashMap<Material,Integer>();
		for(Material k : list) {
			map.put(k, materials.get(k));
		}
		return map;
	}
	
	public static LinkedHashMap<String, int[]> getSortedAbilities(LinkedHashMap<String, int[]> abilities) {
		List<Ability> list = new ArrayList<Ability>();		
		
		Iterator<String> it = abilities.keySet().iterator();
		while(it.hasNext()) {
			String entry = it.next();
			list.add(ModAbilities.registry.getValue(new ResourceLocation(entry)));
		}
		
		Collections.sort(list, Comparator.comparingInt(Ability::getOrder));
		
		LinkedHashMap<String, int[]> map = new LinkedHashMap<String, int[]>();
		for(int i=0;i<list.size();i++) {
			map.put(list.get(i).getRegistryName().toString(), abilities.get(list.get(i).getRegistryName().toString()));
		}
		return map;
	}
	
	public static LinkedHashMap<String, int[]> getSortedDriveForms(LinkedHashMap<String, int[]> driveFormsMap) {
		//System.out.println("UNSORTED: "+driveFormsMap);
		List<DriveForm> list = new ArrayList<DriveForm>();		
		
		Iterator<String> it = driveFormsMap.keySet().iterator();
		while(it.hasNext()) {
			String entry = it.next();
			list.add(ModDriveForms.registry.getValue(new ResourceLocation(entry)));
		}
		
		Collections.sort(list, Comparator.comparingInt(DriveForm::getOrder));
		
		LinkedHashMap<String, int[]> map = new LinkedHashMap<String, int[]>();
		for(int i=0;i<list.size();i++) {
			map.put(list.get(i).getRegistryName().toString(), driveFormsMap.get(list.get(i).getRegistryName().toString()));
		}
		
		//System.out.println("SORTED: "+map);
		
		//Old way
		/*String[] keys = new String[driveFormsMap.size()];
		int[][] values = new int[driveFormsMap.size()][2];
		Iterator<Entry<String, int[]>> it = driveFormsMap.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, int[]> entry = it.next();
			int order = ModDriveForms.registry.getValue(new ResourceLocation(entry.getKey())).getOrder();
			keys[order] = entry.getKey();
			values[order] = entry.getValue();
		}
		
		LinkedHashMap<String, int[]> map = new LinkedHashMap<String, int[]>();
		for(int i=0;i<keys.length;i++) {
			map.put(keys[i], values[i]);
		}*/

		return map; 
	}

	public static List<String> getSortedMagics(List<String> list) {
		Collections.sort(list, ((a,b)-> Integer.compare(ModMagics.registry.getValue(new ResourceLocation(a)).getOrder(), ModMagics.registry.getValue(new ResourceLocation(b)).getOrder())));
		return list;
		
		/*String[] keys = new String[list.size()];
		int[][] values = new int[list.size()][2];
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String entry = it.next();
			int order = ModMagics.registry.getValue(new ResourceLocation(entry)).getOrder();
			keys[order] = entry.getKey();
			values[order] = entry.getValue();
		}
		
		LinkedHashMap<String, int[]> map = new LinkedHashMap<String, int[]>();
		for(int i=0;i<keys.length;i++) {
			map.put(keys[i], values[i]);
		}

		return map; */
	}

	public static PlayerEntity getPlayerByName(World world, String name) {
		for(PlayerEntity p : world.getPlayers()) {
			if(p.getDisplayName().getFormattedText().equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	public static List<PlayerEntity> getAllPlayers(MinecraftServer ms){
		List<PlayerEntity> list = new ArrayList<PlayerEntity>();
		java.util.Iterator<ServerWorld> it = ms.getWorlds().iterator();
		while(it.hasNext()) {
			ServerWorld world = it.next();
			for(PlayerEntity p : world.getPlayers()) {
				list.add(p);
			}
		}
		return list;
	}
	
	public static String getResourceName(String text) {
		return text.replaceAll("[ \\t]+$", "").replaceAll("\\s+", "_").replaceAll("[\\'\\:\\-\\,\\#]", "").replaceAll("\\&", "and").toLowerCase();
	}

	/**
	 *
	 * MOST OF THIS WON'T BE HERE ANYMORE
	 *
	 */
	/*public static boolean summonWeapon(EntityPlayer player, EnumHand hand, int keychainSlot) {
		SummonKeybladeCapability.ISummonKeyblade summonCap = player.getCapability(ModCapabilities.SUMMON_KEYBLADE, null);
		OrganizationXIIICapability.IOrganizationXIII organizationXIIICap = player.getCapability(ModCapabilities.ORGANIZATION_XIII, null);

		if (organizationXIIICap.getMember() == Utils.OrgMember.NONE) {
			if (ItemStack.areItemStacksEqual(summonCap.getInventoryKeychain().getStackInSlot(keychainSlot), ItemStack.EMPTY)) {
				player.sendMessage(new TextComponentTranslation(TextFormatting.RED + "Missing keychain to summon"));
				return false;
			}
			if (!summonCap.getIsKeybladeSummoned(hand) && ItemStack.areItemStacksEqual(player.getHeldItem(hand), ItemStack.EMPTY) && summonCap.getInventoryKeychain().getStackInSlot(0).getItem() instanceof KeychainItem) {
				summonCap.setActiveSlot(player.inventory.currentItem);

				ItemStack keychain = summonCap.getInventoryKeychain().getStackInSlot(keychainSlot);
				ItemStack keyblade = new ItemStack(((KeychainItem) (keychain.getItem())).getKeyblade());

				if (hand == EnumHand.MAIN_HAND) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, keyblade);
				} else {
					player.inventory.offHandInventory.set(0, keyblade);
				}

				if (player.world.isRemote)
					PacketDispatcher.sendToServer(new SummonKeyblade(hand, keychainSlot));

				return true;
			} else if (!ItemStack.areItemStacksEqual(player.getHeldItem(hand), ItemStack.EMPTY) && player.getHeldItem(hand).getItem() instanceof ItemRealKeyblade && summonCap.getIsKeybladeSummoned(hand)) {
				if (player.world.isRemote)
					PacketDispatcher.sendToServer(new DeSummonKeyblade(hand));
				player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
				player.inventory.offHandInventory.set(0, ItemStack.EMPTY);
				return true;
			} else {
				return false;
			}
		} else {
			if (!organizationXIIICap.summonedWeapon(hand) && ItemStack.areItemStacksEqual(player.getHeldItem(hand), ItemStack.EMPTY)) {
				if (player.world.isRemote)
					PacketDispatcher.sendToServer(new SummonOrgWeapon(hand, organizationXIIICap.currentWeapon()));
				if (hand == EnumHand.MAIN_HAND)
					player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(organizationXIIICap.currentWeapon()));
				else
					player.inventory.offHandInventory.set(0, new ItemStack(organizationXIIICap.currentWeapon()));
				organizationXIIICap.setWeaponSummoned(hand, true);
				return true;
			} else if (!ItemStack.areItemStacksEqual(player.getHeldItem(hand), ItemStack.EMPTY) && player.getHeldItem(hand).getItem() instanceof IOrgWeapon || (organizationXIIICap.getMember() == Utils.OrgMember.ROXAS && !ItemStack.areItemStacksEqual(player.getHeldItem(hand), ItemStack.EMPTY) && player.getHeldItem(hand).getItem() instanceof KeybladeItem)) {
				if (player.world.isRemote) {
					PacketDispatcher.sendToServer(new DeSummonOrgWeapon(hand));
				}
				organizationXIIICap.setWeaponSummoned(hand, false);
				if (hand == EnumHand.MAIN_HAND)
					player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
				else
					player.inventory.offHandInventory.set(0, ItemStack.EMPTY);
				return true;
			} else {
				return false;
			}
		}
	}*/

  /*  @SideOnly(Side.CLIENT)
    public static void drawScaledModalRect(Gui gui, float x, float y, int u, int v, int width, int height, float scaleX, float scaleY) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0);
        GlStateManager.scale(scaleX, scaleY, 1);
        gui.drawTexturedModalRect(0, 0, u, v, width, height);
        GlStateManager.popMatrix();
    }
    
    public static Ability getAbilityFromName(String name) {
		return GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation(Reference.MODID+":"+name));
    }*/
}
