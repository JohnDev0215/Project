package org.apache.commons.net.chargen;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import org.apache.commons.net.DatagramSocketClient;

/* JADX INFO: loaded from: classes.dex */
public final class CharGenUDPClient extends DatagramSocketClient {
    public static final int CHARGEN_PORT = 19;
    public static final int DEFAULT_PORT = 19;
    public static final int NETSTAT_PORT = 15;
    public static final int QUOTE_OF_DAY_PORT = 17;
    public static final int SYSTAT_PORT = 11;
    private final byte[] receiveData = new byte[512];
    private final DatagramPacket receivePacket;
    private final DatagramPacket sendPacket;

    public CharGenUDPClient() {
        byte[] bArr = this.receiveData;
        this.receivePacket = new DatagramPacket(bArr, bArr.length);
        this.sendPacket = new DatagramPacket(new byte[0], 0);
    }

    public void send(InetAddress host, int port) throws IOException {
        this.sendPacket.setAddress(host);
        this.sendPacket.setPort(port);
        this._socket_.send(this.sendPacket);
    }

    public void send(InetAddress host) throws IOException {
        send(host, 19);
    }

    public byte[] receive() throws IOException {
        this._socket_.receive(this.receivePacket);
        int length = this.receivePacket.getLength();
        byte[] result = new byte[length];
        System.arraycopy(this.receiveData, 0, result, 0, length);
        return result;
    }
}
