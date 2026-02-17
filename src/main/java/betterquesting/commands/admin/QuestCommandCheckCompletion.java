package betterquesting.commands.admin;

import betterquesting.api.properties.NativeProps;
import betterquesting.api.questing.IQuest;
import betterquesting.api2.storage.DBEntry;
import betterquesting.commands.QuestCommandBase;
import betterquesting.questing.QuestDatabase;
import betterquesting.storage.NameCache;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.server.permission.DefaultPermissionLevel;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class QuestCommandCheckCompletion extends QuestCommandBase {

    @Override
    public String getUsageSuffix() {
        return "<quest_id> <username|uuid>";
    }

    @Override
    public String getPermissionNode() {
        return "betterquesting.command.admin.check_quest_completion";
    }

    @Override
    public DefaultPermissionLevel getPermissionLevel() {
        return DefaultPermissionLevel.OP;
    }

    @Override
    public String getPermissionDescription() {
        return "Permission to check the completion of quests of a given user";
    }

    @Override
    public boolean validArgs(@Nonnull String[] args) {
        return args.length == 3;
    }

    @Override
    public List<String> autoComplete(MinecraftServer server, ICommandSender sender, @Nonnull String[] args) {
        if (args.length == 2) {
            return QuestDatabase.INSTANCE.getEntries().stream().mapToInt(DBEntry::getID).mapToObj(Integer::toString).filter(x -> x.startsWith(args[1])).collect(Collectors.toList());
        }
        if (args.length == 1) {
            return CommandBase.getListOfStringsMatchingLastWord(args, NameCache.INSTANCE.getAllNames().toArray(new String[0]));
        }
        return Collections.emptyList();
    }

    @Override
    public String getCommand() {
        return "check";
    }

    @Override
    public void runCommand(MinecraftServer server, CommandBase command, ICommandSender sender, @Nonnull String[] args) throws CommandException {
        UUID uuid;

        uuid = this.findPlayerID(server, sender, args[2]);

        if (uuid == null) {
            sender.sendMessage(new TextComponentTranslation("betterquesting.cmd.check.no_player_match").setStyle(new Style().setColor(TextFormatting.RED)));
            throw this.getException(command);
        }

        String pName = NameCache.INSTANCE.getName(uuid);

        int id = Integer.parseInt(args[1].trim());
        IQuest quest = QuestDatabase.INSTANCE.getValue(id);
        if (quest == null) {
            sender.sendMessage(new TextComponentTranslation("betterquesting.cmd.check.no_id_match").setStyle(new Style().setColor(TextFormatting.RED)));
            throw getException(command);
        }
        sender.sendMessage(new TextComponentTranslation("betterquesting.cmd.check." + quest.isComplete(uuid), pName, new TextComponentTranslation(quest.getProperty(NativeProps.NAME))));
    }

    @Override
    public boolean isArgUsername(String[] args, int index) {
        return index == 2;
    }
}