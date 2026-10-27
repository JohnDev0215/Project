package android.net.dhcp;

import android.net.util.IpMask;
import java.net.Inet4Address;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class DhcpRequestPacket extends DhcpPacket {
    /* JADX WARN: Illegal instructions before constructor call */
    DhcpRequestPacket(int i, short s, Inet4Address inet4Address, Inet4Address inet4Address2, byte[] bArr, boolean z) {
        Inet4Address inet4Address3 = DhcpPacket.INADDR_ANY;
        super(i, s, inet4Address, inet4Address3, inet4Address3, inet4Address2, bArr, z);
    }

    @Override // android.net.dhcp.DhcpPacket
    public String toString() {
        String string = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append(" REQUEST, desired IP ");
        sb.append(IpMask.maskIpMiddle(this.mRequestedIp));
        sb.append(" from host '");
        sb.append(this.mHostName);
        sb.append("', param list length ");
        byte[] bArr = this.mRequestedParams;
        sb.append(bArr == null ? 0 : bArr.length);
        return sb.toString();
    }

    @Override // android.net.dhcp.DhcpPacket
    public ByteBuffer buildPacket(int i, short s, short s2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1500);
        fillInPacket(i, DhcpPacket.INADDR_BROADCAST, DhcpPacket.INADDR_ANY, s, s2, byteBufferAllocate, (byte) 1, this.mBroadcast);
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    @Override // android.net.dhcp.DhcpPacket
    void finishPacket(ByteBuffer byteBuffer) {
        DhcpPacket.addTlv(byteBuffer, (byte) 53, (byte) 3);
        DhcpPacket.addTlv(byteBuffer, (byte) 61, getClientId());
        if (!DhcpPacket.INADDR_ANY.equals(this.mRequestedIp)) {
            DhcpPacket.addTlv(byteBuffer, (byte) 50, this.mRequestedIp);
        }
        if (!DhcpPacket.INADDR_ANY.equals(this.mServerIdentifier)) {
            DhcpPacket.addTlv(byteBuffer, (byte) 54, this.mServerIdentifier);
        }
        addCommonClientTlvs(byteBuffer);
        DhcpPacket.addTlv(byteBuffer, (byte) 55, this.mRequestedParams);
        DhcpPacket.addTlvEnd(byteBuffer);
    }
}
