package com.cout970.magneticraft.registry

import com.cout970.magneticraft.IBlockState
import com.cout970.magneticraft.api.MagneticraftApi
import com.cout970.magneticraft.api.internal.registries.generators.thermopile.ThermopileRecipeManager
import com.cout970.magneticraft.api.internal.registries.machines.crushingtable.CrushingTableRecipeManager
import com.cout970.magneticraft.api.internal.registries.machines.gasificationunit.GasificationUnitRecipeManager
import com.cout970.magneticraft.api.internal.registries.machines.grinder.GrinderRecipeManager
import com.cout970.magneticraft.api.internal.registries.machines.hydraulicpress.HydraulicPressRecipeManager
import com.cout970.magneticraft.api.internal.registries.machines.oilheater.OilHeaterRecipeManager
import com.cout970.magneticraft.api.internal.registries.machines.refinery.RefineryRecipeManager
import com.cout970.magneticraft.api.internal.registries.machines.sieve.SieveRecipeManager
import com.cout970.magneticraft.api.internal.registries.machines.sluicebox.SluiceBoxRecipeManager
import com.cout970.magneticraft.api.registries.machines.hydraulicpress.HydraulicPressMode
import com.cout970.magneticraft.api.registries.machines.hydraulicpress.HydraulicPressMode.*
import com.cout970.magneticraft.features.items.CraftingItems
import com.cout970.magneticraft.features.items.EnumMetal
import com.cout970.magneticraft.features.items.EnumMetal.*
import com.cout970.magneticraft.misc.*
import com.cout970.magneticraft.misc.inventory.stack
import com.cout970.magneticraft.misc.inventory.toBlockState
import com.cout970.magneticraft.misc.inventory.withSize
import com.cout970.magneticraft.systems.integration.ItemHolder
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.Blocks.COBBLESTONE
import net.minecraft.block.SnowBlock
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.Items.GOLD_INGOT
import net.minecraft.item.Items.IRON_INGOT
import net.minecraftforge.fluids.FluidStack
import com.cout970.magneticraft.features.ores.Blocks.OreType as BlockOreType


/**
 * Created by cout970 on 11/06/2016.
 * Modified by Yurgen
 *
 * Called by CommonProxy to register all the recipes in the mod
 */
