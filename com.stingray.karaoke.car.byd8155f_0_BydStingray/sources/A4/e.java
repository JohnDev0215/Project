package A4;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class e extends FilterInputStream {
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f416m;

    public e(InputStream inputStream, long j3) {
        super(inputStream);
        this.l = j3;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized int available() {
        return (int) Math.max(this.l - ((long) this.f416m), ((FilterInputStream) this).in.available());
    }

    public final void b(int i7) throws IOException {
        if (i7 >= 0) {
            this.f416m += i7;
            return;
        }
        long j3 = this.f416m;
        long j10 = this.l;
        if (j10 - j3 <= 0) {
            return;
        }
        throw new IOException("Failed to read all expected data, expected: " + j10 + ", but read: " + this.f416m);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized int read() {
        int i7;
        i7 = super.read();
        b(i7 >= 0 ? 1 : -1);
        return i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized int read(byte[] bArr, int i7, int i10) {
        int i11;
        i11 = super.read(bArr, i7, i10);
        b(i11);
        return i11;
    }
}
