package android.net.netlink;

import android.system.OsConstants;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class InetDiagMessage extends NetlinkMessage {
    public StructInetDiagMsg mStructInetDiagMsg;

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
