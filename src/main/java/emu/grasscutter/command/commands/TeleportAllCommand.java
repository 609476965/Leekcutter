package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.utils.Position;

import java.util.List;

@Command(label = "tpall", usage = "tpall",
        description = "���Ͷ������������е���ҵ�����ص�", permission = "player.tpall")
public final class TeleportAllCommand implements CommandHandler {
    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "������Ϸ��ִ�д�ָ��");
            return;
        }
        
        if (!sender.getWorld().isMultiplayer()) {
            CommandHandler.sendMessage(sender, "��ֻ���ڶ�����Ϸ��ʹ�ô�ָ��");
            return;
        }
        
        for (Player player : sender.getWorld().getPlayers()) {
            if (player.equals(sender))
                continue;
            Position pos = sender.getPos();

            player.getWorld().transferPlayerToScene(player, sender.getSceneId(), pos);
        }
    }
}
