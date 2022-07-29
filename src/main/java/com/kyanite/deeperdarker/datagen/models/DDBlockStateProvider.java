package com.kyanite.deeperdarker.datagen.models;

import com.kyanite.deeperdarker.DeeperAndDarker;
import com.kyanite.deeperdarker.registry.blocks.DDBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class DDBlockStateProvider extends BlockStateProvider {
    public DDBlockStateProvider(DataGenerator pGenerator, ExistingFileHelper pExistingFileHelper) {
        super(pGenerator, DeeperAndDarker.MOD_ID, pExistingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(DDBlocks.BONE_PLANKS.get());
        slabBlock(DDBlocks.BONE_SLAB.get(), blockLoc(DDBlocks.BONE_PLANKS), blockLoc(DDBlocks.BONE_PLANKS));
        stairsBlock(DDBlocks.BONE_STAIRS.get(), blockLoc(DDBlocks.BONE_PLANKS));
        fenceBlock(DDBlocks.BONE_FENCE, blockLoc(DDBlocks.BONE_PLANKS));
        buttonBlock(DDBlocks.BONE_BUTTON, blockLoc(DDBlocks.BONE_PLANKS));
        pressurePlateBlock(DDBlocks.BONE_PRESSURE_PLATE.get(), blockLoc(DDBlocks.BONE_PLANKS));
        doorBlockWithRenderType(DDBlocks.BONE_DOOR.get(), blockLoc(DDBlocks.BONE_DOOR, "bottom"), blockLoc(DDBlocks.BONE_DOOR, "top"), "translucent");
        trapdoorBlockWithRenderType(DDBlocks.BONE_TRAPDOOR.get(), blockLoc(DDBlocks.BONE_TRAPDOOR), true, "translucent");
        fenceGateBlock(DDBlocks.BONE_FENCE_GATE.get(), blockLoc(DDBlocks.BONE_PLANKS));

        axisBlock(DDBlocks.SCULK_BONE_BLOCK.get(), blockLoc(DDBlocks.SCULK_BONE_BLOCK, "side"), blockLoc(DDBlocks.SCULK_BONE_BLOCK, "top"));
        simpleBlock(DDBlocks.SCULK_BONE_PLANKS.get());
        slabBlock(DDBlocks.SCULK_BONE_SLAB.get(), blockLoc(DDBlocks.SCULK_BONE_PLANKS), blockLoc(DDBlocks.SCULK_BONE_PLANKS));
        stairsBlock(DDBlocks.SCULK_BONE_STAIRS.get(), blockLoc(DDBlocks.SCULK_BONE_PLANKS));
        fenceBlock(DDBlocks.SCULK_BONE_FENCE, blockLoc(DDBlocks.SCULK_BONE_PLANKS));
        buttonBlock(DDBlocks.SCULK_BONE_BUTTON, blockLoc(DDBlocks.SCULK_BONE_PLANKS));
        pressurePlateBlock(DDBlocks.SCULK_BONE_PRESSURE_PLATE.get(), blockLoc(DDBlocks.SCULK_BONE_PLANKS));
        doorBlockWithRenderType(DDBlocks.SCULK_BONE_DOOR.get(), blockLoc(DDBlocks.SCULK_BONE_DOOR, "bottom"), blockLoc(DDBlocks.SCULK_BONE_DOOR, "top"), "translucent");
        trapdoorBlockWithRenderType(DDBlocks.SCULK_BONE_TRAPDOOR.get(), blockLoc(DDBlocks.SCULK_BONE_TRAPDOOR), true, "translucent");
        fenceGateBlock(DDBlocks.SCULK_BONE_FENCE_GATE.get(), blockLoc(DDBlocks.SCULK_BONE_PLANKS));

        simpleBlock(DDBlocks.SCULK_GLEAM.get());
    }

    public void fenceBlock(RegistryObject<FenceBlock> block, ResourceLocation texture) {
        super.fenceBlock(block.get(), texture);
        models().fenceInventory(block.getId().toString() + "_inventory", texture);
    }

    public void buttonBlock(RegistryObject<ButtonBlock> block, ResourceLocation texture) {
        super.buttonBlock(block.get(), texture);
        models().buttonInventory(block.getId().toString() + "_inventory", texture);
    }

    public ResourceLocation blockLoc(RegistryObject<? extends Block> block) {
        return new ResourceLocation(DeeperAndDarker.MOD_ID, "block/" + block.getId().getPath());
    }

    public ResourceLocation blockLoc(RegistryObject<? extends Block> block, String suffix) {
        return new ResourceLocation(DeeperAndDarker.MOD_ID, "block/" + block.getId().getPath() + "_" + suffix);
    }
}