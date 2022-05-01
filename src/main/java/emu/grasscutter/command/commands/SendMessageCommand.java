package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "say", usage = "say <player> <message>", description = "��Ϊ������������Ϣ�����",
        aliases = {"sendservmsg", "sendservermessage", "sendmessage"}, permission = "server.sendmessage")
public final class SendMessageCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (args.size() < 2) {
            CommandHandler.sendMessage(null, "�÷�: sendmessage <player> <message>");
            return;
        }

        try {
            int target = Integer.parseInt(args.get(0));
            String message = String.join(" ", args.subList(1, args.size()));

            Player targetPlayer = Grasscutter.getGameServer().getPlayerByUid(target);
            if (targetPlayer == null) {
                CommandHandler.sendMessage(sender, "û��������");
                return;
            }

            CommandHandler.sendMessage(targetPlayer, message);
            CommandHandler.sendMessage(sender, "��Ϣ�ѷ���");
        } catch (NumberFormatException ignored) {
            CommandHandler.sendMessage(sender, "��Ч�����id");
        }
    }
}