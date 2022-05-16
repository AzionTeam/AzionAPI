package fr.sothis.azionapi.socket;

import fr.sothis.azionapi.listeners.ListenerManager;
import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URI;

public class Sockets {

    private Socket socket;
    private ServerInterface serverInterface;
    private ClientInterface clientInterface;

    public Sockets(ListenerManager listenerManager) {
        URI uri = URI.create("https://127.0.0.1");
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
