package org.tukaani.xz.simple;

/* JADX INFO: loaded from: classes2.dex */
public final class PowerPC implements SimpleFilter {
    private final boolean isEncoder;
    private int pos;

    public PowerPC(boolean isEncoder, int startPos) {
        this.isEncoder = isEncoder;
        this.pos = startPos;
    }

    @Override // org.tukaani.xz.simple.SimpleFilter
    public int code(byte[] buf, int off, int len) {
        int dest;
        int end = (off + len) - 4;
        int i = off;
        while (i <= end) {
            if ((buf[i] & 252) == 72 && (buf[i + 3] & 3) == 1) {
                int src = ((buf[i] & 3) << 24) | ((buf[i + 1] & 255) << 16) | ((buf[i + 2] & 255) << 8) | (buf[i + 3] & 252);
                if (this.isEncoder) {
                    dest = ((this.pos + i) - off) + src;
                } else {
                    int dest2 = this.pos;
                    dest = src - ((dest2 + i) - off);
                }
                buf[i] = (byte) (72 | ((dest >>> 24) & 3));
                buf[i + 1] = (byte) (dest >>> 16);
                buf[i + 2] = (byte) (dest >>> 8);
                buf[i + 3] = (byte) ((buf[i + 3] & 3) | dest);
            }
            i += 4;
        }
        int i2 = i - off;
        this.pos += i2;
        return i2;
    }
}
