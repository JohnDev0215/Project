package android.net.dhcp;

import java.net.Inet4Address;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class DhcpNakPacket extends DhcpPacket {
    /* JADX WARN: Illegal instructions before constructor call */
    DhcpNakPacket(int i, short s, Inet4Address inet4Address, byte[] bArr, boolean z) {
        Inet4Address inet4Address2 = DhcpPacket.INADDR_ANY;
        super(i, s, inet4Address2, inet4Address2, inet4Address2, inet4Address, bArr, z);
    }

    @Override // android.net.dhcp.DhcpPacket
    public String toString() {
        String string = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append(" NAK, reason ");
        String str = this.mMessage;
        if (str == null) {
            str = "(none)";
        }
        sb.append(str);
        return sb.toString();
    }

    @Override // android.net.dhcp.DhcpPacket
    public ByteBuffer buildPacket(int i, short s, short s2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1500);
        Inet4Address inet4Address = DhcpPacket.INADDR_ANY;
        fillInPacket(i, inet4Address, inet4Address, s, s2, byteBufferAllocate, (byte) 2, this.mBroadcast);
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    @Override // android.net.dhcp.DhcpPacket
    void finishPacket(ByteBuffer byteBuffer) {
        DhcpPacket.addTlv(byteBuffer, (byte) 53, (byte) 6);
        DhcpPacket.addTlv(byteBuffer, (byte) 54, this.mServerIdentifier);
        DhcpPacket.addTlv(byteBuffer, (byte) 56, this.mMessage);
        DhcpPacket.addTlvEnd(byteBuffer);
    }
}
