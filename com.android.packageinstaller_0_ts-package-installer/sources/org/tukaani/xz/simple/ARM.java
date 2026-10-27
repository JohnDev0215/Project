package org.tukaani.xz.simple;

/* JADX INFO: loaded from: classes2.dex */
public final class ARM implements SimpleFilter {
    private final boolean isEncoder;
    private int pos;

    public ARM(boolean isEncoder, int startPos) {
        this.isEncoder = isEncoder;
        this.pos = startPos + 8;
    }

    @Override // org.tukaani.xz.simple.SimpleFilter
    public int code(byte[] buf, int off, int len) {
        int dest;
        int end = (off + len) - 4;
        int i = off;
        while (i <= end) {
            if ((buf[i + 3] & 255) == 235) {
                int src = ((((buf[i + 2] & 255) << 16) | ((buf[i + 1] & 255) << 8)) | (buf[i] & 255)) << 2;
                if (this.isEncoder) {
                    dest = ((this.pos + i) - off) + src;
                } else {
                    int dest2 = this.pos;
                    dest = src - ((dest2 + i) - off);
                }
                int dest3 = dest >>> 2;
                buf[i + 2] = (byte) (dest3 >>> 16);
                buf[i + 1] = (byte) (dest3 >>> 8);
                buf[i] = (byte) dest3;
            }
            i += 4;
        }
        int i2 = i - off;
        this.pos += i2;
        return i2;
    }
}
