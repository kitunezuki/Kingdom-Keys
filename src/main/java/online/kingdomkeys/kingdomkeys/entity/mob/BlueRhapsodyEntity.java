package online.kingdomkeys.kingdomkeys.entity.mob;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PlayMessages;
import online.kingdomkeys.kingdomkeys.KingdomKeys;
import online.kingdomkeys.kingdomkeys.entity.EntityHelper;
import online.kingdomkeys.kingdomkeys.entity.ModEntities;
import online.kingdomkeys.kingdomkeys.entity.magic.BlizzardEntity;
import online.kingdomkeys.kingdomkeys.entity.magic.FireEntity;

public class BlueRhapsodyEntity extends BaseElementalMusicalHeartlessEntity {


    public BlueRhapsodyEntity(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
    }

    public BlueRhapsodyEntity(PlayMessages.SpawnEntity spawnEntity, Level world) {
        super(ModEntities.TYPE_BLUE_RHAPSODY.get(), spawnEntity, world);
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return BaseElementalMusicalHeartlessEntity.registerAttributes()
        		.add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
        		
    }

    @Override
    protected Goal goalToUse() {
        return new BlueRhapsodyGoal(this);
    }

    @Override
    public Element getElementToUse() {
        return Element.BLIZZARD;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(KingdomKeys.MODID, "textures/entity/mob/blue_rhapsody.png");
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        float multiplier = 1;
        if(!this.level.isClientSide) {
            if(source.getDirectEntity() instanceof FireEntity)
                multiplier = 2;
            if(source.getDirectEntity() instanceof BlizzardEntity)
            	return false;
        }
        return super.hurt(source, amount * multiplier);
    }

    class BlueRhapsodyGoal extends TargetGoal {
        private boolean canUseAttack = true;
        private int attackTimer = 5, whileAttackTimer;

        public BlueRhapsodyGoal(BlueRhapsodyEntity e) {
        	super(e,true);
        }

        @Override
        public boolean canUse() {
            if (mob.getTarget() != null) {
                if (!canUseAttack) {
                    if (attackTimer > 0) {
                        attackTimer--;
                        return false;
                    } else
                        return true;
                } else
                    return true;
            } else
                return false;
        }

        @Override
        public boolean canContinueToUse() {
            boolean flag = canUseAttack;

            return flag;
        }

        @Override
        public void start() {
            canUseAttack = true;
            attackTimer = 20 + level.random.nextInt(5);
            EntityHelper.setState(mob, 0);
            this.mob.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.20D);
            whileAttackTimer = 0;
        }

        @Override
        public void tick() {
            if (mob.getTarget() != null && canUseAttack) {
                whileAttackTimer+=2;
                LivingEntity target = this.mob.getTarget();

                if (EntityHelper.getState(mob) == 0) {
                    this.mob.getLookControl().setLookAt(target, 30F, 30F);

                    if (level.random.nextInt(100) + level.random.nextDouble() <= 75) {
                        EntityHelper.setState(this.mob, 1);

                        this.mob.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.0D);
                        this.mob.getLookControl().setLookAt(target, 0F, 0F);

                        double d0 = this.mob.distanceToSqr(this.mob.getTarget());
                        //float f = MathHelper.sqrt(MathHelper.sqrt(d0));
                        double d1 = this.mob.getTarget().getX() - this.mob.getX();
                        double d2 = this.mob.getTarget().getBoundingBox().minY + (double) (this.mob.getTarget().getBbHeight() / 2.0F) - (this.mob.getY() + (double) (this.mob.getBbHeight() / 2.0F));
                        double d3 = this.mob.getTarget().getZ() - this.mob.getZ();
                        BlizzardEntity esfb = new BlizzardEntity(this.mob.level, mob, (float) this.mob.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue());
                        esfb.shoot(d1, d2, d3, 1, 0);
                        esfb.setPos(esfb.getX(), this.mob.getY() + (double) (this.mob.getBbHeight() / 2.0F) + 0.5D, esfb.getZ());
                        this.mob.level.addFreshEntity(esfb);
                    } else {
                        if (mob.distanceTo(mob.getTarget()) < 8) {
                            EntityHelper.setState(this.mob, 2);

                            this.mob.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.0D);

                            for (LivingEntity enemy : EntityHelper.getEntitiesNear(this.mob, 4)) {
                                enemy.hurt(DamageSource.mobAttack(this.mob), 4);
                            }
                        } else {
                            return;
                        }
                    }

                }

                if (EntityHelper.getState(mob) == 2 && whileAttackTimer > 20) {
                    canUseAttack = false;
                    EntityHelper.setState(mob, 0);
                    this.mob.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.20D);
                }
                else if (EntityHelper.getState(mob) == 1 && whileAttackTimer > 50) {
                    canUseAttack = false;
                    EntityHelper.setState(mob, 0);
                    this.mob.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.20D);
                }
            }
        }

    }

}
