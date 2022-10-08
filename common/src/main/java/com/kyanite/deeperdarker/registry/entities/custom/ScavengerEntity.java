package com.kyanite.deeperdarker.registry.entities.custom;

import com.kyanite.deeperdarker.DeeperAndDarker;
import com.kyanite.deeperdarker.miscellaneous.DDTags;
import com.kyanite.deeperdarker.miscellaneous.DDUtils;
import com.kyanite.deeperdarker.registry.blocks.DDBlocks;
import com.kyanite.deeperdarker.registry.entities.custom.ai.FlyingWanderGoal;
import com.kyanite.deeperdarker.registry.entities.custom.ai.GoToDisturbanceGoal;
import com.kyanite.deeperdarker.registry.entities.custom.ai.GoToNearestStructureGoal;
import com.kyanite.deeperdarker.registry.items.DDItems;
import com.kyanite.deeperdarker.registry.particle.DDParticleUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.Path;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ScavengerEntity extends TamableAnimal implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    public BlockPos structureLocation = null;

    public ScavengerEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);

        this.moveControl = new FlyingMoveControl(this, 10, true);
    }

    public static AttributeSupplier.Builder attributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 25.0D).add(Attributes.FLYING_SPEED, 0.35).add(Attributes.MOVEMENT_SPEED, 0.3);
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(DDItems.AMBER_CRYSTAL.get());
    }

    @Override
    protected void registerGoals() {
       // this.goalSelector.addGoal(3, new FloatGoal(this));
      //  this.goalSelector.addGoal(1, new FlyingWanderGoal(this));
       // this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new GoToNearestStructureGoal(this, 2.3D));
        //this.goalSelector.addGoal(9, new PanicGoal(this, 1.7D));
      //  this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 10));
    }

    @Override
    @Nullable
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (isFood(itemstack) && !this.isTame()) {
            this.usePlayerItem(pPlayer, pHand, itemstack);
            if (!this.level.isClientSide()) {
                this.tame(pPlayer);
                this.setOwnerUUID(pPlayer.getUUID());
                setTarget(null);
                DDParticleUtils.spawnHeartParticles(this, this.getRandom());
                this.level.broadcastEntityEvent(this, (byte) 244);
            }
            return InteractionResult.SUCCESS;
        }

        if(this.isTame() && !level.isClientSide()) {
            DeeperAndDarker.LOGGER.info("CLICKED");
            BlockPos nearestStructure = DDUtils.getNearestStructure(blockPosition(), getServer().getLevel(level.dimension()));
            if(nearestStructure != null) {
                DeeperAndDarker.LOGGER.info("FOUND NEAREST STRUCTURE");
                this.usePlayerItem(pPlayer, pHand, itemstack);
                structureLocation = nearestStructure;
                DDParticleUtils.spawnHeartParticles(this, this.getRandom());
                this.level.broadcastEntityEvent(this, (byte) 244);
                return InteractionResult.SUCCESS;
            }else{
                DeeperAndDarker.LOGGER.info("NOT FOUND NEAREST STRUCTURE");
                return InteractionResult.FAIL;
            }
        }

        return super.mobInteract(pPlayer, pHand);
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 244) {
            DDParticleUtils.spawnHeartParticles(this, this.getRandom());
        } else {
            super.handleEntityEvent(pId);
        }
    }


    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation pathNavigation = new FlyingPathNavigation(this, level);
        pathNavigation.setCanFloat(true);
        return pathNavigation;
    }


    @Override
    public boolean causeFallDamage(float f, float g, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void checkFallDamage(double d, boolean bl, BlockState blockState, BlockPos blockPos) {

    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 5, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.glare.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}