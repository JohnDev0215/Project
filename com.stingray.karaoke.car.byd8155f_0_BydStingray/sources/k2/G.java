package k2;

import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.net.Uri;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;

/* JADX INFO: loaded from: classes.dex */
public final class G extends AbstractC1602c {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f17635p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final byte[] f17636q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final DatagramPacket f17637r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Uri f17638s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public DatagramSocket f17639t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public MulticastSocket f17640u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public InetAddress f17641v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f17642w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f17643x;

    public G() {
        super(true);
        this.f17635p = BYDAutoEngineDevice.ENGINE_SPEED_MAX;
        byte[] bArr = new byte[2000];
        this.f17636q = bArr;
        this.f17637r = new DatagramPacket(bArr, 0, 2000);
    }

    @Override // k2.h
    public final void close() {
        this.f17638s = null;
        MulticastSocket multicastSocket = this.f17640u;
        if (multicastSocket != null) {
            try {
                InetAddress inetAddress = this.f17641v;
                inetAddress.getClass();
                multicastSocket.leaveGroup(inetAddress);
            } catch (IOException unused) {
            }
            this.f17640u = null;
        }
        DatagramSocket datagramSocket = this.f17639t;
        if (datagramSocket != null) {
            datagramSocket.close();
            this.f17639t = null;
        }
        this.f17641v = null;
        this.f17643x = 0;
        if (this.f17642w) {
            this.f17642w = false;
            p();
        }
    }

    @Override // k2.h
    public final long g(m mVar) throws F {
        Uri uri = mVar.f17675a;
        this.f17638s = uri;
        String host = uri.getHost();
        host.getClass();
        int port = this.f17638s.getPort();
        q();
        try {
            this.f17641v = InetAddress.getByName(host);
            InetSocketAddress inetSocketAddress = new InetSocketAddress(this.f17641v, port);
            if (this.f17641v.isMulticastAddress()) {
                MulticastSocket multicastSocket = new MulticastSocket(inetSocketAddress);
                this.f17640u = multicastSocket;
                multicastSocket.joinGroup(this.f17641v);
                this.f17639t = this.f17640u;
            } else {
                this.f17639t = new DatagramSocket(inetSocketAddress);
            }
            this.f17639t.setSoTimeout(this.f17635p);
            this.f17642w = true;
            r(mVar);
            return -1L;
        } catch (IOException e10) {
            throw new F(e10, 2001);
        } catch (SecurityException e11) {
            throw new F(e11, 2006);
        }
    }

    @Override // k2.h
    public final Uri j() {
        return this.f17638s;
    }

    @Override // e2.InterfaceC1201i
    public final int n(byte[] bArr, int i7, int i10) throws F {
        if (i10 == 0) {
            return 0;
        }
        int i11 = this.f17643x;
        DatagramPacket datagramPacket = this.f17637r;
        if (i11 == 0) {
            try {
                DatagramSocket datagramSocket = this.f17639t;
                datagramSocket.getClass();
                datagramSocket.receive(datagramPacket);
                int length = datagramPacket.getLength();
                this.f17643x = length;
                e(length);
            } catch (SocketTimeoutException e10) {
                throw new F(e10, 2002);
            } catch (IOException e11) {
                throw new F(e11, 2001);
            }
        }
        int length2 = datagramPacket.getLength();
        int i12 = this.f17643x;
        int iMin = Math.min(i12, i10);
        System.arraycopy(this.f17636q, length2 - i12, bArr, i7, iMin);
        this.f17643x -= iMin;
        return iMin;
    }
}
