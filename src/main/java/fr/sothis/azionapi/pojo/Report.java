package fr.sothis.azionapi.pojo;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Report {

    @BsonProperty(value = "serveur")
    private String serveur;
    @BsonProperty(value = "date")
    private String date;
    @BsonProperty(value = "raison")
    private String raison;
    @BsonProperty(value = "playerreport")
    private String playerreport;
    @BsonProperty(value = "victim")
    private String victim;

    public Report(String serveur, String date, String raison, String playerreport, String victim) {
        this.serveur = serveur;
        this.date = date;
        this.raison = raison;
        this.playerreport = playerreport;
        this.victim = victim;
    }

    public Report() {
    }

    public String getServeur() {
        return serveur;
    }

    public void setServeur(String serveur) {
        this.serveur = serveur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getPlayerreport() {
        return playerreport;
    }

    public void setPlayerreport(String playerreport) {
        this.playerreport = playerreport;
    }

    public String getVictim() {
        return victim;
    }

    public void setVictim(String victim) {
        this.victim = victim;
    }
}
