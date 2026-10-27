package android.net.netlink;

import android.system.OsConstants;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class ConntrackMessage extends NetlinkMessage {
    protected StructNfGenMsg mNfGenMsg;

    public static byte[] newIPv4TimeoutUpdateRequest(int i, Inet4Address inet4Address, int i2, Inet4Address inet4Address2, int i3, int i4) {
        StructNlAttr structNlAttr = new StructNlAttr((short) 1, new StructNlAttr((short) 1, new StructNlAttr((short) 1, (InetAddress) inet4Address), new StructNlAttr((short) 2, (InetAddress) inet4Address2)), new StructNlAttr((short) 2, new StructNlAttr((short) 1, (byte) i), new StructNlAttr((short) 2, (short) i2, ByteOrder.BIG_ENDIAN), new StructNlAttr((short) 3, (short) i3, ByteOrder.BIG_ENDIAN)));
        StructNlAttr structNlAttr2 = new StructNlAttr((short) 7, i4, ByteOrder.BIG_ENDIAN);
        int alignedLength = structNlAttr.getAlignedLength() + structNlAttr2.getAlignedLength() + 20;
        byte[] bArr = new byte[alignedLength];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.nativeOrder());
        ConntrackMessage conntrackMessage = new ConntrackMessage();
        StructNlMsgHdr structNlMsgHdr = conntrackMessage.mHeader;
        structNlMsgHdr.nlmsg_len = alignedLength;
        structNlMsgHdr.nlmsg_type = (short) 256;
        structNlMsgHdr.nlmsg_flags = (short) 261;
        structNlMsgHdr.nlmsg_seq = 1;
        conntrackMessage.pack(byteBufferWrap);
        structNlAttr.pack(byteBufferWrap);
        structNlAttr2.pack(byteBufferWrap);
        return bArr;
    }

    private ConntrackMessage() {
        super(new StructNlMsgHdr());
        this.mNfGenMsg = new StructNfGenMsg((byte) OsConstants.AF_INET);
    }

    public void pack(ByteBuffer byteBuffer) {
        this.mHeader.pack(byteBuffer);
        this.mNfGenMsg.pack(byteBuffer);
    }
}
