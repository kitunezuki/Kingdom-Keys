package online.kingdomkeys.kingdomkeys.handler;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.InputEvent.MouseScrollEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import online.kingdomkeys.kingdomkeys.capability.IPlayerCapabilities;
import online.kingdomkeys.kingdomkeys.capability.IWorldCapabilities;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.client.gui.CommandMenuGui;
import online.kingdomkeys.kingdomkeys.client.gui.GuiHelper;
import online.kingdomkeys.kingdomkeys.client.sound.ModSounds;
import online.kingdomkeys.kingdomkeys.driveform.DriveForm;
import online.kingdomkeys.kingdomkeys.driveform.ModDriveForms;
import online.kingdomkeys.kingdomkeys.item.KeybladeItem;
import online.kingdomkeys.kingdomkeys.item.KeychainItem;
import online.kingdomkeys.kingdomkeys.lib.Constants;
import online.kingdomkeys.kingdomkeys.lib.Party;
import online.kingdomkeys.kingdomkeys.lib.Party.Member;
import online.kingdomkeys.kingdomkeys.lib.PortalData;
import online.kingdomkeys.kingdomkeys.lib.Strings;
import online.kingdomkeys.kingdomkeys.lib.Utils;
import online.kingdomkeys.kingdomkeys.magic.ModMagics;
import online.kingdomkeys.kingdomkeys.network.PacketHandler;
import online.kingdomkeys.kingdomkeys.network.cts.CSSetDriveFormPacket;
import online.kingdomkeys.kingdomkeys.network.cts.CSSpawnOrgPortalPacket;
import online.kingdomkeys.kingdomkeys.network.cts.CSSummonKeyblade;
import online.kingdomkeys.kingdomkeys.network.cts.CSSyncAllClientDataPacket;
import online.kingdomkeys.kingdomkeys.network.cts.CSUseMagicPacket;

public class InputHandler {

    List<PortalData> portalCommands;
    Map<String, int[]> driveFormsMap;
    List<String> magicsList;
    List<Member> targetsList;

    public static LivingEntity lockOn = null;

    public boolean antiFormCheck() {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        IPlayerCapabilities playerData = ModCapabilities.getPlayer(player);
        World world = mc.world;
        double random = Math.random();
        int ap = playerData.getAntiPoints();
        //System.out.println("Antipoints: "+ap);
        int prob = 0;
        if (ap > 0 && ap <= 4)
            prob = 0;
        else if (ap > 4 && ap <= 9)
            prob = 10;
        else if (ap >= 10)
            prob = 25;

        if (random * 100 < prob) {
            PacketHandler.sendToServer(new CSSetDriveFormPacket(Strings.Form_Anti));
    		player.world.playSound(player, player.getPosition(), ModSounds.antidrive.get(), SoundCategory.MASTER, 1.0f, 1.0f);

            CommandMenuGui.selected = CommandMenuGui.ATTACK;
            CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
            world.playSound(player, player.getPosition(), ModSounds.menu_select.get(), SoundCategory.MASTER, 1.0f, 1.0f);
            return true;
        } else
            return false;
    }

    public void commandUp() {
        Minecraft mc = Minecraft.getInstance();
        mc.world.playSound(mc.player, mc.player.getPosition(), ModSounds.menu_move.get(), SoundCategory.MASTER, 1.0f, 1.0f);

        loadLists();

        // Mainmenu
        if (CommandMenuGui.submenu == CommandMenuGui.SUB_MAIN) {
            if (CommandMenuGui.selected == CommandMenuGui.ATTACK)
                CommandMenuGui.selected = CommandMenuGui.DRIVE;
            else
                CommandMenuGui.selected++;
        }
        // InsideMagic
        else if (CommandMenuGui.submenu == CommandMenuGui.SUB_MAGIC) {
            if (CommandMenuGui.magicSelected > 0) {
                CommandMenuGui.magicSelected--;
                CommandMenuGui.submenu = CommandMenuGui.SUB_MAGIC;
            } else if (CommandMenuGui.magicSelected <= 1)
                CommandMenuGui.magicSelected = this.magicsList.size() - 1;
        }
        // InsideItems
        /*else if (CommandMenuGui.submenu == CommandMenuGui.SUB_ITEMS) {
            if (CommandMenuGui.potionselected > 0) {
                CommandMenuGui.potionselected--;
                CommandMenuGui.submenu = CommandMenuGui.SUB_ITEMS;
            } else if (CommandMenuGui.potionselected <= 1) {
                CommandMenuGui.potionselected = this.itemsCommands.size() - 1;
            }
        }*/
        // InsideDrive
        else if (CommandMenuGui.submenu == CommandMenuGui.SUB_DRIVE) {
            if (CommandMenuGui.driveSelected > 0) {
                CommandMenuGui.driveSelected--;
                CommandMenuGui.submenu = CommandMenuGui.SUB_DRIVE;
            } else if (CommandMenuGui.driveSelected <= 1) {
                CommandMenuGui.driveSelected = this.driveFormsMap.size() - 1;
            }
        }
        // InsidePortal
        else if (CommandMenuGui.submenu == CommandMenuGui.SUB_PORTALS) {
            if (CommandMenuGui.portalSelected > 0) {
                CommandMenuGui.portalSelected--;
                CommandMenuGui.submenu = CommandMenuGui.SUB_PORTALS;
            } else if (CommandMenuGui.portalSelected <= 1) {
                CommandMenuGui.portalSelected = this.portalCommands.size() - 1;
            }
        }
        // InsideAttacks
        /*else if (CommandMenuGui.submenu == CommandMenuGui.SUB_ATTACKS) {
            if (CommandMenuGui.attackSelected > 0) {
                CommandMenuGui.attackSelected--;
                CommandMenuGui.submenu = CommandMenuGui.SUB_ATTACKS;
            } else if (CommandMenuGui.attackSelected <= 1) {
                CommandMenuGui.attackSelected = this.attackCommands.size() - 1;
            }
        }*/
        //InsideTargetSelector
        else if (CommandMenuGui.submenu == CommandMenuGui.SUB_TARGET) {
            if (CommandMenuGui.targetSelected > 0) {
                CommandMenuGui.targetSelected--;
                CommandMenuGui.submenu = CommandMenuGui.SUB_TARGET;
            } else if (CommandMenuGui.attackSelected <= 1) {
                CommandMenuGui.targetSelected = this.targetsList.size() - 1;
            }
        }
    }

