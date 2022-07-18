package net.Mystery2099.mystmultitoolmod;

import me.shedaniel.autoconfig.AutoConfig;
import net.Mystery2099.mystmultitoolmod.config.ModConfig;
import net.Mystery2099.mystmultitoolmod.item.ModItems;
import net.Mystery2099.mystmultitoolmod.item.custom.MultiToolItem;
import net.Mystery2099.mystmultitoolmod.config.enums.DefaultToolModel;
import net.Mystery2099.mystmultitoolmod.config.enums.ToolModes;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class MystMultiToolModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModConfig mConfig = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        for(MultiToolItem tool : ModItems.getItemList()) {
            ModelPredicateProviderRegistry.register(tool, new Identifier("animated"), (stack, world, entity, seed) -> (tool.toolMode == ToolModes.DEFAULT || !mConfig.dynamicToolModel) &&
                    mConfig.defaultToolModel == DefaultToolModel.ANIMATED ? 1 : 0);
            ModelPredicateProviderRegistry.register(tool, new Identifier("digging"), (stack, world, entity, seed) -> (tool.toolMode == ToolModes.SHOVEL && mConfig.dynamicToolModel) ||
                    (mConfig.defaultToolModel == DefaultToolModel.SHOVEL &&
                            (tool.toolMode == ToolModes.DEFAULT || !mConfig.dynamicToolModel)) ? 1 : 0);
            ModelPredicateProviderRegistry.register(tool, new Identifier("mining"), (stack, world, entity, seed) -> (tool.toolMode == ToolModes.PICKAXE && mConfig.dynamicToolModel) ||
                    (mConfig.defaultToolModel == DefaultToolModel.PICKAXE &&
                            (tool.toolMode == ToolModes.DEFAULT || !mConfig.dynamicToolModel)) ? 1 : 0);
            ModelPredicateProviderRegistry.register(tool, new Identifier("chopping"), (stack, world, entity, seed) -> (tool.toolMode == ToolModes.AXE && mConfig.dynamicToolModel) ||
                    (mConfig.defaultToolModel == DefaultToolModel.AXE &&
                            (tool.toolMode == ToolModes.DEFAULT || !mConfig.dynamicToolModel)) ? 1 : 0);
            ModelPredicateProviderRegistry.register(tool, new Identifier("hoeing"), (stack, world, entity, seed) -> (tool.toolMode == ToolModes.HOE && mConfig.dynamicToolModel) ||
                    (mConfig.defaultToolModel == DefaultToolModel.HOE &&
                            (tool.toolMode == ToolModes.DEFAULT || !mConfig.dynamicToolModel)) ? 1 : 0);
        }
    }
}
