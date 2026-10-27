package k1;

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

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final InputStream f7226e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Charset f7227g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private byte[] f7228h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f7229i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f7230j;

    /* JADX INFO: compiled from: StrictLineReader.java */
    class a extends ByteArrayOutputStream {
        a(int i7) {
            super(i7);
        }

        @Override // java.io.ByteArrayOutputStream
        public String toString() {
            int i7 = ((ByteArrayOutputStream) this).count;
            if (i7 > 0 && ((ByteArrayOutputStream) this).buf[i7 - 1] == 13) {
                i7--;
            }
            try {
                return new String(((ByteArrayOutputStream) this).buf, 0, i7, b.this.f7227g.name());
            } catch (UnsupportedEncodingException e7) {
                throw new AssertionError(e7);
            }
        }
    }

    public b(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    private void z() throws IOException {
        InputStream inputStream = this.f7226e;
        byte[] bArr = this.f7228h;
        int i7 = inputStream.read(bArr, 0, bArr.length);
        if (i7 == -1) {
            throw new EOFException();
        }
        this.f7229i = 0;
        this.f7230j = i7;
    }

    public boolean A() {
        return this.f7230j == -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String G() {
        /*
            r7 = this;
            java.io.InputStream r0 = r7.f7226e
            monitor-enter(r0)
            byte[] r1 = r7.f7228h     // Catch: java.lang.Throwable -> L87
            if (r1 == 0) goto L7f
            int r1 = r7.f7229i     // Catch: java.lang.Throwable -> L87
            int r2 = r7.f7230j     // Catch: java.lang.Throwable -> L87
            if (r1 < r2) goto L10
            r7.z()     // Catch: java.lang.Throwable -> L87
        L10:
            int r1 = r7.f7229i     // Catch: java.lang.Throwable -> L87
        L12:
            int r2 = r7.f7230j     // Catch: java.lang.Throwable -> L87
            r3 = 10
            if (r1 == r2) goto L41
            byte[] r2 = r7.f7228h     // Catch: java.lang.Throwable -> L87
            r4 = r2[r1]     // Catch: java.lang.Throwable -> L87
            if (r4 != r3) goto L3e
            int r3 = r7.f7229i     // Catch: java.lang.Throwable -> L87
            if (r1 == r3) goto L2b
            int r4 = r1 + (-1)
            r5 = r2[r4]     // Catch: java.lang.Throwable -> L87
            r6 = 13
            if (r5 != r6) goto L2b
            goto L2c
        L2b:
            r4 = r1
        L2c:
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Throwable -> L87
            int r4 = r4 - r3
            java.nio.charset.Charset r6 = r7.f7227g     // Catch: java.lang.Throwable -> L87
            java.lang.String r6 = r6.name()     // Catch: java.lang.Throwable -> L87
            r5.<init>(r2, r3, r4, r6)     // Catch: java.lang.Throwable -> L87
            int r1 = r1 + 1
            r7.f7229i = r1     // Catch: java.lang.Throwable -> L87
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L87
            return r5
        L3e:
            int r1 = r1 + 1
            goto L12
        L41:
            k1.b$a r1 = new k1.b$a     // Catch: java.lang.Throwable -> L87
            int r2 = r7.f7230j     // Catch: java.lang.Throwable -> L87
            int r4 = r7.f7229i     // Catch: java.lang.Throwable -> L87
            int r2 = r2 - r4
            int r2 = r2 + 80
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L87
        L4d:
            byte[] r2 = r7.f7228h     // Catch: java.lang.Throwable -> L87
            int r4 = r7.f7229i     // Catch: java.lang.Throwable -> L87
            int r5 = r7.f7230j     // Catch: java.lang.Throwable -> L87
            int r5 = r5 - r4
            r1.write(r2, r4, r5)     // Catch: java.lang.Throwable -> L87
            r2 = -1
            r7.f7230j = r2     // Catch: java.lang.Throwable -> L87
            r7.z()     // Catch: java.lang.Throwable -> L87
            int r2 = r7.f7229i     // Catch: java.lang.Throwable -> L87
        L5f:
            int r4 = r7.f7230j     // Catch: java.lang.Throwable -> L87
            if (r2 == r4) goto L4d
            byte[] r4 = r7.f7228h     // Catch: java.lang.Throwable -> L87
            r5 = r4[r2]     // Catch: java.lang.Throwable -> L87
            if (r5 != r3) goto L7c
            int r3 = r7.f7229i     // Catch: java.lang.Throwable -> L87
            if (r2 == r3) goto L72
            int r5 = r2 - r3
            r1.write(r4, r3, r5)     // Catch: java.lang.Throwable -> L87
        L72:
            int r2 = r2 + 1
            r7.f7229i = r2     // Catch: java.lang.Throwable -> L87
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L87
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L87
            return r1
        L7c:
            int r2 = r2 + 1
            goto L5f
        L7f:
            java.io.IOException r1 = new java.io.IOException     // Catch: java.lang.Throwable -> L87
            java.lang.String r2 = "LineReader is closed"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L87
            throw r1     // Catch: java.lang.Throwable -> L87
        L87:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L87
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: k1.b.G():java.lang.String");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.f7226e) {
            if (this.f7228h != null) {
                this.f7228h = null;
                this.f7226e.close();
            }
        }
    }

    public b(InputStream inputStream, int i7, Charset charset) {
        if (inputStream == null || charset == null) {
            throw null;
        }
        if (i7 < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        if (!charset.equals(c.f7232a)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.f7226e = inputStream;
        this.f7227g = charset;
        this.f7228h = new byte[i7];
    }
}