    public void commandDown() {
        Minecraft mc = Minecraft.getInstance();
        mc.world.playSound(mc.player, mc.player.getPosition(), ModSounds.menu_move.get(), SoundCategory.MASTER, 1.0f, 1.0f);
        loadLists();

        // Mainmenu
        if (CommandMenuGui.submenu == CommandMenuGui.SUB_MAIN) {
            if (CommandMenuGui.selected == CommandMenuGui.DRIVE)
                CommandMenuGui.selected = CommandMenuGui.ATTACK;
            else
                CommandMenuGui.selected--;
        }
        // InsideMagic
        else if (CommandMenuGui.submenu == CommandMenuGui.SUB_MAGIC) {
            if (CommandMenuGui.magicSelected < this.magicsList.size() - 1) {
                CommandMenuGui.magicSelected++;
                CommandMenuGui.submenu = CommandMenuGui.SUB_MAGIC;
            } else if (CommandMenuGui.magicSelected >= this.magicsList.size() - 1)
                CommandMenuGui.magicSelected = 0;
        }
        // InsideItems
        /*else if (CommandMenuGui.submenu == CommandMenuGui.SUB_ITEMS) {
            if (CommandMenuGui.potionselected < this.itemsCommands.size() - 1) {
                CommandMenuGui.potionselected++;
                CommandMenuGui.submenu = CommandMenuGui.SUB_ITEMS;
            } else {
                if (CommandMenuGui.potionselected >= this.itemsCommands.size() - 1)
                    CommandMenuGui.potionselected = 0;
            }
        }*/
        // InsideDrive
        else if (CommandMenuGui.submenu == CommandMenuGui.SUB_DRIVE) {
            if (CommandMenuGui.driveSelected < this.driveFormsMap.size() - 1) {
                CommandMenuGui.driveSelected++;
                CommandMenuGui.submenu = CommandMenuGui.SUB_DRIVE;
            } else {
                if (CommandMenuGui.driveSelected >= this.driveFormsMap.size() - 1)
                    CommandMenuGui.driveSelected = 0;
            }
        }
        // InsidePortal
        else if (CommandMenuGui.submenu == CommandMenuGui.SUB_PORTALS) {
            if (CommandMenuGui.portalSelected < this.portalCommands.size() - 1) {
                CommandMenuGui.portalSelected++;
                CommandMenuGui.submenu = CommandMenuGui.SUB_PORTALS;
            } else {
                if (CommandMenuGui.portalSelected >= this.portalCommands.size() - 1)
                    CommandMenuGui.portalSelected = 0;
            }
        }
        // InsideAttack
        /*else if (CommandMenuGui.submenu == CommandMenuGui.SUB_ATTACKS) {
            if (CommandMenuGui.attackSelected < this.attackCommands.size() - 1) {
                CommandMenuGui.attackSelected++;
                CommandMenuGui.submenu = CommandMenuGui.SUB_ATTACKS;
            } else {
                if (CommandMenuGui.attackSelected >= this.attackCommands.size() - 1)
                    CommandMenuGui.attackSelected = 0;
            }
        }*/
        //InsideTargetSelector
        else if (CommandMenuGui.submenu == CommandMenuGui.SUB_TARGET) {
            if (CommandMenuGui.targetSelected < this.targetsList.size() - 1) {
                CommandMenuGui.targetSelected++;
                CommandMenuGui.submenu = CommandMenuGui.SUB_TARGET;
            } else {
                if (CommandMenuGui.targetSelected >= this.targetsList.size() - 1)
                    CommandMenuGui.targetSelected = 0;
            }
        }
    }

