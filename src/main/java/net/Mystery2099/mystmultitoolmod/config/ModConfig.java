package net.Mystery2099.mystmultitoolmod.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.Mystery2099.mystmultitoolmod.MystMultiToolMod;

@Config(name = MystMultiToolMod.MOD_ID)
public class ModConfig implements ConfigData {
    @ConfigEntry.Category("multiToolModel")
    public boolean toolModelAnimated = false;
    @ConfigEntry.Category("multiToolModel")
    public boolean dynamicToolAppearance = true;


    @ConfigEntry.Category("multiToolFunctionality")
    public boolean stripping = true;
    @ConfigEntry.Category("multiToolFunctionality")
    public boolean tilling = true;
    @ConfigEntry.Category("multiToolFunctionality")
    public boolean pathMaking = true;
    @ConfigEntry.Category("multiToolFunctionality")
    public boolean shiftRightClickToTill = false;


    //Add if statement for showing shiftRightClickToTill only if tilling  & pathmaking are true
}
