package android.net.netlink;

import android.net.INetd;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class NetlinkErrorMessage extends NetlinkMessage {
    private StructNlMsgErr mNlMsgErr;

    public static NetlinkErrorMessage parse(StructNlMsgHdr header, ByteBuffer byteBuffer) {
        NetlinkErrorMessage errorMsg = new NetlinkErrorMessage(header);
        StructNlMsgErr structNlMsgErr = StructNlMsgErr.parse(byteBuffer);
        errorMsg.mNlMsgErr = structNlMsgErr;
        if (structNlMsgErr == null) {
            return null;
        }
        return errorMsg;
    }

    NetlinkErrorMessage(StructNlMsgHdr header) {
        super(header);
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
        String string = INetd.NEXTHOP_NONE;
        sb.append(structNlMsgHdr == null ? INetd.NEXTHOP_NONE : this.mHeader.toString());
        sb.append("}, nlmsgerr{");
        StructNlMsgErr structNlMsgErr = this.mNlMsgErr;
        if (structNlMsgErr != null) {
            string = structNlMsgErr.toString();
        }
        sb.append(string);
        sb.append("} }");
        return sb.toString();
    }
}
