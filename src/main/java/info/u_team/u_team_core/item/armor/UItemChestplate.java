package info.u_team.u_team_core.item.armor;

import info.u_team.u_team_core.creativetab.UCreativeTab;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * Item API<br>
 * -> Basic Armor Item
 * 
 * @date 27.12.17
 * @author HyCraftHD
 *
 */
public class UItemChestplate extends UItemArmor {
	
	public UItemChestplate(String name, UCreativeTab tab, ArmorMaterial material) {
		super(name, tab, material, EntityEquipmentSlot.CHEST, "chestplate");
	}
	
}
