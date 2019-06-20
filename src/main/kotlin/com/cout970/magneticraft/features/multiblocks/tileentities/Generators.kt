package com.cout970.magneticraft.features.multiblocks.tileentities

import com.cout970.magneticraft.api.internal.energy.ElectricNode
import com.cout970.magneticraft.features.multiblocks.structures.MultiblockSolarPanel
import com.cout970.magneticraft.features.multiblocks.structures.MultiblockSteamEngine
import com.cout970.magneticraft.misc.ElectricConstants
import com.cout970.magneticraft.misc.RegisterTileEntity
import com.cout970.magneticraft.misc.fluid.Tank
import com.cout970.magneticraft.misc.fluid.wrapWithFluidFilter
import com.cout970.magneticraft.misc.interpolate
import com.cout970.magneticraft.misc.iterateArea
import com.cout970.magneticraft.misc.tileentity.DoNotRemove
import com.cout970.magneticraft.misc.vector.plus
import com.cout970.magneticraft.misc.vector.rotatePoint
import com.cout970.magneticraft.misc.world.isClient
import com.cout970.magneticraft.registry.ELECTRIC_NODE_HANDLER
import com.cout970.magneticraft.systems.config.Config
import com.cout970.magneticraft.systems.multiblocks.Multiblock
import com.cout970.magneticraft.systems.tilemodules.*
import net.minecraft.util.EnumFacing
import net.minecraft.util.ITickable
import net.minecraft.util.math.BlockPos

@RegisterTileEntity("solar_panel")
class TileSolarPanel : TileMultiblock(), ITickable {

    override fun getMultiblock(): Multiblock = MultiblockSolarPanel

    val node = ElectricNode(ref)

    val ioModule: ModuleMultiblockIO = ModuleMultiblockIO(
        facing = { facing },
        connectionSpots = listOf(
            ConnectionSpot(
                capability = ELECTRIC_NODE_HANDLER!!,
                pos = BlockPos(0, 0, -5),
                side = EnumFacing.NORTH,
                getter = { energyModule }
            ),
            ConnectionSpot(
                capability = ELECTRIC_NODE_HANDLER!!,
                pos = BlockPos(0, 0, 0),
                side = EnumFacing.SOUTH,
                getter = { energyModule }
            )
        )
    )

    val energyModule = ModuleElectricity(
        electricNodes = listOf(node),
        canConnectAtSide = ioModule::canConnectAtSide,
        connectableDirections = ioModule::getElectricConnectPoints
    )

    override val multiblockModule = ModuleMultiblockCenter(
        multiblockStructure = getMultiblock(),
        facingGetter = { facing },
        capabilityGetter = { _, _, _ -> null }
    )


    //client animation
    var currentAngle = 0f
    var deltaTime = System.currentTimeMillis()

    init {
        initModules(multiblockModule, energyModule, ioModule)
    }


    @DoNotRemove
    override fun update() {
        super.update()
        if (world.isClient) return
        if (active && world.isDaytime && world.provider.hasSkyLight()) {
            var count = 0
            iterateArea(0..2, 0..2) { i, j ->
                val offset = facing.rotatePoint(BlockPos.ORIGIN, BlockPos(i - 1, 0, j))
                if (world.canBlockSeeSky(pos + offset)) {
                    count++
                }
            }
            if (count > 0) {
                val limit = interpolate(node.voltage,
                    ElectricConstants.TIER_1_MAX_VOLTAGE,
                    ElectricConstants.TIER_1_GENERATORS_MAX_VOLTAGE
                )
                node.applyPower((1 - limit) * Config.solarPanelMaxProduction * (count / 9f), false)
            }
        }
    }
}

@RegisterTileEntity("steam_engine")
class TileSteamEngine : TileMultiblock(), ITickable {

    override fun getMultiblock(): Multiblock = MultiblockSteamEngine

    val tank = Tank(1000)
    val node = ElectricNode(ref)

    val fluidModule = ModuleFluidHandler(tank, capabilityFilter = wrapWithFluidFilter { it.fluid.name == "steam" })

    val guiModule = ModuleOpenGui()

    val storageModule = ModuleInternalStorage(
        mainNode = node,
        capacity = 10_000,
        lowerVoltageLimit = ElectricConstants.TIER_1_GENERATORS_MAX_VOLTAGE - 5.0,
        upperVoltageLimit = ElectricConstants.TIER_1_GENERATORS_MAX_VOLTAGE - 5.0
    )

    val steamGeneratorModule = ModuleSteamGenerator(
        steamTank = tank,
        node = node
    )

    val steamEngineMbModule = ModuleSteamEngineMb(
        facingGetter = { facing },
        steamProduction = steamGeneratorModule.production,
        guiModule = guiModule
    )

    val ioModule: ModuleMultiblockIO = ModuleMultiblockIO(
        facing = { facing },
        connectionSpots = listOf(ConnectionSpot(
            capability = ELECTRIC_NODE_HANDLER!!,
            pos = BlockPos(-2, 0, -2),
            side = EnumFacing.UP,
            getter = { if (active) energyModule else null }
        ), ConnectionSpot(
            capability = ELECTRIC_NODE_HANDLER!!,
            pos = BlockPos(-2, 0, -2),
            side = EnumFacing.SOUTH,
            getter = { if (active) energyModule else null }
        ))
    )

    val energyModule = ModuleElectricity(
        electricNodes = listOf(node),
        connectableDirections = ioModule::getElectricConnectPoints,
        capabilityFilter = { false }
    )

    override val multiblockModule = ModuleMultiblockCenter(
        multiblockStructure = getMultiblock(),
        facingGetter = { facing },
        capabilityGetter = ioModule::getCapability,
        dynamicCollisionBoxes = steamEngineMbModule::getDynamicCollisionBoxes
    )

    init {
        initModules(multiblockModule, fluidModule, energyModule, storageModule, steamGeneratorModule,
            steamEngineMbModule, ioModule, guiModule)
    }

    @DoNotRemove
    override fun update() {
        super.update()
    }
}