package org.apache.commons.net.daytime;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import org.apache.commons.net.DatagramSocketClient;

/* JADX INFO: loaded from: classes3.dex */
public final class DaytimeUDPClient extends DatagramSocketClient {
    public static final int DEFAULT_PORT = 13;
    private final byte[] dummyData = new byte[1];
    private final byte[] timeData = new byte[256];

    public String getTime(InetAddress host, int port) throws IOException {
        byte[] bArr = this.dummyData;
        DatagramPacket sendPacket = new DatagramPacket(bArr, bArr.length, host, port);
        byte[] bArr2 = this.timeData;
        DatagramPacket receivePacket = new DatagramPacket(bArr2, bArr2.length);
        this._socket_.send(sendPacket);
        this._socket_.receive(receivePacket);
        return new String(receivePacket.getData(), 0, receivePacket.getLength(), getCharset());
    }

    public String getTime(InetAddress host) throws IOException {
        return getTime(host, 13);
    }
}
