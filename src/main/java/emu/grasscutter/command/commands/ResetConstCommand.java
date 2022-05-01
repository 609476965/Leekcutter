package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.avatar.Avatar;
import emu.grasscutter.game.entity.EntityAvatar;
import emu.grasscutter.game.player.Player;

import java.util.List;

@Command(label = "resetconst", usage = "resetconst [all]",
        description = "���õ�ǰ��ɫ������,���µ�¼������Ч",
        aliases = {"resetconstellation"}, permission = "player.resetconstellation")
public final class ResetConstCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "������Ϸ��ִ�д�ָ��");
            return;
        }

        if (args.size() > 0 && args.get(0).equalsIgnoreCase("all")) {
            sender.getAvatars().forEach(this::resetConstellation);
            sender.dropMessage("�������н�ɫ������");
        } else {
            EntityAvatar entity = sender.getTeamManager().getCurrentAvatarEntity();
            if (entity == null) {
                return;
            }

            Avatar avatar = entity.getAvatar();
            this.resetConstellation(avatar);

            sender.dropMessage(avatar.getAvatarData().getName() + "�����������á����µ�½�Բ鿴���");
        }
    }

    private void resetConstellation(Avatar avatar) {
        avatar.getTalentIdList().clear();
        avatar.setCoreProudSkillLevel(0);
        avatar.recalcStats();
        avatar.save();
    }
}