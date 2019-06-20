package com.cout970.magneticraft.features.electric_machines

import com.cout970.magneticraft.misc.guiTexture
import com.cout970.magneticraft.misc.vector.Vec2d
import com.cout970.magneticraft.systems.config.Config
import com.cout970.magneticraft.systems.gui.components.CompBackground
import com.cout970.magneticraft.systems.gui.components.bars.*
import com.cout970.magneticraft.systems.gui.render.GuiBase
import com.cout970.magneticraft.systems.gui.render.dsl

/**
 * Created by cout970 on 2017/08/10.
 */

fun guiElectricFurnace(gui: GuiBase, container: ContainerElectricFurnace) = gui.dsl {
    val tile = container.tile

    bars {
        electricBar(tile.node)
        electricConsumption(tile.processModule.consumption, Config.electricFurnaceMaxConsumption)
        progressBar(tile.processModule.timedProcess)
        slotPair()
    }
}

fun guiBattery(gui: GuiBase, container: ContainerBattery) = gui.run {
    val tile = container.tile
    +CompBackground(guiTexture("battery"))
    +CompElectricBar(tile.node, Vec2d(47, 16))

    +BatteryStorageBar(this, guiTexture("battery"), tile.storageModule)

    +TransferRateBar(
        value = { tile.storageModule.chargeRate.storage.toDouble() },
        min = { -tile.storageModule.maxChargeSpeed },
        base = { 0.0 },
        max = { tile.storageModule.maxChargeSpeed }, pos = Vec2d(58, 16))

    +TransferRateBar(
        value = { tile.itemChargeModule.itemChargeRate.storage.toDouble() },
        min = { -tile.itemChargeModule.transferRate.toDouble() },
        base = { 0.0 },
        max = { tile.itemChargeModule.transferRate.toDouble() }, pos = Vec2d(58 + 33, 16)
    )
}

fun guiThermopile(gui: GuiBase, container: ContainerThermopile) = gui.dsl {
    val tile = container.tile

    bars {
        electricBar(tile.node)
        storageBar(tile.storage)
    }

    bars {
        electricProduction(tile.thermopileModule.production, Config.thermopileProduction)

        StaticBarProvider(0.0, 10_000.0, tile.thermopileModule::totalFlux).let { prov ->
            genericBar(2, 4, prov, prov.toIntText(postfix = " Heat Flux/t"))
        }
    }
}

fun guiWindTurbine(gui: GuiBase, container: ContainerWindTurbine) = gui.dsl {
    val tile = container.tile

    bars {
        electricBar(tile.node)
        storageBar(tile.storageModule)
    }

    bars {
        electricProduction(tile.windTurbineModule.production, Config.windTurbineMaxProduction)

        StaticBarProvider(0.0, 1.0, tile.windTurbineModule::openSpace).let { prov ->
            genericBar(8, 5, prov, prov.toPercentText("Wind not blocked: "))
        }

        StaticBarProvider(0.0, 1.0, tile.windTurbineModule::currentWind).let { prov ->
            genericBar(9, 7, prov, prov.toPercentText("Wind: ", "%"))
        }
    }
}

fun guiElectricHeater(gui: GuiBase, container: ContainerElectricHeater) = gui.dsl {
    val tile = container.tile

    bars {
        electricBar(tile.electricNode)
        heatBar(tile.heatNode)
        electricConsumption(tile.electricHeaterModule.consumption, Config.electricHeaterMaxProduction)
        heatProduction(tile.electricHeaterModule.production, Config.electricHeaterMaxProduction)
    }
}

fun guiRfHeater(gui: GuiBase, container: ContainerRfHeater) = gui.dsl {
    val tile = container.tile

    bars {
        rfBar(tile.storage)
        heatBar(tile.node)
        rfConsumption(tile.electricHeaterModule.consumption, Config.electricHeaterMaxProduction)
        heatProduction(tile.electricHeaterModule.production, Config.electricHeaterMaxProduction)
    }
}
