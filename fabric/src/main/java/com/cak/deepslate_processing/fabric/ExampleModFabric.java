package com.cak.deepslate_processing.fabric;

import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import com.cak.deepslate_processing.DeepslateProcessingCommon;
import net.fabricmc.api.ModInitializer;

public class ExampleModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        DeepslateProcessingCommon.init();
        DeepslateProcessingCommon.LOGGER.info(EnvExecutor.unsafeRunForDist(
                () -> () -> "{} is accessing Porting Lib on a Fabric client!",
                () -> () -> "{} is accessing Porting Lib on a Fabric server!"
                ), DeepslateProcessingCommon.NAME);
        DeepslateProcessingCommon.REGISTRATE.register();
    }
}
