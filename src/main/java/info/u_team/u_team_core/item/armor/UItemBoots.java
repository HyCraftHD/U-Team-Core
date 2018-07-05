package info.u_team.u_team_core.item.armor;

import info.u_team.u_team_core.creativetab.UCreativeTab;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * Item API<br>
 * -> Basic Armor Item
 * 
 * @date 27.12.17
 * @author HyCraftHD
 */
public class UItemBoots extends UItemArmor {
	
	public UItemBoots(String name, UCreativeTab tab, ArmorMaterial material) {
		super(name, tab, material, EntityEquipmentSlot.FEET, "boots");
	}
	
}
