package org.tukaani.xz.simple;

/* JADX INFO: loaded from: classes2.dex */
public final class SPARC implements SimpleFilter {
    private final boolean isEncoder;
    private int pos;

    public SPARC(boolean isEncoder, int startPos) {
        this.isEncoder = isEncoder;
        this.pos = startPos;
    }

    @Override // org.tukaani.xz.simple.SimpleFilter
    public int code(byte[] buf, int off, int len) {
        int dest;
        int end = (off + len) - 4;
        int i = off;
        while (i <= end) {
            if ((buf[i] == 64 && (buf[i + 1] & 192) == 0) || (buf[i] == 127 && (buf[i + 1] & 192) == 192)) {
                int src = (((((buf[i] & 255) << 24) | ((buf[i + 1] & 255) << 16)) | ((buf[i + 2] & 255) << 8)) | (buf[i + 3] & 255)) << 2;
                if (this.isEncoder) {
                    dest = ((this.pos + i) - off) + src;
                } else {
                    int dest2 = this.pos;
                    dest = src - ((dest2 + i) - off);
                }
                int dest3 = dest >>> 2;
                int dest4 = (((0 - ((dest3 >>> 22) & 1)) << 22) & 1073741823) | (4194303 & dest3) | 1073741824;
                buf[i] = (byte) (dest4 >>> 24);
                buf[i + 1] = (byte) (dest4 >>> 16);
                buf[i + 2] = (byte) (dest4 >>> 8);
                buf[i + 3] = (byte) dest4;
            }
            i += 4;
        }
        int i2 = i - off;
        this.pos += i2;
        return i2;
    }
}
