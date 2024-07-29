package com.cak.deepslate_processing;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class PlatformTab {
    
    @ExpectPlatform
    public static CreativeModeTab buildTab(CreativeModeTab.DisplayItemsGenerator generator) {
        throw new AssertionError("Platform Expected");
    }
    
    @ExpectPlatform
    public static DPTabs.TabInfo register(String name, Supplier<CreativeModeTab> supplier) {
        throw new AssertionError("Platform Expected");
    }
    
    @ExpectPlatform
    public static <T> boolean isInCreativeTab(RegistryEntry<T> entry, DPTabs.TabInfo tabInfo) {
        throw new AssertionError("Platform Expected");
    }
    
}
