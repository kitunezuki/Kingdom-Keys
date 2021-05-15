package online.kingdomkeys.kingdomkeys.magic;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import online.kingdomkeys.kingdomkeys.capability.IPlayerCapabilities;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.entity.magic.WategaEntity;
import online.kingdomkeys.kingdomkeys.entity.magic.WaterEntity;
import online.kingdomkeys.kingdomkeys.entity.magic.WateraEntity;
import online.kingdomkeys.kingdomkeys.network.PacketHandler;
import online.kingdomkeys.kingdomkeys.network.stc.SCSyncCapabilityPacket;

public class MagicWater extends Magic {

	public MagicWater(String registryName, int cost, int maxLevel, int order) {
		super(registryName, cost, false, maxLevel, order);
		this.name = registryName;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onUse(PlayerEntity player, PlayerEntity caster, int level) {
		IPlayerCapabilities casterData = ModCapabilities.getPlayer(caster);
		casterData.setMagicCooldownTicks(50 + 20);
		PacketHandler.sendTo(new SCSyncCapabilityPacket(casterData), (ServerPlayerEntity)caster);

		switch(level) {
		case 0:
			WaterEntity water = new WaterEntity(player.world, player);
			water.setCaster(player.getDisplayName().getString());
			player.world.addEntity(water);
			player.world.playSound(null, player.getPosition(), SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.PLAYERS, 1F, 1F);
			break;
		case 1:
			WateraEntity watera = new WateraEntity(player.world, player);
			watera.setCaster(player.getDisplayName().getString());
			player.world.addEntity(watera);
			player.world.playSound(null, player.getPosition(), SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.PLAYERS, 1F, 1F);
			break;
		case 2:
			WategaEntity watega = new WategaEntity(player.world, player);
			watega.setCaster(player.getDisplayName().getString());
			player.world.addEntity(watega);
			player.world.playSound(null, player.getPosition(), SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.PLAYERS, 1F, 1F);
			break;
		}
		
		player.swingArm(Hand.MAIN_HAND);
		if(player.isBurning()) {
			player.extinguish();
		}
	}

}
