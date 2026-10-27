package com.bumptech.glide.j;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: StrictLineReader.java */
/* JADX INFO: loaded from: classes.dex */
class b implements Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InputStream f2887b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Charset f2888c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private byte[] f2889d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2890e;
    private int f;

    /* JADX INFO: compiled from: StrictLineReader.java */
    class a extends ByteArrayOutputStream {
        a(int i) {
            super(i);
        }

        @Override // java.io.ByteArrayOutputStream
        public String toString() {
            int i = ((ByteArrayOutputStream) this).count;
            if (i > 0 && ((ByteArrayOutputStream) this).buf[i - 1] == 13) {
                i--;
            }
            try {
                return new String(((ByteArrayOutputStream) this).buf, 0, i, b.this.f2888c.name());
            } catch (UnsupportedEncodingException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    public b(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    private void k() throws IOException {
        InputStream inputStream = this.f2887b;
        byte[] bArr = this.f2889d;
        int i = inputStream.read(bArr, 0, bArr.length);
        if (i == -1) {
            throw new EOFException();
        }
        this.f2890e = 0;
        this.f = i;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.f2887b) {
            if (this.f2889d != null) {
                this.f2889d = null;
                this.f2887b.close();
            }
        }
    }

    public boolean l() {
        return this.f == -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m() throws java.io.IOException {
        /*
            r7 = this;
            java.io.InputStream r0 = r7.f2887b
            monitor-enter(r0)
            byte[] r1 = r7.f2889d     // Catch: java.lang.Throwable -> L8b
            if (r1 == 0) goto L83
            int r1 = r7.f2890e     // Catch: java.lang.Throwable -> L8b
            int r2 = r7.f     // Catch: java.lang.Throwable -> L8b
            if (r1 < r2) goto L10
            r7.k()     // Catch: java.lang.Throwable -> L8b
        L10:
            int r1 = r7.f2890e     // Catch: java.lang.Throwable -> L8b
        L12:
            int r2 = r7.f     // Catch: java.lang.Throwable -> L8b
            r3 = 10
            if (r1 == r2) goto L45
            byte[] r2 = r7.f2889d     // Catch: java.lang.Throwable -> L8b
            r4 = r2[r1]     // Catch: java.lang.Throwable -> L8b
            if (r4 != r3) goto L42
            int r3 = r7.f2890e     // Catch: java.lang.Throwable -> L8b
            if (r1 == r3) goto L2b
            int r3 = r1 + (-1)
            r2 = r2[r3]     // Catch: java.lang.Throwable -> L8b
            r4 = 13
            if (r2 != r4) goto L2b
            goto L2c
        L2b:
            r3 = r1
        L2c:
            java.lang.String r2 = new java.lang.String     // Catch: java.lang.Throwable -> L8b
            byte[] r4 = r7.f2889d     // Catch: java.lang.Throwable -> L8b
            int r5 = r7.f2890e     // Catch: java.lang.Throwable -> L8b
            int r3 = r3 - r5
            java.nio.charset.Charset r6 = r7.f2888c     // Catch: java.lang.Throwable -> L8b
            java.lang.String r6 = r6.name()     // Catch: java.lang.Throwable -> L8b
            r2.<init>(r4, r5, r3, r6)     // Catch: java.lang.Throwable -> L8b
            int r1 = r1 + 1
            r7.f2890e = r1     // Catch: java.lang.Throwable -> L8b
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8b
            return r2
        L42:
            int r1 = r1 + 1
            goto L12
        L45:
            com.bumptech.glide.j.b$a r1 = new com.bumptech.glide.j.b$a     // Catch: java.lang.Throwable -> L8b
            int r2 = r7.f     // Catch: java.lang.Throwable -> L8b
            int r4 = r7.f2890e     // Catch: java.lang.Throwable -> L8b
            int r2 = r2 - r4
            int r2 = r2 + 80
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L8b
        L51:
            byte[] r2 = r7.f2889d     // Catch: java.lang.Throwable -> L8b
            int r4 = r7.f2890e     // Catch: java.lang.Throwable -> L8b
            int r5 = r7.f     // Catch: java.lang.Throwable -> L8b
            int r5 = r5 - r4
            r1.write(r2, r4, r5)     // Catch: java.lang.Throwable -> L8b
            r2 = -1
            r7.f = r2     // Catch: java.lang.Throwable -> L8b
            r7.k()     // Catch: java.lang.Throwable -> L8b
            int r2 = r7.f2890e     // Catch: java.lang.Throwable -> L8b
        L63:
            int r4 = r7.f     // Catch: java.lang.Throwable -> L8b
            if (r2 == r4) goto L51
            byte[] r4 = r7.f2889d     // Catch: java.lang.Throwable -> L8b
            r5 = r4[r2]     // Catch: java.lang.Throwable -> L8b
            if (r5 != r3) goto L80
            int r3 = r7.f2890e     // Catch: java.lang.Throwable -> L8b
            if (r2 == r3) goto L76
            int r5 = r2 - r3
            r1.write(r4, r3, r5)     // Catch: java.lang.Throwable -> L8b
        L76:
            int r2 = r2 + 1
            r7.f2890e = r2     // Catch: java.lang.Throwable -> L8b
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L8b
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8b
            return r1
        L80:
            int r2 = r2 + 1
            goto L63
        L83:
            java.io.IOException r1 = new java.io.IOException     // Catch: java.lang.Throwable -> L8b
            java.lang.String r2 = "LineReader is closed"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L8b
            throw r1     // Catch: java.lang.Throwable -> L8b
        L8b:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L8b
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.j.b.m():java.lang.String");
    }

    public b(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw null;
        }
        if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        if (!charset.equals(c.f2892a)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.f2887b = inputStream;
        this.f2888c = charset;
        this.f2889d = new byte[i];
    }
}
