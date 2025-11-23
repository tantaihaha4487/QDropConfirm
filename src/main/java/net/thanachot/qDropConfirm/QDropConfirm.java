package net.thanachot.qDropConfirm;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.thanachot.qDropConfirm.bstats.Metrics;
import net.thanachot.qDropConfirm.command.QDropConfirmCommand;
import net.thanachot.qDropConfirm.config.ConfigManager;
import net.thanachot.qDropConfirm.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class QDropConfirm extends JavaPlugin {

    public static QDropConfirm getInstance() {
        return getPlugin(QDropConfirm.class);
    }

    @Override
    public void onEnable() {


        getLogger().info("Q Drop Confirm Enabled!!");
        ConfigManager.setupConfig(this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            BasicCommand QDropConfirmCommand = new QDropConfirmCommand();
            commands.registrar().register("qdropconfirm", QDropConfirmCommand);
        });


        // Bstats Metrics
        int pluginId = 28116;
        new Metrics(this, pluginId);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
