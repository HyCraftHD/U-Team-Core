package info.u_team.u_team_core.intern.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import info.u_team.u_team_core.intern.command.uteamcore.DumbItemStackSubCommand;
import net.minecraft.command.CommandSource;

public class UTeamCoreCommand {
	
	public UTeamCoreCommand(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(LiteralArgumentBuilder.<CommandSource> literal("uteamcore").then(DumbItemStackSubCommand.register()));
	}
	
}