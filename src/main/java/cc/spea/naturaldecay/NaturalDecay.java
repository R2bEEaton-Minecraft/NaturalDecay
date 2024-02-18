package cc.spea.naturaldecay;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.*;

public class NaturalDecay extends JavaPlugin implements Listener {
    SavedBlocks sb;
    public int radius = 5;
    public int speed = 1;
    public boolean started = false;

    HashSet<String> blackListed = new HashSet<>(Arrays.asList(
        Material.NETHER_PORTAL.toString(),
        Material.OBSIDIAN.toString(),
        Material.END_PORTAL_FRAME.toString(),
        Material.END_PORTAL.toString(),
        Material.END_GATEWAY.toString(),
        Material.PISTON.toString(),
        Material.MOVING_PISTON.toString(),
        Material.PISTON_HEAD.toString(),
        Material.STICKY_PISTON.toString(),

        Material.WHITE_BED.toString(),
        Material.LIGHT_GRAY_BED.toString(),
        Material.GRAY_BED.toString(),
        Material.BLACK_BED.toString(),
        Material.RED_BED.toString(),
        Material.ORANGE_BED.toString(),
        Material.BROWN_BED.toString(),
        Material.MAGENTA_BED.toString(),
        Material.YELLOW_BED.toString(),
        Material.LIME_BED.toString(),
        Material.GREEN_BED.toString(),
        Material.CYAN_BED.toString(),
        Material.LIGHT_BLUE_BED.toString(),
        Material.BLUE_BED.toString(),
        Material.PURPLE_BED.toString(),
        Material.PINK_BED.toString(),

        Material.CHEST.toString(),
        Material.TRAPPED_CHEST.toString(),
        Material.DRAGON_EGG.toString(),
        Material.SPAWNER.toString(),
        Material.AIR.toString(),
        Material.CAVE_AIR.toString(),
        Material.VOID_AIR.toString()
    ));

    HashSet<String> lowerChance = new HashSet<>(Arrays.asList(
            Material.DIAMOND_ORE.toString(),
            Material.DEEPSLATE_DIAMOND_ORE.toString(),
            Material.IRON_ORE.toString(),
            Material.DEEPSLATE_IRON_ORE.toString(),
            Material.ANCIENT_DEBRIS.toString()
    ));

    Location getRandomLocation(Location origin, double radius)
    {
        Random r = new Random();
        double randomRadius = Math.pow(r.nextDouble(), 1.0/3.0) * radius;
        double theta =  2 * Math.PI * r.nextDouble();
        double phi = Math.acos(2 * r.nextDouble() - 1);

        double x = randomRadius * Math.sin(phi) * Math.cos(theta);
        double y = randomRadius * Math.sin(phi) * Math.sin(theta);
        double z = randomRadius * Math.cos(phi);
        return origin.add(x, y, z);
    }

    @Override
    public void onEnable() {
        getCommand("nd").setExecutor(new CommandManager(this));
        getCommand("nd").setTabCompleter(new CommandManager(this));

        saveDefaultConfig();
        sb = SavedBlocks.loadData(getDataFolder() + File.separator + "SavedBlocks.data");
        getServer().getPluginManager().registerEvents(new SafeBlock(sb), this);
        getServer().getPluginManager().registerEvents(this, this);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (started) {
                    for (int i = 0; i < speed; i++) {
                        replaceRandomBlock();
                    }
                }
            }

            public void replaceRandomBlock() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getGameMode() != GameMode.SURVIVAL) continue;
                    Location randomLoc = getRandomLocation(player.getLocation(), radius);
                    String block = randomLoc.getWorld() + " " + randomLoc.getBlockX() + " " + randomLoc.getBlockY() + " " + randomLoc.getBlockZ();
                    if (sb.blockList.contains(block) || blackListed.contains(randomLoc.getBlock().getType().toString())) continue;
                    if (lowerChance.contains(randomLoc.getBlock().getType().toString()) && new Random().nextDouble() > 0.5) continue;
                    randomLoc.getWorld().spawnParticle(Particle.BLOCK_CRACK, randomLoc, 100, 0.2, 0.2, 0.2, 0.05, randomLoc.getBlock().getBlockData());
                    randomLoc.getWorld().playSound(randomLoc, randomLoc.getBlock().getBlockData().getSoundGroup().getBreakSound(), 0.25f, 1f);
                    //randomLoc.getWorld().createExplosion(randomLoc, 1);
                    randomLoc.getBlock().setType(Material.AIR);
                }
            }
        }.runTaskTimer(this, 0, 2L);
    }

    @Override
    public void onDisable() {
        saveData();
    }

    @EventHandler
    public void onWorldSave(WorldSaveEvent event) {
        saveData();
    }

    public boolean saveData() {
        if (sb != null) {
            saveDefaultConfig();
            if (!sb.saveData(getDataFolder() + File.separator + "SavedBlocks.data")) {
                Bukkit.broadcastMessage("Error saving block list...");
            }
            return true;
        }
        return false;
    }
}
