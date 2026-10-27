package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class XZInputStream extends InputStream {
    private final ArrayCache arrayCache;
    private boolean endReached;
    private IOException exception;
    private InputStream in;
    private final int memoryLimit;
    private final byte[] tempBuf;
    private final boolean verifyCheck;
    private SingleXZInputStream xzIn;

    public XZInputStream(InputStream in) throws IOException {
        this(in, -1);
    }

    public XZInputStream(InputStream in, ArrayCache arrayCache) throws IOException {
        this(in, -1, arrayCache);
    }

    public XZInputStream(InputStream in, int memoryLimit) throws IOException {
        this(in, memoryLimit, true);
    }

    public XZInputStream(InputStream in, int memoryLimit, ArrayCache arrayCache) throws IOException {
        this(in, memoryLimit, true, arrayCache);
    }

    public XZInputStream(InputStream in, int memoryLimit, boolean verifyCheck) throws IOException {
        this(in, memoryLimit, verifyCheck, ArrayCache.getDefaultCache());
    }

    public XZInputStream(InputStream in, int memoryLimit, boolean verifyCheck, ArrayCache arrayCache) throws IOException {
        this.endReached = false;
        this.exception = null;
        this.tempBuf = new byte[1];
        this.arrayCache = arrayCache;
        this.in = in;
        this.memoryLimit = memoryLimit;
        this.verifyCheck = verifyCheck;
        this.xzIn = new SingleXZInputStream(in, memoryLimit, verifyCheck, arrayCache);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.tempBuf, 0, 1) == -1) {
            return -1;
        }
        return this.tempBuf[0] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] buf, int off, int len) throws IOException {
        if (off < 0 || len < 0 || off + len < 0 || off + len > buf.length) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return 0;
        }
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.endReached) {
            return -1;
        }
        int size = 0;
        while (len > 0) {
            try {
                if (this.xzIn == null) {
                    prepareNextStream();
                    if (this.endReached) {
                        if (size == 0) {
                            return -1;
                        }
                        return size;
                    }
                }
                int ret = this.xzIn.read(buf, off, len);
                if (ret > 0) {
                    size += ret;
                    off += ret;
                    len -= ret;
                } else if (ret == -1) {
                    this.xzIn = null;
                }
            } catch (IOException e) {
                this.exception = e;
                if (size == 0) {
                    throw e;
                }
            }
        }
        return size;
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x0012 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void prepareNextStream() throws java.io.IOException {
        /*
            r9 = this;
            java.io.DataInputStream r0 = new java.io.DataInputStream
            java.io.InputStream r1 = r9.in
            r0.<init>(r1)
            r1 = 12
            byte[] r1 = new byte[r1]
        Lb:
            r2 = 0
            r3 = 1
            int r4 = r0.read(r1, r2, r3)
            r5 = -1
            if (r4 != r5) goto L17
            r9.endReached = r3
            return
        L17:
            r5 = 3
            r0.readFully(r1, r3, r5)
            r2 = r1[r2]
            if (r2 != 0) goto L2c
            r2 = r1[r3]
            if (r2 != 0) goto L2c
            r2 = 2
            r2 = r1[r2]
            if (r2 != 0) goto L2c
            r2 = r1[r5]
            if (r2 == 0) goto Lb
        L2c:
            r2 = 4
            r3 = 8
            r0.readFully(r1, r2, r3)
            org.tukaani.xz.SingleXZInputStream r8 = new org.tukaani.xz.SingleXZInputStream     // Catch: org.tukaani.xz.XZFormatException -> L45
            java.io.InputStream r3 = r9.in     // Catch: org.tukaani.xz.XZFormatException -> L45
            int r4 = r9.memoryLimit     // Catch: org.tukaani.xz.XZFormatException -> L45
            boolean r5 = r9.verifyCheck     // Catch: org.tukaani.xz.XZFormatException -> L45
            org.tukaani.xz.ArrayCache r7 = r9.arrayCache     // Catch: org.tukaani.xz.XZFormatException -> L45
            r2 = r8
            r6 = r1
            r2.<init>(r3, r4, r5, r6, r7)     // Catch: org.tukaani.xz.XZFormatException -> L45
            r9.xzIn = r8     // Catch: org.tukaani.xz.XZFormatException -> L45
            return
        L45:
            r2 = move-exception
            org.tukaani.xz.CorruptedInputException r3 = new org.tukaani.xz.CorruptedInputException
            java.lang.String r4 = "Garbage after a valid XZ Stream"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.tukaani.xz.XZInputStream.prepareNextStream():void");
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.in == null) {
            throw new XZIOException("Stream closed");
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        SingleXZInputStream singleXZInputStream = this.xzIn;
        if (singleXZInputStream == null) {
            return 0;
        }
        return singleXZInputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        close(true);
    }

    public void close(boolean closeInput) throws IOException {
        if (this.in != null) {
            SingleXZInputStream singleXZInputStream = this.xzIn;
            if (singleXZInputStream != null) {
                singleXZInputStream.close(false);
                this.xzIn = null;
            }
            if (closeInput) {
                try {
                    this.in.close();
                } finally {
                    this.in = null;
                }
            }
        }
    }
}