    public void commandEnter() {
    	Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        World world = mc.world;
        loadLists();

        //ExtendedWorldData worldData = ExtendedWorldData.get(world);
        IWorldCapabilities worldData = ModCapabilities.getWorld(world);
        IPlayerCapabilities playerData = ModCapabilities.getPlayer(player);
        switch (CommandMenuGui.selected) {
            case CommandMenuGui.ATTACK: //Accessing ATTACK / PORTAL submenu
                System.out.println("attack");
                //if (player.getCapability(ModCapabilities.ORGANIZATION_XIII, null).getMember() != Utils.OrgMember.NONE) {
                    // Submenu of the portals
                    if (CommandMenuGui.submenu == CommandMenuGui.SUB_MAIN) {
                        if (!this.portalCommands.isEmpty() && !playerData.getRecharge()) {
                            CommandMenuGui.submenu = CommandMenuGui.SUB_PORTALS;
                            CommandMenuGui.portalSelected = 0;
                            world.playSound(player, player.getPosition(), ModSounds.menu_in.get(), SoundCategory.MASTER, 1.0f, 1.0f);
                        } else {
                            CommandMenuGui.selected = CommandMenuGui.ATTACK;
                            world.playSound(player, player.getPosition(), ModSounds.error.get(), SoundCategory.MASTER, 1.0f, 1.0f);
                        }
                        return;
                    }
              /*  } else {
                    // Attacks Submenu
                    if (CommandMenuGui.submenu == CommandMenuGui.SUB_MAIN) {
                        if (!this.attackCommands.isEmpty() && !STATS.getRecharge()) {
                            CommandMenuGui.submenu = CommandMenuGui.SUB_ATTACKS;
                            CommandMenuGui.attackSelected = 0;
                            world.playSound(player, player.getPosition(), ModSounds.select, SoundCategory.MASTER, 1.0f, 1.0f);
                        } else {
                            CommandMenuGui.selected = CommandMenuGui.ATTACK;
                            world.playSound(player, player.getPosition(), ModSounds.error, SoundCategory.MASTER, 1.0f, 1.0f);
                        }
                        return;
                    }
                }

                if (player.getCapability(ModCapabilities.DRIVE_STATE, null).getActiveDriveName().equals(Strings.Form_Wisdom)) {
                    PacketDispatcher.sendToServer(new MagicWisdomShot());
                }*/
                break;
            case CommandMenuGui.MAGIC: //Accessing MAGIC submenu
                if (CommandMenuGui.submenu == CommandMenuGui.SUB_MAIN) {
                    if (!playerData.getRecharge() && (!this.magicsList.isEmpty() && (!playerData.getActiveDriveForm().equals("valor") && !playerData.getActiveDriveForm().equals("anti")))) {
                        CommandMenuGui.magicSelected = 0;
                        CommandMenuGui.submenu = CommandMenuGui.SUB_MAGIC;
                        mc.world.playSound(mc.player, mc.player.getPosition(), ModSounds.menu_in.get(), SoundCategory.MASTER, 1.0f, 1.0f);
                        return;
                    } else {
                        CommandMenuGui.selected = CommandMenuGui.ATTACK;
                        world.playSound(player, player.getPosition(), ModSounds.error.get(), SoundCategory.MASTER, 1.0f, 1.0f);
                    }
                }
                break;

            case CommandMenuGui.ITEMS: //Accessing ITEMS submenu
                if (CommandMenuGui.submenu == CommandMenuGui.SUB_MAIN) {
                    System.out.println("items");
                    /*if (!this.itemsCommands.isEmpty()) {
                        CommandMenuGui.submenu = CommandMenuGui.SUB_ITEMS;
                        CommandMenuGui.potionselected = 0;
                        world.playSound(player, player.getPosition(), ModSounds.select, SoundCategory.MASTER, 1.0f, 1.0f);
                    } else {
                        CommandMenuGui.selected = CommandMenuGui.ATTACK;
                        world.playSound(player, player.getPosition(), ModSounds.error, SoundCategory.MASTER, 1.0f, 1.0f);
                    }
                    return;*/
                }
                break;

            case CommandMenuGui.DRIVE: //Accessing DRIVE submenu
                if (CommandMenuGui.submenu == CommandMenuGui.SUB_MAIN) {
                	if(playerData.getActiveDriveForm().equals("")) {//DRIVE
                        System.out.println("drive submenu");
                        if (playerData.getActiveDriveForm().equals(Strings.Form_Anti)) {// && !player.getCapability(ModCapabilities.CHEAT_MODE, null).getCheatMode()) {//If is in antiform
                        	
                        } else { //If is in a drive form other than antiform
                        	if(!driveFormsMap.isEmpty()) {
                                CommandMenuGui.driveSelected = 0;
                                CommandMenuGui.submenu = CommandMenuGui.SUB_DRIVE;
                                mc.world.playSound(mc.player, mc.player.getPosition(), ModSounds.menu_in.get(), SoundCategory.MASTER, 1.0f, 1.0f);
                                return;
                        	}
                        }
                	} else {//REVERT
                		System.out.println("REVERT");
                		if(playerData.getActiveDriveForm().equals(Strings.Form_Anti)) {
                			player.world.playSound(player, player.getPosition(), ModSounds.error.get(), SoundCategory.MASTER, 1.0f, 1.0f);
                		} else {
		                	PacketHandler.sendToServer(new CSSetDriveFormPacket(""));
		            		player.world.playSound(player, player.getPosition(), ModSounds.unsummon.get(), SoundCategory.MASTER, 1.0f, 1.0f);
                		}
                	}

                   /* if (player.getCapability(ModCapabilities.ORGANIZATION_XIII, null).getMember() != Utils.OrgMember.NONE) {
                        // TODO Use Limit
                        player.sendMessage(new TextComponentString("Limits are not available yet"));
                        CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
                        CommandMenuGui.selected = CommandMenuGui.ATTACK;
                        world.playSound(player, player.getPosition(), ModSounds.error, SoundCategory.MASTER, 1.0f, 1.0f);
                    } else {
                        if (DRIVE.getInDrive()) {// Revert
                            if (DRIVE.getActiveDriveName().equals(Strings.Form_Anti) && !player.getCapability(ModCapabilities.CHEAT_MODE, null).getCheatMode()) {
                                CommandMenuGui.selected = CommandMenuGui.ATTACK;
                                world.playSound(player, player.getPosition(), ModSounds.error, SoundCategory.MASTER, 1.0f, 1.0f);
                                player.sendMessage(new TextComponentTranslation("Cannot revert while in Anti form"));
                            } else {
                                PacketDispatcher.sendToServer(new DriveFormPacket(DRIVE.getActiveDriveName(), true));
                                if (DriveFormRegistry.isDriveFormRegistered(DRIVE.getActiveDriveName()))
                                    DriveFormRegistry.get(DRIVE.getActiveDriveName()).endDrive(player);
                                CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
                                CommandMenuGui.selected = CommandMenuGui.ATTACK;
                                world.playSound(player, player.getPosition(), ModSounds.select, SoundCategory.MASTER, 1.0f, 1.0f);
                            }
                        } else if (this.driveCommands.isEmpty() || DRIVE.getDP() <= 0) {
                            world.playSound(player, player.getPosition(), ModSounds.error, SoundCategory.MASTER, 1.0f, 1.0f);
                            CommandMenuGui.selected = CommandMenuGui.ATTACK;
                        } else {
                            CommandMenuGui.driveselected = 0;
                            CommandMenuGui.submenu = CommandMenuGui.SUB_DRIVE;
                            world.playSound(player, player.getPosition(), ModSounds.select, SoundCategory.MASTER, 1.0f, 1.0f);
                            return;
                        }
                    }*/
                }
                break;
        }
        // Attacks Submenu
        if (CommandMenuGui.selected == CommandMenuGui.ATTACK && CommandMenuGui.submenu == CommandMenuGui.SUB_ATTACKS) {
            /*if (this.attackCommands.isEmpty()) {
            } else {
                // ModDriveForms.getDriveForm(player, world, (String)
                // this.driveCommands.get(CommandMenuGui.driveselected));
                if (!player.getCapability(ModCapabilities.PLAYER_STATS, null).getRecharge()) {
                    Ability ability = this.attackCommands.get((byte) CommandMenuGui.attackSelected);
                    // UseAbility
                    useAttack(player, ability);
                    CommandMenuGui.selected = CommandMenuGui.ATTACK;
                    CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
                    world.playSound(player, player.getPosition(), ModSounds.select, SoundCategory.MASTER, 1.0f, 1.0f);
                }
            }*/
        }

        // Portal Submenu
        if (CommandMenuGui.selected == CommandMenuGui.ATTACK && CommandMenuGui.submenu == CommandMenuGui.SUB_PORTALS) {
            if (this.portalCommands.isEmpty()) {
            } else {
                // ModDriveForms.getDriveForm(player, world, (String)
                // this.driveCommands.get(CommandMenuGui.driveselected));
                if (!ModCapabilities.getPlayer(player).getRecharge()) {
                    PortalData coords = this.portalCommands.get((byte) CommandMenuGui.portalSelected);
                    if (coords.getX() != 0 && coords.getY() != 0 && coords.getZ() != 0) { //If the portal is not default coords
                        summonPortal(player, coords);
                    } else {
                        player.sendMessage(new TranslationTextComponent(TextFormatting.RED + "You don't have any portal destination"));
                    }

                    CommandMenuGui.selected = CommandMenuGui.ATTACK;
                    CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
                    world.playSound(player, player.getPosition(), ModSounds.menu_in.get(), SoundCategory.MASTER, 1.0f, 1.0f);
                }
            }
        }

        // Target Selector Submenu
        if (CommandMenuGui.selected == CommandMenuGui.MAGIC && CommandMenuGui.submenu == CommandMenuGui.SUB_MAGIC) {
            if (this.magicsList.isEmpty()) {
            } else {/* if (!STATS.getRecharge() || Constants.getCost((String) this.magicsList.get(CommandMenuGui.magicselected)) == -1 && STATS.getMP() > 0) {
               // Magic.getMagic(player, world, (String) this.magicsList.get(CommandMenuGui.magicselected));
                CommandMenuGui.selected = CommandMenuGui.ATTACK;
                CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;*/
				String magic = magicsList.get(CommandMenuGui.magicSelected);
				int cost = ModMagics.registry.getValue(new ResourceLocation(magic)).getCost();

            	if(playerData.getMaxMP() == 0 || playerData.getRecharge() || cost > playerData.getMaxMP() && cost < 300) {
                    world.playSound(player, player.getPosition(), ModSounds.error.get(), SoundCategory.MASTER, 1.0f, 1.0f);
                    CommandMenuGui.selected = CommandMenuGui.ATTACK;
                    CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
            	} else {
            		if(worldData.getPartyFromMember(player.getUniqueID()) != null && ModMagics.registry.getValue(new ResourceLocation(magic)).getHasToSelect()) { //Open party target selector
            			Party party = worldData.getPartyFromMember(player.getUniqueID());
                        CommandMenuGui.targetSelected = party.getMemberOrder(player.getUniqueID());
                        CommandMenuGui.submenu = CommandMenuGui.SUB_TARGET;
    	                world.playSound(player, player.getPosition(), ModSounds.menu_in.get(), SoundCategory.MASTER, 1.0f, 1.0f);
                        return;
            		} else {
            			PacketHandler.sendToServer(new CSUseMagicPacket(magicsList.get(CommandMenuGui.magicSelected)));
                        CommandMenuGui.selected = CommandMenuGui.ATTACK;
                        CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
            		}
                    world.playSound(player, player.getPosition(), ModSounds.menu_select.get(), SoundCategory.MASTER, 1.0f, 1.0f);
    			}
            }
        }

        //Items Submenu
        if (CommandMenuGui.selected == CommandMenuGui.ITEMS && CommandMenuGui.submenu == CommandMenuGui.SUB_ITEMS) {
            /*if (this.itemsCommands.isEmpty()) {
            } else if (!this.itemsCommands.isEmpty()) {
                ItemKKPotion.getItem(player, world, (String) this.itemsCommands.get(CommandMenuGui.potionselected), CommandMenuGui.potionselected);

                CommandMenuGui.selected = CommandMenuGui.ATTACK;
                CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
                world.playSound(player, player.getPosition(), ModSounds.select, SoundCategory.MASTER, 1.0f, 1.0f);
            }*/
        }
        
        //Drive Submenu
        if (CommandMenuGui.selected == CommandMenuGui.DRIVE && CommandMenuGui.submenu == CommandMenuGui.SUB_DRIVE) {
            if (this.driveFormsMap.isEmpty()) {
            } else {
            	String formName = (String) driveFormsMap.keySet().toArray()[CommandMenuGui.driveSelected];
            	DriveForm driveForm = ModDriveForms.registry.getValue(new ResourceLocation(formName));
            	if (playerData.getDP() >= driveForm.getDriveCost()) {
	                if (formName.equals(Strings.Form_Final)) {
	                    //driveForm.initDrive(player);
	                	PacketHandler.sendToServer(new CSSetDriveFormPacket(formName));
	            		player.world.playSound(player, player.getPosition(), ModSounds.drive.get(), SoundCategory.MASTER, 1.0f, 1.0f);
	                } else {
	                    if (!antiFormCheck()) {
		                	PacketHandler.sendToServer(new CSSetDriveFormPacket(formName));
		            		player.world.playSound(player, player.getPosition(), ModSounds.drive.get(), SoundCategory.MASTER, 1.0f, 1.0f);
	                    }
	                }
	                CommandMenuGui.selected = CommandMenuGui.ATTACK;
	                CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
	                world.playSound(player, player.getPosition(), ModSounds.menu_in.get(), SoundCategory.MASTER, 1.0f, 1.0f);
	            }
            }
        }
        
        //Target Selector Submenu
        if (CommandMenuGui.selected == CommandMenuGui.MAGIC && CommandMenuGui.submenu == CommandMenuGui.SUB_TARGET) {
            if (this.targetsList.isEmpty()) {
            } else {
            	Member member = targetsList.get(CommandMenuGui.targetSelected);
            	if(world.getPlayerByUuid(member.getUUID()) != null) {
            		PacketHandler.sendToServer(new CSUseMagicPacket(magicsList.get(CommandMenuGui.magicSelected), member.getUsername()));
                	CommandMenuGui.selected = CommandMenuGui.ATTACK;
                	CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
	                world.playSound(player, player.getPosition(), ModSounds.menu_in.get(), SoundCategory.MASTER, 1.0f, 1.0f);
            	} else {
	                world.playSound(player, player.getPosition(), ModSounds.error.get(), SoundCategory.MASTER, 1.0f, 1.0f);
            	}

            }
        }
    }

