package cc.spea.naturaldecay.subcommands;

import cc.spea.naturaldecay.NaturalDecay;
import cc.spea.naturaldecay.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class StopCommand extends SubCommand {
    public StopCommand(NaturalDecay nd) {
        super(nd);
    }

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public boolean perform(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "[NaturalDecay] Stopped.");
        if (this.nd.started) {
            this.nd.saveData();
        }
        this.nd.started = false;
        return true;
    }
}
