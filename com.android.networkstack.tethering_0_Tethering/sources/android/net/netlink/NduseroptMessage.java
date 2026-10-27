package android.net.netlink;

import android.system.OsConstants;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class NduseroptMessage extends NetlinkMessage {
    public final byte family;
    public final byte icmp_code;
    public final byte icmp_type;
    public final int ifindex;
    public final int opts_len;
    public final InetAddress srcaddr;

    NduseroptMessage(StructNlMsgHdr structNlMsgHdr, ByteBuffer byteBuffer) throws UnknownHostException {
        byte[] bArr;
        super(structNlMsgHdr);
        byteBuffer.order(ByteOrder.nativeOrder());
        int iPosition = byteBuffer.position();
        this.family = byteBuffer.get();
        byteBuffer.get();
        this.opts_len = Short.toUnsignedInt(byteBuffer.getShort());
        this.ifindex = byteBuffer.getInt();
        this.icmp_type = byteBuffer.get();
        this.icmp_code = byteBuffer.get();
        byteBuffer.position(byteBuffer.position() + 6);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        int iLimit = byteBuffer.limit();
        int i = iPosition + 16;
        byteBuffer.limit(this.opts_len + i);
        try {
            NdOption.parse(byteBuffer);
            byteBuffer.limit(iLimit);
            int i2 = i + this.opts_len;
            if (i2 >= byteBuffer.limit()) {
                throw new IllegalArgumentException("ND options extend past end of buffer");
            }
            byteBuffer.position(i2);
            StructNlAttr structNlAttr = StructNlAttr.parse(byteBuffer);
            if (structNlAttr == null || structNlAttr.nla_type != 1 || (bArr = structNlAttr.nla_value) == null) {
                throw new IllegalArgumentException("Invalid source address in ND useropt");
            }
            if (this.family == OsConstants.AF_INET6) {
                this.srcaddr = Inet6Address.getByAddress((String) null, bArr, this.ifindex);
            } else {
                this.srcaddr = InetAddress.getByAddress(bArr);
            }
        } catch (Throwable th) {
            byteBuffer.limit(iLimit);
            throw th;
        }
    }

    public static NduseroptMessage parse(StructNlMsgHdr structNlMsgHdr, ByteBuffer byteBuffer) {
        if (byteBuffer != null && byteBuffer.remaining() >= 16) {
            ByteOrder byteOrderOrder = byteBuffer.order();
            try {
                return new NduseroptMessage(structNlMsgHdr, byteBuffer);
            } catch (IllegalArgumentException | UnknownHostException | BufferUnderflowException unused) {
            } finally {
                byteBuffer.order(byteOrderOrder);
            }
        }
        return null;
    }

    @Override // android.net.netlink.NetlinkMessage
    public String toString() {
        return String.format("Nduseroptmsg(%d, %d, %d, %d, %d, %s)", Byte.valueOf(this.family), Integer.valueOf(this.opts_len), Integer.valueOf(this.ifindex), Integer.valueOf(Byte.toUnsignedInt(this.icmp_type)), Integer.valueOf(Byte.toUnsignedInt(this.icmp_code)), this.srcaddr.getHostAddress());
    }
}
