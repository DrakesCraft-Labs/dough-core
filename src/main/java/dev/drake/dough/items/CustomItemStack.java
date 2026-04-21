package dev.drake.dough.items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * A legacy-compatible CustomItemStack that extends {@link ItemStack}.
 * This is used heavily in Slimefun and its addons.
 * 
 * @author TheBusyBiscuit
 * @author DrakesCraft-Labs (1.21.1 Port)
 */
public class CustomItemStack extends ItemStack {

    public CustomItemStack(@Nonnull ItemStack item) {
        super(item.getType(), item.getAmount());
        this.setItemMeta(item.getItemMeta().clone());
    }

    @Override
    public @Nonnull CustomItemStack clone() {
        return new CustomItemStack(this);
    }

    public CustomItemStack(@Nonnull Material type) {
        super(type);
    }

    public CustomItemStack(@Nonnull ItemStack item, @Nonnull Consumer<ItemMeta> consumer) {
        super(item.getType(), item.getAmount());
        this.setItemMeta(item.getItemMeta().clone());
        ItemMeta meta = getItemMeta();
        if (meta != null) {
            consumer.accept(meta);
            setItemMeta(meta);
        }
    }

    public CustomItemStack(@Nonnull Material type, @Nonnull Consumer<ItemMeta> consumer) {
        this(new ItemStack(type), consumer);
    }

    public CustomItemStack(@Nonnull ItemStack item, @Nullable String name, @Nonnull String... lore) {
        super(item.getType(), item.getAmount());
        this.setItemMeta(item.getItemMeta().clone());
        ItemMeta meta = getItemMeta();
        if (meta != null) {
            if (name != null) {
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            }
            if (lore.length > 0) {
                List<String> lines = new ArrayList<>();
                for (String line : lore) {
                    lines.add(ChatColor.translateAlternateColorCodes('&', line));
                }
                meta.setLore(lines);
            }
            setItemMeta(meta);
        }
    }

    public CustomItemStack(@Nonnull Material type, @Nullable String name, @Nonnull String... lore) {
        this(new ItemStack(type), name, lore);
    }

    public CustomItemStack(@Nonnull ItemStack item, @Nonnull List<String> list) {
        this(item, list.isEmpty() ? null : list.get(0), list.isEmpty() ? new String[0] : list.subList(1, list.size()).toArray(new String[0]));
    }

    public CustomItemStack(@Nonnull Material type, @Nonnull List<String> list) {
        this(new ItemStack(type), list);
    }

    public CustomItemStack(@Nonnull ItemStack item, int amount) {
        super(item.getType(), amount);
        this.setItemMeta(item.getItemMeta().clone());
        setAmount(amount);
    }

}
