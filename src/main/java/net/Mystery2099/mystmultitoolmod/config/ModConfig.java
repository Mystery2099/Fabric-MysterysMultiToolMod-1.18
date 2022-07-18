package net.Mystery2099.mystmultitoolmod.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.Mystery2099.mystmultitoolmod.MystMultiToolMod;
import net.Mystery2099.mystmultitoolmod.config.enums.DefaultToolModel;
import net.Mystery2099.mystmultitoolmod.config.enums.ToolControls;

@Config(name = MystMultiToolMod.MOD_ID)
public class ModConfig implements ConfigData {
    //Old Config
    //@ConfigEntry.Category("multiToolModel")
    //public boolean animatedToolModel = false;
    //@ConfigEntry.Category("multiToolModel")
    //public boolean dynamicToolModel = true;
    //
    //@ConfigEntry.Category("multiToolFunctionality")
    //public boolean stripping = true;
    //@ConfigEntry.Category("multiToolFunctionality")
    //public boolean tilling = true;
    //@ConfigEntry.Category("multiToolFunctionality")
    //public boolean flattening = true;
    //@ConfigEntry.Category("multiToolFunctionality")
    //public boolean SwapShiftRightClickAndRightClickFunctions = false;


    //New Config
    @ConfigEntry.Category("multiToolModel")
    @ConfigEntry.Gui.Tooltip()
    public DefaultToolModel defaultToolModel = DefaultToolModel.HAMMER;
    @ConfigEntry.Category("multiToolModel")
    @ConfigEntry.Gui.Tooltip()
    public boolean dynamicToolModel = true;
    @ConfigEntry.Category("multiToolFunctionality")
    @ConfigEntry.Gui.Tooltip()
    public ToolControls stripping = ToolControls.BOTH;
    @ConfigEntry.Category("multiToolFunctionality")
    @ConfigEntry.Gui.Tooltip()
    public ToolControls tilling = ToolControls.RIGHT_CLICK;
    @ConfigEntry.Category("multiToolFunctionality")
    @ConfigEntry.Gui.Tooltip()
    public ToolControls flattening = ToolControls.SHIFT_RIGHT_CLICK;

    //TO-DO LIST
    //Add config stuff to en_us.json
    //Test on Server
    //Add advancements
    //Change Predicate names to tool names
    //Possibly add configurable durability-Lowest is default, highest is 5 times default or give only 2 options which would be 5 times default
    //Possibly add configurable tool attack speed and damage
}
