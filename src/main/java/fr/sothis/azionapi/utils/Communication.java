package fr.sothis.azionapi.utils;

import fr.sothis.azionapi.socket.Sockets;
import io.socket.client.Socket;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Communication {

    private List<String> allAPI;
    private Sockets sockets;
    private Logger logger;

    public Communication(Sockets sockets, Plugin plugin) {
        allAPI = new ArrayList<>();
        this.sockets = sockets;
        this.logger = plugin.getLogger();

        Socket socket = sockets.getServerInterface().getSocket();

        socket.on("api", args -> {
            if(!allAPI.contains(args[0])) {
                allAPI.add(String.valueOf(args[0]));
                logger.info("A new instance of api have been detect and are call '" + args[0] + "'");
            }
            socket.emit("apiResponse", 1, args[0]);
        });
        socket.on("apiResponse", args -> {
            if(!allAPI.contains(args[0])) {
                allAPI.add(String.valueOf(args[0]));
                logger.info("The instance '" + args[0] + "' have replied to your api");
            }
        });
    }

    public void start(String name) {
        logger.info("The API start to find other instance of api who are currently running");
        sockets.getClientInterface().sendRequestInstantiation(name);
    }
}
