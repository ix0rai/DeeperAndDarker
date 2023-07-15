package com.kyanite.deeperdarker.blocks;

import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;

public class SculkVinesBlock extends AbstractPlantStemBlock {
    private static final VoxelShape SHAPE = Block.createCuboidShape(4, 9, 4, 12, 16, 12);

    protected SculkVinesBlock(Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false, 0.1);
    }

    @Override
    protected int getGrowthLength(Random random) {
        return 15;
    }

    @Override
    protected boolean chooseStemState(BlockState state) {
        return state.isAir();
    }

    @Override
    protected Block getPlant() {
        return DeeperDarkerBlocks.SCULK_VINES_PLANT;
    }

    @Override
    protected void dropExperienceWhenMined(ServerWorld world, BlockPos pos, ItemStack tool, IntProvider experience) {
        if (EnchantmentHelper.hasSilkTouch(tool)) this.dropExperience(world, pos, 1);
    }
}