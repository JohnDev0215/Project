package k2;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: ContentLengthInputStream.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends FilterInputStream {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final long f7241e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f7242g;

    private c(InputStream inputStream, long j7) {
        super(inputStream);
        this.f7241e = j7;
    }

    private int m(int i7) throws IOException {
        if (i7 >= 0) {
            this.f7242g += i7;
        } else if (this.f7241e - ((long) this.f7242g) > 0) {
            throw new IOException("Failed to read all expected data, expected: " + this.f7241e + ", but read: " + this.f7242g);
        }
        return i7;
    }

    public static InputStream z(InputStream inputStream, long j7) {
        return new c(inputStream, j7);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() {
        return (int) Math.max(this.f7241e - ((long) this.f7242g), ((FilterInputStream) this).in.available());
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() {
        int i7;
        i7 = super.read();
        m(i7 >= 0 ? 1 : -1);
        return i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i7, int i8) {
        return m(super.read(bArr, i7, i8));
    }
}
