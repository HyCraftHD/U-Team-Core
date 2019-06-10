package info.u_team.u_team_core.potion;

import info.u_team.u_team_core.api.registry.IUPotion;
import net.minecraft.potion.*;

public class UPotion extends Potion implements IUPotion {
	
	protected final String name;
	
	public UPotion(String name, EffectInstance... effects) {
		super(name, effects);
		this.name = name;
	}
	
	@Override
	public String getEntryName() {
		return name;
	}
}
