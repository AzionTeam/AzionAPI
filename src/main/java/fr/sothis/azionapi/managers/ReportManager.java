package fr.sothis.azionapi.managers;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import fr.sothis.azionapi.database.DatabaseManager;
import fr.sothis.azionapi.listeners.ListenerManager;
import fr.sothis.azionapi.pojo.Report;
import fr.sothis.azionapi.socket.Sockets;
import org.bson.Document;

import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;

public class ReportManager {

    private MongoCollection<Report> reports;
    private ListenerManager listenerManager;
    private Sockets sockets;

    public ReportManager(DatabaseManager databaseManager, ListenerManager listenerManager, Sockets sockets) {
        this.reports = databaseManager.getReports();
        this.listenerManager = listenerManager;
        this.sockets = sockets;
    }

    public Report getReport(String uuid) {
        return reports.find(eq("playerreport", uuid)).first();
    }

    public boolean isRegistered(String uuid) {
        if(getReport(uuid) != null) {
            return true;
        }
        return false;
    }

    public void updateReport(Report report) {
        Document filterById = new Document("playerreport", report.getPlayerreport());
        FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
        Report updatedGuildData = reports.findOneAndReplace(filterById, report , returnDocAfterReplace);
    }

    public void updateReport(String uuid, Consumer<Report> consumer) {
        Report report = getReport(uuid);
        consumer.accept(report);
        updateReport(report);
    }

    public void createReport(Report report) {
        reports.insertOne(report);
        listenerManager.deflectReportListener(report);
        sockets.getClientInterface().sendCreationReport(report.getPlayerreport());
    }
}
