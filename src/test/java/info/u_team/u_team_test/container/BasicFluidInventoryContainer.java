package info.u_team.u_team_test.container;

import info.u_team.u_team_core.container.UTileEntityContainer;
import info.u_team.u_team_test.init.TestContainers;
import info.u_team.u_team_test.tileentity.BasicFluidInventoryTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class BasicFluidInventoryContainer extends UTileEntityContainer<BasicFluidInventoryTileEntity> {
	
	// Client
	public BasicFluidInventoryContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
		super(TestContainers.BASIC_FLUID_INVENTORY, id, playerInventory, buffer);
	}
	
	// Server
	public BasicFluidInventoryContainer(int id, PlayerInventory playerInventory, BasicFluidInventoryTileEntity tileEntity) {
		super(TestContainers.BASIC_FLUID_INVENTORY, id, playerInventory, tileEntity);
	}
	
	@Override
	protected void init(boolean server) {
		appendInventory(tileEntity.getItemSlots(), 1, 4, 8, 41);
		appendPlayerInventory(playerInventory, 8, 91);
	}
	
}