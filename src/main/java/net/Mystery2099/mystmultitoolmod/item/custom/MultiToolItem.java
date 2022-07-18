package net.Mystery2099.mystmultitoolmod.item.custom;

import com.mojang.datafixers.util.Pair;
import me.shedaniel.autoconfig.AutoConfig;
import net.Mystery2099.mystmultitoolmod.config.ModConfig;
import net.Mystery2099.mystmultitoolmod.util.enums.ToolControls;
import net.Mystery2099.mystmultitoolmod.util.enums.ToolModes;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.Oxidizable;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoneycombItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static net.minecraft.tag.BlockTags.*;

public class MultiToolItem extends MultiToolAbstractItem {
    public ToolModes toolMode = ToolModes.DEFAULT;

    public MultiToolItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }


    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(2, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        toolMode = ToolModes.DEFAULT;
        return true;
    }


    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        toolMode = state.isIn(SHOVEL_MINEABLE) ? ToolModes.SHOVEL :
                state.isIn(PICKAXE_MINEABLE) ? ToolModes.PICKAXE :
                        state.isIn(AXE_MINEABLE) ? ToolModes.AXE :
                                state.isIn(HOE_MINEABLE) ? ToolModes.HOE : ToolModes.DEFAULT;
        if (!world.isClient && state.getHardness(world, pos) != 0.0f) {
            stack.damage(1, miner, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
        return true;
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        BlockState blockState = world.getBlockState(blockPos);

        //Axe functionality
        Optional<BlockState> optional = this.getStrippedState(blockState);
        Optional<BlockState> optional2 = Oxidizable.getDecreasedOxidationState(blockState);
        Optional<BlockState> optional3 = Optional.ofNullable(HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get().get(blockState.getBlock())).map(block -> block.getStateWithProperties(blockState));
        ItemStack itemStack = context.getStack();
        Optional<BlockState> optional4 = Optional.empty();
        if (config.strippingConfig == ToolControls.BOTH || (config.strippingConfig == ToolControls.RIGHT_CLICK ? !playerEntity.isSneaking() :
                config.strippingConfig == ToolControls.SHIFT_RIGHT_CLICK ? playerEntity.isSneaking() : false)) {
            if (optional.isPresent()) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);
                optional4 = optional;
            } else if (optional2.isPresent()) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.syncWorldEvent(playerEntity, WorldEvents.BLOCK_SCRAPED, blockPos, 0);
                optional4 = optional2;
            } else if (optional3.isPresent()) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.syncWorldEvent(playerEntity, WorldEvents.WAX_REMOVED, blockPos, 0);
                optional4 = optional3;
            }
            if (optional4.isPresent()) {
                if (playerEntity instanceof ServerPlayerEntity) {
                    Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) playerEntity, blockPos, itemStack);
                }
                world.setBlockState(blockPos, optional4.get(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                if (playerEntity != null) {
                    itemStack.damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
                }
                toolMode = ToolModes.AXE;
                return ActionResult.success(world.isClient);
            }
        }
        //Hoe functionality
        if (config.tillingConfig == ToolControls.BOTH || (config.tillingConfig == ToolControls.RIGHT_CLICK ? !playerEntity.isSneaking() :
                config.tillingConfig == ToolControls.SHIFT_RIGHT_CLICK ? playerEntity.isSneaking() : false)) {
            Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>> pair = TILLING_ACTIONS.get(world.getBlockState(blockPos = context.getBlockPos()).getBlock());
            if (pair == null) return ActionResult.PASS;
            Predicate<ItemUsageContext> predicate = pair.getFirst();
            Consumer<ItemUsageContext> consumer = pair.getSecond();
            if (predicate.test(context)) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                if (!world.isClient) {
                    consumer.accept(context);
                    if (playerEntity != null) {
                        context.getStack().damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
                    }
                }
                toolMode = ToolModes.HOE;
                return ActionResult.success(world.isClient);
            }
        }
        //Shovel Functionality
        if (config.flatteningConfig == ToolControls.BOTH || (config.flatteningConfig == ToolControls.RIGHT_CLICK ? !playerEntity.isSneaking() :
                config.flatteningConfig == ToolControls.SHIFT_RIGHT_CLICK ? playerEntity.isSneaking() : false)) {
            if (context.getSide() != Direction.DOWN) {
                BlockState blockState2 = PATH_STATES.get(blockState.getBlock());
                BlockState blockState3 = null;
                if (blockState2 != null && world.getBlockState(blockPos.up()).isAir()) {
                    world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    blockState3 = blockState2;
                } else if (blockState.getBlock() instanceof CampfireBlock && blockState.get(CampfireBlock.LIT)) {
                    if (!world.isClient()) {
                        world.syncWorldEvent(null, WorldEvents.FIRE_EXTINGUISHED, blockPos, 0);
                    }
                    CampfireBlock.extinguish(context.getPlayer(), world, blockPos, blockState);
                    blockState3 = blockState.with(CampfireBlock.LIT, false);
                }
                if (blockState3 != null) {
                    if (!world.isClient) {
                        world.setBlockState(blockPos, blockState3, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                        if (playerEntity != null) {
                            context.getStack().damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
                        }
                    }
                    toolMode = ToolModes.SHOVEL;
                    return ActionResult.success(world.isClient);
                }
                return ActionResult.PASS;
            }
        }
        return ActionResult.PASS;
    }

    //public String getToolMode() {
    //    AttackBlockCallback.EVENT.register((player, world1, hand, pos, direction)-> {
    //        toolMode = (world1.getBlockState(pos).isIn(BlockTags.SHOVEL_MINEABLE)) ? ToolModes.SHOVEL :
    //                (world1.getBlockState(pos).isIn(BlockTags.PICKAXE_MINEABLE)) ? ToolModes.PICKAXE :
    //                        (world1.getBlockState(pos).isIn(BlockTags.AXE_MINEABLE)) ? ToolModes.AXE :
    //                                (world1.getBlockState(pos).isIn(BlockTags.HOE_MINEABLE)) ? ToolModes.HOE : ToolModes.DEFAULT;
    //        return ActionResult.SUCCESS;
    //    });
    //    return toolMode;
    //}
}
