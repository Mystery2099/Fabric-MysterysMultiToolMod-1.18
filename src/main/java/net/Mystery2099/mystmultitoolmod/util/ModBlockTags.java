package net.Mystery2099.mystmultitoolmod.util;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.Mystery2099.mystmultitoolmod.MystMultiToolMod.MOD_ID;

public class ModBlockTags {
    public static TagKey<Block> MULTITOOL_MINEABLE = registerTag("mineable/multitool");

    private static TagKey<Block> registerTag(String name) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(MOD_ID, name));
    }
}
