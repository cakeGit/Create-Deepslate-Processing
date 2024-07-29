package com.cak.deepslate_processing.fabric;

import com.cak.deepslate_processing.DPRegistry;
import com.cak.deepslate_processing.DPTabs;
import com.cak.deepslate_processing.DeepslateProcessingCommon;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.utility.Components;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class PlatformTabImpl {
    
    public static CreativeModeTab buildTab(CreativeModeTab.DisplayItemsGenerator generator) {
        return FabricItemGroup.builder()
            .title(Components.translatable("itemGroup.deepslate_processing"))
            .icon(() -> DPRegistry.DEEP_GRAVEL.asStack())
            .displayItems(generator)
            .build();
    }
    
    public static DPTabs.TabInfo register(String name, Supplier<CreativeModeTab> supplier) {
        ResourceLocation id = DeepslateProcessingCommon.id(name);
        ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, id);
        CreativeModeTab tab = supplier.get();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);
        return new DPTabs.TabInfo(key, ()->tab);
    }
    
    public static <T> boolean isInCreativeTab(RegistryEntry<T> entry, DPTabs.TabInfo tabInfo) {
        //Expect forge to have a forge registry
        return CreateRegistrate.isInCreativeTab(entry, tabInfo.key());
    }
    
    
}
