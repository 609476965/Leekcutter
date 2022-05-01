package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "position", usage = "position", aliases = {"pos"},
        description = "��ȡ�������")
public final class PositionCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "������Ϸ��ִ�д�ָ��");
            return;
        }

        sender.dropMessage(String.format("����: X��%.3f, Y��%.3f, Z��%.3f\n����id: %d",
                sender.getPos().getX(), sender.getPos().getY(), sender.getPos().getZ(), sender.getSceneId()));
    }
}
