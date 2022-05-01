package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "stop", usage = "stop",
        description = "�رշ����", permission = "server.stop")
public final class StopCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        CommandHandler.sendMessage(null, "�������ر���...");
        for (Player p : Grasscutter.getGameServer().getPlayers().values()) {
            CommandHandler.sendMessage(p, "�������ر���...");
        }

        System.exit(1);
    }
}
