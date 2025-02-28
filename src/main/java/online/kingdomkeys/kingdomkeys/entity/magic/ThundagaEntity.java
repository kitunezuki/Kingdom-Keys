package online.kingdomkeys.kingdomkeys.entity.magic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import online.kingdomkeys.kingdomkeys.entity.ModEntities;
import online.kingdomkeys.kingdomkeys.lib.DamageCalculation;
import online.kingdomkeys.kingdomkeys.util.Utils;

public class ThundagaEntity extends ThrowableProjectile {

	int maxTicks = 40;
	float dmgMult = 1;
	
	public ThundagaEntity(EntityType<? extends ThrowableProjectile> type, Level world) {
		super(type, world);
		this.blocksBuilding = true;
	}

	public ThundagaEntity(PlayMessages.SpawnEntity spawnEntity, Level world) {
		super(ModEntities.TYPE_THUNDAGA.get(), world);
	}

	public ThundagaEntity(Level world) {
		super(ModEntities.TYPE_THUNDAGA.get(), world);
		this.blocksBuilding = true;
	}

	public ThundagaEntity(Level world, Player player, float dmgMult) {
		super(ModEntities.TYPE_THUNDAGA.get(), player, world);
		setCaster(player.getUUID());
		this.dmgMult = dmgMult;
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public int getMaxTicks() {
		return maxTicks;
	}

	public void setMaxTicks(int maxTicks) {
		this.maxTicks = maxTicks;
	}

	private static final EntityDataAccessor<Optional<UUID>> OWNER = SynchedEntityData.defineId(ThundagaEntity.class, EntityDataSerializers.OPTIONAL_UUID);

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		if (this.entityData.get(OWNER) != null) {
			compound.putString("OwnerUUID", this.entityData.get(OWNER).get().toString());
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		this.entityData.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
	}

	public Player getCaster() {
		return this.getEntityData().get(OWNER).isPresent() ? this.level.getPlayerByUUID(this.getEntityData().get(OWNER).get()) : null;
	}

	public void setCaster(UUID uuid) {
		this.entityData.set(OWNER, Optional.of(uuid));
	}

	@Override
	protected void defineSynchedData() {
		this.entityData.define(OWNER, Optional.of(Util.NIL_UUID));
	}

	List<LivingEntity> list = new ArrayList<LivingEntity>();

	@Override
	public void tick() {
		if (this.tickCount > maxTicks) {
			this.remove(RemovalReason.KILLED);
		}

		if (getCaster() == null) {
			remove(RemovalReason.KILLED);
			return;
		}
		
		float radius = 3.0F;

		if (!level.isClientSide && getCaster() != null) { // Only calculate and spawn lightning bolts server side
			if (tickCount == 1) {
				list = Utils.getLivingEntitiesInRadiusExcludingParty(getCaster(), radius);
				list.remove(this);
			}

			if (tickCount % 6 == 1) {
				if (!list.isEmpty()) { // find random entity
					int i = level.random.nextInt(list.size());
					Entity e = (Entity) list.get(i);
					if (e instanceof LivingEntity) {
						if(!e.isAlive()) {
							list.remove(e);
						}
						float dmg = this.getOwner() instanceof Player ? DamageCalculation.getMagicDamage((Player) this.getOwner()) * 0.1F : 2;
						ThunderBoltEntity shot = new ThunderBoltEntity(getCaster().level, getCaster(), e.getX(), e.getY(), e.getZ(), dmg * dmgMult);
						shot.setCaster(getCaster().getUUID());
						level.addFreshEntity(shot);

						LightningBolt lightningBoltEntity = EntityType.LIGHTNING_BOLT.create(this.level);
						lightningBoltEntity.setVisualOnly(true);
						lightningBoltEntity.moveTo(Vec3.atBottomCenterOf(e.blockPosition()));
						lightningBoltEntity.setCause(getCaster() instanceof ServerPlayer ? (ServerPlayer) getCaster() : null);
						this.level.addFreshEntity(lightningBoltEntity);
					}
				} else {
					int x = (int) getCaster().getX();
					int z = (int) getCaster().getZ();

					int posX = (int) (x + getCaster().level.random.nextInt((int) (radius*2)) - radius / 2)-1;
					int posZ = (int) (z + getCaster().level.random.nextInt((int) (radius*2)) - radius / 2)-1;

					float dmg = this.getOwner() instanceof Player ? DamageCalculation.getMagicDamage((Player) this.getOwner()) * 0.11F : 2;
					dmg = Math.max(0.4F, dmg);
					ThunderBoltEntity shot = new ThunderBoltEntity(getCaster().level, getCaster(), posX, getCaster().level.getHeight(Types.WORLD_SURFACE, posX, posZ), posZ, dmg * dmgMult);
					shot.setCaster(getCaster().getUUID());
					level.addFreshEntity(shot);

					BlockPos pos = new BlockPos(posX, getCaster().level.getHeight(Types.WORLD_SURFACE, posX, posZ), posZ);
					LightningBolt lightningBoltEntity = EntityType.LIGHTNING_BOLT.create(this.level);
					lightningBoltEntity.moveTo(Vec3.atBottomCenterOf(pos));
					lightningBoltEntity.setVisualOnly(true);
					lightningBoltEntity.setCause(getCaster() instanceof ServerPlayer ? (ServerPlayer) getCaster() : null);
					this.level.addFreshEntity(lightningBoltEntity);
				}
			}
		}

		super.tick();
	}

	@Override
	protected void onHit(HitResult result) {
		// TODO Auto-generated method stub

	}

}
