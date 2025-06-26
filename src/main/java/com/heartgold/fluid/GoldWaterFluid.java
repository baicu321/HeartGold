package com.heartgold.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryQuery;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.*;

import java.util.Optional;

public class GoldWaterFluid extends FlowableFluid {
    @Override
    public Fluid getFlowing(){
        return ModFluids.FLOWING_GOLD_WATER;
    }
    @Override
    public Fluid getStill(){
        return ModFluids.STILL_GOLD_WATER;
    }
    /**
     * @return 给定的流体是否为该流体的实例？
     */
    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }





    /**
     * @return 流体是否可以像无限刷水的方法一样无限生成？在原版，这取决于游戏规则。
     */
    @Override
    protected boolean isInfinite(World world) {
        return false;
    }

    private void playExtinguishEvent(WorldAccess world, BlockPos pos) {
        world.syncWorldEvent(WorldEvents.LAVA_EXTINGUISHED, pos, 0);
    }
    /**
     * 流体流入一个可替换的方块时的行为。
     * 水会掉落方块的战利品表。熔岩会播放“block.lava.extinguish”音效。
     */
    @Override
    protected BlockState toBlockState(FluidState state){
        return ModFluids.GOLD_WATER_BLOCK.getDefaultState().with(Properties.LEVEL_15,getBlockStateLevel(state));
    }
    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        this.playExtinguishEvent(world, pos);

    }

    /**
     * 熔岩在其 FluidState 高于指定的高度且该流体为水时返回 true。
     *
     * @return 给定的流体能否流入它的 FluidState？
     */


    @Override
    protected boolean canBeReplacedWith(FluidState fluidState, BlockView blockView, BlockPos blockPos, Fluid fluid, Direction direction) {
        return false;
    }

    public CameraSubmersionType getSubmersionType(FluidState state) {
        return CameraSubmersionType.LAVA;
    }

    @Override
    public boolean isStill(FluidState state){
        return false;
    }

    public static class Flowing extends GoldWaterFluid{
        @Override
        protected void appendProperties(StateManager.Builder<Fluid,FluidState> builder){
            super.appendProperties(builder);
            builder.add(LEVEL);
        }
        @Override
        public int getLevel(FluidState state){return state.get(LEVEL);}
        @Override
        public boolean isStill(FluidState state){return false;}
    }

    @Override
    public int getLevel(FluidState state){
        return state.get(LEVEL);
    }

    /**
     * 或许与流入周围附近凹洞的距离检查有关？
     * 水返回4。熔岩在主世界返回2，而在下界返回4。
     */
    @Override
    protected int getFlowSpeed(WorldView worldView) {
        return 2;
    }

    @Override
    public Item getBucketItem(){
        return ModFluids.GOLD_WATER_BUCKET;
    }
    /**
     * 返回每次流动一格，其等级减少的数值。水返回1，熔岩在主世界返回2，在下界返回1。
     */
    @Override
    protected int getLevelDecreasePerBlock(WorldView worldView) {
        return 2;
    }

    /**
     * 返回每流一格需要花费的时间（按刻计算）。水返回5。熔岩在主世界返回30，在下界返回10。
     */
    @Override
    public int getTickRate(WorldView worldView) {
        return 30;
    }

    /**
     * 返回爆炸抗性。水和熔岩都返回100.0F。
     */
    @Override
    protected float getBlastResistance() {
        return 100.0F;
    }

    @Override
    public Optional<SoundEvent> getBucketFillSound() {
        return Optional.of(SoundEvents.ITEM_BUCKET_FILL_LAVA);
    }
    public static class Still extends GoldWaterFluid{
        @Override
        public int getLevel(FluidState state){
            return 8;
        }
        @Override
        public boolean isStill(FluidState state){
            return true;
        }
    }
}
