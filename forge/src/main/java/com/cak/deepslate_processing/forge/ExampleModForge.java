package com.cak.deepslate_processing.forge;

import com.cak.deepslate_processing.DPRegistry;
import com.cak.deepslate_processing.DeepslateProcessingCommon;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DeepslateProcessingCommon.MOD_ID)
public class ExampleModForge {
    public static IEventBus MOD_EVENT_BUS;
    public ExampleModForge() {
        // registrate must be given the mod event bus on forge before registration
        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        DeepslateProcessingCommon.REGISTRATE.registerEventListeners(MOD_EVENT_BUS);
        DeepslateProcessingCommon.init();
    }
    
}
