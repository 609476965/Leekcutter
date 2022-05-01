package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.data.def.AvatarSkillDepotData;
import emu.grasscutter.game.avatar.Avatar;
import emu.grasscutter.game.entity.EntityAvatar;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.server.packet.send.PacketAvatarSkillChangeNotify;
import emu.grasscutter.server.packet.send.PacketAvatarSkillUpgradeRsp;

import java.util.List;

@Command(label = "talent", usage = "talent <talentID> <value>",
        description = "���õ�ǰ��ɫ���츳�ȼ�", permission = "player.settalent")
public final class TalentCommand implements CommandHandler {

    @Override
    public void execute(Player sender, List<String> args) {
        if (sender == null) {
            CommandHandler.sendMessage(null, "������Ϸ��ִ�и�ָ��");
            return;
        }

        if (args.size() < 1){
            CommandHandler.sendMessage(sender, "�趨�츳�ȼ�: /talent set <talentID> <value>");
            CommandHandler.sendMessage(sender, "��һ���趨�츳�ȼ��ķ���: /talent <n or e or q> <value>");
            CommandHandler.sendMessage(sender, "��ȡ�츳id: /talent getid");
            return;
        }

        String cmdSwitch = args.get(0);
        switch (cmdSwitch) {
            default:
                CommandHandler.sendMessage(sender, "�趨�츳�ȼ�: /talent set <talentID> <value>");
                CommandHandler.sendMessage(sender, "��һ���趨�츳�ȼ��ķ���: /talent <n or e or q> <value>");
                CommandHandler.sendMessage(sender, "��ȡ�츳id: /talent getid");
            return;
            case "set":
                    try {
                        int skillId = Integer.parseInt(args.get(1));
                        int nextLevel = Integer.parseInt(args.get(2));
                        EntityAvatar entity = sender.getTeamManager().getCurrentAvatarEntity();
                        Avatar avatar = entity.getAvatar(); 
                        int skillIdNorAtk = avatar.getData().getSkillDepot().getSkills().get(0);
                        int skillIdE = avatar.getData().getSkillDepot().getSkills().get(1);
                        int skillIdQ = avatar.getData().getSkillDepot().getEnergySkill();
                        int currentLevelNorAtk = avatar.getSkillLevelMap().get(skillIdNorAtk);
                        int currentLevelE = avatar.getSkillLevelMap().get(skillIdE);
                        int currentLevelQ = avatar.getSkillLevelMap().get(skillIdQ);
                        if (args.size() < 2){
                            CommandHandler.sendMessage(sender, "�����츳�ȼ�: /talent set <talentID> <value>");
                            CommandHandler.sendMessage(sender, "��ȡ�츳id: /talent getid");
                            return;
                        }
                        if (nextLevel > 16){ 
                            CommandHandler.sendMessage(sender, "�츳�ȼ���Ч���ȼ�Ӧ����16��");
                            return;
                        }
                            if (skillId == skillIdNorAtk){ 
                            // Upgrade skill
                            avatar.getSkillLevelMap().put(skillIdNorAtk, nextLevel);
                            avatar.save();
                
                            // Packet
                            sender.sendPacket(new PacketAvatarSkillChangeNotify(avatar, skillIdNorAtk, currentLevelNorAtk, nextLevel));
                            sender.sendPacket(new PacketAvatarSkillUpgradeRsp(avatar, skillIdNorAtk, currentLevelNorAtk, nextLevel));
                            CommandHandler.sendMessage(sender, "�չ��츳�ȼ�����Ϊ " + nextLevel + ".");
                        }    
                        if (skillId == skillIdE){ 
                            // Upgrade skill
                            avatar.getSkillLevelMap().put(skillIdE, nextLevel);
                            avatar.save();
                
                            // Packet
                            sender.sendPacket(new PacketAvatarSkillChangeNotify(avatar, skillIdE, currentLevelE, nextLevel));
                            sender.sendPacket(new PacketAvatarSkillUpgradeRsp(avatar, skillIdE, currentLevelE, nextLevel));
                            CommandHandler.sendMessage(sender, "E�����츳�ȼ�����Ϊ " + nextLevel + ".");
                        }
                        if (skillId == skillIdQ){ 
                            // Upgrade skill
                            avatar.getSkillLevelMap().put(skillIdQ, nextLevel);
                            avatar.save();
                
                            // Packet
                            sender.sendPacket(new PacketAvatarSkillChangeNotify(avatar, skillIdQ, currentLevelQ, nextLevel));
                            sender.sendPacket(new PacketAvatarSkillUpgradeRsp(avatar, skillIdQ, currentLevelQ, nextLevel));
                            CommandHandler.sendMessage(sender, "Q�����츳�ȼ�����Ϊ " + nextLevel + ".");
                        }       
                                
                    } catch (NumberFormatException ignored) {
                        CommandHandler.sendMessage(sender, "��Ч�ļ���id");
                        return;
                    }
                
                break;
            case "n": case "e": case "q":
                try {
                    EntityAvatar entity = sender.getTeamManager().getCurrentAvatarEntity();
                    Avatar avatar = entity.getAvatar();
                    AvatarSkillDepotData SkillDepot = avatar.getData().getSkillDepot();
                    int skillId;
                    switch (cmdSwitch) {
                        default:
                            skillId = SkillDepot.getSkills().get(0);
                            break;
                        case "e":
                            skillId = SkillDepot.getSkills().get(1);
                            break;
                        case "q":
                            skillId = SkillDepot.getEnergySkill();
                            break;
                    }
                    int nextLevel = Integer.parseInt(args.get(1));
                    int currentLevel = avatar.getSkillLevelMap().get(skillId);
                    if (args.size() < 1){
                        CommandHandler.sendMessage(sender, "�����츳�ȼ�: /talent <n or e or q> <value>");
                        return;
                    }
                    if (nextLevel > 16){
                        CommandHandler.sendMessage(sender, "�츳�ȼ���Ч���ȼ�Ӧ����16��");
                        return;
                    }
                    // Upgrade skill
                    avatar.getSkillLevelMap().put(skillId, nextLevel);
                    avatar.save();
                    // Packet
                    sender.sendPacket(new PacketAvatarSkillChangeNotify(avatar, skillId, currentLevel, nextLevel));
                    sender.sendPacket(new PacketAvatarSkillUpgradeRsp(avatar, skillId, currentLevel, nextLevel));
                    CommandHandler.sendMessage(sender, "���ô��츳�ȼ�Ϊ " + nextLevel + ".");
                } catch (NumberFormatException ignored) {
                    CommandHandler.sendMessage(sender, "��Ч���츳�ȼ�");
                    return;
                }
                break;
            case "getid":           
                    EntityAvatar entity = sender.getTeamManager().getCurrentAvatarEntity();
                    Avatar avatar = entity.getAvatar(); 
                    int skillIdNorAtk = avatar.getData().getSkillDepot().getSkills().get(0);
                    int skillIdE = avatar.getData().getSkillDepot().getSkills().get(1);
                    int skillIdQ = avatar.getData().getSkillDepot().getEnergySkill();
                    
                    CommandHandler.sendMessage(sender, "�չ� ID " + skillIdNorAtk + ".");
                    CommandHandler.sendMessage(sender, "E���� ID " + skillIdE + ".");
                    CommandHandler.sendMessage(sender, "Q���� ID " + skillIdQ + ".");
                break;
        }
    }
}
