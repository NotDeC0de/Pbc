package dec0de.me.Utils;

import dec0de.me.Core;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class Items
{
    //Доступ к Гл.Классу
    final Core plugin = Core.getPlugin(Core.class);

    // Стекло
    public void glassadmin(Inventory inv)
    {
        ItemStack gl = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);

        for(int i = 0; i < 10; i++)
        {
            inv.setItem(i, gl);
        }

        inv.setItem(17, gl);
        inv.setItem(18, gl);
        inv.setItem(26, gl);
        inv.setItem(27, gl);
        inv.setItem(35, gl);
        inv.setItem(36, gl);
        inv.setItem(44, gl);
        inv.setItem(45, gl);
        inv.setItem(53, gl);
    }

    public void glass(Inventory inv)
    {
        ItemStack gl = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);

        for(int i = 0; i < 10; i++)
        {
            inv.setItem(i, gl);
        }

        inv.setItem(17, gl);
        inv.setItem(18, gl);
        inv.setItem(26, gl);
    }

    // Админ
    public void adminitem(Inventory inv)
    {

        // Голова
        for (Player s : plugin.pbc.keySet())
        {
            ItemStack phead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta m = (SkullMeta) phead.getItemMeta();

            m.setOwner(s.getName());

            m.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + s.getDisplayName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.WHITE + "Текст: " + ChatColor.GRAY +  plugin.getCustomConfig().getString("Players." + s.getDisplayName() + ".message"));
            lore.add("");
            lore.add(ChatColor.WHITE + "Время отправки: " + ChatColor.GRAY + ChatColor.UNDERLINE + plugin.pbc.get(s));
            m.setLore(lore);

            phead.setItemMeta(m);

            inv.addItem(phead);

        }

        // Компас с банами
        for(int i = 0; i < plugin.banlist.size(); i++)
        {
            ItemStack navi = new ItemStack(Material.COMPASS);
            ItemMeta m = navi.getItemMeta();
            m.setDisplayName(ChatColor.RED + "Игроков в бане: " + (i+1));
            navi.setItemMeta(m);

            inv.setItem(49, navi);
        }

        // Выйти
        ItemStack ex = new ItemStack(Material.BARRIER);
        ItemMeta m = ex.getItemMeta();
        m.setDisplayName(ChatColor.GRAY + "Выйти");
        ex.setItemMeta(m);

        // Хелп
        ItemStack help = new ItemStack(Material.SIGN);
        ItemMeta mhelp = help.getItemMeta();
        mhelp.setDisplayName(ChatColor.GRAY + "Помощь");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.YELLOW + "/bpbc ник - чтобы Игрок не мог делать броадкасты");
        lore.add(ChatColor.GOLD + "/ubpbc ник - чтобы Игрок сново мог делать броадкасты");
        lore.add("");

        mhelp.setLore(lore);
        help.setItemMeta(mhelp);

        inv.setItem(48, ex);
        inv.setItem(50, help);
    }

    // Да/Нет
    public void yn(Inventory ask, InventoryClickEvent e)
    {
        // Да
        ItemStack yes = new ItemStack(Material.LIME_SHULKER_BOX);
        ItemMeta myes = yes.getItemMeta();
        myes.setDisplayName(ChatColor.GREEN + "Одобрить");
        yes.setItemMeta(myes);

        // Нет
        ItemStack no = new ItemStack(Material.RED_SHULKER_BOX);
        ItemMeta mno = no.getItemMeta();
        mno.setDisplayName(ChatColor.RED + "Отклонить");
        no.setItemMeta(mno);

        // Голова Игрока
        ItemStack phead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta m = (SkullMeta) phead.getItemMeta();
        m.setOwner(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(e.getCurrentItem().getItemMeta().getLore().get(0));
        m.setLore(lore);

        // Выйти
        ItemStack ex = new ItemStack(Material.BARRIER);
        ItemMeta me = ex.getItemMeta();
        me.setDisplayName(ChatColor.GRAY + "Назад");
        ex.setItemMeta(me);

        m.setDisplayName(e.getCurrentItem().getItemMeta().getDisplayName());

        phead.setItemMeta(m);

        ask.setItem(27, ex);
        ask.setItem(35, ex);
        ask.setItem(22, phead);
        ask.setItem(11, yes);
        ask.setItem(15, no);
    }
}
