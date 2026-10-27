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

    public static short makeNestedType(short s) {
        return (short) (s | Short.MIN_VALUE);
    }

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

    public StructNlAttr(short s, byte b) {
        this.nla_len = (short) 4;
        this.mByteOrder = ByteOrder.nativeOrder();
        this.nla_type = s;
        setValue(new byte[1]);
        this.nla_value[0] = b;
    }

    public StructNlAttr(short s, short s2, ByteOrder byteOrder) {
        this(byteOrder);
        this.nla_type = s;
        setValue(new byte[2]);
        getValueAsByteBuffer().putShort(s2);
    }

    public StructNlAttr(short s, int i, ByteOrder byteOrder) {
        this(byteOrder);
        this.nla_type = s;
        setValue(new byte[4]);
        getValueAsByteBuffer().putInt(i);
    }

    public StructNlAttr(short s, InetAddress inetAddress) {
        this.nla_len = (short) 4;
        this.mByteOrder = ByteOrder.nativeOrder();
        this.nla_type = s;
        setValue(inetAddress.getAddress());
    }

    public StructNlAttr(short s, StructNlAttr... structNlAttrArr) {
        this();
        this.nla_type = makeNestedType(s);
        int alignedLength = 0;
        for (StructNlAttr structNlAttr : structNlAttrArr) {
            alignedLength += structNlAttr.getAlignedLength();
        }
        setValue(new byte[alignedLength]);
        ByteBuffer valueAsByteBuffer = getValueAsByteBuffer();
        for (StructNlAttr structNlAttr2 : structNlAttrArr) {
            structNlAttr2.pack(valueAsByteBuffer);
        }
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

    private void setValue(byte[] bArr) {
        this.nla_value = bArr;
        this.nla_len = (short) ((bArr != null ? bArr.length : 0) + 4);
    }

    public String toString() {
        return "StructNlAttr{ nla_len{" + ((int) this.nla_len) + "}, nla_type{" + ((int) this.nla_type) + "}, nla_value{" + NetlinkConstants.hexify(this.nla_value) + "}, }";
    }
}
