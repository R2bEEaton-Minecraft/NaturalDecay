package cc.spea.naturaldecay.subcommands;

import cc.spea.naturaldecay.NaturalDecay;
import cc.spea.naturaldecay.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class StartCommand extends SubCommand {
    public StartCommand(NaturalDecay nd) {
        super(nd);
    }

    @Override
    public String getName() {
        return "start";
    }

    @Override
    public boolean perform(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "[NaturalDecay] Started.");
        this.nd.started = true;
        return true;
    }
}
