package dev.beecube31.improved_botania_rings.item;

import dev.beecube31.improved_botania_rings.core.IBRConfig;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.bauble.BandOfManaItem;

public class ItemAsgardRing extends BandOfManaItem {
    public ItemAsgardRing(Properties props) {
        super(props);
    }

    @Override
    public void addToCreativeTab(Item me, CreativeModeTab.Output output) {
        output.accept(this);
        ItemStack full = new ItemStack(this);
        setMana(full, IBRConfig.COMMON.asgardRingCapacity.get());
        output.accept(full);
    }

    public static class ItemAsgardRingImpl extends ManaItemImpl implements ISpecialManaItem {
        public ItemAsgardRingImpl(ItemStack stack) {
            super(stack);
        }

        @Override
        public int getMaxMana() {
            return IBRConfig.COMMON.asgardRingCapacity.get();
        }

        @Override
        public int getMana() {
            return ItemNBTHelper.getInt(this.stack, "mana", 0);
        }

        @Override
        public void addMana(int mana) {
            BandOfManaItem.setMana(this.stack, Math.min(this.getMana() + mana, this.getMaxMana()));
        }

        @Override
        public int getChargeRatePerTick() {
            return Math.max(1, getMaxMana() / 1000);
        }
    }
}
