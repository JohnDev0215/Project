package android.net.netlink;

import java.net.InetAddress;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class RtNetlinkNeighborMessage extends NetlinkMessage {
    private StructNdaCacheInfo mCacheInfo;
    private InetAddress mDestination;
    private byte[] mLinkLayerAddr;
    private StructNdMsg mNdmsg;
    private int mNumProbes;

    private static StructNlAttr findNextAttrOfType(short s, ByteBuffer byteBuffer) {
        while (byteBuffer != null && byteBuffer.remaining() > 0) {
            StructNlAttr structNlAttrPeek = StructNlAttr.peek(byteBuffer);
            if (structNlAttrPeek == null) {
                return null;
            }
            if (structNlAttrPeek.nla_type == s) {
                return StructNlAttr.parse(byteBuffer);
            }
            if (byteBuffer.remaining() < structNlAttrPeek.getAlignedLength()) {
                return null;
            }
            byteBuffer.position(byteBuffer.position() + structNlAttrPeek.getAlignedLength());
        }
        return null;
    }

    public static RtNetlinkNeighborMessage parse(StructNlMsgHdr structNlMsgHdr, ByteBuffer byteBuffer) {
        RtNetlinkNeighborMessage rtNetlinkNeighborMessage = new RtNetlinkNeighborMessage(structNlMsgHdr);
        StructNdMsg structNdMsg = StructNdMsg.parse(byteBuffer);
        rtNetlinkNeighborMessage.mNdmsg = structNdMsg;
        if (structNdMsg == null) {
            return null;
        }
        int iPosition = byteBuffer.position();
        StructNlAttr structNlAttrFindNextAttrOfType = findNextAttrOfType((short) 1, byteBuffer);
        if (structNlAttrFindNextAttrOfType != null) {
            rtNetlinkNeighborMessage.mDestination = structNlAttrFindNextAttrOfType.getValueAsInetAddress();
        }
        byteBuffer.position(iPosition);
        StructNlAttr structNlAttrFindNextAttrOfType2 = findNextAttrOfType((short) 2, byteBuffer);
        if (structNlAttrFindNextAttrOfType2 != null) {
            rtNetlinkNeighborMessage.mLinkLayerAddr = structNlAttrFindNextAttrOfType2.nla_value;
        }
        byteBuffer.position(iPosition);
        StructNlAttr structNlAttrFindNextAttrOfType3 = findNextAttrOfType((short) 4, byteBuffer);
        if (structNlAttrFindNextAttrOfType3 != null) {
            rtNetlinkNeighborMessage.mNumProbes = structNlAttrFindNextAttrOfType3.getValueAsInt(0);
        }
        byteBuffer.position(iPosition);
        StructNlAttr structNlAttrFindNextAttrOfType4 = findNextAttrOfType((short) 3, byteBuffer);
        if (structNlAttrFindNextAttrOfType4 != null) {
            rtNetlinkNeighborMessage.mCacheInfo = StructNdaCacheInfo.parse(structNlAttrFindNextAttrOfType4.getValueAsByteBuffer());
        }
        int iAlignedLengthOf = NetlinkConstants.alignedLengthOf(rtNetlinkNeighborMessage.mHeader.nlmsg_len - 28);
        if (byteBuffer.remaining() < iAlignedLengthOf) {
            byteBuffer.position(byteBuffer.limit());
        } else {
            byteBuffer.position(iPosition + iAlignedLengthOf);
        }
        return rtNetlinkNeighborMessage;
    }

    private RtNetlinkNeighborMessage(StructNlMsgHdr structNlMsgHdr) {
        super(structNlMsgHdr);
        this.mNdmsg = null;
        this.mDestination = null;
        this.mLinkLayerAddr = null;
        this.mNumProbes = 0;
        this.mCacheInfo = null;
    }

    public StructNdMsg getNdHeader() {
        return this.mNdmsg;
    }

    public InetAddress getDestination() {
        return this.mDestination;
    }

    public byte[] getLinkLayerAddress() {
        return this.mLinkLayerAddr;
    }

    @Override // android.net.netlink.NetlinkMessage
    public String toString() {
        InetAddress inetAddress = this.mDestination;
        String hostAddress = inetAddress == null ? "" : inetAddress.getHostAddress();
        StringBuilder sb = new StringBuilder();
        sb.append("RtNetlinkNeighborMessage{ nlmsghdr{");
        StructNlMsgHdr structNlMsgHdr = this.mHeader;
        sb.append(structNlMsgHdr == null ? "" : structNlMsgHdr.toString());
        sb.append("}, ndmsg{");
        StructNdMsg structNdMsg = this.mNdmsg;
        sb.append(structNdMsg == null ? "" : structNdMsg.toString());
        sb.append("}, destination{");
        sb.append(hostAddress);
        sb.append("} linklayeraddr{");
        sb.append(NetlinkConstants.hexify(this.mLinkLayerAddr));
        sb.append("} probes{");
        sb.append(this.mNumProbes);
        sb.append("} cacheinfo{");
        StructNdaCacheInfo structNdaCacheInfo = this.mCacheInfo;
        sb.append(structNdaCacheInfo != null ? structNdaCacheInfo.toString() : "");
        sb.append("} }");
        return sb.toString();
    }
}
