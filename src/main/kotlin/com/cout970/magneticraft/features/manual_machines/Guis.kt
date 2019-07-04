package com.cout970.magneticraft.features.manual_machines

import com.cout970.magneticraft.misc.guiTexture
import com.cout970.magneticraft.systems.gui.GuiBase
import com.cout970.magneticraft.systems.gui.components.CompBackground
import com.cout970.magneticraft.systems.gui.components.CompFabricatorMatches

/**
 * Created by cout970 on 2017/08/10.
 */

@Suppress("UNUSED_PARAMETER")
fun guiBox(gui: GuiBase, container: ContainerBox) = gui.run {
    +CompBackground(guiTexture("box"))
}

@Suppress("UNUSED_PARAMETER")
fun guiFabricator(gui: GuiBase, container: ContainerFabricator) = gui.run {
    +CompBackground(guiTexture("fabricator"))
    +CompFabricatorMatches(container.tile)
}