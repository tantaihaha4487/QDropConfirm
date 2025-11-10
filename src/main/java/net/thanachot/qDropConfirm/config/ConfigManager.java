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
        config.options().copyDefaults(true);
        plugin.saveConfig();
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void reload() {
        // check if config file is not exits
        if (!QDropConfirm.getInstance().getDataFolder().exists()) {
            QDropConfirm.getInstance().saveDefaultConfig();
        }
        QDropConfirm.getInstance().reloadConfig();
        config = QDropConfirm.getInstance().getConfig();
    }

    public static String getConfirmMessage() {
        return config.getString("confirm-message");
    }

    public static Long getDelay() {
        return config.getLong("delay");
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
