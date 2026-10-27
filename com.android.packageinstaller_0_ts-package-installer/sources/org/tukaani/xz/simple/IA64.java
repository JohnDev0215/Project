package org.tukaani.xz.simple;

import android.support.v4.media.session.PlaybackStateCompat;

/* JADX INFO: loaded from: classes2.dex */
public final class IA64 implements SimpleFilter {
    private static final int[] BRANCH_TABLE = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 6, 6, 0, 0, 7, 7, 4, 4, 0, 0, 4, 4, 0, 0};
    private final boolean isEncoder;
    private int pos;

    public IA64(boolean isEncoder, int startPos) {
        this.isEncoder = isEncoder;
        this.pos = startPos;
    }

    @Override // org.tukaani.xz.simple.SimpleFilter
    public int code(byte[] buf, int off, int len) {
        int i;
        int i2;
        int dest;
        int src = 16;
        int end = (off + len) - 16;
        int i3 = off;
        while (i3 <= end) {
            int instrTemplate = buf[i3] & 31;
            int mask = BRANCH_TABLE[instrTemplate];
            int slot = 0;
            int bitPos = 5;
            while (slot < 3) {
                if (((mask >>> slot) & 1) == 0) {
                    i = src;
                    i2 = i3;
                } else {
                    int bytePos = bitPos >>> 3;
                    int bitRes = bitPos & 7;
                    long instr = 0;
                    for (int j = 0; j < 6; j++) {
                        instr |= (((long) buf[(i3 + bytePos) + j]) & 255) << (j * 8);
                    }
                    long instrNorm = instr >>> bitRes;
                    if (((instrNorm >>> 37) & 15) != 5) {
                        i = src;
                        i2 = i3;
                    } else if (((instrNorm >>> 9) & 7) != 0) {
                        i = src;
                        i2 = i3;
                    } else {
                        int src2 = (int) ((instrNorm >>> 13) & 1048575);
                        i2 = i3;
                        int src3 = (((((int) (instrNorm >>> 36)) & 1) << 20) | src2) << 4;
                        if (this.isEncoder) {
                            dest = ((this.pos + i2) - off) + src3;
                        } else {
                            int dest2 = this.pos;
                            dest = src3 - ((dest2 + i2) - off);
                        }
                        int dest3 = dest >>> 4;
                        i = 16;
                        long instr2 = (instr & ((long) ((1 << bitRes) - 1))) | (((((((long) dest3) & 1048575) << 13) | ((-77309403137L) & instrNorm)) | ((((long) dest3) & PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) << 16)) << bitRes);
                        for (int j2 = 0; j2 < 6; j2++) {
                            buf[i2 + bytePos + j2] = (byte) (instr2 >>> (j2 * 8));
                        }
                    }
                }
                slot++;
                bitPos += 41;
                src = i;
                i3 = i2;
            }
            i3 += 16;
        }
        int i4 = i3 - off;
        this.pos += i4;
        return i4;
    }
}
