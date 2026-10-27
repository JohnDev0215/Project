package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class TFTPRequestPacket extends TFTPPacket {
    private final String fileName;
    private final int mode;
    static final String[] modeStrings = {"netascii", "octet"};
    private static final byte[][] modeBytes = {new byte[]{110, 101, 116, 97, 115, 99, 105, 105, 0}, new byte[]{111, 99, 116, 101, 116, 0}};

    TFTPRequestPacket(InetAddress destination, int port, int type, String fileName, int mode) {
        super(type, destination, port);
        this.fileName = fileName;
        this.mode = mode;
    }

    TFTPRequestPacket(int type, DatagramPacket datagram) throws TFTPPacketException {
        super(type, datagram.getAddress(), datagram.getPort());
        byte[] data = datagram.getData();
        if (getType() != data[1]) {
            throw new TFTPPacketException("TFTP operator code does not match type.");
        }
        StringBuilder buffer = new StringBuilder();
        int index = 2;
        int length = datagram.getLength();
        while (index < length && data[index] != 0) {
            buffer.append((char) data[index]);
            index++;
        }
        this.fileName = buffer.toString();
        if (index >= length) {
            throw new TFTPPacketException("Bad file name and mode format.");
        }
        buffer.setLength(0);
        for (int index2 = index + 1; index2 < length && data[index2] != 0; index2++) {
            buffer.append((char) data[index2]);
        }
        String modeString = buffer.toString().toLowerCase(Locale.ENGLISH);
        int length2 = modeStrings.length;
        int mode = 0;
        int index3 = 0;
        while (true) {
            if (index3 >= length2) {
                break;
            }
            if (!modeString.equals(modeStrings[index3])) {
                index3++;
            } else {
                mode = index3;
                break;
            }
        }
        this.mode = mode;
        if (index3 >= length2) {
            throw new TFTPPacketException("Unrecognized TFTP transfer mode: " + modeString);
        }
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    final DatagramPacket newDatagram(DatagramPacket datagram, byte[] data) {
        int fileLength = this.fileName.length();
        int modeLength = modeBytes[this.mode].length;
        data[0] = 0;
        data[1] = (byte) this.type;
        System.arraycopy(this.fileName.getBytes(), 0, data, 2, fileLength);
        data[fileLength + 2] = 0;
        System.arraycopy(modeBytes[this.mode], 0, data, fileLength + 3, modeLength);
        datagram.setAddress(this.address);
        datagram.setPort(this.port);
        datagram.setData(data);
        datagram.setLength(fileLength + modeLength + 3);
        return datagram;
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    public final DatagramPacket newDatagram() {
        int fileLength = this.fileName.length();
        int modeLength = modeBytes[this.mode].length;
        byte[] data = new byte[fileLength + modeLength + 4];
        data[0] = 0;
        data[1] = (byte) this.type;
        System.arraycopy(this.fileName.getBytes(), 0, data, 2, fileLength);
        data[fileLength + 2] = 0;
        System.arraycopy(modeBytes[this.mode], 0, data, fileLength + 3, modeLength);
        return new DatagramPacket(data, data.length, this.address, this.port);
    }

    public final int getMode() {
        return this.mode;
    }

    public final String getFilename() {
        return this.fileName;
    }
}
