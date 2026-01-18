package dev.beecube31.improved_botania_rings.datagen;

import dev.beecube31.improved_botania_rings.core.IBRElements;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.item.BotaniaItems;

import java.util.function.Consumer;

public class IBRRecipeProvder extends RecipeProvider {

    public IBRRecipeProvder(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.TOOLS, IBRElements.NILFHEIM_SPREADER_ITEM)
                .define('P', BotaniaItems.runePride)
                .define('T', BotaniaItems.terrasteel)
                .define('S', BotaniaBlocks.shimmerrock)
                .define('F', BotaniaItems.manaRingGreater)
                .pattern("   ")
                .pattern("PFP")
                .pattern("STS")
                .unlockedBy("has_string", has(BotaniaItems.manaRingGreater))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(RecipeCategory.TOOLS, IBRElements.MUSPELHEIM_SPREADER_ITEM)
                .define('P', BotaniaItems.runeSpring)
                .define('S', BotaniaBlocks.elementiumBlock)
                .define('F', IBRElements.NILFHEIM_SPREADER_ITEM)
                .define('G', BotaniaItems.lifeEssence)
                .pattern("   ")
                .pattern("PFP")
                .pattern("SGS")
                .unlockedBy("has_string", has(IBRElements.NILFHEIM_SPREADER_ITEM))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(RecipeCategory.TOOLS, IBRElements.ALFHEIM_SPREADER_ITEM)
                .define('P', BotaniaItems.runeGluttony)
                .define('S', BotaniaBlocks.elementiumBlock)
                .define('F', IBRElements.MUSPELHEIM_SPREADER_ITEM)
                .define('G', BotaniaItems.gaiaIngot)
                .pattern("   ")
                .pattern("PFP")
                .pattern("SGS")
                .unlockedBy("has_string", has(IBRElements.MUSPELHEIM_SPREADER_ITEM))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(RecipeCategory.TOOLS, IBRElements.ASGARD_SPREADER_ITEM)
                .define('P', BotaniaItems.runeEnvy)
                .define('S', BotaniaBlocks.terrasteelBlock)
                .define('F', IBRElements.ALFHEIM_SPREADER_ITEM)
                .define('G', BotaniaItems.gaiaIngot)
                .pattern("   ")
                .pattern("PFP")
                .pattern("GSG")
                .unlockedBy("has_string", has(IBRElements.ALFHEIM_SPREADER_ITEM))
                .save(consumer);

    }
}
