package com.cout970.magneticraft.systems.blocks

import com.cout970.magneticraft.IBlockState
import com.cout970.magneticraft.misc.tileentity.getTile
import com.cout970.magneticraft.misc.world.isServer
import com.cout970.magneticraft.systems.multiblocks.IMultiblockModule
import com.cout970.magneticraft.systems.multiblocks.MultiblockContext
import com.cout970.magneticraft.systems.multiblocks.MultiblockManager
import com.cout970.magneticraft.systems.tileentities.TileBase
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TranslationTextComponent
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import com.cout970.magneticraft.features.multiblocks.Blocks as Multiblocks

/**
 * Created by cout970 on 2017/07/03.
 */
class BlockMultiblock(
    factory: (IBlockReader, IBlockState) -> TileEntity?,
    filter: ((IBlockState) -> Boolean)?,
    props: Block.Properties
) : BlockTileBase(factory, filter, props) {

//    companion object {
//
//        fun getBoxes(world: World, pos: BlockPos, module: IMultiblockModule): List<AABB> {
//            val multiblock = module.multiblock ?: return emptyList()
//            val facing = module.multiblockFacing ?: return emptyList()
//            val center = module.centerPos ?: return emptyList()
//
//            val main = world.getModule<IMultiblockCenter>(pos - center)
//            val extra = main?.getDynamicCollisionBoxes(pos) ?: emptyList()
//
//            return (multiblock.getGlobalCollisionBoxes() + extra).map {
//                val origin = EnumFacing.SOUTH.rotateBox(vec3Of(0.5), it)
//                facing.rotateBox(vec3Of(0.5), origin)
//            }
//        }
//
//        fun getBoxesInBlock(world: World, pos: BlockPos, module: IMultiblockModule): List<AABB> {
//            val center = module.centerPos ?: return emptyList()
//            val boxes = getBoxes(world, pos, module)
//            val thisBox = FULL_BLOCK_AABB + center
//            return boxes.mapNotNull { it.cut(thisBox) }
//        }
//
//        fun getRelativeBoxesInBlock(world: World, pos: BlockPos, module: IMultiblockModule): List<AABB> {
//            val relPos = module.centerPos?.unaryMinus() ?: return emptyList()
//            return getBoxesInBlock(world, pos, module).map { it.offset(relPos) }
//        }
//    }
//
//    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
//    override fun addCollisionBoxToList(state: IBlockState, worldIn: World, pos: BlockPos, entityBox: AxisAlignedBB,
//                                       collidingBoxes: MutableList<AxisAlignedBB>, entityIn: Entity?,
//                                       p_185477_7_: Boolean) {
//        val active = state[Multiblocks.PROPERTY_MULTIBLOCK_ORIENTATION]?.active ?: true
//        if (active) {
//            val tile = worldIn.getTile<TileBase>(pos)
//            val module = tile?.container?.modules?.find { it is IMultiblockModule } as? IMultiblockModule
//
//            if (module?.multiblock != null) {
//
//                val boxes = getRelativeBoxesInBlock(worldIn, pos, module).map { it.offset(pos) }
//                boxes.filterTo(collidingBoxes) { entityBox.intersects(it) }
//                return
//            }
//        }
//        super.addCollisionBoxToList(state, worldIn, pos, entityBox, collidingBoxes, entityIn, p_185477_7_)
//    }
//
//    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
//    override fun getSelectedBoundingBox(state: IBlockState, worldIn: World, pos: BlockPos): AxisAlignedBB {
//        val active = state[Multiblocks.PROPERTY_MULTIBLOCK_ORIENTATION]?.active ?: true
//        if (active) {
//            val tile = worldIn.getTile<TileBase>(pos)
//            val module = tile?.container?.modules?.find { it is IMultiblockModule } as? IMultiblockModule
//            if (module?.multiblock != null) {
//
//                val player = Minecraft.getMinecraft().player
//
//                val start = player.getPositionEyes(0f)
//                val look = player.getLook(0f)
//                val blockReachDistance = Minecraft.getMinecraft().playerController!!.blockReachDistance
//                val end = start.addVector(
//                    look.x * blockReachDistance,
//                    look.y * blockReachDistance,
//                    look.z * blockReachDistance
//                )
//
//                val res = getRelativeBoxesInBlock(worldIn, pos, module)
//                    .associate { it to rayTrace(pos, start, end, it) }
//                    .filter { it.value != null }
//                    .map { it.key to it.value }
//                    .sortedBy { it.second!!.hitVec.distanceTo(start) }
//                    .firstOrNull()?.first
//
//                return res?.offset(pos) ?: EMPTY_AABB
//            }
//        }
//        return super.getSelectedBoundingBox(state, worldIn, pos)
//    }
//
//    @Suppress("OverridingDeprecatedMember")
//    override fun collisionRayTrace(blockState: IBlockState, worldIn: World, pos: BlockPos, start: Vec3d,
//                                   end: Vec3d): RayTraceResult? {
//        val active = blockState[Multiblocks.PROPERTY_MULTIBLOCK_ORIENTATION]?.active ?: true
//        if (active) {
//            val tile = worldIn.getTile<TileBase>(pos)
//            val module = tile?.container?.modules?.find { it is IMultiblockModule } as? IMultiblockModule
//            if (module?.multiblock != null) {
//
//                return getRelativeBoxesInBlock(worldIn, pos, module)
//                    .associate { it to rayTrace(pos, start, end, it) }
//                    .filter { it.value != null }
//                    .map { it.key to it.value }
//                    .sortedBy { it.second!!.hitVec.distanceTo(start) }
//                    .firstOrNull()?.second
//            }
//        }
//        return this.rayTrace(pos, start, end, blockState.getBoundingBox(worldIn, pos))
//    }

    //removedByPlayer

    override fun canRenderInLayer(state: BlockState, layer: BlockRenderLayer): Boolean {
        val active = state[Multiblocks.PROPERTY_MULTIBLOCK_ORIENTATION].active
        return if (active) false else super.canRenderInLayer(state, layer)
    }

    override fun onReplaced(state: IBlockState, worldIn: World, pos: BlockPos, newState: IBlockState, moving: Boolean) {
        if (worldIn.isServer) {
            val active = state[Multiblocks.PROPERTY_MULTIBLOCK_ORIENTATION].active ?: true
            if (active) {
                val tile = worldIn.getTile<TileBase>(pos)
                val module = tile?.container?.modules?.find { it is IMultiblockModule } as? IMultiblockModule
                if (module?.multiblock != null) {
                    val facing = state[Multiblocks.PROPERTY_MULTIBLOCK_ORIENTATION].facing ?: module.multiblockFacing
                    if (module.multiblock != null && facing != null) {
                        MultiblockManager.deactivateMultiblockStructure(
                            MultiblockContext(
                                multiblock = module.multiblock!!,
                                world = worldIn,
                                center = pos.subtract(module.centerPos!!),
                                facing = facing,
                                player = null
                            )
                        )
                    }
                }
            }
        }
        super.onReplaced(state, worldIn, pos, newState, moving)
    }

    override fun addInformation(stack: ItemStack, worldIn: IBlockReader?, tooltip: MutableList<ITextComponent>, flagIn: ITooltipFlag) {
        tooltip.add(TranslationTextComponent("tooltip.magneticraft.multiblock.blueprint"))
        super.addInformation(stack, worldIn, tooltip, flagIn)
    }
}