package com.cak.deepslate_processing.fabric;

import com.cak.deepslate_processing.DPRegistry;
import com.cak.deepslate_processing.DeepslateProcessingCommon;
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class DPData implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator gen) {
        DeepslateProcessingCommon.LOGGER.info("Hello from datagen!");
        Path resources = Paths.get(System.getProperty(ExistingFileHelper.EXISTING_RESOURCES));
        ExistingFileHelper helper = new ExistingFileHelper(
            Set.of(resources), Set.of("create"), false, null, null
        );
        DeepslateProcessingCommon.REGISTRATE.setupDatagen(gen.createPack(), helper);
        
    }
}
