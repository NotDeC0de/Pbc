package dec0de.me.Commands;

import dec0de.me.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerBroadCast implements CommandExecutor {

    //Доступ к Гл.Классу
    final Core plugin = Core.getPlugin(Core.class);

    //Команда
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {

            // Время
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

                if(sender.hasPermission("pbc.use"))
                {
                    if (args.length >= 1) {

                        if (args[0].equalsIgnoreCase("reload"))
                        {
                            if(sender.hasPermission("pbc.reload"))
                            {
                                plugin.reloadConfig();
                                sender.sendMessage(translate(plugin.getConfig().getString("Menu.reloadconfig")));
                            }
                            else
                                sender.sendMessage(translate(plugin.getConfig().getString("Menu.noperms")));

                        }
                        else
                        {

                            if (sender instanceof Player)
                            {

                                Player p = (Player) sender;

                                if (!plugin.banlist.contains(p))
                                {

                                    StringBuilder sb = new StringBuilder();

                                    for (String arg : args)
                                    {
                                        sb.append(arg).append(" ");
                                    }

                                    String message = sb.toString().trim();
                                    String color = ChatColor.translateAlternateColorCodes('&', message);

                                    plugin.pbc.put(p, formatter.format(date));

                                    plugin.getCustomConfig().set("Players." + p.getDisplayName() + ".message", color);

                                    // Игрок который забанин
                                    for (String wait : plugin.getConfig().getStringList("Menu.waitmessage"))
                                    {
                                        sender.sendMessage(translate(wait));
                                    }

                                    plugin.saveCustomConfig();

                                    return true;
                                }
                                else
                                {
                                    // Игрок который забанин
                                    for (String wbm : plugin.getConfig().getStringList("Menu.whobannedmessage"))
                                    {
                                        sender.sendMessage(translate(wbm));
                                    }
                                }

                            }
                            else
                                sender.sendMessage(translate(plugin.getConfig().getString("Menu.notplayer")));
                        }
                    }
                    else
                    {
                        // Что?
                        for (String unmsg : plugin.getConfig().getStringList("Menu.unknowcmdpbc"))
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
