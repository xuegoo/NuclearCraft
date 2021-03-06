package nc.tile.passive;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import gregtech.api.capability.GregtechCapabilities;
import nc.ModCheck;
import nc.config.NCConfig;
import nc.tile.energyFluid.TileEnergyFluidSidedInventory;
import nc.tile.internal.energy.EnergyConnection;
import nc.tile.internal.fluid.FluidConnection;
import nc.util.ItemStackHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public abstract class TilePassiveAbstract extends TileEnergyFluidSidedInventory implements /*IInterfaceable,*/ ITilePassive {
	
	public int tickCount;
	public final int updateRate;
	public boolean isRunning;
	public boolean energyBool;
	public boolean stackBool;
	public boolean fluidBool;
	
	public final int energyChange;
	public static ItemStack stackChange;
	public final int itemChange;
	public final int fluidChange;
	public final FluidStack fluidStackChange;
	public final Fluid fluidType;
	
	public TilePassiveAbstract(String name, int energyChange, int changeRate) {
		this(name, new ItemStack(Items.BEEF), 0, energyChange, FluidRegistry.LAVA, 0, changeRate);
	}
	
	public TilePassiveAbstract(String name, ItemStack stack, int itemChange, int changeRate) {
		this(name, stack, itemChange, 0, FluidRegistry.LAVA, 0, changeRate);
	}
	
	public TilePassiveAbstract(String name, Fluid fluid, int fluidChange, int changeRate) {
		this(name, new ItemStack(Items.BEEF), 0, 0, fluid, fluidChange, changeRate);
	}
	
	public TilePassiveAbstract(String name, Fluid fluid, int fluidChange, int changeRate, List<String> fluidTypes) {
		this(name, new ItemStack(Items.BEEF), 0, 0, fluid, fluidChange, changeRate, fluidTypes);
	}
	
	public TilePassiveAbstract(String name, ItemStack stack, int itemChange, int energyChange, int changeRate) {
		this(name, stack, itemChange, energyChange, FluidRegistry.LAVA, 0, changeRate);
	}
	
	public TilePassiveAbstract(String name, int energyChange, Fluid fluid, int fluidChange, int changeRate) {
		this(name, new ItemStack(Items.BEEF), 0, energyChange, fluid, fluidChange, changeRate);
	}
	
	public TilePassiveAbstract(String name, ItemStack stack, int itemChange, Fluid fluid, int fluidChange, int changeRate) {
		this(name, stack, itemChange, 0, fluid, fluidChange, changeRate);
	}
	
	public TilePassiveAbstract(String name, ItemStack stack, int itemChange, int energyChange, Fluid fluid, int fluidChange, int changeRate) {
		this(name, stack, itemChange, energyChange, fluid, fluidChange, changeRate, Lists.newArrayList(fluid.getName()));
	}
	
	public TilePassiveAbstract(String name, ItemStack stack, int itemChange, int energyChange, Fluid fluid, int fluidChange, int changeRate, List<String> fluidTypes) {
		super(name, 1, energyChange == 0 ? 1 : NCConfig.rf_per_eu*MathHelper.abs(energyChange)*changeRate, energyChange == 0 ? 0 : NCConfig.rf_per_eu*MathHelper.abs(energyChange), energyChange > 0 ? energyConnectionAll(EnergyConnection.OUT) : (energyChange < 0 ? energyConnectionAll(EnergyConnection.IN) : energyConnectionAll(EnergyConnection.NON)), fluidChange == 0 ? 1 : 2*MathHelper.abs(fluidChange)*changeRate, fluidChange > 0 ? FluidConnection.OUT : (fluidChange < 0 ? FluidConnection.IN : FluidConnection.NON), fluidTypes);
		this.energyChange = energyChange*changeRate;
		this.itemChange = itemChange*changeRate;
		stackChange = ItemStackHelper.changeStackSize(stack, MathHelper.abs(itemChange)*changeRate);
		this.fluidChange = fluidChange*changeRate;
		fluidStackChange = new FluidStack(fluid, MathHelper.abs(fluidChange)*changeRate);
		fluidType = fluid;
		updateRate = changeRate*20;
		
		if (fluidChange < 0) tanks.get(0).setStrictlyInput(true);
		else tanks.get(0).setStrictlyOutput(true);
	}
	
	@Override
	public void update() {
		boolean flag = isRunning;
		boolean flag1 = false;
		super.update();
		if(!world.isRemote) {
			if (shouldUpdate()) {
				energyBool = changeEnergy(false);
				stackBool = changeStack(false);
				fluidBool = changeFluid(false);
			}
			isRunning = isRunning(energyBool, stackBool, fluidBool);
			if (flag != isRunning) {
				flag1 = true;
				if (ModCheck.ic2Loaded()) removeTileFromENet();
				setState(isRunning);
				world.notifyNeighborsOfStateChange(pos, getBlockType(), true);
				if (ModCheck.ic2Loaded()) addTileToENet();
			}
			if (itemChange > 0) pushStacks();
			if (energyChange > 0) pushEnergy();
			if (fluidChange > 0) pushFluid();
		}
		if (flag1) {
			markDirty();
		}
	}
	
	public boolean shouldUpdate() {
		return tickCount == 0;
	}
	
	@Override
	public boolean shouldTileCheck() {
		int currentCount = tickCount;
		currentCount %= updateRate/20;
		return currentCount == 0;
	}
	
	@Override
	public void tickTile() {
		tickCount++; tickCount %= updateRate;
	}
	
	public boolean changeEnergy(boolean simulateChange) {
		if (energyChange == 0) return simulateChange;
		if (getEnergyStorage().getEnergyStored() >= getEnergyStorage().getMaxEnergyStored() && energyChange > 0) return false;
		if (getEnergyStorage().getEnergyStored() < MathHelper.abs(energyChange) && energyChange < 0) return false;
		if (!simulateChange) {
			if (changeStack(true) && changeFluid(true)) getEnergyStorage().changeEnergyStored(energyChange);
		}
		return true;
	}
	
	public boolean changeStack(boolean simulateChange) {
		if (itemChange == 0) return simulateChange;
		if (!ItemStack.areItemsEqual(inventoryStacks.get(0), stackChange) && !inventoryStacks.get(0).isEmpty() && !simulateChange) inventoryStacks.set(0, ItemStack.EMPTY);
		if (itemChange > 0) {
			if (!inventoryStacks.get(0).isEmpty()) if (inventoryStacks.get(0).getCount() + itemChange > getInventoryStackLimit()) return false;
			if (inventoryStacks.get(0).isEmpty() && !simulateChange) {
				if (changeEnergy(true) && changeFluid(true)) setNewStack();
			}
			else if (!simulateChange) {
				if (changeEnergy(true) && changeFluid(true)) inventoryStacks.get(0).grow(itemChange);
			}
			return true;
		} else {
			if (inventoryStacks.get(0).isEmpty() || inventoryStacks.get(0).getCount() < MathHelper.abs(itemChange)) return false;
			else if (inventoryStacks.get(0).getCount() > MathHelper.abs(itemChange) && !simulateChange) {
				if (changeEnergy(true) && changeFluid(true)) inventoryStacks.get(0).grow(itemChange);
			}
			else if (inventoryStacks.get(0).getCount() == MathHelper.abs(itemChange) && !simulateChange) {
				if (changeEnergy(true) && changeFluid(true)) inventoryStacks.set(0, ItemStack.EMPTY);
			}
			return true;
		}
	}
	
	public void setNewStack() {
		inventoryStacks.set(0, stackChange);
	}
	
	public boolean changeFluid(boolean simulateChange) {
		if (fluidChange == 0) return simulateChange;
		if (tanks.get(0).getFluidAmount() >= tanks.get(0).getCapacity() && fluidChange > 0) return false;
		if (tanks.get(0).getFluidAmount() < MathHelper.abs(fluidChange) && fluidChange < 0) return false;
		if (!simulateChange) {
			if (changeEnergy(true) && changeStack(true)) {
				if (fluidChange > 0) {
					if (fluidStackChange != null) tanks.get(0).changeFluidStored(fluidType, fluidChange);
				}
				else tanks.get(0).changeFluidStored(fluidChange);
			}
		}
		return true;
	}
	
	public boolean isRunning(boolean energy, boolean stack, boolean fluid) {
		if (energyChange == 0 && itemChange == 0 && fluidChange == 0) return true;
		if (energyChange >= 0) {
			if (itemChange >= 0) {
				if (fluidChange >= 0) return energy || stack || fluid;
				else return fluid;
			} else {
				if (fluidChange >= 0) return stack;
				else return stack && fluid;
			}
		} else {
			if (itemChange >= 0) {
				if (fluidChange >= 0) return energy;
				else return energy && fluid;
			} else {
				if (fluidChange >= 0) return energy && stack;
				else return energy && stack && fluid;
			}
		}
	}
	
	@Override
	public int getEnergyChange() {
		return energyChange;
	}
	
	@Override
	public int getItemChange() {
		return itemChange;
	}
	
	@Override
	public int getFluidChange() {
		return fluidChange;
	}
	
	@Override
	public boolean canPushEnergyTo() {
		return energyChange < 0;
	}
	
	@Override
	public boolean canPushItemsTo() {
		return itemChange < 0;
	}
	
	@Override
	public boolean canPushFluidsTo() {
		return fluidChange < 0;
	}
	
	// Inventory
	
	@Override
	public int getInventoryStackLimit() {
		return itemChange == 0 ? 1 : 2*MathHelper.abs(itemChange);
	}
	
	// Sided Inventory

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[] {0};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, EnumFacing direction) {
		return itemChange < 0 && ItemStack.areItemsEqual(stack, stackChange);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, EnumFacing direction) {
		return itemChange > 0;
	}
	
	// IC2 EU

	@Override
	public int getEUSourceTier() {
		return 2;
	}

	@Override
	public int getEUSinkTier() {
		return 4;
	}
	
	// NBT
	
	@Override
	public NBTTagCompound writeAll(NBTTagCompound nbt) {
		super.writeAll(nbt);
		nbt.setBoolean("isRunning", isRunning);
		nbt.setBoolean("energyBool", energyBool);
		nbt.setBoolean("stackBool", stackBool);
		nbt.setBoolean("fluidBool", fluidBool);
		return nbt;
	}
		
	@Override
	public void readAll(NBTTagCompound nbt) {
		super.readAll(nbt);
		isRunning = nbt.getBoolean("isRunning");
		energyBool = nbt.getBoolean("energyBool");
		stackBool = nbt.getBoolean("stackBool");
		fluidBool = nbt.getBoolean("fluidBool");
	}
	
	// Capability
	
	IItemHandler itemHandler = new InvWrapper(this);
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing side) {
		if (energyChange != 0) {
			if (capability == CapabilityEnergy.ENERGY) return getEnergySide(side) != null;
			if (ModCheck.gregtechLoaded()) if (capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER) return getEnergySideGT(side) != null;
		}
		if (fluidChange != 0) {
			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return true;
			//else if (capability == Capabilities.GAS_HANDLER_CAPABILITY) return true;
			//else if (capability == Capabilities.TUBE_CONNECTION_CAPABILITY) return true;
		}
		if (itemChange != 0) {
			if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		}
		return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing side) {
		if (energyChange != 0) {
			if (capability == CapabilityEnergy.ENERGY) return (T) getEnergySide(side);
			if (ModCheck.gregtechLoaded()) if (capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER) return (T) getEnergySideGT(side);
		}
		if (fluidChange != 0) {
			if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
			//if (capability == Capabilities.GAS_HANDLER_CAPABILITY) return Capabilities.GAS_HANDLER_CAPABILITY.cast(this);
			//if (capability == Capabilities.TUBE_CONNECTION_CAPABILITY) return Capabilities.TUBE_CONNECTION_CAPABILITY.cast(this);
		}
		if (itemChange != 0) {
			if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) itemHandler;
		}
		return null;
	}
}