    private void summonPortal(PlayerEntity player, PortalData coords) {
		IPlayerCapabilities playerData = ModCapabilities.getPlayer(player);
		BlockPos destination = new BlockPos(coords.getX(), coords.getY(), coords.getZ());

		if (player.isSneaking()) {
			PacketHandler.sendToServer(new CSSpawnOrgPortalPacket(player.getPosition(), destination, coords.getDimID()));
			player.world.playSound((PlayerEntity) player, player.getPosition(), ModSounds.lockon.get(), SoundCategory.MASTER, 1.0f, 1.0f);
		} else {
			RayTraceResult rtr = InputHandler.getMouseOverExtended(100);
			if (rtr != null && rtr instanceof BlockRayTraceResult) {
				BlockRayTraceResult brtr = (BlockRayTraceResult)rtr;
				double distanceSq = player.getDistanceSq(brtr.getPos().getX(), brtr.getPos().getY(), brtr.getPos().getZ());
				double reachSq = 100 * 100;
				if (reachSq >= distanceSq) {
					PacketHandler.sendToServer(new CSSpawnOrgPortalPacket(brtr.getPos().up(), destination, coords.getDimID()));
					player.world.playSound((PlayerEntity) player, player.getPosition(), ModSounds.lockon.get(), SoundCategory.MASTER, 1.0f, 1.0f);
				}

			}
		}
	}

