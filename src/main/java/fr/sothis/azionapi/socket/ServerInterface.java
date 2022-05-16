package fr.sothis.azionapi.socket;

import fr.sothis.azionapi.AzionAPI;
import fr.sothis.azionapi.listeners.ListenerManager;
import fr.sothis.azionapi.pojo.Report;
import io.socket.client.Socket;

public class ServerInterface {

    private Socket socket;

    public ServerInterface(Socket socket, ListenerManager listenerManager) {
        this.socket = socket;

        socket.on("create report", args -> {
            Report report = AzionAPI.getInstance().getReportManager().getReport((String) args[0]);
            listenerManager.deflectReportListener(report);
        });
    }

}