fun registerRecipes() {

    //@formatter:off

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                                  GRINDER RECIPES
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    EnumMetal.values().forEach { metal ->
        metal.getOres().firstOrNull()?.let {
            addGrinderRecipe(it, metal.getRockyChunk(), Blocks.GRAVEL.stack(), 0.15f, 50f)
        }
        if (metal.subComponents.isEmpty()) {
            addGrinderRecipe(metal.getIngot(), metal.getDust(), ItemStack.EMPTY, 0.0f, 50f)
        }
    }
    ItemHolder.tinOre?.let {
        addGrinderRecipe(it, TIN.getRockyChunk(), Blocks.GRAVEL.stack(), 0.15f, 50f)
    }
    ItemHolder.osmiumOre?.let {
        addGrinderRecipe(it, OSMIUM.getRockyChunk(), Blocks.GRAVEL.stack(), 0.15f, 50f)
    }
    ItemHolder.dimensionalShard?.let { shard ->
        ItemHolder.dimensionalShardOre0?.let { ore ->
            addGrinderRecipe(ore, shard.withSize(4), shard.withSize(1), 0.5f, 50f)
        }
        ItemHolder.dimensionalShardOre1?.let { ore ->
            addGrinderRecipe(ore, shard.withSize(4), shard.withSize(1), 0.5f, 50f)
        }
        ItemHolder.dimensionalShardOre2?.let { ore ->
            addGrinderRecipe(ore, shard.withSize(4), shard.withSize(1), 0.5f, 50f)
        }
    }

    addGrinderRecipe(Blocks.REDSTONE_ORE.stack(), Items.REDSTONE.stack(4), Blocks.GRAVEL.stack(), 0.15f, 50f)
//    addGrinderRecipe(Blocks.LAPIS_ORE.stack(), Items.DYE.stack(6, 4), Blocks.GRAVEL.stack(), 0.15f, 50f)
    addGrinderRecipe(Blocks.NETHER_QUARTZ_ORE.stack(), Items.QUARTZ.stack(3), Items.QUARTZ.stack(1), 0.5f, 60f)
    addGrinderRecipe(Blocks.EMERALD_ORE.stack(), Items.EMERALD.stack(2), Blocks.GRAVEL.stack(), 0.15f, 50f)
    addGrinderRecipe(Blocks.DIAMOND_ORE.stack(), Items.DIAMOND.stack(1), Items.DIAMOND.stack(1), 0.75f, 50f)
    addGrinderRecipe(Blocks.COAL_ORE.stack(), Items.COAL.stack(1), Items.COAL.stack(1), 0.5f, 50f)

    addGrinderRecipe(Blocks.GLOWSTONE.stack(), Items.GLOWSTONE_DUST.stack(4), ItemStack.EMPTY, 0.0f, 40f)
    addGrinderRecipe(Blocks.SANDSTONE.stack(), Blocks.SAND.stack(4), ItemStack.EMPTY, 0.0f, 40f)
    addGrinderRecipe(Blocks.RED_SANDSTONE.stack(), Blocks.RED_SAND.stack(4), ItemStack.EMPTY, 0.0f, 40f)
    addGrinderRecipe(Items.BLAZE_ROD.stack(), Items.BLAZE_POWDER.stack(4), CraftingItems.sulfur.stack(), 0.5f, 50f)
//    addGrinderRecipe(Blocks.WOOL.stack(), Items.STRING.stack(4), ItemStack.EMPTY, 0.0f, 40f)
//    addGrinderRecipe(Items.BONE.stack(), Items.DYE.stack(5, 15), Items.DYE.stack(3, 15), 0.5f, 40f)
//    addGrinderRecipe(Items.REEDS.stack(), Items.SUGAR.stack(1), Items.SUGAR.stack(2), 0.5f, 40f)
    addGrinderRecipe(COBBLESTONE.stack(), Blocks.GRAVEL.stack(1), Blocks.SAND.stack(1), 0.5f, 60f)
    addGrinderRecipe(Blocks.QUARTZ_BLOCK.stack(), Items.QUARTZ.stack(4), ItemStack.EMPTY, 0.0f, 50f)

//    addGrinderRecipe(DecorationBlocks.limestone.stack(), DecorationBlocks.limestone.stack(meta = 2), ItemStack.EMPTY, 0.0f, 20f)
//    addGrinderRecipe(DecorationBlocks.burnLimestone.stack(), DecorationBlocks.burnLimestone.stack(meta = 2), ItemStack.EMPTY, 0.0f, 20f)

    addGrinderRecipe(BlockOreType.PYRITE.stack(1), CraftingItems.sulfur.stack(4), IRON.getDust(), 0.01f, 40f)

//    ItemHolder.sawdust?.let { sawdust ->
//        addGrinderRecipe(Blocks.LOG.stack(), sawdust.withSize(8), sawdust.withSize(4), 0.5f, 100f)
//        addGrinderRecipe(Blocks.PLANKS.stack(), sawdust.withSize(2), sawdust.withSize(1), 0.5f, 80f)
//    }

    ItemHolder.pulverizedCoal?.let { pulverizedCoal ->
        addGrinderRecipe(Blocks.COAL_ORE.stack(), pulverizedCoal.withSize(1), pulverizedCoal.withSize(1), 0.25f, 50f)
        addGrinderRecipe(Blocks.COAL_BLOCK.stack(), pulverizedCoal.withSize(9), pulverizedCoal.withSize(1), 0.15f, 120f)
        addGrinderRecipe(Items.COAL.stack(), pulverizedCoal.withSize(1), pulverizedCoal.withSize(1), 0.05f, 40f)
    }

    ItemHolder.pulverizedObsidian?.let { pulverizedObsidian ->
        addGrinderRecipe(Blocks.OBSIDIAN.stack(), pulverizedObsidian.withSize(4), pulverizedObsidian.withSize(1), 0.25f, 80f)
    }

    ItemHolder.pulverizedCharcoal?.let { pulverizedCharcoal ->
        addGrinderRecipe(Items.CHARCOAL.stack(), pulverizedCharcoal.withSize(1), pulverizedCharcoal.withSize(1), 0.15f, 40f)
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                                  SIEVE RECIPES
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    EnumMetal.values().filter { it.isOre }.forEach { metal ->
        val subComponents =
                if (metal.isComposite) {
                    metal.subComponents.map { it.invoke() }.map { it.getChunk() to 1f }
                } else {
                    EnumMetal.subProducts[metal]?.map { it.getDust() to 0.15f } ?: emptyList()
                }

        when (subComponents.size) {
            0 -> addSieveRecipe(metal.getRockyChunk(), metal.getChunk(), 1f, 50f)
            1 -> addSieveRecipe(metal.getRockyChunk(), metal.getChunk(), 1f, subComponents[0].first, subComponents[0].second, 50f)
            2 -> addSieveRecipe(metal.getRockyChunk(), metal.getChunk(), 1f, subComponents[0].first, subComponents[0].second,
                    subComponents[1].first, subComponents[1].second, 50f)
        }
    }

    addSieveRecipe(Blocks.GRAVEL.stack(), Items.FLINT.stack(), 1f, Items.FLINT.stack(), 0.15f, Items.FLINT.stack(), 0.05f, 50f)
    addSieveRecipe(Blocks.SAND.stack(), Items.GOLD_NUGGET.stack(), 0.04f, Items.GOLD_NUGGET.stack(), 0.02f, Items.QUARTZ.stack(), 0.01f, 80f)
    addSieveRecipe(Blocks.SOUL_SAND.stack(), Items.QUARTZ.stack(), 0.15f, Items.QUARTZ.stack(), 0.1f, Items.QUARTZ.stack(), 0.05f, 80f)
//    addSieveRecipe(Blocks..stack(), Items.QUARTZ.stack(), 0.15f, Items.QUARTZ.stack(), 0.1f, Items.QUARTZ.stack(), 0.05f, 80f)

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                              CRUSHING TABLE RECIPES
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // skulls
    addCrushingTableRecipe(Items.CREEPER_HEAD.stack(), Items.GUNPOWDER.stack(8), true)   // creeper
    addCrushingTableRecipe(Items.SKELETON_SKULL.stack(), Items.BONE_MEAL.stack(8), true) // skeleton
    addCrushingTableRecipe(Items.ZOMBIE_HEAD.stack(), Items.ROTTEN_FLESH.stack(4), true) // zombie

    // ores
    EnumMetal.values().forEach { metal ->
        metal.getOres().firstOrNull()?.let {
            addCrushingTableRecipe(it, metal.getRockyChunk())
        }
    }
    ItemHolder.tinOre?.let {
        addCrushingTableRecipe(it, TIN.getRockyChunk())
    }
    ItemHolder.osmiumOre?.let {
        addCrushingTableRecipe(it, OSMIUM.getRockyChunk())
    }

    addCrushingTableRecipe(BlockOreType.PYRITE.stack(), CraftingItems.sulfur.stack(2))
    // limestone
//    addCrushingTableRecipe(Decoration.limestone.stack(), Decoration.limestone.stack(1, 2))
//    addCrushingTableRecipe(Decoration.burnLimestone.stack(), Decoration.burnLimestone.stack(1, 2))
    // double plates
    addCrushingTableRecipe(Blocks.IRON_BLOCK.stack(), IRON.getLightPlate().withSize(5), true)
    addCrushingTableRecipe(Blocks.GOLD_BLOCK.stack(), GOLD.getLightPlate().withSize(5), true)
//    addCrushingTableRecipe(OreBlocks.storageBlocks.stack(1, BlockOreType.COPPER.ordinal), EnumMetal.COPPER.getLightPlate().withSize(5))
//    addCrushingTableRecipe(OreBlocks.storageBlocks.stack(1, BlockOreType.LEAD.ordinal), EnumMetal.LEAD.getLightPlate().withSize(5))
//    addCrushingTableRecipe(OreBlocks.storageBlocks.stack(1, BlockOreType.TUNGSTEN.ordinal), EnumMetal.TUNGSTEN.getLightPlate().withSize(5))
    addCrushingTableRecipe(STEEL.getIngot(), STEEL.getLightPlate())

    // rods
    addCrushingTableRecipe(Items.BLAZE_ROD.stack(), Items.BLAZE_POWDER.stack(5))
    addCrushingTableRecipe(Items.BONE.stack(), Items.BONE_MEAL.stack(4))
    // blocks
    addCrushingTableRecipe(Blocks.STONE.stack(), COBBLESTONE.stack())
    addCrushingTableRecipe(Blocks.POLISHED_GRANITE.stack(1), Blocks.GRANITE.stack(1))
    addCrushingTableRecipe(Blocks.POLISHED_ANDESITE.stack(1), Blocks.ANDESITE.stack(1))
    addCrushingTableRecipe(Blocks.POLISHED_DIORITE.stack(1), Blocks.DIORITE.stack(1))
    addCrushingTableRecipe(Blocks.STONE_BRICKS.stack(), Blocks.CRACKED_STONE_BRICKS.stack(1))
    addCrushingTableRecipe(Blocks.MOSSY_STONE_BRICKS.stack(1), Blocks.CRACKED_STONE_BRICKS.stack())
    addCrushingTableRecipe(Blocks.PRISMARINE.stack(1), Blocks.PRISMARINE.stack())
    addCrushingTableRecipe(Blocks.END_STONE_BRICKS.stack(1), Blocks.END_STONE.stack(1))

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                                  SMELTING RECIPES
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//    addSmeltingRecipe(Decoration.burnLimestone.stack(1, 0), Decoration.limestone.stack(1, 0))
//    addSmeltingRecipe(Decoration.burnLimestone.stack(1, 2), Decoration.limestone.stack(1, 2))

    //ores
//    addSmeltingRecipe(MetallicItems.ingots.stack(1, 2), Ores.ores.stack(1, 0))
//    addSmeltingRecipe(MetallicItems.ingots.stack(1, 3), Ores.ores.stack(1, 1))
//    addSmeltingRecipe(MetallicItems.ingots.stack(1, 4), Ores.ores.stack(1, 2))
//    addSmeltingRecipe(MetallicItems.ingots.stack(1, 5), Ores.ores.stack(1, 3))

    EnumMetal.values().forEach {
        if (it.isComposite) {
            addSmeltingRecipe(it.subComponents[0]().getIngot().withSize(2), it.getRockyChunk())
        } else {
            addSmeltingRecipe(it.getIngot(), it.getDust())
            if (it.isOre) {
                addSmeltingRecipe(it.getIngot(), it.getRockyChunk())
                addSmeltingRecipe(it.getIngot().withSize(2), it.getChunk())
            }
        }
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                                SLUICE BOX RECIPES
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    EnumMetal.values()
            .filter { it.isOre }
            .forEach { metal ->

                val subComponents =
                        if (metal.isComposite) {
                            metal.subComponents.map { it.invoke() }.map { it.getChunk() to 1f }
                        } else {
                            EnumMetal.subProducts[metal]?.map { it.getDust() to 0.15f } ?: emptyList()
                        }

                addSluiceBoxRecipe(metal.getRockyChunk(), metal.getChunk(), subComponents + listOf(COBBLESTONE.stack() to 0.15f))
            }

    addSluiceBoxRecipe(Blocks.GRAVEL.stack(), Items.FLINT.stack(), listOf(Items.FLINT.stack() to 0.15f))
    addSluiceBoxRecipe(Blocks.SAND.stack(), ItemStack.EMPTY,
            listOf(
                    Items.GOLD_NUGGET.stack() to 0.01f,
                    Items.GOLD_NUGGET.stack() to 0.005f,
                    Items.GOLD_NUGGET.stack() to 0.0025f,
                    Items.GOLD_NUGGET.stack() to 0.00125f,
                    Items.GOLD_NUGGET.stack() to 0.000625f,
                    Items.GOLD_NUGGET.stack() to 0.0003125f,
                    Items.GOLD_NUGGET.stack() to 0.00015625f,
                    Items.GOLD_NUGGET.stack() to 0.000078125f,
                    Items.GOLD_NUGGET.stack() to 0.0000390625f
            ))

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                                  THERMOPILE RECIPES
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    addThermopileRecipe(Blocks.SNOW, WATER_FREEZING_POINT, 40.0)
    addThermopileRecipe(Blocks.ICE, WATER_FREEZING_POINT, 60.0)
    addThermopileRecipe(Blocks.PACKED_ICE, WATER_FREEZING_POINT, 80.0)
    addThermopileRecipe(Blocks.TORCH, FIRE_TEMP, 4.0)
    addThermopileRecipe(Blocks.JACK_O_LANTERN, FIRE_TEMP, 3.5)
    addThermopileRecipe(Blocks.FIRE, FIRE_TEMP, 4.5)
    addThermopileRecipe(Blocks.MAGMA_BLOCK, MAGMA_TEMP, 1.4)

    Blocks.SNOW.stateContainer.validStates.forEach { state ->
        addThermopileRecipe(state, WATER_FREEZING_POINT, state[SnowBlock.LAYERS].toDouble() / 15.0 * 40.0)
    }

    ItemHolder.uraniumBlock?.let {
        it.toBlockState()?.let { state -> addThermopileRecipe(state, FIRE_TEMP, 1.5) }
    }

    // TODO FluidRegistry
//    FluidRegistry.getRegisteredFluids().values
//            .filter { it.canBePlacedInWorld() }
//            .forEach { fluid ->
//                val temp = fluid.temperature.toDouble()
//                addThermopileRecipe(fluid.block, temp, balancedConductivity(temp))
//            }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                                  HYDRAULIC PRESS RECIPES
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //@formatter:on

    // Heavy recipes
    addHydraulicPressRecipe(IRON_INGOT.stack(4), IRON.getHeavyPlate(), HEAVY, 120f)
    addHydraulicPressRecipe(GOLD_INGOT.stack(4), GOLD.getHeavyPlate(), HEAVY, 50f)
    addHydraulicPressRecipe(COPPER.getIngot().withSize(4), COPPER.getHeavyPlate(), HEAVY, 100f)
    addHydraulicPressRecipe(LEAD.getIngot().withSize(4), LEAD.getHeavyPlate(), HEAVY, 50f)
    addHydraulicPressRecipe(TUNGSTEN.getIngot().withSize(4), TUNGSTEN.getHeavyPlate(), HEAVY, 250f)
    addHydraulicPressRecipe(STEEL.getIngot().withSize(4), STEEL.getHeavyPlate(), HEAVY, 140f)

    // Medium recipes
    addHydraulicPressRecipe(IRON_INGOT.stack(1), IRON.getLightPlate(), MEDIUM, 120f)
    addHydraulicPressRecipe(GOLD_INGOT.stack(1), GOLD.getLightPlate(), MEDIUM, 50f)
    addHydraulicPressRecipe(COPPER.getIngot().withSize(1), COPPER.getLightPlate(), MEDIUM, 100f)
    addHydraulicPressRecipe(LEAD.getIngot().withSize(1), LEAD.getLightPlate(), MEDIUM, 50f)
    addHydraulicPressRecipe(TUNGSTEN.getIngot().withSize(1), TUNGSTEN.getLightPlate(), MEDIUM, 250f)
    addHydraulicPressRecipe(STEEL.getIngot().withSize(1), STEEL.getLightPlate(), MEDIUM, 140f)

    // Light recipes
    listOf(
            IRON_INGOT.stack() to ItemHolder.ironPlate,
            GOLD_INGOT.stack() to ItemHolder.goldPlate,
            COPPER.getIngot() to ItemHolder.copperPlate,
            TIN.getIngot() to ItemHolder.tinPlate,
            SILVER.getIngot() to ItemHolder.silverPlate,
            LEAD.getIngot() to ItemHolder.leadPlate,
            ALUMINIUM.getIngot() to ItemHolder.aluminiumPlate,
            NICKEL.getIngot() to ItemHolder.nickelPlate,
            ItemHolder.platinumIngot to ItemHolder.platinumPlate,
            ItemHolder.iridiumIngot to ItemHolder.iridiumPlate,
            MITHRIL.getIngot() to ItemHolder.mithilPlate,
            STEEL.getIngot() to ItemHolder.steelPlate,
            ItemHolder.electrumIngot to ItemHolder.electrumPlate,
            ItemHolder.invarIngot to ItemHolder.invarPlate,
            ItemHolder.constantanIngot to ItemHolder.constantanPlate,
            ItemHolder.signalumIngot to ItemHolder.signalumPlate,
            ItemHolder.lumiumIngot to ItemHolder.lumiumPlate,
            ItemHolder.enderiumIngot to ItemHolder.enderiumPlate
    ).forEach { (a, b) ->
        a?.let {
            b?.let {
                addHydraulicPressRecipe(a, b, LIGHT, 80f)
            }
        }
    }

    // utility
    addHydraulicPressRecipe(Blocks.STONE.stack(), COBBLESTONE.stack(), LIGHT, 55f)
    addHydraulicPressRecipe(Blocks.POLISHED_GRANITE.stack(), Blocks.GRANITE.stack(), LIGHT, 55f)
    addHydraulicPressRecipe(Blocks.POLISHED_ANDESITE.stack(), Blocks.ANDESITE.stack(), LIGHT, 55f)
    addHydraulicPressRecipe(Blocks.POLISHED_DIORITE.stack(), Blocks.DIORITE.stack(), LIGHT, 55f)
    addHydraulicPressRecipe(Blocks.MOSSY_STONE_BRICKS.stack(), Blocks.MOSSY_COBBLESTONE.stack(), LIGHT, 55f)
    addHydraulicPressRecipe(Blocks.STONE_BRICKS.stack(), COBBLESTONE.stack(), LIGHT, 55f)
    addHydraulicPressRecipe(Blocks.END_STONE_BRICKS.stack(), Blocks.END_STONE.stack(), LIGHT, 100f)
    addHydraulicPressRecipe(Blocks.CHISELED_RED_SANDSTONE.stack(), Blocks.RED_SANDSTONE.stack(), LIGHT, 40f)
    addHydraulicPressRecipe(Blocks.CHISELED_SANDSTONE.stack(), Blocks.SANDSTONE.stack(), LIGHT, 40f)
    addHydraulicPressRecipe(Blocks.PRISMARINE_BRICKS.stack(), Blocks.PRISMARINE.stack(), LIGHT, 50f)
    addHydraulicPressRecipe(Blocks.ICE.stack(), Blocks.PACKED_ICE.stack(), LIGHT, 200f)

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                                  OIL HEATER RECIPES
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//    addOilHeaterRecipe(FluidRegistry.getFluidStack("water", 1), FluidRegistry.getFluidStack("steam", 10), 1f, WATER_BOILING_POINT)
//    addOilHeaterRecipe(FluidRegistry.getFluidStack("oil", 10), FluidRegistry.getFluidStack("hot_crude", 100), 2f, 350.fromCelsiusToKelvin())
//    addOilHeaterRecipe(FluidRegistry.getFluidStack("crude_oil", 10), FluidRegistry.getFluidStack("hot_crude", 100), 2f, 350.fromCelsiusToKelvin())

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                                   REFINERY RECIPES
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//    addRefineryRecipe(FluidRegistry.getFluidStack("steam", 10), FluidRegistry.getFluidStack("water", 1), null, null, 2f)
//
//    addRefineryRecipe(FluidRegistry.getFluidStack("hot_crude", 100),
//            FluidRegistry.getFluidStack("heavy_oil", 4),
//            FluidRegistry.getFluidStack("light_oil", 3),
//            FluidRegistry.getFluidStack("lpg", 3),
//            1f)
//
//    addRefineryRecipe(FluidRegistry.getFluidStack("heavy_oil", 10),
//            FluidRegistry.getFluidStack("oil_residue", 4),
//            FluidRegistry.getFluidStack("fuel", 5),
//            FluidRegistry.getFluidStack("lubricant", 1),
//            1f)
//
//    addRefineryRecipe(FluidRegistry.getFluidStack("light_oil", 10),
//            FluidRegistry.getFluidStack("diesel", 5),
//            FluidRegistry.getFluidStack("kerosene", 2),
//            FluidRegistry.getFluidStack("gasoline", 3),
//            1f)
//
//    addRefineryRecipe(FluidRegistry.getFluidStack("lpg", 10),
//            FluidRegistry.getFluidStack("plastic", 5),
//            FluidRegistry.getFluidStack("naphtha", 2),
//            FluidRegistry.getFluidStack("natural_gas", 3),
//            1f)

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                                   GASIFIER RECIPES
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//    addGasifierRecipe(Blocks.PLANKS.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 30f, 250f)
//    addGasifierRecipe(Blocks.LOG.stack(), Items.COAL.stack(meta = 1), fluidOf("wood_gas", 150), 30f, 300f)
//    addGasifierRecipe(Blocks.LOG2.stack(), Items.COAL.stack(meta = 1), fluidOf("wood_gas", 150), 30f, 300f)
    addGasifierRecipe(Blocks.OAK_STAIRS.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 30f, 250f)
    addGasifierRecipe(Blocks.ACACIA_STAIRS.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 30f, 250f)
    addGasifierRecipe(Blocks.BIRCH_STAIRS.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 30f, 250f)
    addGasifierRecipe(Blocks.JUNGLE_STAIRS.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 30f, 250f)
    addGasifierRecipe(Blocks.SPRUCE_STAIRS.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 30f, 250f)
    addGasifierRecipe(Blocks.DARK_OAK_STAIRS.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 30f, 250f)
    addGasifierRecipe(Items.OAK_DOOR.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 20f, 250f)
    addGasifierRecipe(Items.ACACIA_DOOR.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 20f, 250f)
    addGasifierRecipe(Items.BIRCH_DOOR.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 20f, 250f)
    addGasifierRecipe(Items.JUNGLE_DOOR.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 20f, 250f)
    addGasifierRecipe(Items.SPRUCE_DOOR.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 20f, 250f)
    addGasifierRecipe(Items.DARK_OAK_DOOR.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 20f, 250f)
    addGasifierRecipe(Blocks.OAK_FENCE.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 30f, 250f)
    addGasifierRecipe(Blocks.ACACIA_FENCE.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 30f, 250f)
    addGasifierRecipe(Blocks.BIRCH_FENCE.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 30f, 250f)
    addGasifierRecipe(Blocks.JUNGLE_FENCE.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 30f, 250f)
    addGasifierRecipe(Blocks.SPRUCE_FENCE.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 30f, 250f)
    addGasifierRecipe(Blocks.DARK_OAK_FENCE.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 30f, 250f)
//    addGasifierRecipe(Blocks.WOODEN_SLAB.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 15f, 200f)
//    addGasifierRecipe(Blocks.WOODEN_PRESSURE_PLATE.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 15f, 200f)
//    addGasifierRecipe(Blocks.TRAPDOOR.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 15f, 200f)
    addGasifierRecipe(Blocks.HAY_BLOCK.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 100), 15f, 250f)
//    addGasifierRecipe(Blocks.SAPLING.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 30), 10f, 180f)
//    addGasifierRecipe(Blocks.LEAVES.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 30), 10f, 180f)
//    addGasifierRecipe(Blocks.LEAVES2.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 30), 10f, 180f)
    addGasifierRecipe(Blocks.VINE.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 30), 10f, 180f)
//    addGasifierRecipe(Blocks.WATERLILY.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 30), 10f, 180f)
//    addGasifierRecipe(Blocks.RED_FLOWER.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 10), 10f, 150f)
//    addGasifierRecipe(Blocks.YELLOW_FLOWER.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 10), 10f, 150f)
    addGasifierRecipe(Blocks.BROWN_MUSHROOM.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 10), 10f, 150f)
    addGasifierRecipe(Blocks.RED_MUSHROOM.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 10), 10f, 150f)
    addGasifierRecipe(Blocks.CACTUS.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 10), 10f, 180f)