	public void commandBack() {
    	Minecraft mc = Minecraft.getInstance();
    	mc.world.playSound(mc.player, mc.player.getPosition(), ModSounds.menu_back.get(), SoundCategory.MASTER, 1.0f, 1.0f);
        PlayerEntity player = mc.player;
        World world = mc.world;

        if (CommandMenuGui.submenu == CommandMenuGui.SUB_MAIN)
            CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
        else if (CommandMenuGui.submenu == CommandMenuGui.SUB_MAGIC) {
            CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
            world.playSound(player, player.getPosition(), ModSounds.menu_back.get(), SoundCategory.MASTER, 1.0f, 1.0f);
        } else if (CommandMenuGui.submenu == CommandMenuGui.SUB_ITEMS) {
            CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
            world.playSound(player, player.getPosition(), ModSounds.menu_back.get(), SoundCategory.MASTER, 1.0f, 1.0f);
        } else if (CommandMenuGui.submenu == CommandMenuGui.SUB_DRIVE) {
            CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
            world.playSound(player, player.getPosition(), ModSounds.menu_back.get(), SoundCategory.MASTER, 1.0f, 1.0f);
        } else if (CommandMenuGui.submenu == CommandMenuGui.SUB_PORTALS) {
            CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
            world.playSound(player, player.getPosition(), ModSounds.menu_back.get(), SoundCategory.MASTER, 1.0f, 1.0f);
        } else if (CommandMenuGui.submenu == CommandMenuGui.SUB_ATTACKS) {
            CommandMenuGui.submenu = CommandMenuGui.SUB_MAIN;
            world.playSound(player, player.getPosition(), ModSounds.menu_back.get(), SoundCategory.MASTER, 1.0f, 1.0f);
        } else if (CommandMenuGui.submenu == CommandMenuGui.SUB_TARGET) {
            CommandMenuGui.submenu = CommandMenuGui.SUB_MAGIC;
            world.playSound(player, player.getPosition(), ModSounds.menu_back.get(), SoundCategory.MASTER, 1.0f, 1.0f);
        }
        //CommandMenuGui.magicSelected = 0;
        CommandMenuGui.driveSelected = 0;

        // GuiHelper.openTutorial(Tutorials.TUTORIAL_SOA_1);

    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        World world = mc.world;
      //  SummonKeybladeCapability.ISummonKeyblade SUMMON = player.getCapability(ModCapabilities.SUMMON_KEYBLADE, null);

       /* if (player.getCapability(ModCapabilities.DRIVE_STATE, null).getInDrive()) {
            Minecraft.getMinecraft().gameSettings.keyBindSwapHands.isPressed();
        }*/

        Keybinds key = getPressedKey();
        if (key != null)
            switch (key) {
                case OPENMENU:
    				PacketHandler.sendToServer(new CSSyncAllClientDataPacket());
                    GuiHelper.openMenu();
                    break;

               /* case SHOW_GUI:
                    MainConfig.toggleShowGUI();
                    break;*/

                case SCROLL_UP:
                   // if (!MainConfig.displayGUI())
                     //   break;
                    commandUp();
                    break;

                case SCROLL_DOWN:
                  //  if (!MainConfig.displayGUI())
                   //     break;
                    commandDown();
                    break;

                case ENTER:
                   /* if (!MainConfig.displayGUI())
                        break;*/
                    commandEnter();

                    break;

                case BACK:
                  //  if (!MainConfig.displayGUI())
                  //      break;
                    commandBack();
                    
                    break;
             
                case SUMMON_KEYBLADE:
                    //if (!player.getCapability(ModCapabilities.DRIVE_STATE, null).getInDrive())
                     //   Utils.summonWeapon(player, EnumHand.MAIN_HAND, 0);
                	if(player.getHeldItemMainhand() != null && (player.getHeldItemMainhand().getItem() instanceof KeychainItem || player.getHeldItemMainhand().getItem() instanceof KeybladeItem)) {
                		PacketHandler.sendToServer(new CSSummonKeyblade());
                	}
                    break;/*
                case SCROLL_ACTIVATOR:
                    break;
                case ACTION:
                    commandAction();
                    break;
                */
                case LOCK_ON:
                    if (lockOn == null) {
                        RayTraceResult rtr = getMouseOverExtended(100);
                        if (rtr != null && rtr instanceof EntityRayTraceResult) {
                        	EntityRayTraceResult ertr = (EntityRayTraceResult) rtr;
                            if (ertr.getEntity() != null) {
                                double distanceSq = player.getDistanceSq(ertr.getEntity());
                                double reachSq = 100 * 100;
                                if (reachSq >= distanceSq) {
                                    if (ertr.getEntity() instanceof LivingEntity) {
                                        lockOn = (LivingEntity) ertr.getEntity();
                                        System.out.println(lockOn);
                                        player.world.playSound((PlayerEntity) player, player.getPosition(), ModSounds.lockon.get(), SoundCategory.MASTER, 1.0f, 1.0f);
                                    }/* else if (rtr.entityHit instanceof EntityPart) {
                                        EntityPart part = (EntityPart) rtr.entityHit;
                                        if (part.getParent() != null && part.getParent() instanceof EntityLivingBase) {
                                            lockOn = (EntityLivingBase) part.getParent();
                                            LockOn.target = (EntityLivingBase) part.getParent();
                                            player.world.playSound((EntityPlayer) player, player.getPosition(), ModSounds.lockon, SoundCategory.MASTER, 1.0f, 1.0f);

                                        }
                                    }*/
                                }
                            }
                        }
                    } else {
                        lockOn = null;
                    }
                    break;

            }
    }

