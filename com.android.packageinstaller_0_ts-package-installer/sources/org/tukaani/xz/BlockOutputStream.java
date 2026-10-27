package org.tukaani.xz;

import java.io.IOException;
import java.io.OutputStream;
import org.tukaani.xz.check.Check;

/* JADX INFO: loaded from: classes2.dex */
class BlockOutputStream extends FinishableOutputStream {
    private final Check check;
    private final long compressedSizeLimit;
    private FinishableOutputStream filterChain;
    private final int headerSize;
    private final OutputStream out;
    private final CountingOutputStream outCounted;
    private long uncompressedSize = 0;
    private final byte[] tempBuf = new byte[1];

    /* JADX WARN: Incorrect condition in loop: B:10:0x005c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public BlockOutputStream(java.io.OutputStream r7, org.tukaani.xz.FilterEncoder[] r8, org.tukaani.xz.check.Check r9, org.tukaani.xz.ArrayCache r10) throws java.io.IOException {
        /*
            r6 = this;
            r6.<init>()
            r0 = 0
            r6.uncompressedSize = r0
            r0 = 1
            byte[] r1 = new byte[r0]
            r6.tempBuf = r1
            r6.out = r7
            r6.check = r9
            org.tukaani.xz.CountingOutputStream r1 = new org.tukaani.xz.CountingOutputStream
            r1.<init>(r7)
            r6.outCounted = r1
            r6.filterChain = r1
            int r1 = r8.length
            int r1 = r1 - r0
        L1b:
            if (r1 < 0) goto L2a
            r2 = r8[r1]
            org.tukaani.xz.FinishableOutputStream r3 = r6.filterChain
            org.tukaani.xz.FinishableOutputStream r2 = r2.getOutputStream(r3, r10)
            r6.filterChain = r2
            int r1 = r1 + (-1)
            goto L1b
        L2a:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            r2 = 0
            r1.write(r2)
            int r3 = r8.length
            int r3 = r3 - r0
            r1.write(r3)
            r0 = 0
        L39:
            int r3 = r8.length
            if (r0 >= r3) goto L56
            r3 = r8[r0]
            long r3 = r3.getFilterID()
            org.tukaani.xz.common.EncoderUtil.encodeVLI(r1, r3)
            r3 = r8[r0]
            byte[] r3 = r3.getFilterProps()
            int r4 = r3.length
            long r4 = (long) r4
            org.tukaani.xz.common.EncoderUtil.encodeVLI(r1, r4)
            r1.write(r3)
            int r0 = r0 + 1
            goto L39
        L56:
            int r0 = r1.size()
            r0 = r0 & 3
            if (r0 == 0) goto L62
            r1.write(r2)
            goto L56
        L62:
            byte[] r0 = r1.toByteArray()
            int r3 = r0.length
            int r3 = r3 + 4
            r6.headerSize = r3
            r4 = 1024(0x400, float:1.435E-42)
            if (r3 > r4) goto L8d
            int r3 = r0.length
            int r3 = r3 / 4
            byte r3 = (byte) r3
            r0[r2] = r3
            r7.write(r0)
            org.tukaani.xz.common.EncoderUtil.writeCRC32(r7, r0)
            r2 = 9223372036854775804(0x7ffffffffffffffc, double:NaN)
            int r4 = r6.headerSize
            long r4 = (long) r4
            long r2 = r2 - r4
            int r4 = r9.getSize()
            long r4 = (long) r4
            long r2 = r2 - r4
            r6.compressedSizeLimit = r2
            return
        L8d:
            org.tukaani.xz.UnsupportedOptionsException r2 = new org.tukaani.xz.UnsupportedOptionsException
            r2.<init>()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.BlockOutputStream.<init>(java.io.OutputStream, org.tukaani.xz.FilterEncoder[], org.tukaani.xz.check.Check, org.tukaani.xz.ArrayCache):void");
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        byte[] bArr = this.tempBuf;
        bArr[0] = (byte) b;
        write(bArr, 0, 1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] buf, int off, int len) throws IOException {
        this.filterChain.write(buf, off, len);
        this.check.update(buf, off, len);
        this.uncompressedSize += (long) len;
        validate();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.filterChain.flush();
        validate();
    }

    @Override // org.tukaani.xz.FinishableOutputStream
    public void finish() throws IOException {
        this.filterChain.finish();
        validate();
        for (long i = this.outCounted.getSize(); (3 & i) != 0; i++) {
            this.out.write(0);
        }
        this.out.write(this.check.finish());
    }

    private void validate() throws IOException {
        long compressedSize = this.outCounted.getSize();
        if (compressedSize < 0 || compressedSize > this.compressedSizeLimit || this.uncompressedSize < 0) {
            throw new XZIOException("XZ Stream has grown too big");
        }
    }

    public long getUnpaddedSize() {
        return ((long) this.headerSize) + this.outCounted.getSize() + ((long) this.check.getSize());
    }

    public long getUncompressedSize() {
        return this.uncompressedSize;
    }
}
