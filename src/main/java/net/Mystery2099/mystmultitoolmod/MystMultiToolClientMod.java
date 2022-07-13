package net.Mystery2099.mystmultitoolmod;

import me.shedaniel.autoconfig.AutoConfig;
import net.Mystery2099.mystmultitoolmod.config.ModConfig;
import net.Mystery2099.mystmultitoolmod.item.ModItems;
import net.Mystery2099.mystmultitoolmod.item.custom.MultiToolItem;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class MystMultiToolClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        for(MultiToolItem tool : ModItems.getItems()) {
            ModelPredicateProviderRegistry.register(tool, new Identifier("default"), (stack, world, entity, seed) -> (tool.toolMode.contains("default") && config.toolModelAnimated != true) || (!config.dynamicToolAppearance && config.toolModelAnimated != true)  ? 1 : 0);
            ModelPredicateProviderRegistry.register(tool, new Identifier("animated"), (stack, world, entity, seed) -> (tool.toolMode.contains("default") && config.toolModelAnimated) || (!config.dynamicToolAppearance && config.toolModelAnimated) ? 1 : 0);
            ModelPredicateProviderRegistry.register(tool, new Identifier("fighting"), (stack, world, entity, seed) -> tool.toolMode.contains("isSword") && config.dynamicToolAppearance ? 1 : 0);
            ModelPredicateProviderRegistry.register(tool, new Identifier("digging"), (stack, world, entity, seed) -> tool.toolMode.contains("isShovel") && config.dynamicToolAppearance ? 1 : 0);
            ModelPredicateProviderRegistry.register(tool, new Identifier("mining"), (stack, world, entity, seed) -> tool.toolMode.contains("isPickaxe") && config.dynamicToolAppearance ? 1 : 0);
            ModelPredicateProviderRegistry.register(tool, new Identifier("chopping"), (stack, world, entity, seed) -> tool.toolMode.contains("isAxe") && config.dynamicToolAppearance ? 1 : 0);
            ModelPredicateProviderRegistry.register(tool, new Identifier("hoeing"), (stack, world, entity, seed) -> tool.toolMode.contains("isHoe") && config.dynamicToolAppearance ? 1 : 0);
        }
    }
}
