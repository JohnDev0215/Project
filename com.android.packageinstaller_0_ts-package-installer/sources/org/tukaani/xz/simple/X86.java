package org.tukaani.xz.simple;

/* JADX INFO: loaded from: classes2.dex */
public final class X86 implements SimpleFilter {
    private static final boolean[] MASK_TO_ALLOWED_STATUS = {true, true, true, false, true, false, false, false};
    private static final int[] MASK_TO_BIT_NUMBER = {0, 1, 2, 2, 3, 3, 3, 3};
    private final boolean isEncoder;
    private int pos;
    private int prevMask = 0;

    private static boolean test86MSByte(byte b) {
        int i = b & 255;
        return i == 0 || i == 255;
    }

    public X86(boolean isEncoder, int startPos) {
        this.isEncoder = isEncoder;
        this.pos = startPos + 5;
    }

    @Override // org.tukaani.xz.simple.SimpleFilter
    public int code(byte[] buf, int off, int len) {
        int dest;
        int prevPos = off - 1;
        int end = (off + len) - 5;
        int i = off;
        while (true) {
            if (i > end) {
                break;
            }
            if ((buf[i] & 254) == 232) {
                int prevPos2 = i - prevPos;
                if ((prevPos2 & (-4)) != 0) {
                    this.prevMask = 0;
                } else {
                    int i2 = (this.prevMask << (prevPos2 - 1)) & 7;
                    this.prevMask = i2;
                    if (i2 != 0 && (!MASK_TO_ALLOWED_STATUS[i2] || test86MSByte(buf[(i + 4) - MASK_TO_BIT_NUMBER[i2]]))) {
                        prevPos = i;
                        this.prevMask = (this.prevMask << 1) | 1;
                    }
                }
                prevPos = i;
                if (test86MSByte(buf[i + 4])) {
                    int src = (buf[i + 1] & 255) | ((buf[i + 2] & 255) << 8) | ((buf[i + 3] & 255) << 16) | ((buf[i + 4] & 255) << 24);
                    while (true) {
                        if (this.isEncoder) {
                            dest = ((this.pos + i) - off) + src;
                        } else {
                            int dest2 = this.pos;
                            dest = src - ((dest2 + i) - off);
                        }
                        int i3 = this.prevMask;
                        if (i3 == 0) {
                            break;
                        }
                        int index = MASK_TO_BIT_NUMBER[i3] * 8;
                        if (!test86MSByte((byte) (dest >>> (24 - index)))) {
                            break;
                        }
                        src = dest ^ ((1 << (32 - index)) - 1);
                    }
                    buf[i + 1] = (byte) dest;
                    buf[i + 2] = (byte) (dest >>> 8);
                    buf[i + 3] = (byte) (dest >>> 16);
                    buf[i + 4] = (byte) (~(((dest >>> 24) & 1) - 1));
                    i += 4;
                } else {
                    int src2 = this.prevMask;
                    this.prevMask = (src2 << 1) | 1;
                }
            }
            i++;
        }
        int prevPos3 = i - prevPos;
        this.prevMask = (prevPos3 & (-4)) == 0 ? this.prevMask << (prevPos3 - 1) : 0;
        int i4 = i - off;
        this.pos += i4;
        return i4;
    }
}
