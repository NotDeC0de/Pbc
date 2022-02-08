package dec0de.me.Commands;


import dec0de.me.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerBroadUnBan implements CommandExecutor
{
    //Доступ к Гл.Классу
    final Core plugin = Core.getPlugin(Core.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender.hasPermission("pbc.uban"))
        {

            if (args.length == 1)
            {
                Player whotounban = Bukkit.getPlayer(args[0]);

                if (whotounban != null)
                {
                    plugin.banlist.remove(whotounban);

                    // Кого разбанили
                    for (String unbanmsg : plugin.getConfig().getStringList("Menu.whounbanned"))
                    {

                        whotounban.sendMessage(translate(unbanmsg).replaceAll("%banned%", whotounban.getDisplayName()));

                    }

                    // Кто разабанил
                    for (String unbanmsg : plugin.getConfig().getStringList("Menu.unbanmessage"))
                    {

                        sender.sendMessage(translate(unbanmsg).replaceAll("%banned%", whotounban.getDisplayName()));

                    }

                }
                else
                    // Игрок не найден
                    for (String notf : plugin.getConfig().getStringList("Menu.notfound"))
                    {

                        sender.sendMessage(translate(notf).replaceAll("%banned%", args[0]));

                    }

            }
            else
            {
                // Что?
                for (String unmsg : plugin.getConfig().getStringList("Menu.unknownunbanpbc"))
                {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', unmsg));
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



