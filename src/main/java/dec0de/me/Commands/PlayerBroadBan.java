package dec0de.me.Commands;

import dec0de.me.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerBroadBan implements CommandExecutor
{
    //Доступ к Гл.Классу
    final Core plugin = Core.getPlugin(Core.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender.hasPermission("pbc.ban"))
        {

            if (args.length == 1)
            {

                Player whotoban = Bukkit.getPlayer(args[0]);

                if (whotoban != null)
                {

                    // Кого забанили
                    for (String banmsg : plugin.getConfig().getStringList("Menu.whobannedmessage"))
                    {

                        whotoban.sendMessage(translate(banmsg).replaceAll("%banned%", whotoban.getDisplayName()));

                    }

                    // Кто забанил
                    for (String bamsg : plugin.getConfig().getStringList("Menu.bannedplayermessage"))
                    {

                        sender.sendMessage(translate(bamsg).replaceAll("%banned%", whotoban.getDisplayName()));

                    }

                    plugin.banlist.add(whotoban);

                }
                else
                {
                    // Игрок не найден
                    for (String notf : plugin.getConfig().getStringList("Menu.notfound"))
                    {

                        sender.sendMessage(translate(notf).replaceAll("%banned%", args[0]));

                    }
                }

            }
            else
            {
                // Что?
                for (String unmsg : plugin.getConfig().getStringList("Menu.unknowcmdbanpbc"))
                {
                    sender.sendMessage(translate(unmsg));
                }

            }
        }
        else
            sender.sendMessage(translate(plugin.getConfig().getString("Menu.noperms")));

        return false;
    }



    // Цвета
    public static String translate(String str)
    {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}



