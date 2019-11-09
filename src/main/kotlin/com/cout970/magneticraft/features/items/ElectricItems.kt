package com.cout970.magneticraft.features.items

import com.cout970.magneticraft.misc.CreativeTabMg
import com.cout970.magneticraft.misc.RegisterItems
import com.cout970.magneticraft.misc.vector.times
import com.cout970.magneticraft.misc.world.isServer
import com.cout970.magneticraft.registry.FORGE_ENERGY
import com.cout970.magneticraft.registry.fromItem
import com.cout970.magneticraft.systems.config.Config
import com.cout970.magneticraft.systems.items.*
import net.minecraft.block.material.Material
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.network.play.server.SEntityVelocityPacket
import net.minecraft.util.*
import net.minecraft.util.math.RayTraceContext
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraftforge.common.ToolType

/**
 * Created by cout970 on 2017/07/02.
 */
@RegisterItems
object ElectricItems : IItemMaker {

    lateinit var batteryItemLow: ItemBase private set
    lateinit var batteryItemMedium: ItemBase private set
    lateinit var electric_drill: ItemBase private set
    lateinit var electric_piston: ItemBase private set
    lateinit var electric_chainsaw: ItemBase private set

    override fun initItems(): List<Item> {
        val builder = ItemBuilder().apply {
            creativeTab = CreativeTabMg
            maxStackSize = 1
        }

        batteryItemLow = builder.withName("battery_item_low").copy {
            constructor = { ElectricItemBase(it, Config.batteryItemLowCapacity) }
            capabilityProvider = { ElectricItemBase.ItemEnergyCapabilityProvider(it.stack) }
        }.build()

        batteryItemMedium = builder.withName("battery_item_medium").copy {
            constructor = { ElectricItemBase(it, Config.batteryItemMediumCapacity) }
            capabilityProvider = { ElectricItemBase.ItemEnergyCapabilityProvider(it.stack) }
        }.build()

        electric_drill = builder.withName("electric_drill").copy {
            constructor = { ElectricItemBase(it, Config.electricToolCapacity) }
            capabilityProvider = { ElectricItemBase.ItemEnergyCapabilityProvider(it.stack) }
            onHitEntity = damageCallback(5f)
            onBlockDestroyed = {
                if (it.worldIn.isServer && it.state.getBlockHardness(it.worldIn, it.pos) != 0.0f) {
                    it.stack.useEnergy(Config.electricToolBreakConsumption)
                }
                true
            }
            getDestroySpeed = {
                val applicable = when (it.state.material) {
                    Material.IRON, Material.ANVIL, Material.ROCK -> 44f
                    Material.ORGANIC, Material.EARTH, Material.SAND, Material.SNOW_BLOCK,
                    Material.SNOW, Material.CLAY, Material.CAKE -> 15f
                    else -> -1f
                }

                if (applicable > 0f && it.stack.hasEnergy(Config.electricToolBreakConsumption))
                    applicable else 1.0f
            }
            getToolClasses = { mutableSetOf(ToolType.PICKAXE, ToolType.SHOVEL) }
            getHarvestLevel = { 3 }
            canHarvestBlock = { true }
        }.build()

        electric_piston = builder.withName("electric_piston").copy {
            constructor = { ElectricItemBase(it, Config.electricToolCapacity) }
            capabilityProvider = { ElectricItemBase.ItemEnergyCapabilityProvider(it.stack) }
            onHitEntity = damageCallback(2f)
            onItemRightClick = callback@{
                val start = it.playerIn.positionVector
                val end = it.playerIn.positionVector.add(it.playerIn.lookVec.times(6))
                // TODO
                val ctx = RayTraceContext(start, end, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, it.playerIn)
                val res = it.worldIn.rayTraceBlocks(ctx)
                if (res.type != RayTraceResult.Type.BLOCK) return@callback it.default

                val item = it.playerIn.getHeldItem(it.handIn)

                if (item.useEnergy(Config.electricToolPistonConsumption)) {
                    val power = if (it.playerIn.isSneaking) 0.25 else 1.75
                    val dir = it.playerIn.lookVec.subtractReverse(Vec3d.ZERO).times(power)
                    it.playerIn.addVelocity(dir.x, dir.y, dir.z)
                    it.playerIn.fallDistance = 0f

                    it.worldIn.playSound(
                        null,
                        it.playerIn.position,
                        SoundEvents.BLOCK_PISTON_EXTEND,
                        SoundCategory.BLOCKS,
                        0.5f,
                        it.worldIn.rand.nextFloat() * 0.25f + 0.6f
                    )

                    if (it.playerIn is ServerPlayerEntity) {
                        it.playerIn.connection.sendPacket(SEntityVelocityPacket(it.playerIn))
                    }

                    ActionResult(ActionResultType.SUCCESS, item)
                } else {
                    it.default
                }
            }
            itemInteractionForEntity = {
                val item = it.player.getHeldItem(it.hand)

                if (item.useEnergy(Config.electricToolPistonConsumption)) {
                    val dir = it.target.positionVector.subtract(it.player.positionVector).normalize().times(1.75)
                    it.target.addVelocity(dir.x, dir.y, dir.z)

                    it.player.world.playSound(
                        null,
                        it.player.position,
                        SoundEvents.BLOCK_PISTON_EXTEND,
                        SoundCategory.BLOCKS,
                        0.5f,
                        it.player.world.rand.nextFloat() * 0.25f + 0.6f
                    )

                    if (it.target is ServerPlayerEntity) {
                        it.target.connection.sendPacket(SEntityVelocityPacket(it.target))
                    }
                    if (it.player is ServerPlayerEntity) {
                        it.player.connection.sendPacket(SEntityVelocityPacket(it.target))
                    }

                    true
                } else {
                    false
                }
            }

        }.build()

        electric_chainsaw = builder.withName("electric_chainsaw").copy {
            constructor = { ElectricItemBase(it, Config.electricToolCapacity) }
            capabilityProvider = { ElectricItemBase.ItemEnergyCapabilityProvider(it.stack) }
            onHitEntity = damageCallback(14f)
            onBlockDestroyed = {
                if (it.worldIn.isServer && it.state.getBlockHardness(it.worldIn, it.pos) != 0.0f) {
                    it.stack.useEnergy(Config.electricToolBreakConsumption)
                }
                true
            }
            getDestroySpeed = {
                val applicable = when (it.state.material) {
                    Material.WOOD, Material.PLANTS, Material.ORGANIC,
                    Material.LEAVES, Material.CACTUS, Material.WEB -> true
                    else -> false
                }

                if (applicable && it.stack.hasEnergy(Config.electricToolBreakConsumption)) 10.0f else 1.0f
            }
            getToolClasses = { mutableSetOf(ToolType.AXE) }
        }.build()

        return listOf(batteryItemLow, batteryItemMedium, electric_drill, electric_chainsaw, electric_piston)
    }

    private fun ItemStack.useEnergy(amount: Int): Boolean {
        val cap = FORGE_ENERGY!!.fromItem(this) ?: return false
        if (cap.extractEnergy(amount, true) != amount) return false

        cap.extractEnergy(amount, false)
        return true
    }

    private fun ItemStack.hasEnergy(amount: Int): Boolean {
        val cap = FORGE_ENERGY!!.fromItem(this) ?: return false
        return cap.energyStored >= amount
    }

    private fun damageCallback(amount: Float): (HitEntityArgs) -> Boolean {
        return callback@{
            if (it.stack.useEnergy(Config.electricToolAttackConsumption)) {
                it.target.attackEntityFrom(DamageSource.GENERIC, amount)
            } else {
                false
            }
        }
    }
}