package org.apache.commons.net.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import org.apache.commons.net.discard.DiscardUDPClient;

/* JADX INFO: loaded from: classes3.dex */
public final class EchoUDPClient extends DiscardUDPClient {
    public static final int DEFAULT_PORT = 7;
    private final DatagramPacket receivePacket = new DatagramPacket(new byte[0], 0);

    @Override // org.apache.commons.net.discard.DiscardUDPClient
    public void send(byte[] data, int length, InetAddress host) throws IOException {
        send(data, length, host, 7);
    }

    @Override // org.apache.commons.net.discard.DiscardUDPClient
    public void send(byte[] data, InetAddress host) throws IOException {
        send(data, data.length, host, 7);
    }

    public int receive(byte[] data, int length) throws IOException {
        this.receivePacket.setData(data);
        this.receivePacket.setLength(length);
        this._socket_.receive(this.receivePacket);
        return this.receivePacket.getLength();
    }

    public int receive(byte[] data) throws IOException {
        return receive(data, data.length);
    }
}
