package android.net.dhcp;

import java.net.Inet4Address;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class DhcpDiscoverPacket extends DhcpPacket {
    final Inet4Address mSrcIp;

    /* JADX WARN: Illegal instructions before constructor call */
    DhcpDiscoverPacket(int i, short s, Inet4Address inet4Address, byte[] bArr, boolean z, Inet4Address inet4Address2, boolean z2) {
        Inet4Address inet4Address3 = DhcpPacket.INADDR_ANY;
        super(i, s, inet4Address3, inet4Address3, inet4Address3, inet4Address, bArr, z);
        this.mSrcIp = inet4Address2;
        this.mRapidCommit = z2;
    }

    @Override // android.net.dhcp.DhcpPacket
    public String toString() {
        String string = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append(" DISCOVER ");
        sb.append(this.mBroadcast ? "broadcast " : "unicast ");
        return sb.toString();
    }

    @Override // android.net.dhcp.DhcpPacket
    public ByteBuffer buildPacket(int i, short s, short s2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1500);
        fillInPacket(i, DhcpPacket.INADDR_BROADCAST, this.mSrcIp, s, s2, byteBufferAllocate, (byte) 1, this.mBroadcast);
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    @Override // android.net.dhcp.DhcpPacket
    void finishPacket(ByteBuffer byteBuffer) {
        DhcpPacket.addTlv(byteBuffer, (byte) 53, (byte) 1);
        DhcpPacket.addTlv(byteBuffer, (byte) 61, getClientId());
        addCommonClientTlvs(byteBuffer);
        DhcpPacket.addTlv(byteBuffer, (byte) 55, this.mRequestedParams);
        if (this.mRapidCommit) {
            DhcpPacket.addTlv(byteBuffer, (byte) 80);
        }
        DhcpPacket.addTlvEnd(byteBuffer);
    }
}
