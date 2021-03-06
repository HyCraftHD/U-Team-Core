package info.u_team.u_team_core.screen;

import java.util.*;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import info.u_team.u_team_core.container.*;
import info.u_team.u_team_core.gui.renderer.FluidInventoryRenderer;
import info.u_team.u_team_core.intern.init.UCoreNetwork;
import info.u_team.u_team_core.intern.network.FluidClickContainerMessage;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.*;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class FluidContainerScreen<T extends Container> extends ContainerScreen<T> {
	
	private static final FluidInventoryRenderer FLUID_RENDERER = new FluidInventoryRenderer();
	
	protected FluidInventoryRenderer fluidRenderer;
	
	protected FluidSlot hoveredFluidSlot;
	
	public FluidContainerScreen(T container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title);
		setFluidRenderer(FLUID_RENDERER);
	}
	
	protected void setFluidRenderer(FluidInventoryRenderer fluidRenderer) {
		this.fluidRenderer = fluidRenderer;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
		if (container instanceof FluidContainer) {
			hoveredFluidSlot = null;
			
			final FluidContainer fluidContainer = (FluidContainer) container;
			for (int index = 0; index < fluidContainer.fluidSlots.size(); index++) {
				
				final FluidSlot fluidSlot = fluidContainer.fluidSlots.get(index);
				
				if (fluidSlot.isEnabled()) {
					drawFluidSlot(matrixStack, fluidSlot);
					
					if (isFluidSlotSelected(fluidSlot, mouseX, mouseY)) {
						hoveredFluidSlot = fluidSlot;
						final int x = fluidSlot.getX();
						final int y = fluidSlot.getY();
						RenderSystem.disableDepthTest();
						RenderSystem.colorMask(true, true, true, false);
						final int slotColor = getFluidSlotColor(index);
						fillGradient(matrixStack, x, y, x + 16, y + 16, slotColor, slotColor);
						RenderSystem.colorMask(true, true, true, true);
						RenderSystem.enableDepthTest();
					}
				}
			}
		}
	}
	
	@Override
	protected void renderHoveredTooltip(MatrixStack matrixStack, int mouseX, int mouseY) {
		super.renderHoveredTooltip(matrixStack, mouseX, mouseY);
		
		if (minecraft.player.inventory.getItemStack().isEmpty() && hoveredFluidSlot != null && !hoveredFluidSlot.getStack().isEmpty()) {
			func_243308_b(matrixStack, getTooltipFromFluid(hoveredFluidSlot), mouseX, mouseY);
		}
		
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (button == 0) {
			final FluidSlot fluidSlot = getSelectedFluidSlot(mouseX, mouseY);
			if (fluidSlot != null) {
				if (!playerInventory.getItemStack().isEmpty()) {
					UCoreNetwork.NETWORK.sendToServer(new FluidClickContainerMessage(container.windowId, fluidSlot.slotNumber, hasShiftDown(), playerInventory.getItemStack()));
				}
				return true;
			}
		}
		return super.mouseClicked(mouseX, mouseY, button);
	}
	
	protected void drawFluidSlot(MatrixStack matrixStack, FluidSlot fluidSlot) {
		fluidRenderer.drawFluid(matrixStack, fluidSlot.getX(), fluidSlot.getY(), fluidSlot.getStack());
	}
	
	protected boolean isFluidSlotSelected(FluidSlot fluidSlot, double mouseX, double mouseY) {
		return isPointInRegion(fluidSlot.getX(), fluidSlot.getY(), 16, 16, mouseX, mouseY);
	}
	
	public int getFluidSlotColor(int index) {
		return super.getSlotColor(index);
	}
	
	public List<ITextComponent> getTooltipFromFluid(FluidSlot fluidSlot) {
		final FluidStack stack = fluidSlot.getStack();
		
		final List<ITextComponent> list = new ArrayList<>();
		
		list.add(stack.getDisplayName());
		list.add(new StringTextComponent(stack.getAmount() + " / " + fluidSlot.getSlotCapacity()).mergeStyle(TextFormatting.GRAY));
		
		if (minecraft.gameSettings.advancedItemTooltips) {
			list.add((new StringTextComponent(ForgeRegistries.FLUIDS.getKey(stack.getFluid()).toString())).mergeStyle(TextFormatting.DARK_GRAY));
		}
		
		return list;
	}
	
	private FluidSlot getSelectedFluidSlot(double mouseX, double mouseY) {
		if (container instanceof FluidContainer) {
			final FluidContainer fluidContainer = (FluidContainer) container;
			for (int index = 0; index < fluidContainer.fluidSlots.size(); index++) {
				final FluidSlot fluidSlot = fluidContainer.fluidSlots.get(index);
				if (isFluidSlotSelected(fluidSlot, mouseX, mouseY) && fluidSlot.isEnabled()) {
					return fluidSlot;
				}
			}
		}
		return null;
	}
}
