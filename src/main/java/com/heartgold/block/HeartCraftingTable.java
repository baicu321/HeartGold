package com.heartgold.block;
import com.heartgold.block.entity.HeartCraftingTableEntiy;
import com.heartgold.registry.HeartBlockEntitiles;
import com.heartgold.screen.HeartCraftingScreenHandler;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class HeartCraftingTable extends BlockWithEntity {
    private static final Text TITLIE = Text.translatable("container.heart_crafting");

    public HeartCraftingTable(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (screenHandlerFactory != null)
                    player.openHandledScreen(screenHandlerFactory);
////            player.openHandledScreen(createScreenHandlerFactory(state,world, pos));
//                player.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player1) -> new HeartCraftingScreenHandler(syncId,playerInventory),Text.translatable("container.heart_crafting")));
//            player.incrementStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
//                return ActionResult.SUCCESS;
            }
            return ActionResult.SUCCESS;
        }
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HeartCraftingTableEntiy(pos, state);
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, HeartBlockEntitiles.HEART_CRAFTING_TABLE_ENTITY, HeartCraftingTableEntiy::tick);
    }
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        /*if(world.getBlockEntity(pos) != null){
            NeutronCollectorBlockEntity be = (NeutronCollectorBlockEntity) world.getBlockEntity(pos);
            int i = 0;
            if(be != null){
                DefaultedList<ItemStack> list = be.getItems();
                for(i = 0; i < list.size(); i++){
                    dropStack(world, pos, list.get(i));
                }
            }
        }*/
        super.onBreak(world, pos, state, player);
    }
}