//    addGasifierRecipe(Blocks.DOUBLE_PLANT.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 10), 10f, 150f)
    addGasifierRecipe(Blocks.CHEST.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 30f, 200f)
    addGasifierRecipe(Items.BOWL.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 10), 15f, 150f)
//    addGasifierRecipe(Items.SIGN.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 10), 10f, 150f)
    addGasifierRecipe(Items.STICK.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 10), 10f, 150f)
    addGasifierRecipe(Items.WHEAT_SEEDS.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 30), 10f, 150f)
    addGasifierRecipe(Items.BEETROOT_SEEDS.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 30), 10f, 150f)
    addGasifierRecipe(Items.MELON_SEEDS.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 30), 10f, 150f)
    addGasifierRecipe(Items.PUMPKIN_SEEDS.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 30), 10f, 150f)
    addGasifierRecipe(Items.WHEAT.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 20f, 200f)
//    addGasifierRecipe(Items.REEDS.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 30), 10f, 200f)
    addGasifierRecipe(Items.NETHER_WART.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 10f, 200f)
    addGasifierRecipe(Items.CARROT.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 10f, 200f)
    addGasifierRecipe(Items.POTATO.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 10f, 200f)
    addGasifierRecipe(Items.BEETROOT.stack(), ItemStack.EMPTY, fluidOf("wood_gas", 50), 10f, 200f)

    ItemHolder.coalCoke?.let {
        addGasifierRecipe(Items.COAL.stack(), it, fluidOf("wood_gas", 15), 30f, 300f)
    }
    ItemHolder.sawdust?.let {
        addGasifierRecipe(it, ItemStack.EMPTY, fluidOf("wood_gas", 10), 30f, 10f)
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                                   FLUID FUELS
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // For balance: 1 coal = 16_000 J

    // 1 oil bucket  -> 18.75  coal items // unrefined
    addFluidFuel("oil", 10_000, 30.0)           // 300_000 J/B   = 18.75 coal items
    addFluidFuel("crude_oil", 10_000, 30.0)     // 300_000 J/B   = 18.75 coal items

    // 1 oil bucket  -> 225    coal items // refined 1 time
    addFluidFuel("heavy_oil", 25_000, 60.0)     // 1_500_000 J/B = 93.75 coal items
    addFluidFuel("light_oil", 25_000, 80.0)     // 2_000_000 J/B = 125   coal items
    addFluidFuel("natural_gas", 2_500, 40.0)    // 100_000 J/B   = 6.25  coal items

    // 1 oil bucket  -> 318.75 coal items // refined 2 times
    addFluidFuel("fuel", 25_000, 60.0)          // 1_500_000 J/B = 93.75 coal items
    addFluidFuel("diesel", 10_000, 80.0)        // 800_000 J/B   = 62.5  coal items
    addFluidFuel("kerosene", 5_000, 120.0)      // 600_000 J/B   = 37.5  coal items
    addFluidFuel("gasoline", 12_000, 100.0)     // 1_200_000 J/B = 62.5  coal items
    addFluidFuel("naphtha", 25_000, 40.0)       // 1_000_000 J/B = 62.5  coal items

    // other fuels
    addFluidFuel("wood_gas", 2_500, 20.0)       // 50_000 J/B    = 3.125 coal items

    // IE
    addFluidFuel("creosote", 1_000, 20.0)       // 20_000 J/B    = 1.25  coal items
    addFluidFuel("ethanol", 2_000, 20.0)        // 40_000 J/B    = 2.5   coal items
    addFluidFuel("plantoil", 2_000, 20.0)       // 40_000 J/B    = 2.5   coal items
    addFluidFuel("biodiesel", 10_000, 50.0)     // 500_000 J/B   = 31.25 coal items

    // TE
    addFluidFuel("coal", 10_000, 40.0)          // 400_000 J/B   = 62.5  coal items
    addFluidFuel("tree_oil", 12_500, 80.0)      // 1_000_000 J/B = 62.5  coal items
    addFluidFuel("refined_fuel", 25_000, 80.0)  // 2_000_000 J/B = 125   coal items
    addFluidFuel("refined_oil", 25_000, 40.0)   // 1_000_000 J/B = 62.5  coal items

    // IF
    addFluidFuel("biofuel", 10_000, 50.0)       // 500_000 J/B   = 31.25 coal items

    // Forestry
    addFluidFuel("bioethanol", 10_000, 50.0)    // 500_000 J/B   = 31.25 coal items
}

