package fr.sothis.azionapi.socket;

import io.socket.client.Ack;
import io.socket.client.Socket;
import org.bukkit.Bukkit;

public class ClientInterface {

    private Socket socket;

    public ClientInterface(Socket socket) {
        this.socket = socket;
    }

    public void sendCreationReport(String uuid) {
        socket.emit("create report", 1, uuid, (Ack) args -> {
            Bukkit.getLogger().info("Sending creation report");
        });
    }

    public void sendRequestInstantiation(String name) {
        socket.emit("api", 1, name);
    }

    public Socket getSocket() {
        return socket;
    }
}
