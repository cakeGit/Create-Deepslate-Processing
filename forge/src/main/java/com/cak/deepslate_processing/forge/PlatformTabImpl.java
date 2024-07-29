package com.cak.deepslate_processing.forge;

import com.cak.deepslate_processing.DPRegistry;
import com.cak.deepslate_processing.DPTabs;
import com.cak.deepslate_processing.DeepslateProcessingCommon;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.utility.Components;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class PlatformTabImpl {
    
    public static CreativeModeTab buildTab(CreativeModeTab.DisplayItemsGenerator generator) {
        return CreativeModeTab.builder()
            .title(Components.translatable("itemGroup.deepslate_processing"))
            .icon(() -> DPRegistry.DEEP_GRAVEL.asStack())
            .displayItems(generator)
            .build();
    }
    
    public static DPTabs.TabInfo register(String name, Supplier<CreativeModeTab> supplier) {
        DeferredRegister<CreativeModeTab> tabRegister = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DeepslateProcessingCommon.MOD_ID);
        
        RegistryObject<CreativeModeTab> tab = tabRegister.register(name, supplier);
        
        tabRegister.register(ExampleModForge.MOD_EVENT_BUS);
        return new DPTabs.TabInfo(tab.getKey(), tab);
    }
    
    public static <T> boolean isInCreativeTab(RegistryEntry<T> entry, DPTabs.TabInfo tabInfo) {
        //Expect forge to have a forge registry
        return CreateRegistrate.isInCreativeTab(entry, (RegistryObject<CreativeModeTab>) tabInfo.tab());
    }
    
}
