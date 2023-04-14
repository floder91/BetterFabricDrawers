package io.github.floder91.betterfabricdrawers.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BetterFabricDrawersDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        var pack = dataGenerator.createPack();

        pack.addProvider(DrawersModelProvider::new);
        pack.addProvider(DrawersBlockLootTableProvider::new);
        pack.addProvider(DrawersRecipeProvider::new);
        var blockTagProvider = pack.addProvider(DrawersBlockTagProvider::new);
        pack.addProvider((output, future) -> new DrawersItemTagProvider(output, future, blockTagProvider));
        pack.addProvider(ReadmeDataProvider::new);
    }
}
