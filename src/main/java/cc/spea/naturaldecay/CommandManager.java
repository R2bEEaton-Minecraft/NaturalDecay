package cc.spea.naturaldecay;

import cc.spea.naturaldecay.subcommands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {
    ArrayList<SubCommand> subcommands = new ArrayList<>();

    public CommandManager(NaturalDecay nd) {
        subcommands.add(new StartCommand(nd));
        subcommands.add(new StopCommand(nd));
        subcommands.add(new RadiusCommand(nd));
        subcommands.add(new SaveCommand(nd));
        subcommands.add(new SpeedCommand(nd));
    }

    public ArrayList<SubCommand> getSubCommands() { return subcommands; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean flag = false;
        if (args.length > 0) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                    flag = getSubCommands().get(i).perform(sender, args);
                }
            }
        }
        if (!flag) {
            sender.sendMessage(ChatColor.RED + "[NaturalDecay] Invalid command.");
        }
        return flag;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < getSubCommands().size(); i++) {
                list.add(getSubCommands().get(i).getName());
            }
            return list;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("radius")) {
            if (args[1].trim().equalsIgnoreCase("")) {
                List<String> list = new ArrayList<>();
                list.add("<blocks>");
                return list;
            }
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("speed")) {
            if (args[1].trim().equalsIgnoreCase("")) {
                List<String> list = new ArrayList<>();
                list.add("<speed>");
                return list;
            }
        }
        return null;
    }
}
