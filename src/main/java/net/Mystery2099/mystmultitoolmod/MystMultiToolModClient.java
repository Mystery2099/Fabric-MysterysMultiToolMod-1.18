package net.Mystery2099.mystmultitoolmod;

import me.shedaniel.autoconfig.AutoConfig;
import net.Mystery2099.mystmultitoolmod.config.ModConfig;
import net.Mystery2099.mystmultitoolmod.item.ModItems;
import net.Mystery2099.mystmultitoolmod.item.custom.MultiToolItem;
import net.Mystery2099.mystmultitoolmod.config.enums.DefaultToolModel;
import net.Mystery2099.mystmultitoolmod.config.enums.ToolModes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.GlowParticle;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

public class MystMultiToolModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModConfig mConfig = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        for(MultiToolItem tool : ModItems.getItemList()) {
            ModelPredicateProviderRegistry.register(tool, new Identifier("animated"), (stack, world, entity, seed) -> (tool.getToolMode() == ToolModes.DEFAULT || !mConfig.dynamicToolModel) &&
                    mConfig.defaultToolModel == DefaultToolModel.ANIMATED ? 1 : 0);
            ModelPredicateProviderRegistry.register(tool, new Identifier("shovel"), (stack, world, entity, seed) -> (tool.getToolMode() == ToolModes.SHOVEL && mConfig.dynamicToolModel) ||
                    (mConfig.defaultToolModel == DefaultToolModel.SHOVEL &&
                            (tool.getToolMode() == ToolModes.DEFAULT || !mConfig.dynamicToolModel)) ? 1 : 0);
            ModelPredicateProviderRegistry.register(tool, new Identifier("pickaxe"), (stack, world, entity, seed) -> (tool.getToolMode() == ToolModes.PICKAXE && mConfig.dynamicToolModel) ||
                    (mConfig.defaultToolModel == DefaultToolModel.PICKAXE &&
                            (tool.getToolMode() == ToolModes.DEFAULT || !mConfig.dynamicToolModel)) ? 1 : 0);
            ModelPredicateProviderRegistry.register(tool, new Identifier("axe"), (stack, world, entity, seed) -> (tool.getToolMode() == ToolModes.AXE && mConfig.dynamicToolModel) ||
                    (mConfig.defaultToolModel == DefaultToolModel.AXE &&
                            (tool.getToolMode() == ToolModes.DEFAULT || !mConfig.dynamicToolModel)) ? 1 : 0);
            ModelPredicateProviderRegistry.register(tool, new Identifier("hoe"), (stack, world, entity, seed) -> (tool.getToolMode() == ToolModes.HOE && mConfig.dynamicToolModel) ||
                    (mConfig.defaultToolModel == DefaultToolModel.HOE &&
                            (tool.getToolMode() == ToolModes.DEFAULT || !mConfig.dynamicToolModel)) ? 1 : 0);
        }
    }
}