    private Keybinds getPressedKey() {
        for (Keybinds key : Keybinds.values())
            if (key.isPressed())
                return key;
        return null;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.RawMouseEvent event) {
        /*
         * if (player.getCapability(ModCapabilities.DRIVE_STATE, null).getInDrive()) {
         * if (player.getCapability(ModCapabilities.DRIVE_STATE,
         * null).getActiveDriveName().equals(Strings.Form_Wisdom)) {
         * event.setCanceled(true); } else { event.setCanceled(false); } }
         */

    	Minecraft mc = Minecraft.getInstance();
    	if(mc.world != null){
	        if (event.getButton() == Constants.LEFT_MOUSE && KeyboardHelper.isScrollActivatorDown() && event.getAction() == 1) {
	            commandEnter();
	            event.setCanceled(true);
	        }
	
	        if (event.getButton() == Constants.RIGHT_MOUSE && KeyboardHelper.isScrollActivatorDown() && event.getAction() == 1) {
	            commandBack();
	            event.setCanceled(true);
	        }
    	}
    }

    @SubscribeEvent
    public void OnMouseWheelScroll(MouseScrollEvent event) {
    	Minecraft mc = Minecraft.getInstance();
        if (mc.isGameFocused() && KeyboardHelper.isScrollActivatorDown()) {
        	event.setCanceled(true);
        	if(event.getScrollDelta() == Constants.WHEEL_DOWN) {
                commandDown();
        	}else if(event.getScrollDelta() == Constants.WHEEL_UP) {
                commandUp();
        	}
        	return;
        }
    }

