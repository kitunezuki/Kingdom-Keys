package online.kingdomkeys.kingdomkeys.datagen.init;

import net.minecraft.data.DataGenerator;
import online.kingdomkeys.kingdomkeys.ability.ModAbilities;
import online.kingdomkeys.kingdomkeys.block.ModBlocks;
import online.kingdomkeys.kingdomkeys.datagen.provider.KKLanguageProvider;
import online.kingdomkeys.kingdomkeys.driveform.ModDriveForms;
import online.kingdomkeys.kingdomkeys.entity.ModEntities;
import online.kingdomkeys.kingdomkeys.handler.InputHandler;
import online.kingdomkeys.kingdomkeys.item.ModItems;
import online.kingdomkeys.kingdomkeys.limit.ModLimits;
import online.kingdomkeys.kingdomkeys.magic.ModMagic;
import online.kingdomkeys.kingdomkeys.reactioncommands.ModReactionCommands;
import online.kingdomkeys.kingdomkeys.shotlock.ModShotlocks;

import static online.kingdomkeys.kingdomkeys.lib.Strings.*;

public class LanguageENUS extends KKLanguageProvider {

    public LanguageENUS(DataGenerator gen) {
        super(gen, "en_us");
    }

    @Override
    protected void addTranslations() {
        /**GUIS**/
        //Containers
        add("container.magical_chest", "Magical Chest");
        add("container.pedestal", "Pedestal");

        //Menu
        add(Gui_Menu_Back, "Back");
        add(Gui_Menu_Back + ".desc", "Go back to the previous menu.");
        add(Gui_Menu_Accept, "Accept");
        add(Gui_Menu_Main_Button_Items, "Items");
        add(Gui_Menu_Main_Button_Items + ".desc", "Access to your equipables (weapon, potions...) and your inventory.");
        add(Gui_Menu_Main_Button_Abilities, "Abilities");
        add(Gui_Menu_Main_Button_Abilities + ".desc", "Equip or unequip your abilities.");
        add(Gui_Menu_Main_Button_Customize, "Customize");
        add(Gui_Menu_Main_Button_Customize + ".desc", "Customize the magic shortcuts");
        add(Gui_Menu_Main_Button_Party, "Party");
        add(Gui_Menu_Main_Button_Party + ".desc", "Create and manage your party.");
        add(Gui_Menu_Main_Button_Status, "Status");
        add(Gui_Menu_Main_Button_Status + ".desc", "Check your stats.");
        add(Gui_Menu_Main_Button_Journal, "Journal");
        add(Gui_Menu_Main_Button_Journal + ".desc", "");
        add(Gui_Menu_Main_Button_Config, "Config");
        add(Gui_Menu_Main_Button_Config + ".desc", "Configure various graphical aspects of the HUD.");
        add(Gui_Menu_Main_Synthesis_Tier, "Synthesis Tier");
        add(Gui_Menu_Main_Munny, "Munny");
        add(Gui_Menu_Main_Hearts, "Hearts");
        add(Gui_Menu_Main_Time, "World Time");
        add(Gui_Menu_Main_Time_Spent, "Global Time");
        add(Gui_Menu_Items, "Items");
        add(Gui_Menu_Items_Equipment, "Equipment");
        add(Gui_Menu_Items_Equipment + ".desc", "Equip your weapon and various equipables (not yet available).");
        add(Gui_Menu_Items_Stock, "Stock");
        add(Gui_Menu_Items_Stock + ".desc", "Check your inventory.");
        add(Gui_Menu_Items_Equipment_Weapon, "Weapon");
        add(Gui_Menu_Items_Equipment_Weapon_Keyblades, "Keyblades");
        add(Gui_Menu_Items_Equipment_Shotlock, "Shotlock");
        add(Gui_Menu_Items_Equipment_Items, "Items");
        add(Gui_Menu_Customize, "Customize");
        add(Gui_Menu_Customize + ".shortcut", "Shortcut");
        add(Gui_Menu_Customize + ".unequip", "Unequip");
        add(Gui_Menu_Party, "Party");
        add(Gui_Menu_Party_Create, "Create party");
        add(Gui_Menu_Party_Create + ".desc", "Create a new party.");
        add(Gui_Menu_Party_Create_Name, "Name");
        add(Gui_Menu_Party_Create_Accessibility, "Accessibility and limit");
        add(Gui_Menu_Party_Create_Accessibility_Public, "Public");
        add(Gui_Menu_Party_Create_Accessibility_Private, "Private");
        add(Gui_Menu_Party_Join, "Join party");
        add(Gui_Menu_Party_Join + "desc", "Join an already created party.");
        add(Gui_Menu_Party_Leader_Invite, "Invite");
        add(Gui_Menu_Party_Leader_Settings, "Settings");
        add(Gui_Menu_Party_Leader_Kick, "Kick");
        add(Gui_Menu_Party_Leader_Disband, "Disband");
        add(Gui_Menu_Party_Member_Leave, "Leave");
        add(Gui_Menu_Status, "Status");
        add(Gui_Menu_Status_Level, "Level");
        add(Gui_Menu_Status_TotalExp, "Experience");
        add(Gui_Menu_Status_NextLevel, "Next LV");
        add(Gui_Menu_Status_HP, "HP");
        add(Gui_Menu_Status_MP, "MP");
        add(Gui_Menu_Status_AP, "AP");
        add(Gui_Menu_Status_DriveGauge, "Drive Gauge");
        add(Gui_Menu_Status_Strength, "Strength");
        add(Gui_Menu_Status_Magic, "Magic");
        add(Gui_Menu_Status_Defense, "Defense");
        add(Gui_Menu_Status_FireRes, "Fire Resistance");
        add(Gui_Menu_Status_BlizzardRes, "Blizzard Resistance");
        add(Gui_Menu_Status_ThunderRes, "Thunder Resistance");
        add(Gui_Menu_Status_DarkRes, "Dark Resistance");
        add(Gui_Menu_Status_FireResShort, "Fire Res.");
        add(Gui_Menu_Status_BlizzardResShort, "Blizzard Res.");
        add(Gui_Menu_Status_ThunderResShort, "Thunder Res.");
        add(Gui_Menu_Status_DarkResShort, "Dark Res.");
        add(Gui_Menu_Status_FormLevel, "Form Level");
        add(Gui_Menu_Status_FormGauge, "Form Gauge");
        add(Gui_Menu_Status_Abilities, "Abilities");
        add(Gui_Menu_Status_Ability, "Ability");
        add(Gui_Menu_Config, "Config");
        add(Gui_Menu_Config + ".bg", "Background");
        add(Gui_Menu_Config + ".command_menu", "Command Menu");
        add(Gui_Menu_Config + ".hp", "HP Bar");
        add(Gui_Menu_Config + ".mp", "MP Bar");
        add(Gui_Menu_Config + ".dp", "DP Bar");
        add(Gui_Menu_Config + ".player_skin", "Player Skin");
        add(Gui_Menu_Config + ".lock_on_hp", "Lock On");
        add(Gui_Menu_Config + ".party", "Party");
        add(Gui_Menu_Config + ".focus", "Focus Bar");
        add(Gui_Menu_Config + ".x_scale", "X Scale");
        add(Gui_Menu_Config + ".x_pos", "X Position");
        add(Gui_Menu_Config + ".selected_x_pos", "Selected X Offset");
        add(Gui_Menu_Config + ".y_pos", "Y Position");
        add(Gui_Menu_Config + ".y_dist", "Y Distance");
        add(Gui_Menu_Config + ".sub_x_offset", "Submenu X Offset");
        add(Gui_Menu_Config + ".header_title", "Header Title");
        add(Gui_Menu_Config + ".text_x_offset", "Text X Offset");
        add(Gui_Menu_Config + ".hp_scale", "HP Bar Scale");
        add(Gui_Menu_Config + ".icon_scale", "Lock On Icon Scale");
        add(Gui_Menu_Config + ".icon_rotation", "Lock On Icon Rotation Speed");
        add(Gui_Menu_Config + ".hp_per_bar", "HP Per Bar");
        add(Gui_Menu_Config + ".show_hearts", "Show hearts on HUD");
        add(Gui_Menu_Config + ".impexp", "Import/Export");
        add(Gui_Menu_Config + ".impexp.import", "Import");
        add(Gui_Menu_Config + ".impexp.export", "Export to clipboard");

        //Synthesis
        add(Gui_Synthesis, "Item Workshop");
        add(Gui_Synthesis_Synthesise, "Synthesise Items");
        add(Gui_Synthesis_Synthesise_Create, "Create");
        add(Gui_Synthesis_Forge_Upgrade, "Upgrade");
        add(Gui_Synthesis_Forge, "Keyblade Forge");
        add(Gui_Synthesis_Materials, "Material List");
        add(Gui_Synthesis_Materials_Deposit, "Deposit");
        add(Gui_Synthesis_Materials_Take, "Take");
        add(Gui_Shop, "Shop");
        add(Gui_Shop_Buy_Cost, "Cost");

        //Command Menu
        add(Gui_CommandMenu_Command, "COMMAND");
        add(Gui_CommandMenu_Attack, "Attack");
        add(Gui_CommandMenu_Portal, "Portal");
        add(Gui_CommandMenu_Magic, "Magic");
        add(Gui_CommandMenu_Items, "Items");
        add(Gui_CommandMenu_Drive, "Drive");
        add(Gui_CommandMenu_Drive_Revert, "Revert");
        add(Gui_CommandMenu_Limit, "Limit");
        add(Gui_CommandMenu_Portals_Title, "Portals");
        add(Gui_CommandMenu_Magic_Title, "Magic");
        add(Gui_CommandMenu_Items_Title, "Items");
        add(Gui_CommandMenu_Drive_Title, "Forms");
        add(Gui_CommandMenu_Limit_Title, "Limits");

        //Synthesis Bag
        add("gui.synthesisbag.upgrade", "Upgrade size");
        add("gui.synthesisbag.munny", "Munny");
        add("gui.synthesisbag.notenoughmunny", "Not enough munny");

        //Proof of Heart
        add("gui.proofofheart.desc", "Use this to leave Organization XIII");
        add("gui.proofofheart.desc2", "You won't be able to use it if you're wearing the Organization XIII robes");
        add("gui.proofofheart.notinorg", "You are not in Organization XIII");
        add("gui.proofofheart.leftorg", "You have left Organization XIII");
        add("gui.proofofheart.unequip", "First unequip your Organization XIII armor");

        //Organization XIII
        add("gui.org.line1", "By donning the Dark Robe you are now a member of Organization XIII.");
        add("gui.org.line2", "Choose a member of Organization XIII you align with.");
        add("gui.org.line3", "Your choice will determine the weapon you start with.");
        add("gui.org.line4", "You wish to align with %1$s?");
        add("gui.org.line5", "It will cost to change this after you have made your choice.");
        add("gui.org.ok", "Ok");
        add("gui.org.select", "Select");
        add("gui.org.cancel", "Cancel");
        add("gui.org.confirm", "Confirm");

        //Level up messages
        add("stats.levelUpStr", "Strength Increased!");
        add("stats.levelUpDef", "Defense Increased!");
        add("stats.levelUpMag", "Magic Increased!");
        add("stats.levelUpHP", "Maximum HP Increased!");
        add("stats.levelUpMP", "Maximum MP Increased!");
        add("stats.levelUpAP", "Maximum AP Increased!");
        add("stats.levelUpFG", "Form Gauge Powered Up!");

        /**Blocks**/
        //Blox
        addBlock(ModBlocks.normalBlox, "Normal Blox");
        addBlock(ModBlocks.hardBlox, "Hard Blox");
        addBlock(ModBlocks.metalBlox, "Metal Blox");
        addBlock(ModBlocks.dangerBlox, "Danger Blox");
        addBlock(ModBlocks.bounceBlox, "Bounce Blox");
        addBlock(ModBlocks.blastBlox, "Blast Blox");
        addBlock(ModBlocks.ghostBlox, "Ghost Blox");
        addBlock(ModBlocks.prizeBlox, "Prize Blox");
        addBlock(ModBlocks.rarePrizeBlox, "Rare Prize Blox");
        addBlock(ModBlocks.magnetBlox, "Magnet Blox");
        addBlock(ModBlocks.pairBlox, "Pair Blox");

        //Ores
        addBlock(ModBlocks.blazingOre, "Blazing Ore");
        addBlock(ModBlocks.blazingOreN, "Blazing Ore");
        addBlock(ModBlocks.blazingOreD, "Blazing Ore");
        addBlock(ModBlocks.soothingOre, "Soothing Ore");
        addBlock(ModBlocks.soothingOreD, "Soothing Ore");
        addBlock(ModBlocks.writhingOre, "Writhing Ore");
        addBlock(ModBlocks.writhingOreN, "Writhing Ore");
        addBlock(ModBlocks.writhingOreE, "Writhing Ore");
        addBlock(ModBlocks.writhingOreD, "Writhing Ore");
        addBlock(ModBlocks.betwixtOre, "Betwixt Ore");
        addBlock(ModBlocks.betwixtOreD, "Betwixt Ore");
        addBlock(ModBlocks.wellspringOre, "Wellspring Ore");
        addBlock(ModBlocks.wellspringOreN, "Wellspring Ore");
        addBlock(ModBlocks.frostOre, "Frost Ore");
        addBlock(ModBlocks.frostOreD, "Frost Ore");
        addBlock(ModBlocks.lucidOre, "Lucid Ore");
        addBlock(ModBlocks.lightningOre, "Lightning Ore");
        addBlock(ModBlocks.pulsingOre, "Pulsing Ore");
        addBlock(ModBlocks.pulsingOreD, "Pulsing Ore");
        addBlock(ModBlocks.pulsingOreE, "Pulsing Ore");
        addBlock(ModBlocks.remembranceOre, "Remembrance Ore");
        addBlock(ModBlocks.hungryOre, "Hungry Ore");
        addBlock(ModBlocks.sinisterOre, "Sinister Ore");
        addBlock(ModBlocks.sinisterOreD, "Sinister Ore");
        addBlock(ModBlocks.stormyOre, "Stormy Ore");
        addBlock(ModBlocks.stormyOreD, "Stormy Ore");
        addBlock(ModBlocks.tranquilityOre, "Tranquility Ore");
        addBlock(ModBlocks.twilightOre, "Twilight Ore");
        addBlock(ModBlocks.twilightOreD, "Twilight Ore");
        addBlock(ModBlocks.twilightOreN, "Twilight Ore");

        //Other
        addBlock(ModBlocks.mosaic_stained_glass, "Mosaic Stained Glass");
        addBlock(ModBlocks.orgPortal, "Organization Portal");
        addBlock(ModBlocks.moogleProjector, "Moogle Projector");
        addBlock(ModBlocks.station_of_awakening_core, "Station of Awakening Platform Core");
        addBlock(ModBlocks.magicalChest, "Magical Chest");
        addBlock(ModBlocks.pedestal, "Pedestal");
        addBlock(ModBlocks.savepoint, "Save Point");
        addBlock(ModBlocks.soADoor, "Mysterious Door");
        addBlock(ModBlocks.gummiEditor, "Gummi Editor");
        addBlock(ModBlocks.sorCore, "Station of Sorrow Core");
        addBlock(ModBlocks.dataPortal, "Data Portal");

        /**KK stuff**/
        //Abilities
        addAbility(ModAbilities.AUTO_VALOR, "Auto Valor");
        addAbility(ModAbilities.AUTO_WISDOM, "Auto Wisdom");
        addAbility(ModAbilities.AUTO_LIMIT, "Auto Limit");
        addAbility(ModAbilities.AUTO_MASTER, "Auto Master");
        addAbility(ModAbilities.AUTO_FINAL, "Auto Final");

        addGrowthAbility(ModAbilities.HIGH_JUMP, "High Jump LV.1", "High Jump LV.2", "High Jump LV.3", "High Jump MAX");
        addAbilityDesc(ModAbilities.HIGH_JUMP, "Now you can jump really high!");
        addGrowthAbility(ModAbilities.QUICK_RUN, "Quick Run LV.1", "Quick Run LV.2", "Quick Run LV.3", "Quick Run MAX");
        addAbilityDesc(ModAbilities.QUICK_RUN, "If you press the action button while running you will sprint.");
        addGrowthAbility(ModAbilities.DODGE_ROLL, "Dodge Roll LV.1", "Dodge Roll LV.2", "Dodge Roll LV.3", "Dodge Roll MAX");
        addAbilityDesc(ModAbilities.DODGE_ROLL, "If you press the action button while walking you will dodge roll. ");
        addGrowthAbility(ModAbilities.AERIAL_DODGE, "Aerial Dodge LV.1", "Aerial Dodge LV.2", "Aerial Dodge LV.3", "Aerial Dodge MAX");
        addAbilityDesc(ModAbilities.AERIAL_DODGE, "In the air you can press jump again to double jump.");
        addGrowthAbility(ModAbilities.GLIDE, "Glide LV.1", "Glide LV.2", "Glide LV.3", "Glide MAX");
        addAbilityDesc(ModAbilities.GLIDE, "In the air, hold the jump key to glide.");

        addAbilityWithDesc(ModAbilities.SCAN, "Scan", "Inspect the target's current HP.");
        addAbilityWithDesc(ModAbilities.ZERO_EXP, "Zero EXP", "Disables the gaining of experience.");
        addAbilityWithDesc(ModAbilities.MP_HASTE, "MP Haste", "Increases MP restoration speed after MP is fully consumed.");
        addAbilityWithDesc(ModAbilities.MP_HASTERA, "MP Hastera", "Increases MP restoration speed even more after MP is fully consumed.");
        addAbilityWithDesc(ModAbilities.MP_HASTEGA, "MP Hastega", "Highly Increases MP restoration speed after MP is fully consumed.");
        addAbilityWithDesc(ModAbilities.MP_RAGE,  "MP Rage", "Restores MP relative to the amount of damage taken.");
        addAbilityWithDesc(ModAbilities.DAMAGE_CONTROL, "Damage Control", "Halve the damage you take when your HP is at 25% or below. Stack the ability to increase the effect.");
        addAbilityWithDesc(ModAbilities.DAMAGE_DRIVE, "Damage Drive", "Restores the Drive Gauge every time damage is taken. The amount restored to the Drive Gauge is relative to the damage received.");
        addAbilityWithDesc(ModAbilities.DRIVE_BOOST, "Drive Boost", "Allows greater restoration of the Drive Gauge during MP Charge.");
        addAbilityWithDesc(ModAbilities.FORM_BOOST, "Form Boost", "Increases the duration of each Drive Form.");
        addAbilityWithDesc(ModAbilities.FIRE_BOOST, "Fire Boost", "Increases damage done with fire-based attacks.");
        addAbilityWithDesc(ModAbilities.BLIZZARD_BOOST, "Blizzard Boost", "Increases damage done with blizzard-based attacks.");
        addAbilityWithDesc(ModAbilities.THUNDER_BOOST, "Thunder Boost", "Increases damage done with thunder-based attacks.");
        addAbilityWithDesc(ModAbilities.TREASURE_MAGNET, "Treasure Magnet", "Automatically draw in and collect nearby prizes. Stack the ability to increase the effect.");
        addAbilityWithDesc(ModAbilities.EXPERIENCE_BOOST, "Experience Boost", "Increases experience gained by 100% when you are at half health or less.");
        addAbilityWithDesc(ModAbilities.SECOND_CHANCE, "Second Chance", "Ensures 1 HP remains after taking massive damage.");
        addAbilityWithDesc(ModAbilities.LIGHT_AND_DARKNESS, "Light & Darkness", "Has a secret effect");
        addAbilityWithDesc(ModAbilities.SYNCH_BLADE, "Synch Blade", "Equips a weapon in each hand. The ability of the left hand weapon becomes available as well.");
        addAbilityWithDesc(ModAbilities.MP_SAFETY, "MP Safety", "Disable recharging when MP runs out using shortcuts. Except if it's Cure");
        addAbilityWithDesc(ModAbilities.DRIVE_CONVERTER, "Drive Converter", "Increment all Drive Point prizes value");
        addAbilityWithDesc(ModAbilities.FOCUS_CONVERTER, "Focus Converter", "Increment all Focus prizes value.");
        addAbilityWithDesc(ModAbilities.FULL_MP_BLAST, "Full MP Blast", "When your MP is full, increase the power of your first magical ability by 50%. Stack the ability to increase the effect.");
        addAbilityWithDesc(ModAbilities.WIZARDS_RUSE, "Wizard's Ruse", "Potentially recover HP proportionate to the MP you should expend. Stack the ability to increase the odds.");
        addAbilityWithDesc(ModAbilities.EXTRA_CAST, "Extra Cast", "Allows the use of one last spell before running out of MP.");
        addAbilityWithDesc(ModAbilities.MP_THRIFT, "MP Thrift", "Decrease MP cost by 20%. Stack the ability to increaase the effect.");
        addAbilityWithDesc(ModAbilities.CRITICAL_BOOST, "Critical Boost", "Increases damage dealt by critical hits by 10%. Stack the ability to increase the effect.");
        addAbilityWithDesc(ModAbilities.JACKPOT, "Jackpot", "Increment the values for HP, MP and Munny prizes. Stack the ability to increase the effect.");
        addAbilityWithDesc(ModAbilities.LUCKY_LUCKY, "Lucky Strike", "Brings luck, fortune and looting to the wearer, increasing the drop rate of items. Stack the ability to increase the effect.");
        addAbilityWithDesc(ModAbilities.ITEM_BOOST, "Item Boost", "Increases effect done with healin gitems on the field by 50%. Equip more to increase the effect.");
        addAbilityWithDesc(ModAbilities.FIRAZA, "Firaza", "Allows the user to cast get the Firaza reaction command. Stack the ability to increase the chance.");
        addAbilityWithDesc(ModAbilities.BLIZZAZA, "Blizzaza", "Allows the user to get the Blizzaza reaction command. Stack the ability to increase the chance.");
        addAbilityWithDesc(ModAbilities.WATERZA, "Waterza", "Allows the user to cast get the Waterza reaction command. Stack the ability to increase the chance.");
        addAbilityWithDesc(ModAbilities.THUNDAZA, "Thundaza", "Allows the user to cast get the Thundaza reaction command. Stack the ability to increase the chance.");

        //Limits
        addLimit(ModLimits.LASER_CIRCLE, "Laser Circle");
        addLimit(ModLimits.LASER_DOME, "Laser Dome");
        addLimit(ModLimits.ARROW_RAIN, "Arrow Rain");

        //Shotlocks
        addShotlock(ModShotlocks.RAGNAROK, "Ragnarok");
        addShotlock(ModShotlocks.DARK_VOLLEY, "Dark Volley");
        addShotlock(ModShotlocks.PRISM_RAIN, "Prism Rain");

        //Magic
        addMagic(ModMagic.FIRE, "Fire", "Fira", "Firaga", "Firaza");
        addMagic(ModMagic.BLIZZARD, "Blizzard", "Blizzara", "Blizzaga", "Blizzaza");
        addMagic(ModMagic.WATER, "Water", "Watera", "Waterga", "Waterza");
        addMagic(ModMagic.THUNDER, "Thunder", "Thundara", "Thundaga", "Thundaza");
        addMagic(ModMagic.CURE, "Cure", "Cura", "Curaga");
        addMagic(ModMagic.AERO, "Aero", "Aerora", "Aeroga");
        addMagic(ModMagic.MAGNET, "Magnet", "Magnera", "Magnega");
        addMagic(ModMagic.REFLECT, "Reflect", "Reflera", "Reflega");
        addMagic(ModMagic.GRAVITY, "Gravity", "Gravira", "Graviga");
        addMagic(ModMagic.STOP, "Stop", "Stopra", "Stopga");

        //Drive Forms
        addDriveForm(ModDriveForms.VALOR, "Valor");
        addDriveForm(ModDriveForms.WISDOM, "Wisdom");
        addDriveForm(ModDriveForms.LIMIT, "Limit");
        addDriveForm(ModDriveForms.MASTER, "Master");
        addDriveForm(ModDriveForms.FINAL, "Final");

        //Reaction Commands
        addReactionCommand(ModReactionCommands.AUTO_VALOR, "Auto Valor");
        addReactionCommand(ModReactionCommands.AUTO_WISDOM, "Auto Wisdom");
        addReactionCommand(ModReactionCommands.AUTO_LIMIT, "Auto Limit");
        addReactionCommand(ModReactionCommands.AUTO_MASTER, "Auto Master");
        addReactionCommand(ModReactionCommands.AUTO_FINAL, "Auto Final");

        /**Items**/
        //Cards
        add("item.mapcard.prefix", "(%s) %s");
        addItem(ModItems.tranquilDarkness, "Tranquil Darkness");
        addItem(ModItems.teemingDarkness, "Teeming Darkness");
        addItem(ModItems.feebleDarkness, "Feeble Darkness");
        addItem(ModItems.almightyDarkness, "Almighty Darkness");
        addItem(ModItems.sleepingDarkness, "Sleeping Darkness");
        addItem(ModItems.loomingDarkness, "Looming Darkness");
        addItem(ModItems.bottomlessDarkness, "Bottomless Darkness");
        addItem(ModItems.martialWaking, "Martial Waking");
        addItem(ModItems.sorcerousWaking, "Sorcerous Waking");
        addItem(ModItems.alchemicWaking, "Alchemic Waking");
        addItem(ModItems.stagnantSpace, "Stagnant Space");
        addItem(ModItems.weightlessSpace, "Weightless Space");
        addItem(ModItems.calmBounty, "Calm Bounty");
        addItem(ModItems.guardedTrove, "Guarded Trove");
        addItem(ModItems.falseBounty, "False Bounty");
        addItem(ModItems.momentsReprieve, "Moment's Reprieve");
        addItem(ModItems.minglingWorlds, "Mingling Worlds");
        addItem(ModItems.moogleRoom, "Moogle Room");
        addItem(ModItems.prosperousRepository, "Prosperous Repository");
        addItem(ModItems.treacherousRepository, "Treacherous Repository");
        addItem(ModItems.reposefulGrove, "Reposeful Grove");

        //Materials
        addItem(ModItems.blazing_shard, "Blazing Shard");
        addItem(ModItems.blazing_stone, "Blazing Stone");
        addItem(ModItems.blazing_gem, "Blazing Gem");
        addItem(ModItems.blazing_crystal, "Blazing Crystal");

        addItem(ModItems.soothing_shard, "Soothing Shard");
        addItem(ModItems.soothing_stone, "Soothing Stone");
        addItem(ModItems.soothing_gem, "Soothing Gem");
        addItem(ModItems.soothing_crystal, "Soothing Crystal");

        addItem(ModItems.writhing_shard, "Writhing Shard");
        addItem(ModItems.writhing_stone, "Writhing Stone");
        addItem(ModItems.writhing_gem, "Writhing Gem");
        addItem(ModItems.writhing_crystal, "Writhing Crystal");

        addItem(ModItems.betwixt_shard, "Betwixt Shard");
        addItem(ModItems.betwixt_stone, "Betwixt Stone");
        addItem(ModItems.betwixt_gem, "Betwixt Gem");
        addItem(ModItems.betwixt_crystal, "Betwixt Crystal");

        addItem(ModItems.wellspring_shard, "Wellspring Shard");
        addItem(ModItems.wellspring_stone, "Wellspring Stone");
        addItem(ModItems.wellspring_gem, "Wellspring Gem");
        addItem(ModItems.wellspring_crystal, "Wellspring Crystal");

        addItem(ModItems.frost_shard, "Frost Shard");
        addItem(ModItems.frost_stone, "Frost Stone");
        addItem(ModItems.frost_gem, "Frost Gem");
        addItem(ModItems.frost_crystal, "Frost Crystal");

        addItem(ModItems.lightning_shard, "Lightning Shard");
        addItem(ModItems.lightning_stone, "Lightning Stone");
        addItem(ModItems.lightning_gem, "Lightning Gem");
        addItem(ModItems.lightning_crystal, "Lightning Crystal");

        addItem(ModItems.lucid_shard, "Lucid Shard");
        addItem(ModItems.lucid_stone, "Lucid Stone");
        addItem(ModItems.lucid_gem, "Lucid Gem");
        addItem(ModItems.lucid_crystal, "Lucid Crystal");

        addItem(ModItems.hungry_shard, "Hungry Shard");
        addItem(ModItems.hungry_stone, "Hungry Stone");
        addItem(ModItems.hungry_gem, "Hungry Gem");
        addItem(ModItems.hungry_crystal, "Hungry Crystal");

        addItem(ModItems.twilight_shard, "Twilight Shard");
        addItem(ModItems.twilight_stone, "Twilight Stone");
        addItem(ModItems.twilight_gem, "Twilight Gem");
        addItem(ModItems.twilight_crystal, "Twilight Crystal");

        addItem(ModItems.mythril_shard, "Mythril Shard");
        addItem(ModItems.mythril_stone, "Mythril Stone");
        addItem(ModItems.mythril_gem, "Mythril Gem");
        addItem(ModItems.mythril_crystal, "Mythril Crystal");

        addItem(ModItems.tranquility_shard, "Tranquility Shard");
        addItem(ModItems.tranquility_stone, "Tranquility Stone");
        addItem(ModItems.tranquility_gem, "Tranquility Gem");
        addItem(ModItems.tranquility_crystal, "Tranquility Crystal");

        addItem(ModItems.sinister_shard, "Sinister Shard");
        addItem(ModItems.sinister_stone, "Sinister Stone");
        addItem(ModItems.sinister_gem, "Sinister Gem");
        addItem(ModItems.sinister_crystal, "Sinister Crystal");

        addItem(ModItems.stormy_shard, "Stormy Shard");
        addItem(ModItems.stormy_stone, "Stormy Stone");
        addItem(ModItems.stormy_gem, "Stormy Gem");
        addItem(ModItems.stormy_crystal, "Stormy Crystal");

        addItem(ModItems.remembrance_shard, "Remembrance Shard");
        addItem(ModItems.remembrance_stone, "Remembrance Stone");
        addItem(ModItems.remembrance_gem, "Remembrance Gem");
        addItem(ModItems.remembrance_crystal, "Remembrance Crystal");

        addItem(ModItems.pulsing_shard, "Pulsing Shard");
        addItem(ModItems.pulsing_stone, "Pulsing Stone");
        addItem(ModItems.pulsing_gem, "Pulsing Gem");
        addItem(ModItems.pulsing_crystal, "Pulsing Crystal");

        addItem(ModItems.orichalcum, "Orichalcum");
        addItem(ModItems.orichalcumplus, "Orichalcum+");
        addItem(ModItems.lost_illusion, "Lost Illusion");
        addItem(ModItems.manifest_illusion, "Manifest Illusion");

        addItem(ModItems.fluorite, "Fluorite");
        addItem(ModItems.damascus, "Damascus");
        addItem(ModItems.adamantite, "Adamantite");
        addItem(ModItems.electrum, "Electrum");
        addItem(ModItems.evanescent_crystal, "Evanescent Crystal");
        addItem(ModItems.illusory_crystal, "Illusory Crystal");

        //Keyblades
        addItem(ModItems.abaddonPlasma, "Abaddon Plasma");
        addItem(ModItems.abyssalTide, "Abyssal Tide");
        addItem(ModItems.acedsKeyblade, "Aced's Keyblade");
        addItem(ModItems.allForOne, "All For One");
        addItem(ModItems.astralBlast, "Astral Blast");
        addItem(ModItems.aubade, "Aubade");
        addItem(ModItems.avasKeyblade, "Ava's Keyblade");
        addItem(ModItems.bondOfFlame, "Bond Of Flame");
        addItem(ModItems.bondOfTheBlaze, "Bond of the Blaze");
        addItem(ModItems.brightcrest, "Brightcrest");
        addItem(ModItems.chaosRipper, "Chaos Ripper");
        addItem(ModItems.circleOfLife, "Circle Of Life");
        addItem(ModItems.counterpoint, "Counterpoint");
        addItem(ModItems.crabclaw, "Crabclaw");
        addItem(ModItems.crownOfGuilt, "Crown Of Guilt");
        addItem(ModItems.darkerThanDark, "Darker Than Dark");
        addItem(ModItems.darkgnaw, "Darkgnaw");
        addItem(ModItems.decisivePumpkin, "Decisive Pumpkin");
        addItem(ModItems.destinysEmbrace, "Destinys Embrace");
        addItem(ModItems.diamondDust, "Diamond Dust");
        addItem(ModItems.divewing, "Divewing");
        addItem(ModItems.divineRose, "Divine Rose");
        addItem(ModItems.dualDisc, "Dual Disc");
        addItem(ModItems.earthshaker, "Earthshaker");
        addItem(ModItems.endOfPain, "End Of Pain");
        addItem(ModItems.endsOfTheEarth, "Ends Of The Earth");
        addItem(ModItems.fairyHarp, "Fairy Harp");
        addItem(ModItems.fairyStars, "Fairy Stars");
        addItem(ModItems.fatalCrest, "Fatal Crest");
        addItem(ModItems.fenrir, "Fenrir");
        addItem(ModItems.ferrisGear, "Ferris Gear");
        addItem(ModItems.followTheWind, "Follow the Wind");
        addItem(ModItems.frolicFlame, "Frolic Flame");
        addItem(ModItems.glimpseOfDarkness, "Glimpse Of Darkness");
        addItem(ModItems.guardianBell, "Guardian Bell");
        addItem(ModItems.guardianSoul, "Guardian Soul");
        addItem(ModItems.gulasKeyblade, "Gula's Keyblade");
        addItem(ModItems.gullWing, "Gull Wing");
        addItem(ModItems.herosCrest, "Heros Crest");
        addItem(ModItems.hiddenDragon, "Hidden Dragon");
        addItem(ModItems.hyperdrive, "Hyperdrive");
        addItem(ModItems.incompleteKiblade, "Incomplete \u03c7-Blade");
        addItem(ModItems.invisKeyblade, "Invi's Keyblade");
        addItem(ModItems.irasKeyblade, "Ira's Keyblade");
        addItem(ModItems.jungleKing, "Jungle King");
        addItem(ModItems.keybladeOfPeoplesHearts, "Keyblade Of People's Hearts");
        addItem(ModItems.kiblade, "\u03c7-Blade");
        addItem(ModItems.kingdomKey, "Kingdom Key");
        addItem(ModItems.kingdomKeyD, "Kingdom Key D");
        addItem(ModItems.knockoutPunch, "Knockout Punch");
        addItem(ModItems.ladyLuck, "Lady Luck");
        addItem(ModItems.leviathan, "Leviathan");
        addItem(ModItems.lionheart, "Lionheart");
        addItem(ModItems.lostMemory, "Lost Memory");
        addItem(ModItems.lunarEclipse, "Lunar Eclipse");
        addItem(ModItems.markOfAHero, "Mark Of A Hero");
        addItem(ModItems.mastersDefender, "Masters Defender");
        addItem(ModItems.maverickFlare, "Maverick Flare");
        addItem(ModItems.metalChocobo, "Metal Chocobo");
        addItem(ModItems.midnightRoar, "Midnight Roar");
        addItem(ModItems.mirageSplit, "Mirage Split");
        addItem(ModItems.missingAche, "Missing Ache");
        addItem(ModItems.monochrome, "Monochrome");
        addItem(ModItems.moogleOGlory, "Moogle O Glory");
        addItem(ModItems.mysteriousAbyss, "Mysterious Abyss");
        addItem(ModItems.nightmaresEnd, "Nightmares End");
        addItem(ModItems.nightmaresEndAndMirageSplit, "Combined Keyblade");
        addItem(ModItems.noName, "No Name");
        addItem(ModItems.noNameBBS, "No Name (BBS)");
        addItem(ModItems.oathkeeper, "Oathkeeper");
        addItem(ModItems.oblivion, "Oblivion");
        addItem(ModItems.oceansRage, "Oceans Rage");
        addItem(ModItems.olympia, "Olympia");
        addItem(ModItems.omegaWeapon, "Omega Weapon");
        addItem(ModItems.ominousBlight, "Ominous Blight");
        addItem(ModItems.oneWingedAngel, "One Winged Angel");
        addItem(ModItems.painOfSolitude, "Pain Of Solitude");
        addItem(ModItems.photonDebugger, "Photon Debugger");
        addItem(ModItems.pixiePetal, "Pixie Petal");
        addItem(ModItems.pumpkinhead, "Pumpkinhead");
        addItem(ModItems.rainfell, "Rainfell");
        addItem(ModItems.rejectionOfFate, "Rejection Of Fate");
        addItem(ModItems.royalRadiance, "Royal Radiance");
        addItem(ModItems.rumblingRose, "Rumbling Rose");
        addItem(ModItems.shootingStar, "Shooting Star");
        addItem(ModItems.signOfInnocence, "Sign Of Innocence");
        addItem(ModItems.silentDirge, "Silent Dirge");
        addItem(ModItems.skullNoise, "Skull Noise");
        addItem(ModItems.sleepingLion, "Sleeping Lion");
        addItem(ModItems.soulEater, "Soul Eater");
        addItem(ModItems.spellbinder, "Spellbinder");
        addItem(ModItems.starSeeker, "Star Seeker");
        addItem(ModItems.starlight, "Starlight");
        addItem(ModItems.stormfall, "Stormfall");
        addItem(ModItems.strokeOfMidnight, "Stroke Of Midnight");
        addItem(ModItems.sweetDreams, "Sweet Dreams");
        addItem(ModItems.sweetMemories, "Sweet Memories");
        addItem(ModItems.sweetstack, "Sweetstack");
        addItem(ModItems.threeWishes, "Three Wishes");
        addItem(ModItems.totalEclipse, "Total Eclipse");
        addItem(ModItems.treasureTrove, "Treasure Trove");
        addItem(ModItems.trueLightsFlight, "True Lights Flight");
        addItem(ModItems.twilightBlaze, "Twilight Blaze");
        addItem(ModItems.twoBecomeOne, "Two Become One");
        addItem(ModItems.ultimaWeaponBBS, "Ultima Weapon (BBS)");
        addItem(ModItems.ultimaWeaponDDD, "Ultima Weapon (DDD)");
        addItem(ModItems.ultimaWeaponKH1, "Ultima Weapon (KH1)");
        addItem(ModItems.ultimaWeaponKH2, "Ultima Weapon (KH2)");
        addItem(ModItems.ultimaWeaponKH3, "Ultima Weapon (KH3)");
        addItem(ModItems.umbrella, "Umbrella");
        addItem(ModItems.unbound, "Unbound");
        addItem(ModItems.victoryLine, "Victory Line");
        addItem(ModItems.voidGear, "Void Gear");
        addItem(ModItems.voidGearRemnant, "Void Gear Remnant");
        addItem(ModItems.waytotheDawn, "Way to the Dawn");
        addItem(ModItems.waywardWind, "Wayward Wind");
        addItem(ModItems.winnersProof, "Winners Proof");
        addItem(ModItems.wishingLamp, "Wishing Lamp");
        addItem(ModItems.wishingStar, "Wishing Star");
        addItem(ModItems.woodenKeyblade, "Wooden Keyblade");
        addItem(ModItems.woodenStick, "Wooden Stick");
        addItem(ModItems.youngXehanortsKeyblade, "Young Xehanort's Keyblade");
        addItem(ModItems.zeroOne, "Zero One");
        addItem(ModItems.dreamSword, "Dream Sword");
        addItem(ModItems.dreamStaff, "Dream Staff");
        addItem(ModItems.dreamShield, "Dream Shield");

        //Keychains
        addItem(ModItems.abaddonPlasmaChain, "Abaddon Plasma Chain");
        addItem(ModItems.abyssalTideChain, "Abyssal Tide Chain");
        addItem(ModItems.acedsKeybladeChain, "Aced's Keyblade Chain");
        addItem(ModItems.allForOneChain, "All For One Chain");
        addItem(ModItems.astralBlastChain, "Astral Blast Chain");
        addItem(ModItems.aubadeChain, "Aubade Chain");
        addItem(ModItems.avasKeybladeChain, "Ava's Keyblade Chain");
        addItem(ModItems.bondOfFlameChain, "Bond Of Flame Chain");
        addItem(ModItems.bondOfTheBlazeChain, "Bond of the Blaze Chain");
        addItem(ModItems.brightcrestChain, "Brightcrest Chain");
        addItem(ModItems.chaosRipperChain, "Chaos Ripper Chain");
        addItem(ModItems.circleOfLifeChain, "Circle Of Life Chain");
        addItem(ModItems.counterpointChain, "Counterpoint Chain");
        addItem(ModItems.crabclawChain, "Crabclaw Chain");
        addItem(ModItems.crownOfGuiltChain, "Crown Of Guilt Chain");
        addItem(ModItems.darkerThanDarkChain, "Darker Than Dark Chain");
        addItem(ModItems.darkgnawChain, "Darkgnaw Chain");
        addItem(ModItems.decisivePumpkinChain, "Decisive Pumpkin Chain");
        addItem(ModItems.destinysEmbraceChain, "Destinys Embrace Chain");
        addItem(ModItems.diamondDustChain, "Diamond Dust Chain");
        addItem(ModItems.divewingChain, "Divewing Chain");
        addItem(ModItems.divineRoseChain, "Divine Rose Chain");
        addItem(ModItems.dualDiscChain, "Dual Disc Chain");
        addItem(ModItems.earthshakerChain, "Earthshaker Chain");
        addItem(ModItems.endOfPainChain, "End Of Pain Chain");
        addItem(ModItems.endsOfTheEarthChain, "Ends Of The Earth Chain");
        addItem(ModItems.fairyHarpChain, "Fairy Harp Chain");
        addItem(ModItems.fairyStarsChain, "Fairy Stars Chain");
        addItem(ModItems.fatalCrestChain, "Fatal Crest Chain");
        addItem(ModItems.fenrirChain, "Fenrir Chain");
        addItem(ModItems.ferrisGearChain, "Ferris Gear Chain");
        addItem(ModItems.followTheWindChain, "Follow The Wind Chain");
        addItem(ModItems.frolicFlameChain, "Frolic Flame Chain");
        addItem(ModItems.glimpseOfDarknessChain, "Glimpse Of Darkness Chain");
        addItem(ModItems.guardianBellChain, "Guardian Bell Chain");
        addItem(ModItems.guardianSoulChain, "Guardian Soul Chain");
        addItem(ModItems.gulasKeybladeChain, "Gula's Keyblade Chain");
        addItem(ModItems.gullWingChain, "Gull Wing Chain");
        addItem(ModItems.herosCrestChain, "Heros Crest Chain");
        addItem(ModItems.hiddenDragonChain, "Hidden Dragon Chain");
        addItem(ModItems.hyperdriveChain, "Hyperdrive Chain");
        addItem(ModItems.incompleteKibladeChain, "Incomplete \u03c7-Blade Chain");
        addItem(ModItems.invisKeybladeChain, "Invi's Keyblade Chain");
        addItem(ModItems.irasKeybladeChain, "Ira's Keyblade Chain");
        addItem(ModItems.jungleKingChain, "Jungle King Chain");
        addItem(ModItems.keybladeOfPeoplesHeartsChain, "Keyblade Of People's Hearts Chain");
        addItem(ModItems.kibladeChain, "\u03c7-Blade Chain");
        addItem(ModItems.kingdomKeyChain, "Kingdom Key Chain");
        addItem(ModItems.kingdomKeyDChain, "Kingdom Key D Chain");
        addItem(ModItems.knockoutPunchChain, "Knockout Punch Chain");
        addItem(ModItems.ladyLuckChain, "Lady Luck Chain");
        addItem(ModItems.leviathanChain, "Leviathan Chain");
        addItem(ModItems.lionheartChain, "Lionheart Chain");
        addItem(ModItems.lostMemoryChain, "Lost Memory Chain");
        addItem(ModItems.lunarEclipseChain, "Lunar Eclipse Chain");
        addItem(ModItems.markOfAHeroChain, "Mark Of A Hero Chain");
        addItem(ModItems.mastersDefenderChain, "Masters Defender Chain");
        addItem(ModItems.maverickFlareChain, "Maverick Flare Chain");
        addItem(ModItems.metalChocoboChain, "Metal Chocobo Chain");
        addItem(ModItems.midnightRoarChain, "Midnight Roar Chain");
        addItem(ModItems.mirageSplitChain, "Mirage Split Chain");
        addItem(ModItems.missingAcheChain, "Missing Ache Chain");
        addItem(ModItems.monochromeChain, "Monochrome Chain");
        addItem(ModItems.moogleOGloryChain, "Moogle O Glory Chain");
        addItem(ModItems.mysteriousAbyssChain, "Mysterious Abyss Chain");
        addItem(ModItems.nightmaresEndChain, "Nightmares End Chain");
        addItem(ModItems.nightmaresEndAndMirageSplitChain, "Combined Keyblade Chain");
        addItem(ModItems.noNameChain, "No Name Chain");
        addItem(ModItems.noNameBBSChain, "No Name (BBS) Chain");
        addItem(ModItems.oathkeeperChain, "Oathkeeper Chain");
        addItem(ModItems.oblivionChain, "Oblivion Chain");
        addItem(ModItems.oceansRageChain, "Oceans Rage Chain");
        addItem(ModItems.olympiaChain, "Olympia Chain");
        addItem(ModItems.omegaWeaponChain, "Omega Weapon Chain");
        addItem(ModItems.ominousBlightChain, "Ominous Blight Chain");
        addItem(ModItems.oneWingedAngelChain, "One Winged Angel Chain");
        addItem(ModItems.painOfSolitudeChain, "Pain Of Solitude Chain");
        addItem(ModItems.photonDebuggerChain, "Photon Debugger Chain");
        addItem(ModItems.pixiePetalChain, "Pixie Petal Chain");
        addItem(ModItems.pumpkinheadChain, "Pumpkinhead Chain");
        addItem(ModItems.rainfellChain, "Rainfell Chain");
        addItem(ModItems.rejectionOfFateChain, "Rejection Of Fate Chain");
        addItem(ModItems.royalRadianceChain, "Royal Radiance Chain");
        addItem(ModItems.rumblingRoseChain, "Rumbling Rose Chain");
        addItem(ModItems.shootingStarChain, "Shooting Star Chain");
        addItem(ModItems.signOfInnocenceChain, "Sign Of Innocence Chain");
        addItem(ModItems.silentDirgeChain, "Silent Dirge Chain");
        addItem(ModItems.skullNoiseChain, "Skull Noise Chain");
        addItem(ModItems.sleepingLionChain, "Sleeping Lion Chain");
        addItem(ModItems.soulEaterChain, "Soul Eater Chain");
        addItem(ModItems.spellbinderChain, "Spellbinder Chain");
        addItem(ModItems.starSeekerChain, "Star Seeker Chain");
        addItem(ModItems.starlightChain, "Starlight Chain");
        addItem(ModItems.stormfallChain, "Stormfall Chain");
        addItem(ModItems.strokeOfMidnightChain, "Stroke Of Midnight Chain");
        addItem(ModItems.sweetDreamsChain, "Sweet Dreams Chain");
        addItem(ModItems.sweetMemoriesChain, "Sweet Memories Chain");
        addItem(ModItems.sweetstackChain, "Sweetstack Chain");
        addItem(ModItems.threeWishesChain, "Three Wishes Chain");
        addItem(ModItems.totalEclipseChain, "Total Eclipse Chain");
        addItem(ModItems.treasureTroveChain, "Treasure Trove Chain");
        addItem(ModItems.trueLightsFlightChain, "True Lights Flight Chain");
        addItem(ModItems.twilightBlazeChain, "Twilight Blaze Chain");
        addItem(ModItems.twoBecomeOneChain, "Two Become One Chain");
        addItem(ModItems.ultimaWeaponBBSChain, "Ultima Weapon (BBS) Chain");
        addItem(ModItems.ultimaWeaponDDDChain, "Ultima Weapon (DDD) Chain");
        addItem(ModItems.ultimaWeaponKH1Chain, "Ultima Weapon (KH1) Chain");
        addItem(ModItems.ultimaWeaponKH2Chain, "Ultima Weapon (KH2) Chain");
        addItem(ModItems.ultimaWeaponKH3Chain, "Ultima Weapon (KH3) Chain");
        addItem(ModItems.umbrellaChain, "Umbrella Chain");
        addItem(ModItems.unboundChain, "Unbound Chain");
        addItem(ModItems.victoryLineChain, "Victory Line Chain");
        addItem(ModItems.voidGearChain, "Void Gear Chain");
        addItem(ModItems.voidGearRemnantChain, "Void Gear Remnant Chain");
        addItem(ModItems.waytotheDawnChain, "Way to the Dawn Chain");
        addItem(ModItems.waywardWindChain, "Wayward Wind Chain");
        addItem(ModItems.winnersProofChain, "Winners Proof Chain");
        addItem(ModItems.wishingLampChain, "Wishing Lamp Chain");
        addItem(ModItems.wishingStarChain, "Wishing Star Chain");
        addItem(ModItems.youngXehanortsKeybladeChain, "Young Xehanort's Keyblade Chain");
        addItem(ModItems.zeroOneChain, "Zero One Chain");

        //Organization Weapons
        addItem(ModItems.malice, "Malice");
        addItem(ModItems.sanction, "Sanction");
        addItem(ModItems.overlord, "Overlord");
        addItem(ModItems.veneration, "Veneration");
        addItem(ModItems.autocracy, "Autocracy");
        addItem(ModItems.conquest, "Conquest");
        addItem(ModItems.terminus, "Terminus");
        addItem(ModItems.judgement, "Judgement");
        addItem(ModItems.discipline, "Discipline");
        addItem(ModItems.aristocracy, "Aristocracy");
        addItem(ModItems.superiority, "Superiority");
        addItem(ModItems.aggression, "Aggression");
        addItem(ModItems.fury, "Fury");
        addItem(ModItems.despair, "Despair");
        addItem(ModItems.triumph, "Triumph");
        addItem(ModItems.ruination, "Ruination");
        addItem(ModItems.domination, "Domination");
        addItem(ModItems.annihilation, "Annihilation");
        addItem(ModItems.tyrant, "Tyrant");
        addItem(ModItems.magnificence, "Magnificence");
        addItem(ModItems.infinity, "Infinity");
        addItem(ModItems.interdiction, "Interdiction");
        addItem(ModItems.roundFan, "Round Fan");
        addItem(ModItems.absolute, "Absolute");

        addItem(ModItems.standalone, "Standalone");
        addItem(ModItems.killerbee, "Killerbee");
        addItem(ModItems.stingray, "Stingray");
        addItem(ModItems.counterweight, "Counterweight");
        addItem(ModItems.precision, "Precision");
        addItem(ModItems.dualHead, "Dual Head");
        addItem(ModItems.bahamut, "Bahamut");
        addItem(ModItems.gullwing, "Gullwing");
        addItem(ModItems.blueFrame, "Blue Frame");
        addItem(ModItems.starShell, "Star Shell");
        addItem(ModItems.sunrise, "Sunrise");
        addItem(ModItems.ignition, "Ignition");
        addItem(ModItems.armstrong, "Armstrong");
        addItem(ModItems.hardBoiledHeat, "Hard Boiled Heat");
        addItem(ModItems.diabloEye, "Diablo Eye");
        addItem(ModItems.doubleTap, "Double Tap");
        addItem(ModItems.stardust, "Stardust");
        addItem(ModItems.energyMuzzle, "Energy Muzzle");
        addItem(ModItems.crimeAndPunishment, "Crime And Punishment");
        addItem(ModItems.cupidsArrow, "Cupids Arrow");
        addItem(ModItems.finalWeapon, "Final Weapon");
        addItem(ModItems.sharpshooter, "Sharpshooter");
        addItem(ModItems.dryer, "Dryer");
        addItem(ModItems.trumpet, "Trumpet");

        addItem(ModItems.zephyr, "Zephyr");
        addItem(ModItems.moonglade, "Moonglade");
        addItem(ModItems.aer, "Aer");
        addItem(ModItems.nescience, "Nescience");
        addItem(ModItems.brume, "Brume");
        addItem(ModItems.asura, "Asura");
        addItem(ModItems.crux, "Crux");
        addItem(ModItems.paladin, "Paladin");
        addItem(ModItems.fellking, "Fellking");
        addItem(ModItems.nightcloud, "Nightcloud");
        addItem(ModItems.shimmer, "Shimmer");
        addItem(ModItems.vortex, "Vortex");
        addItem(ModItems.scission, "Scission");
        addItem(ModItems.heavenfall, "Heavenfall");
        addItem(ModItems.aether, "Aether");
        addItem(ModItems.mazzaroth, "Mazzaroth");
        addItem(ModItems.hegemon, "Hegemon");
        addItem(ModItems.foxfire, "Foxfire");
        addItem(ModItems.yaksha, "Yaksha");
        addItem(ModItems.cynosura, "Cynosura");
        addItem(ModItems.dragonreign, "Dragonreign");
        addItem(ModItems.lindworm, "Lindworm");
        addItem(ModItems.broom, "Broom");
        addItem(ModItems.wyvern, "Wyvern");

        addItem(ModItems.testerZero, "Tester Zero");
        addItem(ModItems.productOne, "Product One");
        addItem(ModItems.deepFreeze, "Deep Freeze");
        addItem(ModItems.cryoliteShield, "Cryolite Shield");
        addItem(ModItems.falseTheory, "False Theory");
        addItem(ModItems.glacier, "Glacier");
        addItem(ModItems.absoluteZero, "Absolute Zero");
        addItem(ModItems.gunz, "Gunz");
        addItem(ModItems.mindel, "Mindel");
        addItem(ModItems.snowslide, "Snowslide");
        addItem(ModItems.iceberg, "Iceberg");
        addItem(ModItems.inquisition, "Inquisition");
        addItem(ModItems.scrutiny, "Scrutiny");
        addItem(ModItems.empiricism, "Empiricism");
        addItem(ModItems.edification, "Edification");
        addItem(ModItems.contrivance, "Contrivance");
        addItem(ModItems.wurm, "Wurm");
        addItem(ModItems.subzero, "Subzero");
        addItem(ModItems.coldBlood, "Cold Blood");
        addItem(ModItems.diamondShield, "Diamond Shield");
        addItem(ModItems.aegis, "Aegis");
        addItem(ModItems.frozenPride, "Frozen Pride");
        addItem(ModItems.potLid, "Pot Lid");
        addItem(ModItems.snowman, "Snowman");

        addItem(ModItems.reticence, "Reticence");
        addItem(ModItems.goliath, "Goliath");
        addItem(ModItems.copperRed, "Copper Red");
        addItem(ModItems.daybreak, "Daybreak");
        addItem(ModItems.colossus, "Colossus");
        addItem(ModItems.ursaMajor, "Ursa Major");
        addItem(ModItems.megacosm, "Megacosm");
        addItem(ModItems.terrene, "Terrene");
        addItem(ModItems.fuligin, "Fuligin");
        addItem(ModItems.hardWinter, "Hard Winter");
        addItem(ModItems.firefly, "Firefly");
        addItem(ModItems.harbinger, "Harbinger");
        addItem(ModItems.redwood, "Redwood");
        addItem(ModItems.sequoia, "Sequoia");
        addItem(ModItems.ironBlack, "Iron Black");
        addItem(ModItems.earthshine, "Earthshine");
        addItem(ModItems.octiron, "Octiron");
        addItem(ModItems.hyperion, "Hyperion");
        addItem(ModItems.clarity, "Clarity");
        addItem(ModItems.oneThousandAndOneNights, "One Thousand And One Nights");
        addItem(ModItems.cardinalVirtue, "Cardinal Virtue");
        addItem(ModItems.skysplitter, "Skysplitter");
        addItem(ModItems.bleepBloopBop, "Bleep Bloop Bop");
        addItem(ModItems.monolith, "Monolith");

        addItem(ModItems.blackPrimer, "Black Primer");
        addItem(ModItems.whiteTome, "White Tome");
        addItem(ModItems.illicitResearch, "Illicit Research");
        addItem(ModItems.buriedSecrets, "Buried Secrets");
        addItem(ModItems.arcaneCompendium, "Arcane Compendium");
        addItem(ModItems.dissentersNotes, "Dissenters Notes");
        addItem(ModItems.nefariousCodex, "Nefarious Codex");
        addItem(ModItems.mysticAlbum, "Mystic Album");
        addItem(ModItems.cursedManual, "Cursed Manual");
        addItem(ModItems.tabooText, "Taboo Text");
        addItem(ModItems.eldritchEsoterica, "Eldritch Esoterica");
        addItem(ModItems.freakishBestiary, "Freakish Bestiary");
        addItem(ModItems.madmansVita, "Madmans Vita");
        addItem(ModItems.untitledWritings, "Untitled Writings");
        addItem(ModItems.abandonedDogma, "Abandoned Dogma");
        addItem(ModItems.atlasOfOmens, "Atlas Of Omens");
        addItem(ModItems.revoltingScrapbook, "Revolting Scrapbook");
        addItem(ModItems.lostHeterodoxy, "Lost Heterodoxy");
        addItem(ModItems.otherworldlyTales, "Otherworldly Tales");
        addItem(ModItems.indescribableLore, "Indescribable Lore");
        addItem(ModItems.radicalTreatise, "Radical Treatise");
        addItem(ModItems.bookOfRetribution, "Book Of Retribution");
        addItem(ModItems.midnightSnack, "Midnight Snack");
        addItem(ModItems.dearDiary, "Dear Diary");

        addItem(ModItems.newMoon, "New Moon");
        addItem(ModItems.werewolf, "Werewolf");
        addItem(ModItems.artemis, "Artemis");
        addItem(ModItems.luminary, "Luminary");
        addItem(ModItems.selene, "Selene");
        addItem(ModItems.moonrise, "Moonrise");
        addItem(ModItems.astrologia, "Astrologia");
        addItem(ModItems.crater, "Crater");
        addItem(ModItems.lunarPhase, "Lunar Phase");
        addItem(ModItems.crescent, "Crescent");
        addItem(ModItems.gibbous, "Gibbous");
        addItem(ModItems.berserker, "Berserker");
        addItem(ModItems.twilight, "Twilight");
        addItem(ModItems.queenOfTheNight, "Queen Of The Night");
        addItem(ModItems.balsamicMoon, "Balsamic Moon");
        addItem(ModItems.orbit, "Orbit");
        addItem(ModItems.lightYear, "Light Year");
        addItem(ModItems.kingOfTheNight, "King Of The Night");
        addItem(ModItems.moonset, "Moonset");
        addItem(ModItems.horoscope, "Horoscope");
        addItem(ModItems.dichotomy, "Dichotomy");
        addItem(ModItems.lunatic, "Lunatic");
        addItem(ModItems.justDesserts, "Just Desserts");
        addItem(ModItems.bunnymoon, "Bunnymoon");

        addItem(ModItems.ashes, "Ashes");
        addItem(ModItems.doldrums, "Doldrums");
        addItem(ModItems.delayedAction, "Delayed Action");
        addItem(ModItems.diveBombers, "Dive Bombers");
        addItem(ModItems.combustion, "Combustion");
        addItem(ModItems.moulinRouge, "Moulin Rouge");
        addItem(ModItems.blazeOfGlory, "Blaze Of Glory");
        addItem(ModItems.prometheus, "Prometheus");
        addItem(ModItems.ifrit, "Ifrit");
        addItem(ModItems.magmaOcean, "Magma Ocean");
        addItem(ModItems.volcanics, "Volcanics");
        addItem(ModItems.inferno, "Inferno");
        addItem(ModItems.sizzlingEdge, "Sizzling Edge");
        addItem(ModItems.corona, "Corona");
        addItem(ModItems.ferrisWheel, "Ferris Wheel");
        addItem(ModItems.burnout, "Burnout");
        addItem(ModItems.omegaTrinity, "Omega Trinity");
        addItem(ModItems.outbreak, "Outbreak");
        addItem(ModItems.doubleEdge, "Double Edge");
        addItem(ModItems.wildfire, "Wildfire");
        addItem(ModItems.prominence, "Prominence");
        addItem(ModItems.eternalFlames, "Eternal Flames");
        addItem(ModItems.pizzaCut, "Pizza Cut");
        addItem(ModItems.conformers, "Conformers");

        addItem(ModItems.basicModel, "Basic Model");
        addItem(ModItems.tuneUp, "Tune Up");
        addItem(ModItems.quartet, "Quartet");
        addItem(ModItems.quintet, "Quintet");
        addItem(ModItems.overture, "Overture");
        addItem(ModItems.oldHand, "Old Hand");
        addItem(ModItems.daCapo, "Da Capo");
        addItem(ModItems.powerChord, "Power Chord");
        addItem(ModItems.fermata, "Fermata");
        addItem(ModItems.interlude, "Interlude");
        addItem(ModItems.serenade, "Serenade");
        addItem(ModItems.songbird, "Songbird");
        addItem(ModItems.riseToFame, "Rise To Fame");
        addItem(ModItems.rockStar, "Rock Star");
        addItem(ModItems.eightFinger, "Eight Finger");
        addItem(ModItems.concerto, "Concerto");
        addItem(ModItems.harmonics, "Harmonics");
        addItem(ModItems.millionBucks, "Million Bucks");
        addItem(ModItems.fortissimo, "Fortissimo");
        addItem(ModItems.upToEleven, "Up To Eleven");
        addItem(ModItems.sanctuary, "Sanctuary");
        addItem(ModItems.arpeggio, "Arpeggio");
        addItem(ModItems.princeOfAwesome, "Prince Of Awesome");
        addItem(ModItems.afterSchool, "After School");

        addItem(ModItems.theFool, "The Fool");
        addItem(ModItems.theMagician, "The Magician");
        addItem(ModItems.theStar, "The Star");
        addItem(ModItems.theMoon, "The Moon");
        addItem(ModItems.justice, "Justice");
        addItem(ModItems.theHierophant, "The Hierophant");
        addItem(ModItems.theWorld, "The World");
        addItem(ModItems.temperance, "Temperance");
        addItem(ModItems.theHighPriestess, "The High Priestess");
        addItem(ModItems.theTower, "The Tower");
        addItem(ModItems.theHangedMan, "The Hanged Man");
        addItem(ModItems.death, "Death");
        addItem(ModItems.theHermit, "The Hermit");
        addItem(ModItems.strength, "Strength");
        addItem(ModItems.theLovers, "The Lovers");
        addItem(ModItems.theChariot, "The Chariot");
        addItem(ModItems.theSun, "The Sun");
        addItem(ModItems.theDevil, "The Devil");
        addItem(ModItems.theEmpress, "The Empress");
        addItem(ModItems.theEmperor, "The Emperor");
        addItem(ModItems.theJoker, "The Joker");
        addItem(ModItems.fairGame, "Fair Game");
        addItem(ModItems.finestFantasy13, "Finest Fantasy 13");
        addItem(ModItems.highRollersSecret, "High Rollers Secret");

        addItem(ModItems.fickleErica, "Fickle Erica");
        addItem(ModItems.jiltedAnemone, "Jilted Anemone");
        addItem(ModItems.proudAmaryllis, "Proud Amaryllis");
        addItem(ModItems.madSafflower, "Mad Safflower");
        addItem(ModItems.poorMelissa, "Poor Melissa");
        addItem(ModItems.tragicAllium, "Tragic Allium");
        addItem(ModItems.mournfulCineria, "Mournful Cineria");
        addItem(ModItems.pseudoSilene, "Pseudo Silene");
        addItem(ModItems.faithlessDigitalis, "Faithless Digitalis");
        addItem(ModItems.grimMuscari, "Grim Muscari");
        addItem(ModItems.docileVallota, "Docile Vallota");
        addItem(ModItems.quietBelladonna, "Quiet Belladonna");
        addItem(ModItems.partingIpheion, "Parting Ipheion");
        addItem(ModItems.loftyGerbera, "Lofty Gerbera");
        addItem(ModItems.gallantAchillea, "Gallant Achillea");
        addItem(ModItems.noblePeony, "Noble Peony");
        addItem(ModItems.fearsomeAnise, "Fearsome Anise");
        addItem(ModItems.vindictiveThistle, "Vindictive Thistle");
        addItem(ModItems.fairHelianthus, "Fair Helianthus");
        addItem(ModItems.solemnMagnolia, "Solemn Magnolia");
        addItem(ModItems.hallowedLotus, "Hallowed Lotus");
        addItem(ModItems.gracefulDahlia, "Graceful Dahlia");
        addItem(ModItems.stirringLadle, "Stirring Ladle");
        addItem(ModItems.daintyBellflowers, "Dainty Bellflowers");

        addItem(ModItems.trancheuse, "Trancheuse");
        addItem(ModItems.orage, "Orage");
        addItem(ModItems.tourbillon, "Tourbillon");
        addItem(ModItems.tempete, "Tempete");
        addItem(ModItems.carmin, "Carmin");
        addItem(ModItems.meteore, "Meteore");
        addItem(ModItems.etoile, "Etoile");
        addItem(ModItems.irregulier, "Irregulier");
        addItem(ModItems.dissonance, "Dissonance");
        addItem(ModItems.eruption, "Eruption");
        addItem(ModItems.soleilCouchant, "Soleil Couchant");
        addItem(ModItems.indigo, "Indigo");
        addItem(ModItems.vague, "Vague");
        addItem(ModItems.deluge, "Deluge");
        addItem(ModItems.rafale, "Rafale");
        addItem(ModItems.typhon, "Typhon");
        addItem(ModItems.extirpeur, "Extirpeur");
        addItem(ModItems.croixDuSud, "Croix Du Sud");
        addItem(ModItems.lumineuse, "Lumineuse");
        addItem(ModItems.clairDeLune, "Clair De Lune");
        addItem(ModItems.volDeNuit, "Vol De Nuit");
        addItem(ModItems.foudre, "Foudre");
        addItem(ModItems.demoiselle, "Demoiselle");
        addItem(ModItems.ampoule, "Ampoule");

        //Rings
        addItem(ModItems.abilityRing, "Ability Ring");
        addItem(ModItems.aquamarineRing, "Aquamarine Ring");
        addItem(ModItems.cosmicArts, "Cosmic Arts");
        addItem(ModItems.fullBloom, "Full Bloom");
        addItem(ModItems.fullBloomPlus, "Full Bloom+");
        addItem(ModItems.shadowArchive, "Shadow Archive");
        addItem(ModItems.shadowArchivePlus, "Shadow Archive+");
        addItem(ModItems.drawRing, "Draw Ring");
        addItem(ModItems.executiveRing, "Executive Ring");
        addItem(ModItems.starCharm, "Star Charm");
        addItem(ModItems.luckyRing, "Lucky Ring");
        addItem(ModItems.fireBangle, "Fire Bangle");

        //Spawn Eggs
        addItem(ModEntities.MOOGLE_EGG, "Spawn Moogle");
        addItem(ModEntities.SHADOW_EGG, "Spawn Shadow");
        addItem(ModEntities.MEGA_SHADOW_EGG, "Spawn MegaShadow");
        addItem(ModEntities.GIGA_SHADOW_EGG, "Spawn GigaShadow");
        addItem(ModEntities.DARKBALL_EGG, "Spawn Darkball");
        addItem(ModEntities.SHADOW_GLOB_EGG, "Spawn Shadow Glob");

        addItem(ModEntities.MINUTE_BOMB_EGG, "Spawn Minute Bomb");
        addItem(ModEntities.SKATER_BOMB_EGG, "Spawn Skater Bomb");
        addItem(ModEntities.STORM_BOMB_EGG, "Spawn Storm Bomb");
        addItem(ModEntities.DETONATOR_EGG, "Spawn Detonator");

        addItem(ModEntities.RED_NOCTURNE_EGG, "Spawn Red Nocturne");
        addItem(ModEntities.BLUE_RHAPSODY_EGG, "Spawn Blue Rhapsody");
        addItem(ModEntities.YELLOW_OPERA_EGG, "Spawn Yellow Opera");
        addItem(ModEntities.GREEN_REQUIEM_EGG, "Spawn Green Requiem");
        addItem(ModEntities.LARGE_BODY_EGG, "Spawn Large Body");
        addItem(ModEntities.DIRE_PLANT_EGG, "Spawn Dire Plant");

        addItem(ModEntities.NOBODY_CREEPER_EGG, "Spawn Creeper (Nobody)");
        addItem(ModEntities.DUSK_EGG, "Spawn Dusk");
        addItem(ModEntities.ASSASSIN_EGG, "Spawn Assassin");

        //Armour
        addItem(ModItems.organizationRobe_Helmet, "Organization Hood");
        addItem(ModItems.organizationRobe_Chestplate, "Organization Coat");
        addItem(ModItems.organizationRobe_Leggings, "Organization Leggings");
        addItem(ModItems.organizationRobe_Boots, "Organization Boots");

        addItem(ModItems.terra_Helmet, "Terra Helmet");
        addItem(ModItems.terra_Chestplate, "Terra Chestplate");
        addItem(ModItems.terra_Leggings, "Terra Leggings");
        addItem(ModItems.terra_Boots, "Terra Boots");

        addItem(ModItems.aqua_Helmet, "Aqua Helmet");
        addItem(ModItems.aqua_Chestplate, "Aqua Chestplate");
        addItem(ModItems.aqua_Leggings, "Aqua Leggings");
        addItem(ModItems.aqua_Boots, "Aqua Boots");

        addItem(ModItems.ventus_Helmet, "Ventus Helmet");
        addItem(ModItems.ventus_Chestplate, "Ventus Chestplate");
        addItem(ModItems.ventus_Leggings, "Ventus Leggings");
        addItem(ModItems.ventus_Boots, "Ventus Boots");

        addItem(ModItems.eraqus_Helmet, "Eraqus Helmet");
        addItem(ModItems.eraqus_Chestplate, "Eraqus Chestplate");
        addItem(ModItems.eraqus_Leggings, "Eraqus Leggings");
        addItem(ModItems.eraqus_Boots, "Eraqus Boots");

        addItem(ModItems.vanitas_Helmet, "Vanitas Helmet");
        addItem(ModItems.vanitas_Chestplate, "Vanitas Chestplate");
        addItem(ModItems.vanitas_Leggings, "Vanitas Leggings");
        addItem(ModItems.vanitas_Boots, "Vanitas Boots");

        addItem(ModItems.nightmareVentus_Helmet, "Nightmare Ventus Helmet");
        addItem(ModItems.nightmareVentus_Chestplate, "Nightmare Ventus Chestplate");
        addItem(ModItems.nightmareVentus_Leggings, "Nightmare Ventus Leggings");
        addItem(ModItems.nightmareVentus_Boots, "Nightmare Ventus Boots");

        addItem(ModItems.antiCoat_Helmet, "AntiCoat Hood");
        addItem(ModItems.antiCoat_Chestplate, "AntiCoat Coat");
        addItem(ModItems.antiCoat_Leggings, "AntiCoat Leggings");
        addItem(ModItems.antiCoat_Boots, "AntiCoat Boots");

        addItem(ModItems.xemnas_Helmet, "Xemnas Hood");
        addItem(ModItems.xemnas_Chestplate, "Xemnas Coat");
        addItem(ModItems.xemnas_Leggings, "Xemnas Leggings");
        addItem(ModItems.xemnas_Boots, "Xemnas Boots");

        addItem(ModItems.aced_Helmet, "Aced Hood");
        addItem(ModItems.aced_Chestplate, "Aced Coat");
        addItem(ModItems.aced_Leggings, "Aced Leggings");
        addItem(ModItems.aced_Boots, "Aced Boots");

        addItem(ModItems.ava_Helmet, "Ava Hood");
        addItem(ModItems.ava_Chestplate, "Ava Coat");
        addItem(ModItems.ava_Leggings, "Ava Leggings");
        addItem(ModItems.ava_Boots, "Ava Boots");

        addItem(ModItems.gula_Helmet, "Gula Hood");
        addItem(ModItems.gula_Chestplate, "Gula Coat");
        addItem(ModItems.gula_Leggings, "Gula Leggings");
        addItem(ModItems.gula_Boots, "Gula Boots");

        addItem(ModItems.invi_Helmet, "Invi Hood");
        addItem(ModItems.invi_Chestplate, "Invi Coat");
        addItem(ModItems.invi_Leggings, "Invi Leggings");
        addItem(ModItems.invi_Boots, "Invi Boots");

        addItem(ModItems.ira_Helmet, "Ira Hood");
        addItem(ModItems.ira_Chestplate, "Ira Coat");
        addItem(ModItems.ira_Leggings, "Ira Leggings");
        addItem(ModItems.ira_Boots, "Ira Boots");

        //Discs
        add("disc.duration.desc", "Duration");
        add("disc.durationunits.desc", "(mins:secs)");
        addMusicDisc(ModItems.disc_Birth_by_Sleep_A_Link_to_the_Future, "Yoko Shimomura & Kaoru Wada - Birth by Sleep -A Link to the Future-");
        addMusicDisc(ModItems.disc_Darkness_of_the_Unknown, "Yoko Shimomura - Darkness of the Unknown");
        addMusicDisc(ModItems.disc_Dearly_Beloved_Symphony_Version, "Yoko Shimomura, Jonne Valtonen & Roger Wanamo - Dearly Beloved -Symphony Version-");
        addMusicDisc(ModItems.disc_Dream_Drop_Distance_The_Next_Awakening, "Yoko Shimomura & Kaoru Wada - Dream Drop Distance -The Next Awakening-");
        addMusicDisc(ModItems.disc_Hikari_KINGDOM_Instrumental_Version, "Yoko Shimomura & Kaoru Wada - Hikari -KINGDOM Instrumental Version-");
        addMusicDisc(ModItems.disc_L_Oscurita_Dell_Ignoto, "Yoko Shimomura - L'Oscurita Dell'Ignoto");
        addMusicDisc(ModItems.disc_Musique_pour_la_tristesse_de_Xion, "Yoko Shimomura - Musique pour la tristesse de Xion");
        addMusicDisc(ModItems.disc_No_More_Bugs_Bug_Version, "Yoko Shimomura & Hirosato Noda - No More Bugs!! -Bug Version-");
        addMusicDisc(ModItems.disc_Organization_XIII, "Yoko Shimomura - Organization XIII");
        addMusicDisc(ModItems.disc_Sanctuary, "Hikaru Utada - Sanctuary");
        addMusicDisc(ModItems.disc_Simple_And_Clean_PLANITb_Remix, "Hikaru Utada - Simple And Clean -PLANITb Remix-");
        addMusicDisc(ModItems.disc_Sinister_Sundown, "Yoko Shimomura - Sinister Sundown");
        addMusicDisc(ModItems.disc_The_13th_Anthology, "Yoko Shimomura - The 13th Anthology");

        //Command Menu Items
        addItem(ModItems.potion, "Potion");
        addItem(ModItems.hiPotion, "Hi-Potion");
        addItem(ModItems.megaPotion, "Mega-Potion");
        addItem(ModItems.ether, "Ether");
        addItem(ModItems.hiEther, "Hi-Ether");
        addItem(ModItems.megaEther, "Mega-Ether");
        addItem(ModItems.elixir, "Elixir");
        addItem(ModItems.megaLixir, "Megalixir");
        addItem(ModItems.driveRecovery, "Drive Recovery");
        addItem(ModItems.hiDriveRecovery, "High Drive Recovery");
        addItem(ModItems.refocuser, "Refocuser");
        addItem(ModItems.hiRefocuser, "Hi-Refocuser");
        addItem(ModItems.apBoost, "AP Boost");
        addItem(ModItems.powerBoost, "Power Boost");
        addItem(ModItems.magicBoost, "Magic Boost");
        addItem(ModItems.defenseBoost, "Defense Boost");

        add("potion.desc.hp", "\u00A7aHP\u00A7r");
        add("potion.desc.mp", "\u00A79MP\u00A7r");
        add("potion.desc.hpmp", "\u00A7aHP\u00A7r and \u00A79MP\u00A7r");
        add("potion.desc.drive", "\u00A7eDrive\u00A7r");
        add("potion.desc.focus", "\u00A76Focus\u00A7r");
        add("potion.desc.beginning", "Will restore %s%s %s ");
        add("potion.desc.toall", "to all your party members in range");
        add("potion.desc.toone", "to the chosen party member");

        //Orbs
        addItem(ModItems.fireSpell, "Fire Spell");
        addItem(ModItems.blizzardSpell, "Blizzard Spell");
        addItem(ModItems.waterSpell, "Water Spell");
        addItem(ModItems.thunderSpell, "Thunder Spell");
        addItem(ModItems.cureSpell, "Cure Spell");
        addItem(ModItems.aeroSpell, "Aero Spell");
        addItem(ModItems.magnetSpell, "Magnet Spell");
        addItem(ModItems.reflectSpell, "Reflect Spell");
        addItem(ModItems.gravitySpell, "Gravity Spell");
        addItem(ModItems.stopSpell, "Stop Spell");

        addItem(ModItems.valorOrb, "Valor Form Orb");
        addItem(ModItems.wisdomOrb, "Wisdom Form Orb");
        addItem(ModItems.limitOrb, "Limit Form Orb");
        addItem(ModItems.masterOrb, "Master Form Orb");
        addItem(ModItems.finalOrb, "Final Form Orb");

        //Other
        addItem(ModItems.recipe, "Recipe");
        addItem(ModItems.recipeD, "Tier D Recipe");
        addItem(ModItems.recipeC, "Tier C Recipe");
        addItem(ModItems.recipeB, "Tier B Recipe");
        addItem(ModItems.recipeA, "Tier A Recipe");
        addItem(ModItems.recipeS, "Tier S Recipe");
        addItem(ModItems.recipeSS, "Tier SS Recipe");
        addItem(ModItems.recipeSSS, "Tier SSS Recipe");
        addItem(ModItems.iceCream, "Sea Salt Ice Cream");
        addItem(ModItems.synthesisBag, "Synthesis Bag");
        addItem(ModItems.proofOfHeart, "Proof of Heart");

        /**Entities**/
        addEntityType(ModEntities.TYPE_BLAST_BLOX,"Primed Blast Blox");
        addEntityType(ModEntities.TYPE_PAIR_BLOX, "Pair Blox");

        addEntityType(ModEntities.TYPE_BLIZZARD, "Blizzard");
        addEntityType(ModEntities.TYPE_FIRE, "Fire");
        addEntityType(ModEntities.TYPE_THUNDER, "Thunder");
        addEntityType(ModEntities.TYPE_THUNDERBOLT, "Thunderbolt");
        addEntityType(ModEntities.TYPE_GRAVITY, "Gravity");
        addEntityType(ModEntities.TYPE_MAGNET, "Magnet");
        addEntityType(ModEntities.TYPE_WATER, "Water");
        addEntityType(ModEntities.TYPE_CHAKRAM, "Chakram");
        addEntityType(ModEntities.TYPE_LANCE, "Lance");
        addEntityType(ModEntities.TYPE_ORG_PORTAL, "Organization Portal");
        addEntityType(ModEntities.TYPE_HPORB, "HP Orb");
        addEntityType(ModEntities.TYPE_MPORB, "MP Orb");
        addEntityType(ModEntities.TYPE_DRIVEORB, "DP Orb");
        addEntityType(ModEntities.TYPE_MUNNY, "Munny");

        addEntityType(ModEntities.TYPE_MOOGLE, "Moogle");
        addEntityType(ModEntities.TYPE_SHADOW, "Shadow");
        addEntityType(ModEntities.TYPE_MEGA_SHADOW, "MegaShadow");
        addEntityType(ModEntities.TYPE_GIGA_SHADOW, "GigaShadow");
        addEntityType(ModEntities.TYPE_DARKBALL, "Darkball");
        addEntityType(ModEntities.TYPE_SHADOW_GLOB, "Shadow Glob");

        addEntityType(ModEntities.TYPE_MINUTE_BOMB, "Minute Bomb");
        addEntityType(ModEntities.TYPE_SKATER_BOMB, "Skater Bomb");
        addEntityType(ModEntities.TYPE_STORM_BOMB, "Storm Bomb");
        addEntityType(ModEntities.TYPE_DETONATOR, "Detonator");

        addEntityType(ModEntities.TYPE_RED_NOCTURNE, "Red Nocturne");
        addEntityType(ModEntities.TYPE_BLUE_RHAPSODY, "Blue Rhapsody");
        addEntityType(ModEntities.TYPE_YELLOW_OPERA, "Yellow Opera");
        addEntityType(ModEntities.TYPE_GREEN_REQUIEM, "Green Requiem");
        addEntityType(ModEntities.TYPE_LARGE_BODY, "Large Body");
        addEntityType(ModEntities.TYPE_DIRE_PLANT, "Dire Plant");

        addEntityType(ModEntities.TYPE_NOBODY_CREEPER, "Creeper (Nobody)");
        addEntityType(ModEntities.TYPE_DUSK, "Dusk");
        addEntityType(ModEntities.TYPE_ASSASSIN, "Assassin");
        addEntityType(ModEntities.TYPE_MARLUXIA, "Marluxia");

        /**Biomes**/
        add("biome.kingdomkeys.dive_to_the_heart", "Dive to the Heart");

        /**JEI**/
        add("jei.category.kingdomkeys.synthesis", "Item Synthesis");
        add("jei.category.kingdomkeys.keyblade_summon", "Keyblade Summoning");
        add("jei.category.kingdomkeys.synthesis.locked", "Recipe not unlocked");
        add("jei.category.kingdomkeys.synthesis.unlocked", "Recipe unlocked");
        add("jei.category.kingdomkeys.keyblade_summon.info", "View info for how-to");
        add("jei.info.kingdomkeys.moogle_projector", "Obtained from Moogles when killed by an Anvil. Used for Item Synthesis and upgrading Keyblades via the Keyblade Forge and depositing materials used for Synthesis. Moogles also serve the same purpose as this.");
        add("jei.info.kingdomkeys.organization_weapons", "As an Organization member you can unlock weapons within the equipment menu by spending hearts gained from kills, you will earn 2x hearts from using a weapon from your chosen member. Summon the weapons using the summon key.");
        add("jei.info.kingdomkeys.organization_robes", "Wear the full Organization set to join and select a member to start with, no matter who you choose you can unlock every member's weapons however it requires unlocking the adjacent member's weapon first.");
        add("jei.info.kingdomkeys.proof_of_heart", "Obtained from defeating the Ender Dragon, use this to leave the Organization.");
        add("jei.info.kingdomkeys.keychains", "Keychains can be used to summon the associated Keyblade by equipping the keychain through the Kingdom Keys menu, use the summon key to summon the Keyblade.");
        add("jei.info.kingdomkeys.recipes", "Dropped from mobs and found in Moogle house chests in villages. Use these to unlock recipes for Item Synthesis.");
        add("jei.info.kingdomkeys.ghost_blox", "Apply a redstone signal to toggle the visibility of the Ghost Blox and all adjacent Ghost Blox, while in the invisible state they have no collision.");
        add("jei.info.kingdomkeys.danger_blox", "Similar to a Cactus but deals more damage, causes damage on contact and also when hit. Unlike a Cactus it has no placement limits or growth. Wear boots to avoid damage while walking on them.");
        add("jei.info.kingdomkeys.blast_blox", "TNT-like with more destructive power, triggers on contact with anything but a feather in your hand.");
        add("jei.info.kingdomkeys.bounce_blox", "Entities that step on this block will bounce, sneak to land on the block without bouncing.");
        add("jei.info.kingdomkeys.magnet_blox", "Pulls or pushes entities in the direction its facing. Apply a redstone signal to activate, right click to change the range and sneak-right click with your fist to toggle attract and repel mode.");
        add("jei.info.kingdomkeys.spell_orb", "Use to unlock the specified spell. Once unlocked magic can be used with the Command Menu as long as you have enough MP, until level 5 you will have 0 MP. Dropped from breaking Prize Blox.");
        add("jei.info.kingdomkeys.valor_orb", "Use to unlock Valor Form. Valor has a 2nd Keyblade slot. Activation requires 3 bars of the Drive Gauge. Dropped from breaking Rare Prize Blox.");
        add("jei.info.kingdomkeys.wisdom_orb", "Use to unlock Wisdom Form. Activation requires 3 bars of the Drive Gauge. Dropped from breaking Rare Prize Blox.");
        add("jei.info.kingdomkeys.limit_orb", "Use to unlock Limit Form. Activation requires 4 bars of the Drive Gauge. Dropped from breaking Rare Prize Blox.");
        add("jei.info.kingdomkeys.master_orb", "Use to unlock Master Form. Master has a 2nd Keyblade slot. Activation requires 4 bars of the Drive Gauge. Dropped from breaking Rare Prize Blox.");
        add("jei.info.kingdomkeys.final_orb", "Use to unlock Final Form. Final has a 2nd Keyblade slot. Activation requires 5 bars of the Drive Gauge. Dropped from breaking Rare Prize Blox.");

        /**Others**/
        //Messages
        add("message.magnet_blox.attract", "Attract Mode");
        add("message.magnet_blox.repel", "Repel Mode");
        add("message.magnet_blox.range", "Range is now: %s");
        add("message.form_unlocked", "Unlocked %s form");
        add("message.chest.lock", "Use a keyblade to lock this chest");
        add("message.chest.can_be_locked", "Can be locked with a keyblade");
        add("message.chest.locked", "This chest is locked");
        add("message.chest.keyblade_set", "Your keyblade has been set to unlock this chest");
        add("message.chest.unlocked", "Chest has been unlocked");
        add("message.kingdomkeys.gui_toggle", "GUI display set to: %s");

        //Station of Awakening
        add("soa.menu.1", "Before you can open the menu.");
        add("soa.menu.2", "You must make a choice.");
        add("soa.menu.ok", "Ok.");
        add("soa.menu.cancel", "Cancel.");
        add("soa.warrior.1", "The power of the warrior.");
        add("soa.warrior.2", "Invincible courage.");
        add("soa.warrior.3", "A sword of terrible destruction.");
        add("soa.guardian.1", "The power of the guardian.");
        add("soa.guardian.2", "Kindness to aid friends.");
        add("soa.guardian.3", "A shield to repell all.");
        add("soa.mystic.1", "The power of the mystic.");
        add("soa.mystic.2", "Inner strength.");
        add("soa.mystic.3", "A staff of wonder and ruin.");
        add("soa.choice.confirm", "Is this the power you seek?");
        add("soa.sacrifice.confirm", "You give up this power?");
        add("soa.ok", "Yes.");
        add("soa.cancel", "No.");
        add("soa.confirm.cancel", "Maybe not.");
        add("soa.title",  "Station of Awakening");
        add("soa.subtitle", "Dive to the Heart");
        add("soa.choice.intro.1", "Power sleeps within you.");
        add("soa.choice.intro.2", "If you give it form...");
        add("soa.choice.intro.3", "It will give you strength.");
        add("soa.choice.intro.4", "Choose well.");
        add("soa.sacrifice.intro.1", "Your path is set.");
        add("soa.sacrifice.intro.2", "Now, what will you give up in exchange?");
        add("soa.reset.intro.1", "Choose carefully.");
        add("soa.reset.intro.2", "What form will your power take?");
        add("soa.confirm.1", "You've chosen the power");
        add("soa.confirm.warrior", "of the Warrior.");
        add("soa.confirm.guardian", "of the Guardian.");
        add("soa.confirm.mystic", "of the Mystic.");
        add("soa.confirm.3", "You've given the power");
        add("soa.confirm.5", "Is this the form you choose?");

        //Controls
        add("key.categories.kingdomkeys", "Kingdom Keys");
        add(InputHandler.Keybinds.ACTION, "Action key");
        add(InputHandler.Keybinds.BACK, "Command menu back");
        add(InputHandler.Keybinds.ENTER, "Command menu enter");
        add(InputHandler.Keybinds.SCROLL_ACTIVATOR, "Command menu mouse controller key");
        add(InputHandler.Keybinds.SCROLL_UP, "Command menu up");
        add(InputHandler.Keybinds.SCROLL_DOWN, "Command menu down");
        add(InputHandler.Keybinds.SUMMON_KEYBLADE, "Summon weapon");
        add(InputHandler.Keybinds.REACTION_COMMAND, "Reaction Command");
        add(InputHandler.Keybinds.LOCK_ON, "Lock-on");
        add(InputHandler.Keybinds.OPENMENU, "Open Menu");
        add(InputHandler.Keybinds.SHOW_GUI, "Toggle HUD");

        //Groups
        add("itemGroup.kingdomkeys_misc", "Kingdom Keys: Misc");
        add("itemGroup.kingdomkeys_keyblades", "Kingdom Keys: Keyblades");
        add("itemGroup.kingdomkeys_org_weapons", "Kingdom Keys: Organization Weapons");

        //Death Messages
        add("keybladedamage.death", "%s was slain by %s");

    }
}
