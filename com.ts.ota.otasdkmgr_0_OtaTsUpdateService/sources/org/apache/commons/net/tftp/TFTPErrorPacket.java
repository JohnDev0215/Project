package org.apache.commons.net.tftp;

import com.engine.tbox.data.ReservationCharging;
import java.net.DatagramPacket;
import java.net.InetAddress;

/* JADX INFO: loaded from: classes.dex */
public final class TFTPErrorPacket extends TFTPPacket {
    public static final int ACCESS_VIOLATION = 2;
    public static final int FILE_EXISTS = 6;
    public static final int FILE_NOT_FOUND = 1;
    public static final int ILLEGAL_OPERATION = 4;
    public static final int NO_SUCH_USER = 7;
    public static final int OUT_OF_SPACE = 3;
    public static final int UNDEFINED = 0;
    public static final int UNKNOWN_TID = 5;
    private final int error;
    private final String message;

    public TFTPErrorPacket(InetAddress destination, int port, int error, String message) {
        super(5, destination, port);
        this.error = error;
        this.message = message;
    }

    TFTPErrorPacket(DatagramPacket datagram) throws TFTPPacketException {
        super(5, datagram.getAddress(), datagram.getPort());
        byte[] data = datagram.getData();
        int length = datagram.getLength();
        if (getType() != data[1]) {
            throw new TFTPPacketException("TFTP operator code does not match type.");
        }
        this.error = ((data[2] & ReservationCharging.DEFAULT_ELECTROVALENCE) << 8) | (data[3] & ReservationCharging.DEFAULT_ELECTROVALENCE);
        if (length < 5) {
            throw new TFTPPacketException("Bad error packet. No message.");
        }
        StringBuilder buffer = new StringBuilder();
        for (int index = 4; index < length && data[index] != 0; index++) {
            buffer.append((char) data[index]);
        }
        this.message = buffer.toString();
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    DatagramPacket newDatagram(DatagramPacket datagram, byte[] data) {
        int length = this.message.length();
        data[0] = 0;
        data[1] = (byte) this.type;
        int i = this.error;
        data[2] = (byte) ((65535 & i) >> 8);
        data[3] = (byte) (i & 255);
        System.arraycopy(this.message.getBytes(), 0, data, 4, length);
        data[length + 4] = 0;
        datagram.setAddress(this.address);
        datagram.setPort(this.port);
        datagram.setData(data);
        datagram.setLength(length + 4);
        return datagram;
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    public DatagramPacket newDatagram() {
        int length = this.message.length();
        byte[] data = new byte[length + 5];
        data[0] = 0;
        data[1] = (byte) this.type;
        int i = this.error;
        data[2] = (byte) ((65535 & i) >> 8);
        data[3] = (byte) (i & 255);
        System.arraycopy(this.message.getBytes(), 0, data, 4, length);
        data[length + 4] = 0;
        return new DatagramPacket(data, data.length, this.address, this.port);
    }

    public int getError() {
        return this.error;
    }

    public String getMessage() {
        return this.message;
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    public String toString() {
        return super.toString() + " ERR " + this.error + " " + this.message;
    }
}