    public enum Keybinds {
        OPENMENU("key.kingdomkeys.openmenu", GLFW.GLFW_KEY_M),
        SCROLL_UP("key.kingdomkeys.scrollup",GLFW.GLFW_KEY_UP),
        SCROLL_DOWN("key.kingdomkeys.scrolldown", GLFW.GLFW_KEY_DOWN),
        ENTER("key.kingdomkeys.enter",GLFW.GLFW_KEY_RIGHT),
        BACK("key.kingdomkeys.back", GLFW.GLFW_KEY_LEFT),
        SCROLL_ACTIVATOR("key.kingdomkeys.scrollactivator",GLFW.GLFW_KEY_LEFT_ALT),
        SUMMON_KEYBLADE("key.kingdomkeys.summonkeyblade", GLFW.GLFW_KEY_G),
        LOCK_ON("key.kingdomkeys.lockon",GLFW.GLFW_KEY_Z),
        SHOW_GUI("key.kingdomkeys.showgui", GLFW.GLFW_KEY_O),
        ACTION("key.kingdomkeys.action",GLFW.GLFW_KEY_X);
        //TEST("key.kingdomkeys.test",GLFW.GLFW_KEY_K);

        private final KeyBinding keybinding;
        Keybinds(String name, int defaultKey) {
            keybinding = new KeyBinding(name, defaultKey, "key.categories.kingdomkeys");
        }

