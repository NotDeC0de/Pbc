package dec0de.me.Utils;

import dec0de.me.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class Gui {

    //Доступ к Гл.Классу
    final Core plugin = Core.getPlugin(Core.class);

    // Доступ к Items
    Items itms = new Items();

    // Админ меню
    public void admingui(Player p)
    {
        Inventory gui = Bukkit.createInventory(p, 54, translate(plugin.getConfig().getString("Menu.name")));

        itms.glassadmin(gui);

        itms.adminitem(gui);

        p.openInventory(gui);
    }

    // Да/Нет меню
    public void yesno(Player wc, InventoryClickEvent e)
    {
        Inventory ask = Bukkit.createInventory(null, 36, translate(plugin.getConfig().getString("Menu.secondGuiName")));

        itms.yn(ask, e);

        itms.glass(ask);

        wc.openInventory(ask);
    }

    // Цвета
    public static String translate(String str)
    {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
