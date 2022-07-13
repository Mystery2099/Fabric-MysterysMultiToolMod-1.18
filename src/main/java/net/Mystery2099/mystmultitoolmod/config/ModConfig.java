package net.Mystery2099.mystmultitoolmod.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.Mystery2099.mystmultitoolmod.MystMultiToolMod;

@Config(name = MystMultiToolMod.MOD_ID)
public class ModConfig implements ConfigData {
    public boolean animatedToolModel = false;
    public boolean dynamicToolAppearance = true;
}
