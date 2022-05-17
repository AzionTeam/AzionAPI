package fr.sothis.azionapi.listeners;

import java.util.ArrayList;
import java.util.List;

import fr.sothis.azionapi.pojo.Report;

public class ListenerManager {

    private final List<AzionListener> listeners = new ArrayList<>();

    public void addListener(AzionListener toAdd) {
        listeners.add(toAdd);
    }

    public void deflectReportListener(Report report) {
        listeners.forEach(azionListener -> {
            if (azionListener instanceof ReportListener) {
                ((ReportListener) azionListener).onCreateReport(report);
            }
        });
    }
}
