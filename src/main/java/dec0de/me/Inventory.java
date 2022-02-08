package dec0de.me;

import dec0de.me.Utils.Gui;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.Bukkit.getServer;


public class Inventory implements Listener
{

    //Доступ к Гл.Классу
    final Core plugin = Core.getPlugin(Core.class);

    //Доступ к Гуи
    Gui g = new Gui();

    //Цвета
    public static String translate(String str)
    {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    //На что нажал Игрок
    @EventHandler
    public void click(InventoryClickEvent e)
    {
        Player wc = (Player) e.getWhoClicked();

        org.bukkit.inventory.Inventory i = e.getInventory();

        if (i.getTitle().equals(translate(plugin.getConfig().getString("Menu.name"))))
        {
            if(e.getCurrentItem() != null && e.getClickedInventory() != null)
            {
                if(e.getCurrentItem().getType() == Material.SKULL_ITEM)
                {
                    g.yesno(wc, e);
                }

                if(e.getCurrentItem().getType() == Material.BARRIER)
                {
                    wc.closeInventory();
                }
                else
                {
                    e.setCancelled(true);
                }

            }

        }

       if (i.getTitle().equals(translate(plugin.getConfig().getString("Menu.secondGuiName"))))
       {

           if (e.getCurrentItem() != null && e.getClickedInventory() != null)
           {
               // Нет
               if (e.getCurrentItem().getType() == Material.RED_SHULKER_BOX && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Отклонить"))
               {
                   String p = ChatColor.stripColor(e.getInventory().getItem(22).getItemMeta().getDisplayName());

                   plugin.pbc.remove(getPlayer(p));

                   plugin.getCustomConfig().set("Players." + p, null);
                   plugin.saveCustomConfig();

                   wc.closeInventory();
               }else
                   e.setCancelled(true);

               // Да
               if (e.getCurrentItem().getType() == Material.LIME_SHULKER_BOX && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Одобрить"))
               {
                   String p = ChatColor.stripColor(e.getInventory().getItem(22).getItemMeta().getDisplayName());
                   String msg = plugin.getCustomConfig().getString("Players." + p + ".message");

                   // Текст
                   TextComponent message = new TextComponent(translate(plugin.getConfig().getString("Menu.broadcast"))
                           .replace("%broadplayer%", p)
                           .replace("%playermsg%", msg));

                   // Наводит курсор
                   if (plugin.getConfig().getBoolean("Menu.hover"))
                   {

                       message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(translate(plugin.getConfig().getString("Menu.hovermsg")).replace("%admin%", wc.getDisplayName())).create()));

                       getServer().spigot().broadcast(message);
                   } else
                       getServer().spigot().broadcast(message);

                   plugin.pbc.remove(getPlayer(p));

                   plugin.getCustomConfig().set("Players." + p, null);
                   plugin.saveCustomConfig();

                   wc.closeInventory();
               }else
                   e.setCancelled(true);

               //Назад
               if (e.getCurrentItem().getType() == Material.BARRIER)
               {
                   g.admingui(wc);
               }
               e.setCancelled(true);
           }
       }

    }


}
