package android.net.netlink;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public class StructNlAttr {
    private ByteOrder mByteOrder;
    public short nla_len;
    public short nla_type;
    public byte[] nla_value;

    public static StructNlAttr peek(ByteBuffer byteBuffer) {
        if (byteBuffer == null || byteBuffer.remaining() < 4) {
            return null;
        }
        int iPosition = byteBuffer.position();
        StructNlAttr structNlAttr = new StructNlAttr(byteBuffer.order());
        ByteOrder byteOrderOrder = byteBuffer.order();
        byteBuffer.order(ByteOrder.nativeOrder());
        try {
            structNlAttr.nla_len = byteBuffer.getShort();
            structNlAttr.nla_type = byteBuffer.getShort();
            byteBuffer.order(byteOrderOrder);
            byteBuffer.position(iPosition);
            if (structNlAttr.nla_len < 4) {
                return null;
            }
            return structNlAttr;
        } catch (Throwable th) {
            byteBuffer.order(byteOrderOrder);
            throw th;
        }
    }

    public static StructNlAttr parse(ByteBuffer byteBuffer) {
        StructNlAttr structNlAttrPeek = peek(byteBuffer);
        if (structNlAttrPeek == null || byteBuffer.remaining() < structNlAttrPeek.getAlignedLength()) {
            return null;
        }
        int iPosition = byteBuffer.position();
        byteBuffer.position(iPosition + 4);
        int i = (structNlAttrPeek.nla_len & 65535) - 4;
        if (i > 0) {
            byte[] bArr = new byte[i];
            structNlAttrPeek.nla_value = bArr;
            byteBuffer.get(bArr, 0, i);
            byteBuffer.position(iPosition + structNlAttrPeek.getAlignedLength());
        }
        return structNlAttrPeek;
    }

    public StructNlAttr() {
        this.nla_len = (short) 4;
        this.mByteOrder = ByteOrder.nativeOrder();
    }

    public StructNlAttr(ByteOrder byteOrder) {
        this.nla_len = (short) 4;
        this.mByteOrder = ByteOrder.nativeOrder();
        this.mByteOrder = byteOrder;
    }

    public int getAlignedLength() {
        return NetlinkConstants.alignedLengthOf(this.nla_len);
    }

    public ByteBuffer getValueAsByteBuffer() {
        byte[] bArr = this.nla_value;
        if (bArr == null) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(this.mByteOrder);
        return byteBufferWrap;
    }

    public int getValueAsInt(int i) {
        ByteBuffer valueAsByteBuffer = getValueAsByteBuffer();
        return (valueAsByteBuffer == null || valueAsByteBuffer.remaining() != 4) ? i : getValueAsByteBuffer().getInt();
    }

    public InetAddress getValueAsInetAddress() {
        byte[] bArr = this.nla_value;
        if (bArr == null) {
            return null;
        }
        try {
            return InetAddress.getByAddress(bArr);
        } catch (UnknownHostException unused) {
            return null;
        }
    }

    public void pack(ByteBuffer byteBuffer) {
        ByteOrder byteOrderOrder = byteBuffer.order();
        int iPosition = byteBuffer.position();
        byteBuffer.order(ByteOrder.nativeOrder());
        try {
            byteBuffer.putShort(this.nla_len);
            byteBuffer.putShort(this.nla_type);
            if (this.nla_value != null) {
                byteBuffer.put(this.nla_value);
            }
            byteBuffer.order(byteOrderOrder);
            byteBuffer.position(iPosition + getAlignedLength());
        } catch (Throwable th) {
            byteBuffer.order(byteOrderOrder);
            throw th;
        }
    }

    public String toString() {
        return "StructNlAttr{ nla_len{" + ((int) this.nla_len) + "}, nla_type{" + ((int) this.nla_type) + "}, nla_value{" + NetlinkConstants.hexify(this.nla_value) + "}, }";
    }
}
