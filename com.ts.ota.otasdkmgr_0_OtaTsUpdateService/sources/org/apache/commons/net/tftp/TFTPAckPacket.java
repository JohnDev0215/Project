package org.apache.commons.net.tftp;

import com.engine.tbox.data.ReservationCharging;
import java.net.DatagramPacket;
import java.net.InetAddress;

/* JADX INFO: loaded from: classes.dex */
public final class TFTPAckPacket extends TFTPPacket {
    int blockNumber;

    public TFTPAckPacket(InetAddress destination, int port, int blockNumber) {
        super(4, destination, port);
        this.blockNumber = blockNumber;
    }

    TFTPAckPacket(DatagramPacket datagram) throws TFTPPacketException {
        super(4, datagram.getAddress(), datagram.getPort());
        byte[] data = datagram.getData();
        if (getType() != data[1]) {
            throw new TFTPPacketException("TFTP operator code does not match type.");
        }
        this.blockNumber = ((data[2] & ReservationCharging.DEFAULT_ELECTROVALENCE) << 8) | (data[3] & ReservationCharging.DEFAULT_ELECTROVALENCE);
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    DatagramPacket newDatagram(DatagramPacket datagram, byte[] data) {
        data[0] = 0;
        data[1] = (byte) this.type;
        int i = this.blockNumber;
        data[2] = (byte) ((65535 & i) >> 8);
        data[3] = (byte) (i & 255);
        datagram.setAddress(this.address);
        datagram.setPort(this.port);
        datagram.setData(data);
        datagram.setLength(4);
        return datagram;
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    public DatagramPacket newDatagram() {
        int i = this.blockNumber;
        byte[] data = {0, (byte) this.type, (byte) ((65535 & i) >> 8), (byte) (i & 255)};
        return new DatagramPacket(data, data.length, this.address, this.port);
    }

    public int getBlockNumber() {
        return this.blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    @Override // org.apache.commons.net.tftp.TFTPPacket
    public String toString() {
        return super.toString() + " ACK " + this.blockNumber;
    }
}