// TODO
private fun fluidOf(name: String, amount: Int): FluidStack? = null // FluidRegistry.getFluidStack(name, amount)

private fun addSmeltingRecipe(result: ItemStack, input: ItemStack) {
    if (input.isEmpty)
        throw IllegalStateException("Trying to register furnace recipe with empty input stack: $input")
    if (result.isEmpty)
        throw IllegalStateException("Trying to register furnace recipe with empty result empty stack: $result")

    // TODO
//    GameRegistry.addSmelting(input, result, 0.1f) // i don't care about xp
}

private fun addCrushingTableRecipe(input: ItemStack, output: ItemStack, strict: Boolean = false) {
    CrushingTableRecipeManager.registerRecipe(CrushingTableRecipeManager.createRecipe(input, output, !strict))
}

private fun addSluiceBoxRecipe(input: ItemStack, output: ItemStack,
                               otherOutput: List<Pair<ItemStack, Float>> = emptyList()) {
    SluiceBoxRecipeManager.registerRecipe(SluiceBoxRecipeManager.createRecipe(input, (listOf(output to 1f) + otherOutput).toMutableList(), true))
}

private fun addThermopileRecipe(input: IBlockState, temperature: Double, conductivity: Double) {
    ThermopileRecipeManager.registerRecipe(
            ThermopileRecipeManager.createRecipe(input, temperature.toFloat(), conductivity.toFloat())
    )
}

