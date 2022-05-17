package fr.sothis.azionapi.listeners;

import fr.sothis.azionapi.pojo.Report;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {

    private final List<AzionListener> listeners = new ArrayList<>();

    public void addListener(AzionListener toAdd) {
        listeners.add(toAdd);
    }

    public void deflectReportListener(Report report) {
        listeners.forEach(azionListener -> {
            if(azionListener instanceof ReportListener) {
                ((ReportListener) azionListener).onCreateReport(report);
            }
        });
    }
}
