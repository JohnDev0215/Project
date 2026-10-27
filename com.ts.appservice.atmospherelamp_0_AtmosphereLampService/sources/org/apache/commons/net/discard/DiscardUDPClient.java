package org.apache.commons.net.discard;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import org.apache.commons.net.DatagramSocketClient;

/* JADX INFO: loaded from: classes3.dex */
public class DiscardUDPClient extends DatagramSocketClient {
    public static final int DEFAULT_PORT = 9;
    private final DatagramPacket sendPacket = new DatagramPacket(new byte[0], 0);

    public void send(byte[] data, int length, InetAddress host, int port) throws IOException {
        this.sendPacket.setData(data);
        this.sendPacket.setLength(length);
        this.sendPacket.setAddress(host);
        this.sendPacket.setPort(port);
        this._socket_.send(this.sendPacket);
    }

    public void send(byte[] data, int length, InetAddress host) throws IOException {
        send(data, length, host, 9);
    }

    public void send(byte[] data, InetAddress host) throws IOException {
        send(data, data.length, host, 9);
    }
}