private fun addThermopileRecipe(input: Block, temperature: Double, conductivity: Double) {
    input.stateContainer.validStates.forEach { state ->
        ThermopileRecipeManager.registerRecipe(
                ThermopileRecipeManager.createRecipe(state, temperature.toFloat(), conductivity.toFloat())
        )
    }
}

private fun balancedConductivity(temp: Double): Double {
    if (temp < STANDARD_AMBIENT_TEMPERATURE) {
        return 2000.0 / ensureNonZero(STANDARD_AMBIENT_TEMPERATURE - temp)
    }
    return 1000.0 / ensureNonZero(temp - STANDARD_AMBIENT_TEMPERATURE)
}

private fun addSieveRecipe(input: ItemStack, output0: ItemStack, prob0: Float, output1: ItemStack, prob1: Float,
                           output2: ItemStack,
                           prob2: Float, duration: Float) {
    SieveRecipeManager.registerRecipe(
            SieveRecipeManager.createRecipe(input, output0, prob0, output1, prob1, output2, prob2, duration, true))
}

private fun addSieveRecipe(input: ItemStack, output0: ItemStack, prob0: Float, output1: ItemStack, prob1: Float,
                           duration: Float) {
    SieveRecipeManager.registerRecipe(
            SieveRecipeManager.createRecipe(input, output0, prob0, output1, prob1, output1, 0f, duration, true))
}

