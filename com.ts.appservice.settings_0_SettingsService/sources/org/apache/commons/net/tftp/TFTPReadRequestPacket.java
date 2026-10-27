package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;

/* JADX INFO: loaded from: classes.dex */
public final class TFTPReadRequestPacket extends TFTPRequestPacket {
    public TFTPReadRequestPacket(InetAddress destination, int port, String fileName, int mode) {
        super(destination, port, 1, fileName, mode);
    }

    TFTPReadRequestPacket(DatagramPacket datagram) throws TFTPPacketException {
        super(1, datagram);
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    public String toString() {
        return super.toString() + " RRQ " + getFilename() + " " + TFTP.getModeName(getMode());
    }
}
