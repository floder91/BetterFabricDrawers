package io.github.floder91.betterfabricdrawers.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BetterFabricDrawersDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        dataGenerator.addProvider(DrawersModelProvider::new);
        dataGenerator.addProvider(DrawersBlockLootTableProvider::new);
        dataGenerator.addProvider(DrawersRecipeProvider::new);
        var blockTagProvider = dataGenerator.addProvider(DrawersBlockTagProvider::new);
        dataGenerator.addProvider(new DrawersItemTagProvider(dataGenerator, blockTagProvider));
        dataGenerator.addProvider(ReadmeDataProvider::new);
    }
}
