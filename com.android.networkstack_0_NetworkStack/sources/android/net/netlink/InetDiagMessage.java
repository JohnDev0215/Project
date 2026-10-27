package android.net.netlink;

import android.system.OsConstants;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class InetDiagMessage extends NetlinkMessage {
    public StructInetDiagMsg mStructInetDiagMsg;

    public static byte[] InetDiagReqV2(int i, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, int i2, short s, int i3, int i4, int i5) throws NullPointerException {
        byte[] bArr = new byte[72];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        StructNlMsgHdr structNlMsgHdr = new StructNlMsgHdr();
        structNlMsgHdr.nlmsg_len = 72;
        structNlMsgHdr.nlmsg_type = (short) 20;
        structNlMsgHdr.nlmsg_flags = s;
        structNlMsgHdr.pack(byteBufferWrap);
        new StructInetDiagReqV2(i, inetSocketAddress, inetSocketAddress2, i2, i3, i4, i5).pack(byteBufferWrap);
        return bArr;
    }

    private InetDiagMessage(StructNlMsgHdr structNlMsgHdr) {
        super(structNlMsgHdr);
        this.mStructInetDiagMsg = new StructInetDiagMsg();
    }

    public static InetDiagMessage parse(StructNlMsgHdr structNlMsgHdr, ByteBuffer byteBuffer) {
        InetDiagMessage inetDiagMessage = new InetDiagMessage(structNlMsgHdr);
        inetDiagMessage.mStructInetDiagMsg = StructInetDiagMsg.parse(byteBuffer);
        return inetDiagMessage;
    }

    static {
        int i = OsConstants.AF_INET6;
        int i2 = OsConstants.AF_INET;
    }

    @Override // android.net.netlink.NetlinkMessage
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InetDiagMessage{ nlmsghdr{");
        StructNlMsgHdr structNlMsgHdr = this.mHeader;
        sb.append(structNlMsgHdr == null ? "" : structNlMsgHdr.toString());
        sb.append("}, inet_diag_msg{");
        StructInetDiagMsg structInetDiagMsg = this.mStructInetDiagMsg;
        sb.append(structInetDiagMsg != null ? structInetDiagMsg.toString() : "");
        sb.append("} }");
        return sb.toString();
    }
}
