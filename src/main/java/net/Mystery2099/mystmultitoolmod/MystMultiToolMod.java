package net.Mystery2099.mystmultitoolmod;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.Mystery2099.mystmultitoolmod.config.ModConfig;
import net.Mystery2099.mystmultitoolmod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MystMultiToolMod implements ModInitializer {
	public static final String MOD_ID = "mystmultitoolmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		registerItem("wooden_multi_tool", ModItems.WOODEN_MULTI_TOOL);
		registerItem("stone_multi_tool", ModItems.STONE_MULTI_TOOL);
		registerItem("golden_multi_tool", ModItems.GOLDEN_MULTI_TOOL);
		registerItem("iron_multi_tool", ModItems.IRON_MULTI_TOOL);
		registerItem("diamond_multi_tool", ModItems.DIAMOND_MULTI_TOOL);
		registerItem("netherite_multi_tool", ModItems.NETHERITE_MULTI_TOOL);
		//registerItem("wooden_hammer", ModItems.WOODEN_HAMMER);
		//registerItem("stone_hammer", ModItems.STONE_HAMMER);
		//registerItem("golden_hammer", ModItems.GOLDEN_HAMMER);
		//registerItem("iron_hammer", ModItems.IRON_HAMMER);
		//registerItem("diamond_hammer", ModItems.DIAMOND_HAMMER);
		//registerItem("netherite_hammer", ModItems.NETHERITE_HAMMER);
		//ModItems.registerClass();

		LOGGER.info("Hello Fabric world!");
	}
	private static Item registerItem(String name, Item item) {
		return Registry.register(Registry.ITEM, new Identifier(MOD_ID, name), item);
	}
}
