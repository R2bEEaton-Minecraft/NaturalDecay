package cc.spea.naturaldecay.subcommands;

import cc.spea.naturaldecay.NaturalDecay;
import cc.spea.naturaldecay.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class SaveCommand extends SubCommand {

    public SaveCommand(NaturalDecay nd) {
        super(nd);
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public boolean perform(CommandSender sender, String[] args) {
        if (this.nd.saveData()) {
            sender.sendMessage(ChatColor.GRAY + "[NaturalDecay] Saved data.");
        }
        return true;
    }
}
