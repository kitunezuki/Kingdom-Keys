package online.kingdomkeys.kingdomkeys.entity.magic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import online.kingdomkeys.kingdomkeys.capability.ModCapabilities;
import online.kingdomkeys.kingdomkeys.entity.ModEntities;
import online.kingdomkeys.kingdomkeys.lib.Party;

public class ThunderEntity extends ThrowableEntity {

	int maxTicks = 20;

	public ThunderEntity(EntityType<? extends ThrowableEntity> type, World world) {
		super(type, world);
		this.preventEntitySpawning = true;
	}

	public ThunderEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
		super(ModEntities.TYPE_THUNDER.get(), world);
	}

	public ThunderEntity(World world) {
		super(ModEntities.TYPE_THUNDER.get(), world);
		this.preventEntitySpawning = true;
	}

	public ThunderEntity(World world, PlayerEntity player) {
		super(ModEntities.TYPE_THUNDER.get(), player, world);
		//setPosition(x, y + 10, z);
		setCaster(player.getUniqueID());
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public int getMaxTicks() {
		return maxTicks;
	}

	public void setMaxTicks(int maxTicks) {
		this.maxTicks = maxTicks;
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		if (this.dataManager.get(OWNER) != null) {
			compound.putString("OwnerUUID", this.dataManager.get(OWNER).get().toString());
		}
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
	}

	private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(ThunderEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);

	public PlayerEntity getCaster() {
		return this.getDataManager().get(OWNER).isPresent() ? this.world.getPlayerByUuid(this.getDataManager().get(OWNER).get()) : null;
	}

	public void setCaster(UUID uuid) {
		this.dataManager.set(OWNER, Optional.of(uuid));
	}

	@Override
	protected void registerData() {
		this.dataManager.register(OWNER, Optional.of(new UUID(0L, 0L)));
	}

	@Override
	public void tick() {
		if (this.ticksExisted > maxTicks) {
			this.remove();
		}

		if(getCaster() == null) {
			remove();
			return;
		}
		
		if(!world.isRemote && getCaster() != null) { //Only calculate and spawn lightning bolts server side
			if(ticksExisted % 5 == 0) {
        		double radius = 2.0D;
				List<Entity> list = this.world.getEntitiesInAABBexcluding(getCaster(), new AxisAlignedBB(this.getPosX() - radius, this.getPosY() - radius, this.getPosZ() - radius, this.getPosX() + radius, this.getPosY() + 6.0D + radius, this.getPosZ() + radius), Entity::isAlive);
				Party casterParty = ModCapabilities.getWorld(world).getPartyFromMember(getThrower().getUniqueID());
				if(casterParty != null) {
					for(Party.Member m : casterParty.getMembers()) {
						list.remove(world.getPlayerByUuid(m.getUUID()));
					}
				} else {
					list.remove(getThrower());
				}
		        if (!list.isEmpty() && list.get(0) != this) {
		            for (int i = 0; i < list.size(); i++) {
		                Entity e = (Entity) list.get(i);
		                if (e instanceof LivingEntity) {
		                	ThunderBoltEntity shot = new ThunderBoltEntity(getCaster().world, getCaster(), e.getPosX(), e.getPosY(), e.getPosZ());
		                	shot.setCaster(getCaster().getUniqueID());
		            		world.addEntity(shot);
		            		
			    			LightningBoltEntity lightning = new LightningBoltEntity(getCaster().world, e.getPosX(), e.getPosY(), e.getPosZ(), true);
			    			lightning.setCaster(getCaster() instanceof ServerPlayerEntity ? (ServerPlayerEntity) getCaster() : null);
			    			((ServerWorld)getCaster().world).addLightningBolt(lightning);
		                }
		            }
		        } else {
		        	int x = (int) getCaster().getPosX();
		        	int z = (int) getCaster().getPosZ();
		        	
	        		int posX = x + getCaster().world.rand.nextInt(6) - 3;
		        	int posZ = z + getCaster().world.rand.nextInt(6) - 3;
		        	
		        	ThunderBoltEntity shot = new ThunderBoltEntity(getCaster().world, getCaster(), posX, getCaster().world.getHeight(Type.WORLD_SURFACE, posX, posZ), posZ);
            		shot.setCaster(getCaster().getUniqueID());
            		world.addEntity(shot);
            		
	    			LightningBoltEntity lightning = new LightningBoltEntity(getCaster().world, posX, getCaster().world.getHeight(Type.WORLD_SURFACE, posX, posZ), posZ, true);
					lightning.setCaster(getCaster() instanceof ServerPlayerEntity ? (ServerPlayerEntity) getCaster() : null);
					((ServerWorld)getCaster().world).addLightningBolt(lightning);
	    		}
        	}
        }

		super.tick();
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		// TODO Auto-generated method stub
		
	}

}
