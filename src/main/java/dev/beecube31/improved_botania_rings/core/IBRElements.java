package dev.beecube31.improved_botania_rings.core;

import dev.beecube31.improved_botania_rings.item.ItemAlfheimRing;
import dev.beecube31.improved_botania_rings.item.ItemAsgardRing;
import dev.beecube31.improved_botania_rings.item.ItemMuspelheimRing;
import dev.beecube31.improved_botania_rings.item.ItemNilfheimRing;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.equipment.bauble.BandOfManaItem;

import java.util.function.BiConsumer;

public abstract class IBRElements {
    private static final Item.Properties DEFAULT_ITEM_BUILDER = BotaniaItems.defaultBuilder().stacksTo(1);

    public static final BandOfManaItem NILFHEIM_SPREADER_ITEM = new ItemNilfheimRing(DEFAULT_ITEM_BUILDER);
    public static final BandOfManaItem MUSPELHEIM_SPREADER_ITEM = new ItemMuspelheimRing(DEFAULT_ITEM_BUILDER);
    public static final BandOfManaItem ALFHEIM_SPREADER_ITEM = new ItemAlfheimRing(DEFAULT_ITEM_BUILDER);
    public static final BandOfManaItem ASGARD_SPREADER_ITEM = new ItemAsgardRing(DEFAULT_ITEM_BUILDER);

    public static void registerItems(BiConsumer<Item, ResourceLocation> r) {
        r.accept(NILFHEIM_SPREADER_ITEM, prefix("nilfheim_ring"));
        r.accept(MUSPELHEIM_SPREADER_ITEM, prefix("muspelheim_ring"));
        r.accept(ALFHEIM_SPREADER_ITEM, prefix("alfheim_ring"));
        r.accept(ASGARD_SPREADER_ITEM, prefix("asgard_ring"));
    }

    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(IBRCore.MODID, path);
    }
}
