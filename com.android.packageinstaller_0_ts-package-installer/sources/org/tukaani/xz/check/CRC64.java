package org.tukaani.xz.check;

/* JADX INFO: loaded from: classes2.dex */
public class CRC64 extends Check {
    private static final long[] crcTable = new long[256];
    private static final long poly = -3932672073523589310L;
    private long crc = -1;

    static {
        for (int b = 0; b < crcTable.length; b++) {
            long r = b;
            for (int i = 0; i < 8; i++) {
                if ((r & 1) == 1) {
                    r = (r >>> 1) ^ poly;
                } else {
                    r >>>= 1;
                }
            }
            crcTable[b] = r;
        }
    }

    public CRC64() {
        this.size = 8;
        this.name = "CRC64";
    }

    @Override // org.tukaani.xz.check.Check
    public void update(byte[] buf, int off, int len) {
        int end = off + len;
        while (off < end) {
            long[] jArr = crcTable;
            int off2 = off + 1;
            int off3 = buf[off];
            long j = this.crc;
            this.crc = (j >>> 8) ^ jArr[(off3 ^ ((int) j)) & 255];
            off = off2;
        }
    }

    @Override // org.tukaani.xz.check.Check
    public byte[] finish() {
        long value = ~this.crc;
        this.crc = -1L;
        byte[] buf = new byte[8];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) (value >> (i * 8));
        }
        return buf;
    }
}
