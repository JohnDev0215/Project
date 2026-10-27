package android.net.dhcp;

import java.net.Inet4Address;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class DhcpReleasePacket extends DhcpPacket {
    /* JADX WARN: Illegal instructions before constructor call */
    public DhcpReleasePacket(int i, Inet4Address inet4Address, Inet4Address inet4Address2, Inet4Address inet4Address3, byte[] bArr) {
        Inet4Address inet4Address4 = DhcpPacket.INADDR_ANY;
        super(i, (short) 0, inet4Address2, inet4Address4, inet4Address4, inet4Address3, bArr, false);
        this.mServerIdentifier = inet4Address;
    }

    @Override // android.net.dhcp.DhcpPacket
    public ByteBuffer buildPacket(int i, short s, short s2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1500);
        fillInPacket(i, this.mServerIdentifier, this.mClientIp, s, s2, byteBufferAllocate, (byte) 2, this.mBroadcast);
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    @Override // android.net.dhcp.DhcpPacket
    void finishPacket(ByteBuffer byteBuffer) {
        DhcpPacket.addTlv(byteBuffer, (byte) 53, (byte) 7);
        DhcpPacket.addTlv(byteBuffer, (byte) 61, getClientId());
        DhcpPacket.addTlv(byteBuffer, (byte) 54, this.mServerIdentifier);
        addCommonClientTlvs(byteBuffer);
        DhcpPacket.addTlvEnd(byteBuffer);
    }
}
