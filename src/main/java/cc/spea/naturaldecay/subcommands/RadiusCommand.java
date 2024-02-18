package cc.spea.naturaldecay.subcommands;

import cc.spea.naturaldecay.NaturalDecay;
import cc.spea.naturaldecay.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class RadiusCommand extends SubCommand {

    public RadiusCommand(NaturalDecay nd) {
        super(nd);
    }

    @Override
    public String getName() {
        return "radius";
    }

    @Override
    public boolean perform(CommandSender sender, String[] args) {
        String message = ChatColor.RED + "[NaturalDecay] Usage: /nd radius <blocks:1-50>";

        if (args.length != 2) {
            sender.sendMessage(message);
            return false;
        }

        int radius;
        try {
            radius = Integer.parseInt(args[1]);
        } catch (Exception e) {
            sender.sendMessage(message);
            return false;
        }

        if (radius > 50 || radius < 1) {
            sender.sendMessage(message);
            return false;
        }

        sender.sendMessage(ChatColor.GREEN + "[NaturalDecay] Radius set to " + radius + ".");
        this.nd.radius = radius;

        return true;
    }
}
