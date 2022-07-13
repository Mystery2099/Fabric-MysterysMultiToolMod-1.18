package net.Mystery2099.mystmultitoolmod.item;

import net.Mystery2099.mystmultitoolmod.item.custom.MultiToolItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;

import java.util.ArrayList;

public class ModItems {
    public static final MultiToolItem WOODEN_MULTI_TOOL = new MultiToolItem(ToolMaterials.WOOD, 5.0f, -3.0f, new FabricItemSettings().group(ItemGroup.TOOLS));

    public static final MultiToolItem STONE_MULTI_TOOL = new MultiToolItem(ToolMaterials.STONE, 5.0f, -3.0f, new FabricItemSettings().group(ItemGroup.TOOLS));

    public static final MultiToolItem GOLDEN_MULTI_TOOL = new MultiToolItem(ToolMaterials.GOLD, 5.0f, -3.0f, new FabricItemSettings().group(ItemGroup.TOOLS));

    public static final MultiToolItem IRON_MULTI_TOOL = new MultiToolItem(ToolMaterials.IRON, 5.0f, -3.0f, new FabricItemSettings().group(ItemGroup.TOOLS));

    public static final MultiToolItem DIAMOND_MULTI_TOOL = new MultiToolItem(ToolMaterials.DIAMOND, 5.0f, -3.0f, new FabricItemSettings().group(ItemGroup.TOOLS));

    public static final MultiToolItem NETHERITE_MULTI_TOOL = new MultiToolItem(ToolMaterials.NETHERITE, 5.0f, -3.0f, new FabricItemSettings().group(ItemGroup.TOOLS).fireproof());

    //public static final MultiToolItem WOODEN_HAMMER = new MultiToolItem(ToolMaterials.WOOD, 5.0f, -3.0f, new FabricItemSettings().group(ItemGroup.TOOLS));
    //
    //public static final MultiToolItem STONE_HAMMER = new MultiToolItem(ToolMaterials.STONE, 5.0f, -3.0f, new FabricItemSettings().group(ItemGroup.TOOLS));
    //
    //public static final MultiToolItem GOLDEN_HAMMER = new MultiToolItem(ToolMaterials.GOLD, 5.0f, -3.0f, new FabricItemSettings().group(ItemGroup.TOOLS));
    //
    //public static final MultiToolItem IRON_HAMMER = new MultiToolItem(ToolMaterials.IRON, 5.0f, -3.0f, new FabricItemSettings().group(ItemGroup.TOOLS));
    //
    //public static final MultiToolItem DIAMOND_HAMMER = new MultiToolItem(ToolMaterials.DIAMOND, 5.0f, -3.0f, new FabricItemSettings().group(ItemGroup.TOOLS));
    //
    //public static final MultiToolItem NETHERITE_HAMMER = new MultiToolItem(ToolMaterials.NETHERITE, 5.0f, -3.0f, new FabricItemSettings().group(ItemGroup.TOOLS));


    public static ArrayList<MultiToolItem> getItems() {
        ArrayList<MultiToolItem> list = new ArrayList<>();
        list.add(WOODEN_MULTI_TOOL);
        list.add(STONE_MULTI_TOOL);
        list.add(GOLDEN_MULTI_TOOL);
        list.add(IRON_MULTI_TOOL);
        list.add(DIAMOND_MULTI_TOOL);
        list.add(NETHERITE_MULTI_TOOL);
        return list;
    }
}
