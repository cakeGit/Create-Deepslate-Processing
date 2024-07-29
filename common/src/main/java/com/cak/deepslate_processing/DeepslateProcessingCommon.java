package com.cak.deepslate_processing;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

public class DeepslateProcessingCommon {
    public static final String MOD_ID = "create_deepslate_processing";
    public static final String NAME = "Create: Deepslate Processing";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
    
    public static CreateRegistrate REGISTRATE = CreateRegistrate.create(DeepslateProcessingCommon.MOD_ID);
    
    public static void init() {
        LOGGER.info("{} initializing! Create version: {}", NAME, Create.VERSION);
        DPTabs.register();
        REGISTRATE.defaultCreativeTab(DPTabs.DEEPSLATE_PROCESSING.key());
        DPRegistry.register();
        DPLang.register();
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
