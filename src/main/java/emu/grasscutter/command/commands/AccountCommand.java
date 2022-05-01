package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.database.DatabaseHelper;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "account", usage = "account <create|delete> <username> [uid]", description = "�޸��û��˺�")
public final class AccountCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender != null) {
            CommandHandler.sendMessage(sender, "���ָ��ֻ���ڿ���̨������");
            return;
        }

        if (args.size() < 2) {
            CommandHandler.sendMessage(null, "�÷�: account <create|delete> <username> [uid]");
            return;
        }

        String action = args.get(0);
        String username = args.get(1);

        switch (action) {
            default:
                CommandHandler.sendMessage(null, "�÷�: account <create|delete> <username> [uid]");
                return;
            case "create":
                int uid = 0;
                if (args.size() > 2) {
                    try {
                        uid = Integer.parseInt(args.get(2));
                    } catch (NumberFormatException ignored) {
                        CommandHandler.sendMessage(null, "UID��Ч");
                        return;
                    }
                }

                emu.grasscutter.game.Account account = DatabaseHelper.createAccountWithId(username, uid);
                if (account == null) {
                    CommandHandler.sendMessage(null, "�벻Ҫ���Դ���һ���Ѵ��ڵ��˺�");
                    return;
                } else {
                    account.addPermission("*");
                    account.save(); // Save account to database.

                    CommandHandler.sendMessage(null, "�˺��Ѵ�����uidΪ��" + account.getPlayerUid());
                }
                return;
            case "delete":
                if (DatabaseHelper.deleteAccount(username)) {
                    CommandHandler.sendMessage(null, "�˺�ɾ���ɹ�");
                } else {
                    CommandHandler.sendMessage(null, "�Ҳ������˺�");
                }
        }
    }
}
