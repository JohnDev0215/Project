package android.net.netlink;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class NetlinkMessage {
    protected StructNlMsgHdr mHeader;

    public static NetlinkMessage parse(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            byteBuffer.position();
        }
        StructNlMsgHdr structNlMsgHdr = StructNlMsgHdr.parse(byteBuffer);
        if (structNlMsgHdr == null) {
            return null;
        }
        int iAlignedLengthOf = NetlinkConstants.alignedLengthOf(structNlMsgHdr.nlmsg_len) - 16;
        if (iAlignedLengthOf < 0 || iAlignedLengthOf > byteBuffer.remaining()) {
            byteBuffer.position(byteBuffer.limit());
            return null;
        }
        short s = structNlMsgHdr.nlmsg_type;
        if (s == 2) {
            return NetlinkErrorMessage.parse(structNlMsgHdr, byteBuffer);
        }
        if (s == 3) {
            byteBuffer.position(byteBuffer.position() + iAlignedLengthOf);
            return new NetlinkMessage(structNlMsgHdr);
        }
        if (s == 20) {
            return InetDiagMessage.parse(structNlMsgHdr, byteBuffer);
        }
        if (s != 68) {
            switch (s) {
                case 28:
                case 29:
                case 30:
                    return RtNetlinkNeighborMessage.parse(structNlMsgHdr, byteBuffer);
                default:
                    if (s > 15) {
                        return null;
                    }
                    byteBuffer.position(byteBuffer.position() + iAlignedLengthOf);
                    return new NetlinkMessage(structNlMsgHdr);
            }
        }
        return NduseroptMessage.parse(structNlMsgHdr, byteBuffer);
    }

    public NetlinkMessage(StructNlMsgHdr structNlMsgHdr) {
        this.mHeader = structNlMsgHdr;
    }

    public StructNlMsgHdr getHeader() {
        return this.mHeader;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NetlinkMessage{");
        StructNlMsgHdr structNlMsgHdr = this.mHeader;
        sb.append(structNlMsgHdr == null ? "" : structNlMsgHdr.toString());
        sb.append("}");
        return sb.toString();
    }
}