private fun addSieveRecipe(input: ItemStack, output0: ItemStack, prob0: Float, duration: Float) {
    SieveRecipeManager.registerRecipe(
            SieveRecipeManager.createRecipe(input, output0, prob0, output0, 0f, output0, 0f, duration, true))
}

private fun addGrinderRecipe(input: ItemStack, output0: ItemStack, output1: ItemStack, prob: Float, ticks: Float) {
    GrinderRecipeManager.registerRecipe(GrinderRecipeManager.createRecipe(input, output0, output1, prob, ticks, true))
}

private fun addHydraulicPressRecipe(input: ItemStack, output: ItemStack, mode: HydraulicPressMode, ticks: Float) {
    HydraulicPressRecipeManager.registerRecipe(HydraulicPressRecipeManager.createRecipe(input, output, ticks, mode, true))
}

private fun addOilHeaterRecipe(input: FluidStack?, output: FluidStack?, ticks: Float, minTemp: Double) {
    if (input == null || output == null) {
        warn("(Ignoring) Trying to register a OilHeaterRecipe with null params: input=$input, output=$output, duration=$ticks, minTemp=$minTemp")
        return
    }
    OilHeaterRecipeManager.registerRecipe(OilHeaterRecipeManager.createRecipe(input, output, ticks, minTemp.toFloat()))
}

