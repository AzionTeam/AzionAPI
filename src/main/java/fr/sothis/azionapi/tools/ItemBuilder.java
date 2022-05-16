package fr.sothis.azionapi.tools;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ItemBuilder {

    private ItemStack itemStack;

    public ItemStack toItemStack() {
        return itemStack;
    }

    public ItemBuilder(ItemBuilder itemBuilder) {
        itemStack = itemBuilder.toItemStack();
    }

    public ItemBuilder(ItemBuilder itemBuilder, int amount) {
        itemStack = itemBuilder.toItemStack();
        itemStack.setAmount(amount);
    }

    public ItemBuilder(ItemBuilder itemBuilder, short durability) {
        itemStack = itemBuilder.toItemStack();
        itemStack.setDurability(durability);
    }

    public ItemBuilder(ItemBuilder itemBuilder, int amount, short durability) {
        itemStack = itemBuilder.toItemStack();
        itemStack.setAmount(amount);
        itemStack.setDurability(durability);
    }

    public ItemBuilder(ItemStack itemstack) {
        itemStack = itemstack;
    }

    public ItemBuilder(ItemStack itemstack, int amount) {
        itemStack = itemstack;
        itemStack.setAmount(amount);
    }

    public ItemBuilder(ItemStack itemstack, short durability) {
        itemStack = itemstack;
        itemStack.setDurability(durability);
    }

    public ItemBuilder(ItemStack itemstack, int amount, short durability) {
        itemStack = itemstack;
        itemStack.setAmount(amount);
        itemStack.setDurability(durability);
    }

    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material, 1);
    }

    public ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
    }

    public ItemBuilder(Material material, short durability) {
        itemStack = new ItemStack(material, 1, durability);
    }

    public ItemBuilder(Material material, int amount, short durability) {
        itemStack = new ItemStack(material, amount, durability);
    }

    public ItemMeta getItemMeta() {
        return itemStack.getItemMeta();
    }

    public Material getType() {
        return itemStack.getType();
    }

    public short getDurability() {
        return itemStack.getDurability();
    }

    public ItemBuilder setItemMeta(ItemMeta itemMeta) {
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment) {
        itemStack.addEnchantment(enchantment, 1);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        itemStack.addEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder addUnsafeEnchantement(Enchantment enchantment) {
        itemStack.addUnsafeEnchantment(enchantment, 1);
        return this;
    }

    public ItemBuilder addUnsafeEnchantement(Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public boolean containsEnchantement(Enchantment enchantment) {
        return itemStack.containsEnchantment(enchantment);
    }

    public int getAmount() {
        return itemStack.getAmount();
    }

    public int getEnchantementLevel(Enchantment enchantment) {
        return itemStack.getEnchantmentLevel(enchantment);
    }

    public Map<Enchantment, Integer> getEnchantements() {
        return itemStack.getEnchantments();
    }

    public MaterialData getData() {
        return itemStack.getData();
    }

    public int getMaxStackSize() {
        return itemStack.getMaxStackSize();
    }

    public boolean hasItemMeta() {
        return itemStack.hasItemMeta();
    }

    public boolean isSimilar(ItemStack itemstack) {
        return itemStack.isSimilar(itemstack);
    }

    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        itemStack.removeEnchantment(enchantment);
        return this;
    }

    public boolean equals(ItemStack itemstack) {
        return itemstack.equals(itemstack);
    }

    public ItemBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setData(MaterialData materialData) {
        itemStack.setData(materialData);
        return this;
    }

    public ItemBuilder setType(Material material) {
        itemStack.setType(material);
        return this;
    }

    public ItemBuilder setDurability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    public ItemBuilder setDisplayName(String name) {
        getItemMeta().setDisplayName(name);
        setItemMeta(getItemMeta());
        return this;
    }

    /*public ItemBuilder setUnbreakable() {
        getItemMeta().setUnbreakable(true);
        setItemMeta(getItemMeta());
        return this;
    }*/

    /*public ItemBuilder removeUnbreakable() {
        getItemMeta().spigot().setUnbreakable(false);
        setItemMeta(getItemMeta());
        return this;
    }*/

    /*public boolean isUnbreakable() {
        return getItemMeta().spigot().isUnbreakable();
    }*/

    public String getDisplayName() {
        return getItemMeta().getDisplayName();
    }

    public ItemBuilder setLore(List<String> lore) {
        getItemMeta().setLore(lore);
        setItemMeta(getItemMeta());
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        getItemMeta().setLore(Arrays.asList(lore));
        setItemMeta(getItemMeta());
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... itemFlag) {
        getItemMeta().addItemFlags(itemFlag);
        setItemMeta(getItemMeta());
        return this;
    }

    public Set<ItemFlag> getItemFlags() {
        return getItemMeta().getItemFlags();
    }

    public List<String> getLore() {
        return getItemMeta().getLore();
    }

    public boolean hasItemFlags(ItemFlag itemFlag) {
        return getItemMeta().hasItemFlag(itemFlag);
    }

    public boolean hasDisplayName() {
        return getItemMeta().hasDisplayName();
    }

    public boolean hasLore() {
        return getItemMeta().hasLore();
    }

    public ItemBuilder removeItemFlags(ItemFlag... itemFlag) {
        getItemMeta().removeItemFlags(itemFlag);
        setItemMeta(getItemMeta());
        return this;
    }
}
