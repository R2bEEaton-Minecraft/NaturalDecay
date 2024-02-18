package cc.spea.naturaldecay;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.util.HashSet;
import java.util.zip.GZIPOutputStream;
import java.util.zip.GZIPInputStream;

public class SavedBlocks implements Serializable {
    public final HashSet<String> blockList;

    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static SavedBlocks loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            SavedBlocks data = (SavedBlocks) in.readObject();
            in.close();
            return new SavedBlocks(data);
        } catch (Exception e) {
            return new SavedBlocks(new HashSet<>());
        }
    }

    // Can be used for saving
    public SavedBlocks(HashSet<String> blockList) {
        this.blockList = blockList;
    }
    // Can be used for loading
    public SavedBlocks(SavedBlocks loadedData) {
        this.blockList = loadedData.blockList;
    }
}
