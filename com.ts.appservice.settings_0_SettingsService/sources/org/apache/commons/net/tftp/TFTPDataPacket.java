package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;

/* JADX INFO: loaded from: classes.dex */
public final class TFTPDataPacket extends TFTPPacket {
    public static final int MAX_DATA_LENGTH = 512;
    public static final int MIN_DATA_LENGTH = 0;
    int blockNumber;
    private byte[] data;
    private int length;
    private int offset;

    public TFTPDataPacket(InetAddress destination, int port, int blockNumber, byte[] data, int offset, int length) {
        super(3, destination, port);
        this.blockNumber = blockNumber;
        this.data = data;
        this.offset = offset;
        if (length > 512) {
            this.length = 512;
        } else {
            this.length = length;
        }
    }

    public TFTPDataPacket(InetAddress destination, int port, int blockNumber, byte[] data) {
        this(destination, port, blockNumber, data, 0, data.length);
    }

    TFTPDataPacket(DatagramPacket datagram) throws TFTPPacketException {
        super(3, datagram.getAddress(), datagram.getPort());
        this.data = datagram.getData();
        this.offset = 4;
        int type = getType();
        byte[] bArr = this.data;
        if (type != bArr[1]) {
            throw new TFTPPacketException("TFTP operator code does not match type.");
        }
        this.blockNumber = ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        int length = datagram.getLength() - 4;
        this.length = length;
        if (length > 512) {
            this.length = 512;
        }
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    DatagramPacket _newDatagram(DatagramPacket datagram, byte[] data) {
        data[0] = 0;
        data[1] = (byte) this.type;
        int i = this.blockNumber;
        data[2] = (byte) ((65535 & i) >> 8);
        data[3] = (byte) (i & 255);
        byte[] bArr = this.data;
        if (data != bArr) {
            System.arraycopy(bArr, this.offset, data, 4, this.length);
        }
        datagram.setAddress(this.address);
        datagram.setPort(this.port);
        datagram.setData(data);
        datagram.setLength(this.length + 4);
        return datagram;
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    public DatagramPacket newDatagram() {
        byte[] data = new byte[this.length + 4];
        data[0] = 0;
        data[1] = (byte) this.type;
        int i = this.blockNumber;
        data[2] = (byte) ((65535 & i) >> 8);
        data[3] = (byte) (i & 255);
        System.arraycopy(this.data, this.offset, data, 4, this.length);
        return new DatagramPacket(data, this.length + 4, this.address, this.port);
    }

    public int getBlockNumber() {
        return this.blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public void setData(byte[] data, int offset, int length) {
        this.data = data;
        this.offset = offset;
        this.length = length;
        if (length > 512) {
            this.length = 512;
        } else {
            this.length = length;
        }
    }

    public int getDataLength() {
        return this.length;
    }

    public int getDataOffset() {
        return this.offset;
    }

    public byte[] getData() {
        return this.data;
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    public String toString() {
        return super.toString() + " DATA " + this.blockNumber + " " + this.length;
    }
}
