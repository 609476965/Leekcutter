package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "reload", usage = "reload",
        description = "重新载入服务器配置文件", permission = "server.reload")
public final class ReloadCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        CommandHandler.sendMessage(sender, "正在重载服务器配置文件...");
        Grasscutter.loadConfig();
        Grasscutter.getGameServer().getGachaManager().load();
        Grasscutter.getGameServer().getDropManager().load();
        Grasscutter.getGameServer().getShopManager().load();
        Grasscutter.getDispatchServer().loadQueries();
        CommandHandler.sendMessage(sender, "重载完毕~");
    }
}
