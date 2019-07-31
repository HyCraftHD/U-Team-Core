package info.u_team.u_team_core.intern.init;

import info.u_team.u_team_core.UCoreMain;
import info.u_team.u_team_core.api.dye.*;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UCoreMain.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class UCoreColors {
	
	@SubscribeEvent
	public static void register(ColorHandlerEvent.Item event) {
		event.getItemColors().register((itemstack, index) -> {
			final Item item = itemstack.getItem();
			if (item instanceof IDyeableItem) {
				return ((IDyeableItem) item).getColor(itemstack);
			}
			return 0;
		}, DyeableItemsRegistry.getDyeableItems().stream().toArray(Item[]::new));
	}
}
