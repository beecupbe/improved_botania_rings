package dev.beecube31.improved_botania_rings.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = IBRCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IBRConfig {
    public static final CommonConfig COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class CommonConfig {
        public final ForgeConfigSpec.IntValue nilfheimRingCapacity;
        public final ForgeConfigSpec.IntValue muspelheimRingCapacity;
        public final ForgeConfigSpec.IntValue alfheimRingCapacity;
        public final ForgeConfigSpec.IntValue asgardRingCapacity;

        public CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.comment("Mana Spreaders configuration").push("spreaders");

            nilfheimRingCapacity = builder
                    .defineInRange("nilfheimRingCapacity", 5000000, 0, Integer.MAX_VALUE);

            muspelheimRingCapacity = builder
                    .defineInRange("muspelheimRingCapacity", 25000000, 0, Integer.MAX_VALUE);

            alfheimRingCapacity = builder
                    .defineInRange("alfheimRingCapacity", 125000000, 0, Integer.MAX_VALUE);

            asgardRingCapacity = builder
                    .defineInRange("asgardRingCapacity", 500000000, 0, Integer.MAX_VALUE);

            builder.pop();
        }
    }

    public static void register(net.minecraftforge.fml.ModLoadingContext context) {
        context.registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, COMMON_SPEC, "improved_botania_rings-common.toml");
    }
}
