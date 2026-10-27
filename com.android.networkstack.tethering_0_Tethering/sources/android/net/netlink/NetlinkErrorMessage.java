package android.net.netlink;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class NetlinkErrorMessage extends NetlinkMessage {
    private StructNlMsgErr mNlMsgErr;

    public static NetlinkErrorMessage parse(StructNlMsgHdr structNlMsgHdr, ByteBuffer byteBuffer) {
        NetlinkErrorMessage netlinkErrorMessage = new NetlinkErrorMessage(structNlMsgHdr);
        StructNlMsgErr structNlMsgErr = StructNlMsgErr.parse(byteBuffer);
        netlinkErrorMessage.mNlMsgErr = structNlMsgErr;
        if (structNlMsgErr == null) {
            return null;
        }
        return netlinkErrorMessage;
    }

    NetlinkErrorMessage(StructNlMsgHdr structNlMsgHdr) {
        super(structNlMsgHdr);
        this.mNlMsgErr = null;
    }

    public StructNlMsgErr getNlMsgError() {
        return this.mNlMsgErr;
    }

    @Override // android.net.netlink.NetlinkMessage
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NetlinkErrorMessage{ nlmsghdr{");
        StructNlMsgHdr structNlMsgHdr = this.mHeader;
        sb.append(structNlMsgHdr == null ? "" : structNlMsgHdr.toString());
        sb.append("}, nlmsgerr{");
        StructNlMsgErr structNlMsgErr = this.mNlMsgErr;
        sb.append(structNlMsgErr != null ? structNlMsgErr.toString() : "");
        sb.append("} }");
        return sb.toString();
    }
}
