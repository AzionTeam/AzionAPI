package fr.sothis.azionapi.socket;

import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URI;

import fr.sothis.azionapi.listeners.ListenerManager;

public class Sockets {

    private Socket socket;
    private ServerInterface serverInterface;
    private ClientInterface clientInterface;

    public Sockets(ListenerManager listenerManager) {
        URI uri = URI.create("https://192.168.11.201");
        IO.Options options = IO.Options.builder()
                .build();

        socket = IO.socket(uri, options);

        serverInterface = new ServerInterface(socket, listenerManager);
        clientInterface = new ClientInterface(socket);
    }

    public ServerInterface getServerInterface() {
        return serverInterface;
    }

    public ClientInterface getClientInterface() {
        return clientInterface;
    }
}
