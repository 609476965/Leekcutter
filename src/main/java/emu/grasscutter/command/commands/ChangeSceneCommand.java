package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "changescene", usage = "changescene <scene id>",
        description = "�л���ĳ�����Scene��", aliases = {"scene"}, permission = "player.changescene")
public final class ChangeSceneCommand implements CommandHandler {
    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "������Ϸ��ִ�и�ָ��");
            return;
        }

        if (args.size() < 1) {
            CommandHandler.sendMessage(sender, "�÷�: changescene <scene id>");
            return;
        }

        try {
            int sceneId = Integer.parseInt(args.get(0));
            
            if (sceneId == sender.getSceneId()) {
            	CommandHandler.sendMessage(sender, "�㱾���Ѿ��ڴ˳���������");
            	return;
            }
            
            boolean result = sender.getWorld().transferPlayerToScene(sender, sceneId, sender.getPos());
            CommandHandler.sendMessage(sender, "�����л��������� " + sceneId);
            
            if (!result) {
                CommandHandler.sendMessage(sender, "�Ҳ����˳���");
            }
        } catch (Exception e) {
            CommandHandler.sendMessage(sender, "�����ˣ�������Ϣ��" + e + "��ס���÷���: changescene <scene id>");
        }
    }
}
