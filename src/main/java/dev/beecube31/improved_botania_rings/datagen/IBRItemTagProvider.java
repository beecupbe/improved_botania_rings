package dev.beecube31.improved_botania_rings.datagen;

import dev.beecube31.improved_botania_rings.core.IBRCore;
import dev.beecube31.improved_botania_rings.core.IBRElements;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class IBRItemTagProvider extends ItemTagsProvider {
    private static final TagKey<Item> RING = tagItem("ring");

    public IBRItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagProvider, IBRCore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(RING).add(
                IBRElements.ALFHEIM_SPREADER_ITEM,
                IBRElements.MUSPELHEIM_SPREADER_ITEM,
                IBRElements.ASGARD_SPREADER_ITEM,
                IBRElements.NILFHEIM_SPREADER_ITEM
        );
    }

    private static TagKey<Item> tagItem(String name) {
        return ItemTags.create(new ResourceLocation("curios", name));
    }
}

