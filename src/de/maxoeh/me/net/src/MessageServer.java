package de.maxoeh.me.net.src;

import de.maxoeh.me.net.lib.Server;

public class MessageServer extends Server {

    public MessageServer() {
        super(8080);
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        send(pClientIP, pClientPort, "Pong");
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        System.out.println(pMessage);
        send(pClientIP, pClientPort, "Pong");
    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {

    }

}
