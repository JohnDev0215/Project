package android.net.dhcp;

import java.net.Inet4Address;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class DhcpDeclinePacket extends DhcpPacket {
    DhcpDeclinePacket(int i, short s, Inet4Address inet4Address, Inet4Address inet4Address2, Inet4Address inet4Address3, Inet4Address inet4Address4, byte[] bArr, Inet4Address inet4Address5, Inet4Address inet4Address6) {
        super(i, s, inet4Address, inet4Address2, inet4Address3, inet4Address4, bArr, false);
        this.mRequestedIp = inet4Address5;
        this.mServerIdentifier = inet4Address6;
    }

    @Override // android.net.dhcp.DhcpPacket
    public String toString() {
        return super.toString() + " DECLINE";
    }

    @Override // android.net.dhcp.DhcpPacket
    public ByteBuffer buildPacket(int i, short s, short s2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1500);
        fillInPacket(i, this.mClientIp, this.mYourIp, s, s2, byteBufferAllocate, (byte) 1, false);
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    @Override // android.net.dhcp.DhcpPacket
    void finishPacket(ByteBuffer byteBuffer) {
        DhcpPacket.addTlv(byteBuffer, (byte) 53, (byte) 4);
        DhcpPacket.addTlv(byteBuffer, (byte) 61, getClientId());
        DhcpPacket.addTlv(byteBuffer, (byte) 50, this.mRequestedIp);
        DhcpPacket.addTlv(byteBuffer, (byte) 54, this.mServerIdentifier);
        DhcpPacket.addTlvEnd(byteBuffer);
    }
}
