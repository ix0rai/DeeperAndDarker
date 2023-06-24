package com.kyanite.deeperdarker.registry.entities.custom;

import com.kyanite.deeperdarker.miscellaneous.DDTypes;
import com.kyanite.deeperdarker.registry.entities.custom.ai.SculkLeechMelee;
import com.kyanite.deeperdarker.registry.entities.custom.ai.nav.BetterWallClimberNavigation;
import com.kyanite.deeperdarker.registry.sounds.DDSounds;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SculkCentipedeEntity extends Monster implements GeoAnimatable {
    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(SculkCentipedeEntity.class, EntityDataSerializers.BYTE);

    public SculkCentipedeEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder attributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 25.0D).add(Attributes.MOVEMENT_SPEED, 0.2D).add(Attributes.ATTACK_DAMAGE, 2.5D);
    }

    @Override
    public boolean onClimbable() {
        return this.isClimbing();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte)0);
    }

    public boolean isClimbing() {
        return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new BetterWallClimberNavigation(this, pLevel);
    }

    public void setClimbing(boolean pClimbing) {
        byte b0 = this.entityData.get(DATA_FLAGS_ID);
        if(pClimbing) {
            b0 = (byte)(b0 | 1);
        } else {
            b0 = (byte)(b0 & -2);
        }

        this.entityData.set(DATA_FLAGS_ID, b0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.5D));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 10));
        this.goalSelector.addGoal(4, new SculkLeechMelee(this, 1.5D, false));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    @Override
    public MobType getMobType() {
        return DDTypes.SCULK;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return DDSounds.SCULK_CENTIPEDE_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return DDSounds.SCULK_CENTIPEDE_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(@Nullable DamageSource pDamageSource) {
        return DDSounds.SCULK_CENTIPEDE_HURT.get();
    }

    @Override
    public void tick() {
        super.tick();
        if(!this.level().isClientSide) {
            this.setClimbing(this.horizontalCollision);
        }
        if(this.horizontalCollision && this.onClimbable()) {
            this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * 0.5f, this.getDeltaMovement().z);
        }
    }

    public void setYBodyRot(float pOffset) {
        super.setYBodyRot(pOffset);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<GeoAnimatable>(this, "controller", 5, state -> {
            if(state.isMoving()) {
                state.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sculk_centipede.walk"));
                return PlayState.CONTINUE;
            }
            state.getController().setAnimation(RawAnimation.begin().thenLoop("animation.sculk_centipede.idle"));
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.factory;
    }

    @Override
    public double getTick(Object object) {
        return 5;
    }
}