        public KeyBinding getKeybind() {
            return keybinding;
        }

        public boolean isPressed() {
            return keybinding.isPressed();
        }
    }
    
    public static RayTraceResult getMouseOverExtended(float dist) {
		Minecraft mc = Minecraft.getInstance();
		Entity theRenderViewEntity = mc.getRenderViewEntity();
		AxisAlignedBB theViewBoundingBox = new AxisAlignedBB(theRenderViewEntity.getPosX() - 0.5D, theRenderViewEntity.getPosY() - 0.0D, theRenderViewEntity.getPosZ() - 0.5D, theRenderViewEntity.getPosX() + 0.5D, theRenderViewEntity.getPosY() + 1.5D, theRenderViewEntity.getPosZ() + 0.5D);
		RayTraceResult returnMOP = null;
		if (mc.world != null) {
			double var2 = dist;
			returnMOP = theRenderViewEntity.pick(var2, 0, false);
			double calcdist = var2;
			Vec3d pos = theRenderViewEntity.getEyePosition(0);
			var2 = calcdist;
			if (returnMOP != null) {
				calcdist = returnMOP.getHitVec().distanceTo(pos);
			}

			Vec3d lookvec = theRenderViewEntity.getLook(0);
			Vec3d var8 = pos.add(lookvec.x * var2, lookvec.y * var2, lookvec.z * var2);
			Entity pointedEntity = null;
			float var9 = 1.0F;

			List<Entity> list = mc.world.getEntitiesWithinAABBExcludingEntity(theRenderViewEntity, theViewBoundingBox.grow(lookvec.x * var2, lookvec.y * var2, lookvec.z * var2).grow(var9, var9, var9));
			double d = calcdist;

			for (Entity entity : list) {
				if (entity.canBeCollidedWith()) {
					float bordersize = entity.getCollisionBorderSize();
					AxisAlignedBB aabb = new AxisAlignedBB(entity.getPosX() - entity.getWidth() / 2, entity.getPosY(), entity.getPosZ() - entity.getWidth() / 2, entity.getPosX() + entity.getWidth() / 2, entity.getPosY() + entity.getHeight(), entity.getPosZ() + entity.getWidth() / 2);
					aabb.grow(bordersize, bordersize, bordersize);
					Optional<Vec3d> mop0 = aabb.rayTrace(pos, var8);

					if (aabb.contains(pos)) {
						if (0.0D < d || d == 0.0D) {
							pointedEntity = entity;
							d = 0.0D;
						}
					} else if (mop0 != null && mop0.isPresent()) {
						double d1 = pos.distanceTo(mop0.get());

						if (d1 < d || d == 0.0D) {
							pointedEntity = entity;
							d = d1;
						}
					}
				}
			}

			if (pointedEntity != null && (d < calcdist || returnMOP == null)) {
				returnMOP = new EntityRayTraceResult(pointedEntity);
			}
		}
		return returnMOP;
	}


    public void loadLists() {
        Minecraft mc = Minecraft.getInstance();
        this.driveFormsMap = Utils.getSortedDriveForms(ModCapabilities.getPlayer(mc.player).getDriveFormMap());
        this.magicsList = ModCapabilities.getPlayer(mc.player).getMagicList();
        this.portalCommands = ModCapabilities.getPlayer(mc.player).getPortalList();
        if(ModCapabilities.getWorld(mc.world).getPartyFromMember(mc.player.getUniqueID()) != null) {
        	this.targetsList = ModCapabilities.getWorld(mc.world).getPartyFromMember(mc.player.getUniqueID()).getMembers();
        }
        

        /* this.attackCommands.clear();
        IAbilities ABILITIES = player.getCapability(ModCapabilities.ABILITIES, null);
        // for (int i = 0; i < ABILITIES.getEquippedAbilities().size(); i++) {
        for (Ability ability : ABILITIES.getEquippedAbilities()) {
            if (ability == ModAbilities.sonicBlade || ability == ModAbilities.strikeRaid) {
                this.attackCommands.add(ability);
            }
        }*/
    }
}
