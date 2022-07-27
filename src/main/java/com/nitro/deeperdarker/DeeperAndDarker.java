package com.nitro.deeperdarker;

import com.nitro.deeperdarker.registry.blocks.DDBlocks;
import com.nitro.deeperdarker.registry.items.DDItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DeeperAndDarker.MOD_ID)
public class DeeperAndDarker {
    public static final String MOD_ID = "deeperdarker";

    public DeeperAndDarker() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        DDBlocks.BLOCKS.register(eventBus);
        DDItems.ITEMS.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
