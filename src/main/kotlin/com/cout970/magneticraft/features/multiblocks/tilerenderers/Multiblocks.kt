@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.cout970.magneticraft.features.multiblocks.tilerenderers

import com.cout970.magneticraft.features.multiblocks.tileentities.TileMultiblock
import com.cout970.magneticraft.misc.vector.length
import com.cout970.magneticraft.misc.vector.vec3Of
import com.cout970.magneticraft.systems.multiblocks.MultiblockManager
import com.cout970.magneticraft.systems.tilerenderers.BaseTileRenderer
import com.cout970.magneticraft.systems.tilerenderers.Utilities
import com.cout970.magneticraft.totalWorldTime

/**
 * Created by cout970 on 2017/08/10.
 */

abstract class TileRendererMultiblock<T : TileMultiblock> : BaseTileRenderer<T>() {

    override fun renderTileEntityAt(te: T, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int) {
        stackMatrix {
            translate(x, y, z)

            if (!te.active) {
                val player = mc.player

                // If the player is too far we don't render the template,
                // because part of the multiblock may be unloaded and the template would be rendered from far away
                if (vec3Of(x, y, z).length > 32) return@stackMatrix

                Utilities.multiblockPreview(te.multiblockContext())

                if (world.totalWorldTime % 20L == 0L) {
                    val ctx = te.multiblockContext().copy(player = player)
                    te.clientErrors = MultiblockManager.checkMultiblockStructure(ctx)
                }

                if (te.clientErrors.isNotEmpty()) {
                    val txt = te.clientErrors.first().formattedText
                    Utilities.renderFloatingLabel(txt, vec3Of(0.5f, 2f, 0.5f))
                }
            } else {
                ticks = partialTicks
                time = (world.totalWorldTime and 0xFF_FFFF).toDouble() + partialTicks
                render(te)
            }
        }
    }

    override fun isGlobalRenderer(te: T): Boolean = true
}



