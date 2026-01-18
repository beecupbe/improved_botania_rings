package dev.beecube31.improved_botania_rings.datagen;

import dev.beecube31.improved_botania_rings.core.IBRCore;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = IBRCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IBRDatagen {

    public IBRDatagen() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        IBRBlockTagProvider prov = new IBRBlockTagProvider(output, lookupProvider, existingFileHelper);

        if (event.includeServer()) {
            generator.addProvider(true, new IBRRecipeProvder(output));
            generator.addProvider(true, prov);
            generator.addProvider(true, new IBRItemTagProvider(output, lookupProvider, prov.contentsGetter(), existingFileHelper));
        }
    }
}
