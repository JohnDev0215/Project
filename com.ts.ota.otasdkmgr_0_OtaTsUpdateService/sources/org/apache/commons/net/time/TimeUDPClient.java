package org.apache.commons.net.time;

import com.engine.tbox.data.ReservationCharging;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;
import org.apache.commons.net.DatagramSocketClient;

/* JADX INFO: loaded from: classes.dex */
public final class TimeUDPClient extends DatagramSocketClient {
    public static final int DEFAULT_PORT = 37;
    public static final long SECONDS_1900_TO_1970 = 2208988800L;
    private final byte[] dummyData = new byte[1];
    private final byte[] timeData = new byte[4];

    public long getTime(InetAddress host, int port) throws IOException {
        byte[] bArr = this.dummyData;
        DatagramPacket sendPacket = new DatagramPacket(bArr, bArr.length, host, port);
        byte[] bArr2 = this.timeData;
        DatagramPacket receivePacket = new DatagramPacket(bArr2, bArr2.length);
        this._socket_.send(sendPacket);
        this._socket_.receive(receivePacket);
        byte[] bArr3 = this.timeData;
        long time = 0 | (((long) ((bArr3[0] & ReservationCharging.DEFAULT_ELECTROVALENCE) << 24)) & 4294967295L);
        return time | (((long) ((bArr3[1] & ReservationCharging.DEFAULT_ELECTROVALENCE) << 16)) & 4294967295L) | (((long) ((bArr3[2] & ReservationCharging.DEFAULT_ELECTROVALENCE) << 8)) & 4294967295L) | (((long) (bArr3[3] & ReservationCharging.DEFAULT_ELECTROVALENCE)) & 4294967295L);
    }

    public long getTime(InetAddress host) throws IOException {
        return getTime(host, 37);
    }

    public Date getDate(InetAddress host, int port) throws IOException {
        return new Date((getTime(host, port) - 2208988800L) * 1000);
    }

    public Date getDate(InetAddress host) throws IOException {
        return new Date((getTime(host, 37) - 2208988800L) * 1000);
    }
}