private fun addRefineryRecipe(input: FluidStack?, output0: FluidStack?, output1: FluidStack?, output2: FluidStack?, ticks: Float) {
    if (input == null || (output0 == null && output1 == null && output2 == null)) {
        warn("Error trying to register a RefineryRecipe with params: input=$input, output0=$output0, " +
                "output1=$output1, output2=$output2, duration=$ticks")
        return
    }
    RefineryRecipeManager.registerRecipe(RefineryRecipeManager.createRecipe(input, output0, output1, output2, ticks))
}

private fun addGasifierRecipe(input: ItemStack, output0: ItemStack, output1: FluidStack?, ticks: Float, minTemp: Float) {
    if (input.isEmpty) return
    GasificationUnitRecipeManager.registerRecipe(
            GasificationUnitRecipeManager.createRecipe(input, output0, output1,
                    ticks, minTemp.fromCelsiusToKelvin().toFloat(), true)
    )
}

private fun addFluidFuel(name: String, ticks: Int, value: Double) {
    val fluid = fluidOf(name, 1)

    if (fluid == null) {
        warn("(Ignoring) Unable to add a fuel for '$name': fluid not found")
        return
    }

    val manager = MagneticraftApi.getFluidFuelManager()

    manager.registerFuel(manager.createFuel(fluid, ticks, value))
}

