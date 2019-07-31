package info.u_team.u_team_core.api;

import info.u_team.u_team_core.item.tool.UToolMaterial;
import net.minecraft.item.IItemTier;

/**
 * This interface extends the {@link IItemTier} interface for tool materials.
 * 
 * @author HyCraftHD
 *
 */
public interface IToolMaterial extends IItemTier {
	
	/**
	 * Returns the additional damage that is added to the {@link #getAttackDamage()} per given tool. {@link AxeItem} and
	 * {@link ShovelItem} can accept float values so this method returns floats. {@link PickaxeItem} and {@link SwordItem}
	 * cannot accept floats so they will be casted to integers.
	 * 
	 * @param tools The tool
	 * @return Additional damage
	 */
	float getAdditionalDamage(Tools tools);
	
	/**
	 * Returns the attack speed per given tool.
	 * 
	 * @param tools The tool
	 * @return Attack speed
	 */
	float getAttackSpeed(Tools tools);
	
	/**
	 * Just a little helper enumeration with all vanilla tools.
	 * 
	 * @author HyCraftHD
	 *
	 */
	public static enum Tools {
		AXE(0),
		HOE(1),
		PICKAXE(2),
		SHOVEL(3),
		SWORD(4);
		
		private final int index;
		
		private Tools(int index) {
			this.index = index;
		}
		
		/**
		 * Returns the index. Only used in {@link UToolMaterial} for simple arrays.
		 * 
		 * @return
		 */
		public int getIndex() {
			return index;
		}
	}
	
}
