package com.heartgold.entity.custom;

import com.heartgold.entity.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GoldPigEntity extends AnimalEntity {
    public final AnimationState walkAnimationState = new AnimationState();
    private int walkAnimationTimeOut = 0;

    private void setUpAnimationState(){
        if (this.walkAnimationTimeOut <= 0){
            this.walkAnimationTimeOut = this.random.nextInt(40)+80;
        }
    }

    public GoldPigEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick(){
        super.tick();
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f,1.0f):0.0f;
        this.limbAnimator.updateLimbs(f,0.2f);
    }

    @Override
    public void initGoals(){
        this.goalSelector.add(0,new SwimGoal(this));
        this.goalSelector.add(1,new EscapeDangerGoal(this, 1.25));
        this.goalSelector.add(3,new AnimalMateGoal(this,1.00));
        this.goalSelector.add(4,new TemptGoal(this,1.25, Ingredient.ofItems(Items.GOLDEN_CARROT),false));
        this.goalSelector.add(5,new FollowParentGoal(this,1.20));
        this.goalSelector.add(6,new WanderAroundGoal(this,1.00D));
        this.goalSelector.add(7,new LookAtEntityGoal(this, PlayerEntity.class,6.0f));
        this.goalSelector.add(8,new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createGoldPigAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.25F)
                .add(EntityAttributes.GENERIC_ARMOR,5F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PIG_AMBIENT;
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_PIG_HURT;
    }
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PIG_DEATH;
    }
    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
    }


    @Override
    public boolean isBreedingItem(ItemStack stack){
        return stack.isOf(Items.GOLDEN_CARROT);
    }
    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.GOLDPIG.create(world);
    }
}
