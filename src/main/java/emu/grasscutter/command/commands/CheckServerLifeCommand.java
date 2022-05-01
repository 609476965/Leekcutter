package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "checklife", usage = "checklife", aliases = {"cf"},
        description = "ȷ�Ϸ������Ƿ�������")
public final class CheckServerLifeCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "������Ϸ��ִ�и�ָ��");
            return;
        }

        sender.dropMessage(String.format("ү��������"));
    }
}
