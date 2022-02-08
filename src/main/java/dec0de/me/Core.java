package dec0de.me;

import dec0de.me.Commands.PlayerBroadBan;
import dec0de.me.Commands.PlayerBroadAdmin;
import dec0de.me.Commands.PlayerBroadCast;
import dec0de.me.Commands.PlayerBroadUnBan;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public final class Core extends JavaPlugin {

    public HashMap<Player, String> pbc = new HashMap<>();
    public ArrayList<Player> banlist = new ArrayList<>();

    private File customConfigFile;
    private FileConfiguration customConfig;

    @Override
    public void onEnable()
    {

        getServer().getPluginManager().registerEvents(new Inventory(), this);
        getCommand("playerbroadcast").setExecutor(new PlayerBroadCast());
        getCommand("playerbroadcastadmin").setExecutor(new PlayerBroadAdmin());
        getCommand("banplayerbroadcast").setExecutor(new PlayerBroadBan());
        getCommand("unplayerbroadcast").setExecutor(new PlayerBroadUnBan());

        loadConfig();
        createCustomConfig();

    }

    public FileConfiguration getCustomConfig()
    {
        return this.customConfig;
    }

    // Конфиг с Игроками
    private void createCustomConfig()
    {
        customConfigFile = new File(getDataFolder(), "players.yml");
        if (!customConfigFile.exists())
        {
            customConfigFile.getParentFile().mkdirs();
            saveResource("players.yml", false);
        }

        customConfig = new YamlConfiguration();
        try
        {
            customConfig.load(customConfigFile);
        }
        catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    public void saveCustomConfig()
    {
        if (customConfig == null || customConfigFile == null) {
            return;
        }
        try {
            getCustomConfig().save(customConfigFile);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    //Конфиг
    public void loadConfig()
    {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

}
