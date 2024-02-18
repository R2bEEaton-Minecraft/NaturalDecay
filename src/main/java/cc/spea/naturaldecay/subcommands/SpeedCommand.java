package cc.spea.naturaldecay.subcommands;

import cc.spea.naturaldecay.NaturalDecay;
import cc.spea.naturaldecay.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class SpeedCommand extends SubCommand {

    public SpeedCommand(NaturalDecay nd) {
        super(nd);
    }

    @Override
    public String getName() {
        return "speed";
    }

    @Override
    public boolean perform(CommandSender sender, String[] args) {
        String message = ChatColor.RED + "[NaturalDecay] Usage: /nd speed <speed:1-10>";

        if (args.length != 2) {
            sender.sendMessage(message);
            return false;
        }

        int speed;
        try {
            speed = Integer.parseInt(args[1]);
        } catch (Exception e) {
            sender.sendMessage(message);
            return false;
        }

        if (speed > 10 || speed < 1) {
            sender.sendMessage(message);
            return false;
        }

        sender.sendMessage(ChatColor.GREEN + "[NaturalDecay] Speed set to " + speed + ".");
        this.nd.speed = speed;

        return true;
    }
}
