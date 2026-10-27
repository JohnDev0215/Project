package org.apache.commons.net.tftp;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.SocketException;
import org.apache.commons.net.DatagramSocketClient;

/* JADX INFO: loaded from: classes3.dex */
public class TFTP extends DatagramSocketClient {
    public static final int ASCII_MODE = 0;
    public static final int BINARY_MODE = 1;
    public static final int DEFAULT_PORT = 69;
    public static final int DEFAULT_TIMEOUT = 5000;
    public static final int IMAGE_MODE = 1;
    public static final int NETASCII_MODE = 0;
    public static final int OCTET_MODE = 1;
    static final int PACKET_SIZE = 516;
    private byte[] receiveBuffer;
    private DatagramPacket receiveDatagram;
    byte[] sendBuffer;
    private DatagramPacket sendDatagram;

    public static final String getModeName(int mode) {
        return TFTPRequestPacket._modeStrings[mode];
    }

    public TFTP() {
        setDefaultTimeout(5000);
        this.receiveBuffer = null;
        this.receiveDatagram = null;
    }

    public final void discardPackets() throws IOException {
        DatagramPacket datagram = new DatagramPacket(new byte[516], 516);
        int to = getSoTimeout();
        setSoTimeout(1);
        while (true) {
            try {
                this._socket_.receive(datagram);
            } catch (InterruptedIOException | SocketException e) {
                setSoTimeout(to);
                return;
            }
        }
    }

    public final TFTPPacket bufferedReceive() throws TFTPPacketException, IOException {
        this.receiveDatagram.setData(this.receiveBuffer);
        this.receiveDatagram.setLength(this.receiveBuffer.length);
        this._socket_.receive(this.receiveDatagram);
        TFTPPacket newTFTPPacket = TFTPPacket.newTFTPPacket(this.receiveDatagram);
        trace("<", newTFTPPacket);
        return newTFTPPacket;
    }

    public final void bufferedSend(TFTPPacket packet) throws IOException {
        trace(">", packet);
        this._socket_.send(packet._newDatagram(this.sendDatagram, this.sendBuffer));
    }

    public final void beginBufferedOps() {
        this.receiveBuffer = new byte[516];
        byte[] bArr = this.receiveBuffer;
        this.receiveDatagram = new DatagramPacket(bArr, bArr.length);
        this.sendBuffer = new byte[516];
        byte[] bArr2 = this.sendBuffer;
        this.sendDatagram = new DatagramPacket(bArr2, bArr2.length);
    }

    public final void endBufferedOps() {
        this.receiveBuffer = null;
        this.receiveDatagram = null;
        this.sendBuffer = null;
        this.sendDatagram = null;
    }

    public final void send(TFTPPacket packet) throws IOException {
        trace(">", packet);
        this._socket_.send(packet.newDatagram());
    }

    public final TFTPPacket receive() throws TFTPPacketException, IOException {
        DatagramPacket packet = new DatagramPacket(new byte[516], 516);
        this._socket_.receive(packet);
        TFTPPacket newTFTPPacket = TFTPPacket.newTFTPPacket(packet);
        trace("<", newTFTPPacket);
        return newTFTPPacket;
    }

    protected void trace(String direction, TFTPPacket packet) {
    }
}
