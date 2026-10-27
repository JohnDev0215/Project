package android.net.dhcp;

import android.net.dhcp.DhcpPacket;
import android.net.util.FdEventsReader;
import android.os.Handler;
import android.system.Os;
import android.util.Log;
import java.io.FileDescriptor;
import java.net.Inet4Address;
import java.net.InetSocketAddress;

/* JADX INFO: loaded from: classes.dex */
abstract class DhcpPacketListener extends FdEventsReader<Payload> {
    protected abstract void logParseError(byte[] bArr, int i, DhcpPacket.ParseException parseException);

    protected abstract void onReceive(DhcpPacket dhcpPacket, Inet4Address inet4Address, int i);

    static final class Payload {
        protected final byte[] mBytes = new byte[1500];
        protected Inet4Address mSrcAddr;
        protected int mSrcPort;

        Payload() {
        }
    }

    DhcpPacketListener(Handler handler) {
        super(handler, new Payload());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.net.util.FdEventsReader
    public final void handlePacket(Payload payload, int i) {
        if (payload.mSrcAddr == null) {
            return;
        }
        try {
            onReceive(DhcpPacket.decodeFullPacket(payload.mBytes, i, 2), payload.mSrcAddr, payload.mSrcPort);
        } catch (DhcpPacket.ParseException e) {
            logParseError(payload.mBytes, i, e);
        } catch (Throwable th) {
            Log.wtf(DhcpPacketListener.class.getSimpleName(), "Error handling DHCP packet", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.net.util.FdEventsReader
    public int readPacket(FileDescriptor fileDescriptor, Payload payload) throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(0);
        byte[] bArr = payload.mBytes;
        int iRecvfrom = Os.recvfrom(fileDescriptor, bArr, 0, bArr.length, 0, inetSocketAddress);
        payload.mSrcAddr = inet4AddrOrNull(inetSocketAddress);
        payload.mSrcPort = inetSocketAddress.getPort();
        return iRecvfrom;
    }

    private static Inet4Address inet4AddrOrNull(InetSocketAddress inetSocketAddress) {
        if (inetSocketAddress.getAddress() instanceof Inet4Address) {
            return (Inet4Address) inetSocketAddress.getAddress();
        }
        return null;
    }
}