/* OLD RECIPES

//
//    //ICEBOX RECIPES
//    addIceboxRecipeWater(ItemStack(Items.SNOWBALL), 125, false)
//    addIceboxRecipeWater(ItemStack(Blocks.SNOW), 500, false)
//    addIceboxRecipeWater(ItemStack(Blocks.ICE), 900, true)
//    addIceboxRecipeWater(ItemStack(Blocks.PACKED_ICE), 1000, false)

//
//    //KILN RECIPES
//    addKilnRecipe(ItemStack(COAL_BLOCK), BlockCoke.defaultState, 50, COKE_REACTION_TEMP, CARBON_SUBLIMATION_POINT)
//    addKilnRecipe(ItemStack(Blocks.LOG, 1, 0), BlockCharcoalSlab.defaultState, 25, COKE_REACTION_TEMP, CARBON_SUBLIMATION_POINT)
//    addKilnRecipe(ItemStack(Blocks.SAND), Blocks.GLASS.defaultState, 25, GLASS_MAKING_TEMP, QUARTZ_MELTING_POINT)
//    addKilnRecipe(ItemStack(Blocks.CLAY), Blocks.HARDENED_CLAY.defaultState, 25, DEFAULT_SMELTING_TEMPERATURE, QUARTZ_MELTING_POINT)
//    addKilnRecipe(ItemStack(BlockFluxedGravel), BlockGlazedBrick.defaultState, 25, FURNACE_BRICK_TEMP, QUARTZ_MELTING_POINT)
//    addKilnRecipe(ItemStack(Blocks.SPONGE, 1, 1), ItemStack(Blocks.SPONGE, 1, 0), 25, WATER_BOILING_POINT, COKE_REACTION_TEMP)
//
//    //KILN SHELF RECIPES
//    addKilnRecipe(ItemStack(COAL, 1, 0), ItemStack(ItemCoke), 50, COKE_REACTION_TEMP, CARBON_SUBLIMATION_POINT)
//    addKilnRecipe(ItemStack(CLAY_BALL), ItemStack(BRICK), 25, DEFAULT_SMELTING_TEMPERATURE, QUARTZ_MELTING_POINT)
//    addKilnRecipe(ItemStack(CHORUS_FRUIT), ItemStack(CHORUS_FRUIT_POPPED, 1, 0), 25, DEFAULT_SMELTING_TEMPERATURE, QUARTZ_MELTING_POINT)
 */

//private fun addKilnRecipe(input: ItemStack, output: ItemStack, duration: Int, minTemp: Double, maxTemp: Double) {
//    KilnRecipeManager.registerRecipe(KilnRecipeManager.createRecipe(input, output, duration, minTemp, maxTemp, true))
//}
//
//private fun addKilnRecipe(input: ItemStack, output: IBlockState, duration: Int, minTemp: Double, maxTemp: Double) {
//    KilnRecipeManager.registerRecipe(KilnRecipeManager.createRecipe(input, output, duration, minTemp, maxTemp, true))
//}
//
//private fun addIceboxRecipe(input: ItemStack, output: FluidStack, heat: Long, specificHeat: Double, minTemp: Double,
//                            maxTemp: Double, reverse: Boolean) {
//    IceboxRecipeManager.registerRecipe(
//            IceboxRecipeManager.createRecipe(input, output, heat, specificHeat, minTemp, maxTemp, reverse))
//}
//
//private fun addIceboxRecipeWater(input: ItemStack, output: Int, reverse: Boolean) {
//    IceboxRecipeManager.registerRecipe(IceboxRecipeManager.createRecipe(input, FluidStack(FluidRegistry.WATER, output),
//            (WATER_HEAT_OF_FUSION * output / 1000).toLong(), WATER_HEAT_CAPACITY, WATER_MELTING_POINT,
//            WATER_BOILING_POINT, reverse))
//}
//

