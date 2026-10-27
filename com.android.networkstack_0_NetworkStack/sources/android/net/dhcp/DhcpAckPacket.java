package android.net.dhcp;

import android.net.util.IpMask;
import java.net.Inet4Address;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class DhcpAckPacket extends DhcpPacket {
    private final Inet4Address mSrcIp;

    DhcpAckPacket(int i, short s, boolean z, Inet4Address inet4Address, Inet4Address inet4Address2, Inet4Address inet4Address3, Inet4Address inet4Address4, byte[] bArr, boolean z2) {
        super(i, s, inet4Address3, inet4Address4, inet4Address, inet4Address2, bArr, z);
        this.mBroadcast = z;
        this.mSrcIp = inet4Address;
        this.mRapidCommit = z2;
    }

    @Override // android.net.dhcp.DhcpPacket
    public String toString() {
        String string = super.toString();
        Iterator<Inet4Address> it = this.mDnsServers.iterator();
        String str = " DNS servers: ";
        while (it.hasNext()) {
            str = str + IpMask.maskIpMiddle(it.next()) + " ";
        }
        return string + " ACK: your new IP " + IpMask.maskIpMiddle(this.mYourIp) + ", netmask " + this.mSubnetMask + ", gateways " + this.mGateways + str + ", lease time " + this.mLeaseTime;
    }

    @Override // android.net.dhcp.DhcpPacket
    public ByteBuffer buildPacket(int i, short s, short s2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1500);
        fillInPacket(i, this.mBroadcast ? DhcpPacket.INADDR_BROADCAST : this.mYourIp, this.mBroadcast ? DhcpPacket.INADDR_ANY : this.mSrcIp, s, s2, byteBufferAllocate, (byte) 2, this.mBroadcast);
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    @Override // android.net.dhcp.DhcpPacket
    void finishPacket(ByteBuffer byteBuffer) {
        DhcpPacket.addTlv(byteBuffer, (byte) 53, (byte) 5);
        DhcpPacket.addTlv(byteBuffer, (byte) 54, this.mServerIdentifier);
        addCommonServerTlvs(byteBuffer);
        if (this.mRapidCommit) {
            DhcpPacket.addTlv(byteBuffer, (byte) 80);
        }
        DhcpPacket.addTlvEnd(byteBuffer);
    }
}
