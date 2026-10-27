package android.net.dhcp;

import android.net.util.IpMask;
import java.net.Inet4Address;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DhcpOfferPacket extends DhcpPacket {
    private final Inet4Address mSrcIp;

    DhcpOfferPacket(int i, short s, boolean z, Inet4Address inet4Address, Inet4Address inet4Address2, Inet4Address inet4Address3, Inet4Address inet4Address4, byte[] bArr) {
        super(i, s, inet4Address3, inet4Address4, inet4Address, inet4Address2, bArr, z);
        this.mSrcIp = inet4Address;
    }

    @Override // android.net.dhcp.DhcpPacket
    public String toString() {
        String string = super.toString();
        List<Inet4Address> list = this.mDnsServers;
        String str = ", DNS servers: ";
        if (list != null) {
            Iterator<Inet4Address> it = list.iterator();
            while (it.hasNext()) {
                str = str + IpMask.maskIpMiddle(it.next()) + " ";
            }
        }
        return string + " OFFER, ip " + IpMask.maskIpMiddle(this.mYourIp) + ", mask " + this.mSubnetMask + str + ", gateways " + IpMask.maskIpMiddleList(this.mGateways) + " lease time " + this.mLeaseTime + ", domain " + this.mDomainName;
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
        DhcpPacket.addTlv(byteBuffer, (byte) 53, (byte) 2);
        DhcpPacket.addTlv(byteBuffer, (byte) 54, this.mServerIdentifier);
        addCommonServerTlvs(byteBuffer);
        DhcpPacket.addTlvEnd(byteBuffer);
    }
}
