package dec0de.me.Commands;

import dec0de.me.Core;
import dec0de.me.Utils.Gui;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerBroadAdmin implements CommandExecutor {

    //Доступ к Гл.Классу
    final Core plugin = Core.getPlugin(Core.class);

    //Доступ к Гуи
    Gui g = new Gui();

    //Команда
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player)
        {

            Player p = (Player) sender;

            if(p.hasPermission("pbc.admin"))
            {
                if (command.getName().equalsIgnoreCase("playerbroadcastadmin"))
                {

                    g.admingui(p);

                }
            }
            else
                p.sendMessage(translate(plugin.getConfig().getString(translate("Menu.noperms"))));

        }
        else
            sender.sendMessage(translate(plugin.getConfig().getString("Menu.notplayer")));

        return false;
    }

    // Цвета
    public static String translate(String str)
    {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

}
