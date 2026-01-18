package dev.beecube31.improved_botania_rings.core;

import com.google.common.base.Suppliers;
import dev.beecube31.improved_botania_rings.item.ItemAlfheimRing;
import dev.beecube31.improved_botania_rings.item.ItemAsgardRing;
import dev.beecube31.improved_botania_rings.item.ItemMuspelheimRing;
import dev.beecube31.improved_botania_rings.item.ItemNilfheimRing;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import vazkii.botania.api.BotaniaForgeCapabilities;
import vazkii.botania.api.BotaniaRegistries;
import vazkii.botania.api.mana.ManaItem;
import vazkii.botania.common.item.CustomCreativeTabContents;
import vazkii.botania.forge.CapabilityUtil;

import java.util.*;
import java.util.function.*;

import static vazkii.botania.common.lib.ResourceLocationHelper.prefix;

@Mod(IBRCore.MODID)
@Mod.EventBusSubscriber(modid = IBRCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IBRCore {
    public static final String MODID = "improved_botania_rings";

    private final Set<Item> itemsToAddToCreativeTab = new LinkedHashSet<>();

    private static final Supplier<Map<Item, Function<ItemStack, ManaItem>>> MANA_ITEM = Suppliers.memoize(() -> Map.of(
            IBRElements.NILFHEIM_SPREADER_ITEM, ItemNilfheimRing.ItemNilfheimRingImpl::new,
            IBRElements.MUSPELHEIM_SPREADER_ITEM, ItemMuspelheimRing.ItemMuspelheimRingImpl::new,
            IBRElements.ALFHEIM_SPREADER_ITEM, ItemAlfheimRing.ItemAlfheimRingImpl::new,
            IBRElements.ASGARD_SPREADER_ITEM, ItemAsgardRing.ItemAsgardRingImpl::new
    ));

    public IBRCore() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::addCreative);

        IBRConfig.register(net.minecraftforge.fml.ModLoadingContext.get());

        bindForItems(IBRElements::registerItems);

        bus.addListener((BuildCreativeModeTabContentsEvent e) -> {
            if (e.getTabKey() == BotaniaRegistries.BOTANIA_TAB_KEY) {
                for (Item item : this.itemsToAddToCreativeTab) {
                    if (item instanceof CustomCreativeTabContents cc) {
                        cc.addToCreativeTab(item, e);
                    } else if (item instanceof BlockItem bi && bi.getBlock() instanceof CustomCreativeTabContents cc) {
                        cc.addToCreativeTab(item, e);
                    } else {
                        e.accept(item);
                    }
                }
            }
        });

        MinecraftForge.EVENT_BUS.addGenericListener(ItemStack.class, IBRCore::attachItemCaps);
    }

    private static void attachItemCaps(AttachCapabilitiesEvent<ItemStack> e) {
        var stack = e.getObject();
        var makeManaItem = MANA_ITEM.get().get(stack.getItem());
        if (makeManaItem != null) {
            e.addCapability(prefix("mana_item"),
                    CapabilityUtil.makeProvider(BotaniaForgeCapabilities.MANA_ITEM, makeManaItem.apply(stack)));
        }
    }

    private void bindForItems(Consumer<BiConsumer<Item, ResourceLocation>> source) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener((RegisterEvent event) -> {
            if (event.getRegistryKey().equals(Registries.ITEM)) {
                source.accept((t, rl) -> {
                    itemsToAddToCreativeTab.add(t);
                    event.register(Registries.ITEM, rl, () -> t);
                });
            }
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == BotaniaRegistries.BOTANIA_TAB_KEY) {
            event.accept(IBRElements.NILFHEIM_SPREADER_ITEM);
            event.accept(IBRElements.MUSPELHEIM_SPREADER_ITEM);
            event.accept(IBRElements.ALFHEIM_SPREADER_ITEM);
            event.accept(IBRElements.ASGARD_SPREADER_ITEM);
        }
    }
}