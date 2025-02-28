package online.kingdomkeys.kingdomkeys.item.organization;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import online.kingdomkeys.kingdomkeys.capability.IPlayerCapabilities;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.client.sound.ModSounds;
import online.kingdomkeys.kingdomkeys.entity.organization.ArrowgunShotEntity;
import online.kingdomkeys.kingdomkeys.lib.Strings;
import online.kingdomkeys.kingdomkeys.util.Utils;

public class ArrowgunItem extends OrgSwordItem implements IOrgWeapon {
	int ammo = 10, reload = 30, tempAmmo;

	/*
	 * public ArrowgunItem(String name, int ammo, int reload) { super(); this.ammo =
	 * ammo; this.reload = reload; }
	 */

	@Override
	public Utils.OrgMember getMember() {
		return Utils.OrgMember.XIGBAR;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (!player.isShiftKeyDown()) {
			if (player.getItemInHand(hand).getTag() != null && player.getItemInHand(hand).getTag().getInt("ammo") > 0) {
				world.playSound(player, player.blockPosition(), ModSounds.sharpshooterbullet.get(), SoundSource.PLAYERS, 1F, 1F);
				ArrowgunShotEntity bullet = new ArrowgunShotEntity(world, player);
				bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 3F, 0);
				world.addFreshEntity(bullet);

				//player.swingArm(Hand.MAIN_HAND);
				tempAmmo = player.getItemInHand(hand).getTag().getInt("ammo") - 1;

				player.getItemInHand(hand).getTag().putInt("ammo", tempAmmo);
				if(tempAmmo == 0) {
					player.getItemInHand(hand).getTag().putInt("ammo", getMaxAmmo(player));
					player.getCooldowns().addCooldown(this, reload);
					world.playSound(null, player.blockPosition(), ModSounds.arrowgunReload.get(), SoundSource.PLAYERS, 1F, 1F);
				}
			}

		} else {
			CompoundTag nbt = player.getItemInHand(hand).getTag();

			if (nbt.getInt("ammo") > 0) {
				world.playSound(player, player.blockPosition(), ModSounds.arrowgunReload.get(), SoundSource.PLAYERS, 1F, 1F);

				player.getCooldowns().addCooldown(this, reload / nbt.getInt("ammo"));
				player.getItemInHand(hand).getTag().putInt("ammo", getMaxAmmo(player));
				player.swing(InteractionHand.MAIN_HAND);
			}
			return super.use(world, player, hand);
		}

		return InteractionResultHolder.consume(player.getItemInHand(hand));
	}

	private int getMaxAmmo(Player player) {
		IPlayerCapabilities playerData = ModCapabilities.getPlayer(player);
		if(playerData.isAbilityEquipped(Strings.synchBlade)) {
			return ammo*2;
		}
		return ammo;
	}

	@Override
	public void inventoryTick(ItemStack itemStack, Level world, Entity entity, int itemSlot, boolean isSelected) {
		if (entity instanceof Player && !world.isClientSide) {
			Player player = (Player) entity;
			if (!itemStack.hasTag()) {
				itemStack.setTag(new CompoundTag());
				itemStack.getTag().putInt("ammo", getMaxAmmo(player));
			}

			if (itemStack.getTag().getInt("ammo") == 0) {
				
			}
		}
	}
}
