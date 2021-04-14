package online.kingdomkeys.kingdomkeys.entity.magic;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import online.kingdomkeys.kingdomkeys.capability.IGlobalCapabilities;
import online.kingdomkeys.kingdomkeys.capability.IWorldCapabilities;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.entity.ModEntities;
import online.kingdomkeys.kingdomkeys.lib.Party;
import online.kingdomkeys.kingdomkeys.lib.Party.Member;
import online.kingdomkeys.kingdomkeys.network.PacketHandler;
import online.kingdomkeys.kingdomkeys.network.stc.SCRecalculateEyeHeight;

public class GravityEntity extends ThrowableEntity {

	int maxTicks = 100;

	public GravityEntity(EntityType<? extends ThrowableEntity> type, World world) {
		super(type, world);
		this.preventEntitySpawning = true;
	}

	public GravityEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
		super(ModEntities.TYPE_GRAVITY.get(), world);
	}

	public GravityEntity(World world) {
		super(ModEntities.TYPE_GRAVITY.get(), world);
		this.preventEntitySpawning = true;
	}

	public GravityEntity(World world, PlayerEntity player) {
		super(ModEntities.TYPE_GRAVITY.get(), player, world);
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

		if (ticksExisted > 2)
			world.addParticle(ParticleTypes.DRAGON_BREATH, getPosX(), getPosY(), getPosZ(), 0, 0, 0);

		super.tick();
	}

	@Override
	protected void onImpact(RayTraceResult rtRes) {
		float radius = 2F;
		double X = getPosX();
		double Y = getPosY();
		double Z = getPosZ();

		for (int t = 1; t < 360; t += 20) {
			for (int s = 1; s < 360 ; s += 20) {
				double x = X + (radius * Math.cos(Math.toRadians(s)) * Math.sin(Math.toRadians(t)));
				double z = Z + (radius * Math.sin(Math.toRadians(s)) * Math.sin(Math.toRadians(t)));
				double y = Y + (radius * Math.cos(Math.toRadians(t)));
				world.addParticle(ParticleTypes.DRAGON_BREATH, x, y, z, 0, -0.05, 0);
			}
		}
		
		IWorldCapabilities worldData = ModCapabilities.getWorld(world);
		if (!world.isRemote && getShooter() != null && worldData != null) {
			List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(getShooter(), getBoundingBox().grow(3.0D, 3.0D, 3.0D));
			Party casterParty = worldData.getPartyFromMember(getShooter().getUniqueID());

			if(casterParty != null && !casterParty.getFriendlyFire()) {
				for(Member m : casterParty.getMembers()) {
					list.remove(world.getPlayerByUuid(m.getUUID()));
				}
			} else {
				list.remove(getShooter());
			}
			
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Entity e = (Entity) list.get(i);
					if (e instanceof LivingEntity) {
						IGlobalCapabilities globalData = ModCapabilities.getGlobal((LivingEntity) e);
						globalData.setFlatTicks(100);
						
						if (e instanceof LivingEntity)
							PacketHandler.syncToAllAround((LivingEntity) e, globalData);

						if(e instanceof ServerPlayerEntity)
							PacketHandler.sendTo(new SCRecalculateEyeHeight(), (ServerPlayerEntity) e);
						
						e.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getShooter()), 1);
					}
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
		// compound.putInt("lvl", this.getLvl());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		// this.setLvl(compound.getInt("lvl"));
	}

	@Override
	protected void registerData() {
		// TODO Auto-generated method stub

	}
}
