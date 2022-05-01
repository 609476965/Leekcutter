package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.Account;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "permission", usage = "permission <add|remove> <username> <permission>",
        description = "��ӻ��Ƴ���ҵ�Ȩ��", permission = "*")
public final class PermissionCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (args.size() < 3) {
            CommandHandler.sendMessage(sender, "�÷�: permission <add|remove> <username> <permission>");
            return;
        }

        String action = args.get(0);
        String username = args.get(1);
        String permission = args.get(2);

        Account account = Grasscutter.getGameServer().getAccountByName(username);
        if (account == null) {
            CommandHandler.sendMessage(sender, "�Ҳ������˺�");
            return;
        }

        switch (action) {
            default:
                CommandHandler.sendMessage(sender, "�÷�: permission <add|remove> <username> <permission>");
                break;
            case "add":
                if (account.addPermission(permission)) {
                    CommandHandler.sendMessage(sender, "Ȩ������ӣ���ɱ����������ε����ţ�");
                } else CommandHandler.sendMessage(sender, "���Ѿ������Ȩ������");
                break;
            case "remove":
                if (account.removePermission(permission)) {
                    CommandHandler.sendMessage(sender, "Ȩ��ɾ���ɹ����ǲ�������������ˣ�");
                } else CommandHandler.sendMessage(sender, "����...�����ұ�����û�д�Ȩ�޺ð�");
                break;
        }

        account.save();
    }
}