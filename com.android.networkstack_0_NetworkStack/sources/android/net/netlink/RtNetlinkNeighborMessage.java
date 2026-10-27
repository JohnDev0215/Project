package android.net.netlink;

import android.system.OsConstants;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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

    public static byte[] newNewNeighborMessage(int i, InetAddress inetAddress, short s, int i2, byte[] bArr) {
        StructNlMsgHdr structNlMsgHdr = new StructNlMsgHdr();
        structNlMsgHdr.nlmsg_type = (short) 28;
        structNlMsgHdr.nlmsg_flags = (short) 261;
        structNlMsgHdr.nlmsg_seq = i;
        RtNetlinkNeighborMessage rtNetlinkNeighborMessage = new RtNetlinkNeighborMessage(structNlMsgHdr);
        StructNdMsg structNdMsg = new StructNdMsg();
        rtNetlinkNeighborMessage.mNdmsg = structNdMsg;
        structNdMsg.ndm_family = (byte) (inetAddress instanceof Inet6Address ? OsConstants.AF_INET6 : OsConstants.AF_INET);
        StructNdMsg structNdMsg2 = rtNetlinkNeighborMessage.mNdmsg;
        structNdMsg2.ndm_ifindex = i2;
        structNdMsg2.ndm_state = s;
        rtNetlinkNeighborMessage.mDestination = inetAddress;
        rtNetlinkNeighborMessage.mLinkLayerAddr = bArr;
        int requiredSpace = rtNetlinkNeighborMessage.getRequiredSpace();
        byte[] bArr2 = new byte[requiredSpace];
        structNlMsgHdr.nlmsg_len = requiredSpace;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr2);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        rtNetlinkNeighborMessage.pack(byteBufferWrap);
        return bArr2;
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

    public int getRequiredSpace() {
        InetAddress inetAddress = this.mDestination;
        int iAlignedLengthOf = inetAddress != null ? 28 + NetlinkConstants.alignedLengthOf(inetAddress.getAddress().length + 4) : 28;
        byte[] bArr = this.mLinkLayerAddr;
        return bArr != null ? iAlignedLengthOf + NetlinkConstants.alignedLengthOf(bArr.length + 4) : iAlignedLengthOf;
    }

    private static void packNlAttr(short s, byte[] bArr, ByteBuffer byteBuffer) {
        StructNlAttr structNlAttr = new StructNlAttr();
        structNlAttr.nla_type = s;
        structNlAttr.nla_value = bArr;
        structNlAttr.nla_len = (short) (bArr.length + 4);
        structNlAttr.pack(byteBuffer);
    }

    public void pack(ByteBuffer byteBuffer) {
        getHeader().pack(byteBuffer);
        this.mNdmsg.pack(byteBuffer);
        InetAddress inetAddress = this.mDestination;
        if (inetAddress != null) {
            packNlAttr((short) 1, inetAddress.getAddress(), byteBuffer);
        }
        byte[] bArr = this.mLinkLayerAddr;
        if (bArr != null) {
            packNlAttr((short) 2, bArr, byteBuffer);
        }
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
