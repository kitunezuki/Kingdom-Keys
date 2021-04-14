package online.kingdomkeys.kingdomkeys.entity.magic;

import java.util.Optional;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import online.kingdomkeys.kingdomkeys.client.sound.ModSounds;
import online.kingdomkeys.kingdomkeys.entity.ModEntities;

public class ShotlockShotEntity extends ThrowableEntity {

	int maxTicks = 100;
	float dmg;
	Entity target;
	
	public ShotlockShotEntity(EntityType<? extends ThrowableEntity> type, World world) {
		super(type, world);
		this.preventEntitySpawning = true;
	}

	public ShotlockShotEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
		super(ModEntities.TYPE_SHOTLOCK_SHOT.get(), world);
	}

	public ShotlockShotEntity(World world) {
		super(ModEntities.TYPE_SHOTLOCK_SHOT.get(), world);
		this.preventEntitySpawning = true;
	}

	public ShotlockShotEntity(World world, LivingEntity player, Entity target, double dmg) {
		super(ModEntities.TYPE_SHOTLOCK_SHOT.get(), player, world);
		this.dmg = (float)dmg;
		setTarget(target.getEntityId());
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected float getGravityVelocity() {
		return 0F;
	}

	@Override
	public void tick() {
		if (this.ticksExisted > maxTicks) {
			this.remove();
		}
		
		if(ticksExisted > 1) {
			world.addParticle(ParticleTypes.DRAGON_BREATH, getPosX(), getPosY(), getPosZ(), 0, 0, 0);
		}
		
		if(ticksExisted % 10 == 0) {
			updateMovement();
		}
		
		super.tick();
	}

	private void updateMovement() {
		System.out.println(getTarget());

		if(getTarget() != null) {
			if(getTarget().isAlive()) {
				this.shoot(getTarget().getPosX() - this.getPosX(), (getTarget().getPosY() + (getTarget().getHeight() / 2.0F) - this.getHeight()) - getPosY() + 0.5, getTarget().getPosZ() - this.getPosZ(), 1, 0);
			} else {
				if(getShooter() != null)
					this.setDirectionAndMovement(this, getShooter().rotationPitch, getShooter().rotationYaw, 0, 1, 0); // Work in progress
			}
		}
	}

	@Override
	protected void onImpact(RayTraceResult rtRes) {
		if (!world.isRemote) {

			EntityRayTraceResult ertResult = null;
			BlockRayTraceResult brtResult = null;

			if (rtRes instanceof EntityRayTraceResult) {
				ertResult = (EntityRayTraceResult) rtRes;
			}

			if (rtRes instanceof BlockRayTraceResult) {
				brtResult = (BlockRayTraceResult) rtRes;
			}

			if (ertResult != null && ertResult.getEntity() instanceof LivingEntity) {
				LivingEntity target = (LivingEntity) ertResult.getEntity();
				if (target != getShooter()) {
					target.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getShooter()), dmg);
					System.out.println(dmg);
					remove();
				}
			}
			remove();
		}
	}

	public int getMaxTicks() {
		return maxTicks;
	}

	public void setMaxTicks(int maxTicks) {
		this.maxTicks = maxTicks;
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		if (this.dataManager.get(OWNER) != null) {
			compound.putString("OwnerUUID", this.dataManager.get(OWNER).get().toString());
			compound.putInt("TargetUUID", this.dataManager.get(TARGET));
		}
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
		this.dataManager.set(TARGET, compound.getInt("TargetUUID"));
	}

	private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(DarkVolleyCoreEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Integer> TARGET = EntityDataManager.createKey(DarkVolleyCoreEntity.class, DataSerializers.VARINT);

	public PlayerEntity getCaster() {
		return this.getDataManager().get(OWNER).isPresent() ? this.world.getPlayerByUuid(this.getDataManager().get(OWNER).get()) : null;
	}

	public void setCaster(UUID uuid) {
		this.dataManager.set(OWNER, Optional.of(uuid));
	}

	public Entity getTarget() {
		return this.world.getEntityByID(this.getDataManager().get(TARGET));
	}

	public void setTarget(int i) {
		this.dataManager.set(TARGET, i);
	}

	@Override
	protected void registerData() {
		this.dataManager.register(OWNER, Optional.of(new UUID(0L, 0L)));
		this.dataManager.register(TARGET, 0);
	}
}