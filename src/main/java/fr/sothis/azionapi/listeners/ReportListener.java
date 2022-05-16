package fr.sothis.azionapi.listeners;

import fr.sothis.azionapi.pojo.Report;

public interface ReportListener extends AzionListener {

    void onCreateReport(Report report);
}
