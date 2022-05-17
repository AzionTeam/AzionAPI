package fr.sothis.azionapi.tools.title;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

public class TitleBuilder {

        public static void sendTitle(Player player, String title, ChatColor chatColorTitle, String subtitle,
                        ChatColor chatColorSubTitle, TitleOptions options) {

                IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a(
                                "{\"text\": \"" + title + "\",color:" + chatColorTitle.name().toLowerCase() +
                                                "}");
                IChatBaseComponent chatSubTitle = IChatBaseComponent.ChatSerializer.a(
                                "{\"text\": \"" + subtitle + "\", color:" +
                                                chatColorSubTitle.name().toLowerCase() + "}");

                PacketPlayOutTitle titleOut = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                                chatTitle);
                PacketPlayOutTitle subTitleOut = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                                chatSubTitle);
                PacketPlayOutTitle length = new PacketPlayOutTitle(options.getFadeIn(),
                                options.getStay(),
                                options.getFadeOut());

                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titleOut);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subTitleOut);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);

        }
}
