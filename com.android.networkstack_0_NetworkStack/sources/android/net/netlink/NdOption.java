package android.net.netlink;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class NdOption {
    public static final NdOption UNKNOWN = new NdOption((byte) 0, 0);
    public final int length;
    public final byte type;

    NdOption(byte b, int i) {
        this.type = b;
        this.length = i;
    }

    public static NdOption parse(ByteBuffer byteBuffer) {
        if (byteBuffer == null || byteBuffer.remaining() < 2) {
            return null;
        }
        byte b = byteBuffer.get(byteBuffer.position());
        int unsignedInt = Byte.toUnsignedInt(byteBuffer.get(byteBuffer.position() + 1));
        if (unsignedInt == 0) {
            return null;
        }
        if (b == 38) {
            return StructNdOptPref64.parse(byteBuffer);
        }
        byteBuffer.position(Math.min(byteBuffer.limit(), byteBuffer.position() + (unsignedInt * 8)));
        return UNKNOWN;
    }

    public String toString() {
        return String.format("NdOption(%d, %d)", Integer.valueOf(Byte.toUnsignedInt(this.type)), Integer.valueOf(this.length));
    }
}
