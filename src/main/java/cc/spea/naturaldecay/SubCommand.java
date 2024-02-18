package cc.spea.naturaldecay;

import org.bukkit.command.CommandSender;

public abstract class SubCommand {
    public final NaturalDecay nd;

    protected SubCommand(NaturalDecay nd) {
        this.nd = nd;
    }

    public abstract String getName();
    public abstract boolean perform(CommandSender sender, String[] args);

}
