package cc.spea.naturaldecay;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class SafeBlock implements Listener {
    SavedBlocks sb;

    public SafeBlock(SavedBlocks sb) {
        this.sb = sb;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Location placedBlock = event.getBlockPlaced().getLocation();
        String block = placedBlock.getWorld() + " " + placedBlock.getBlockX() + " " + placedBlock.getBlockY() + " " + placedBlock.getBlockZ();
        sb.blockList.add(block);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Location brokenBlock = event.getBlock().getLocation();
        String block = brokenBlock.getWorld() + " " + brokenBlock.getBlockX() + " " + brokenBlock.getBlockY() + " " + brokenBlock.getBlockZ();
        sb.blockList.remove(block);
    }
}
