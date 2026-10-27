package com.craigtec.leakyshulkerbox;

import com.craigtec.leakyshulkerbox.datagen.ModBlockLootTableProvider;
import com.craigtec.leakyshulkerbox.datagen.ModBlockTagsProvider;
import com.craigtec.leakyshulkerbox.datagen.ModModelProvider;
import com.craigtec.leakyshulkerbox.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class LeakyShulkerBoxDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
	}
}
