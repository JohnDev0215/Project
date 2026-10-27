package org.tukaani.xz.simple;

/* JADX INFO: loaded from: classes2.dex */
public final class ARMThumb implements SimpleFilter {
    private final boolean isEncoder;
    private int pos;

    public ARMThumb(boolean isEncoder, int startPos) {
        this.isEncoder = isEncoder;
        this.pos = startPos + 4;
    }

    @Override // org.tukaani.xz.simple.SimpleFilter
    public int code(byte[] buf, int off, int len) {
        int dest;
        int end = (off + len) - 4;
        int i = off;
        while (i <= end) {
            if ((buf[i + 1] & 248) == 240 && (buf[i + 3] & 248) == 248) {
                int src = (((((buf[i + 1] & 7) << 19) | ((buf[i] & 255) << 11)) | ((buf[i + 3] & 7) << 8)) | (buf[i + 2] & 255)) << 1;
                if (this.isEncoder) {
                    dest = ((this.pos + i) - off) + src;
                } else {
                    int dest2 = this.pos;
                    dest = src - ((dest2 + i) - off);
                }
                int dest3 = dest >>> 1;
                buf[i + 1] = (byte) (240 | ((dest3 >>> 19) & 7));
                buf[i] = (byte) (dest3 >>> 11);
                buf[i + 3] = (byte) (248 | ((dest3 >>> 8) & 7));
                buf[i + 2] = (byte) dest3;
                i += 2;
            }
            i += 2;
        }
        int i2 = i - off;
        this.pos += i2;
        return i2;
    }
}
