package info.u_team.u_team_core.api.gui;

import net.minecraft.util.text.ITextComponent;

public interface ITextProvider extends ITextColorProvider {
	
	ITextComponent getCurrentText();
	
}
