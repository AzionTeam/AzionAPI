package fr.sothis.azionapi.pojo;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.List;

public class User {

    @BsonProperty(value = "uuid")
    private String uuid;
    @BsonProperty(value = "name")
    private String name;
    @BsonProperty(value = "money")
    private double money;
    @BsonProperty(value = "civilisation")
    private String civilisation;
    @BsonProperty(value = "grades")
    private List<String> grades;

    public User(String uuid, String name, double money, String civilisation, List<String> grades) {
        this.uuid = uuid;
        this.name = name;
        this.money = money;
        this.civilisation = civilisation;
        this.grades = grades;
    }

    public User() {

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCivilisation() {
        return civilisation;
    }

    public void setCivilisation(String civilisation) {
        this.civilisation = civilisation;
    }

    public List<String> getGrades() {
        return grades;
    }

    public void setGrades(List<String> grades) {
        this.grades = grades;
    }
}
