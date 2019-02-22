package info.u_team.u_team_test.tileentity;

import java.util.Iterator;

import info.u_team.u_team_core.api.ISyncedContainerTileEntity;
import info.u_team.u_team_core.tileentity.UTileEntityContainer;
import info.u_team.u_team_test.TestMod;
import info.u_team.u_team_test.container.ContainerTileEntity;
import info.u_team.u_team_test.init.TestTileEntityTypes;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraftforge.api.distmarker.*;

public class TileEntityTileEntity extends UTileEntityContainer implements IInventory, ISyncedContainerTileEntity, ITickable {
	
	private NonNullList<ItemStack> list;
	
	public TileEntityTileEntity() {
		super(TestTileEntityTypes.tileentity);
		list = NonNullList.withSize(18, ItemStack.EMPTY);
	}
	
	public int cooldown, value;
	
	@Override
	public void writeOnContainerSyncServer(NBTTagCompound compound) {
		compound.setInt("value", value);
		compound.setInt("cooldown", cooldown);
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void readOnContainerSyncClient(NBTTagCompound compound) {
		value = compound.getInt("value");
		cooldown = compound.getInt("cooldown");
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void writeOnContainerSyncClient(NBTTagCompound compound) {
		compound.setInt("value", value);
		compound.setInt("cooldown", cooldown);
	}
	
	@Override
	public void readOnContainerSyncServer(NBTTagCompound compound) {
		value = compound.getInt("value");
		cooldown = Math.min(compound.getInt("cooldown"), 100);
		markDirty();
	}
	
	private int timer;
	
	public void tick() {
		if (world.isRemote) {
			return;
		}
		
		if (timer < cooldown) {
			timer++;
			return;
		}
		timer = 0;
		value++;
		markDirty();
	}
	
	@Override
	public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
		return new ContainerTileEntity(inventoryPlayer, this);
	}
	
	@Override
	public ResourceLocation getGui() {
		return new ResourceLocation(TestMod.modid, "tileentity");
	}
	
	@Override
	public void readNBT(NBTTagCompound compound) {
		ItemStackHelper.loadAllItems(compound, list);
		value = compound.getInt("value");
		cooldown = compound.getInt("cooldown");
	}
	
	@Override
	public void writeNBT(NBTTagCompound compound) {
		ItemStackHelper.saveAllItems(compound, list);
		compound.setInt("value", value);
		compound.setInt("cooldown", cooldown);
	}
	
	@Override
	public void clear() {
		list.clear();
	}
	
	@Override
	public void closeInventory(EntityPlayer var1) {
	}
	
	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		ItemStack lvt_3_1_ = ItemStackHelper.getAndSplit(list, var1, var2);
		if (!lvt_3_1_.isEmpty()) {
			this.markDirty();
		}
		return lvt_3_1_;
	}
	
	@Override
	public int getField(int var1) {
		return 0;
	}
	
	@Override
	public int getFieldCount() {
		return 0;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public int getSizeInventory() {
		return list.size();
	}
	
	@Override
	public ItemStack getStackInSlot(int var1) {
		return var1 >= 0 && var1 < list.size() ? (ItemStack) list.get(var1) : ItemStack.EMPTY;
	}
	
	@Override
	public boolean isEmpty() {
		Iterator<ItemStack> var1 = list.iterator();
		
		ItemStack lvt_2_1_;
		do {
			if (!var1.hasNext()) {
				return true;
			}
			
			lvt_2_1_ = (ItemStack) var1.next();
		} while (lvt_2_1_.isEmpty());
		
		return false;
	}
	
	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return true;
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer var1) {
		return true;
	}
	
	@Override
	public void openInventory(EntityPlayer var1) {
	}
	
	@Override
	public ItemStack removeStackFromSlot(int var1) {
		ItemStack lvt_2_1_ = (ItemStack) this.list.get(var1);
		if (lvt_2_1_.isEmpty()) {
			return ItemStack.EMPTY;
		} else {
			this.list.set(var1, ItemStack.EMPTY);
			return lvt_2_1_;
		}
	}
	
	@Override
	public void setField(int var1, int var2) {
		
	}
	
	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		this.list.set(var1, var2);
		if (!var2.isEmpty() && var2.getCount() > this.getInventoryStackLimit()) {
			var2.setCount(this.getInventoryStackLimit());
		}
		
		this.markDirty();
		
	}
	
}