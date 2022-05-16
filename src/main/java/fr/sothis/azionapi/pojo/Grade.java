package fr.sothis.azionapi.pojo;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.List;

public class Grade {

    @BsonProperty(value = "name")
    private String name;
    @BsonProperty(value = "permissions")
    private List<String> permissions;
    @BsonProperty(value = "suffix")
    private String suffix;
    @BsonProperty(value = "prefix")
    private String prefix;
    @BsonProperty(value = "chatColor")
    private String chatColor;
    @BsonProperty(value = "nameColor")
    private String nameColor;

    public Grade(String name, List<String> permissions, String suffix, String prefix, String chatColor, String nameColor) {
        this.name = name;
        this.permissions = permissions;
        this.suffix = suffix;
        this.prefix = prefix;
        this.chatColor = chatColor;
        this.nameColor = nameColor;
    }

    public Grade() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getChatColor() {
        return chatColor;
    }

    public void setChatColor(String chatColor) {
        this.chatColor = chatColor;
    }

    public String getNameColor() {
        return nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }
}
