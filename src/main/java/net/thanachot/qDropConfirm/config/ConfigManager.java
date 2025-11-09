package net.thanachot.qDropConfirm.config;

import net.thanachot.qDropConfirm.QDropConfirm;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private static FileConfiguration config;

    public static void setupConfig(QDropConfirm plugin) {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void reload() {
        QDropConfirm.getInstance().reloadConfig();
        config = QDropConfirm.getInstance().getConfig();
    }

    public static @NotNull List<Material> getWhitelistMaterials() {
        List<Material> materialList = new ArrayList<>();
        for (String material : getConfig().getStringList("whitelist")) {
            try {
                materialList.add(Material.valueOf(material));
            } catch (IllegalArgumentException e) {
                QDropConfirm.getInstance().getLogger().warning("Invalid material in config.yml: " + material);
            }
        }
        return materialList;
    }
}
