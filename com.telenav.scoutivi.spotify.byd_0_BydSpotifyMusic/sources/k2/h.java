package k2;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: MarkEnforcingInputStream.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends FilterInputStream {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f7254e;

    public h(InputStream inputStream) {
        super(inputStream);
        this.f7254e = Integer.MIN_VALUE;
    }

    private long m(long j7) {
        int i7 = this.f7254e;
        if (i7 == 0) {
            return -1L;
        }
        return (i7 == Integer.MIN_VALUE || j7 <= ((long) i7)) ? j7 : i7;
    }

    private void z(long j7) {
        int i7 = this.f7254e;
        if (i7 == Integer.MIN_VALUE || j7 == -1) {
            return;
        }
        this.f7254e = (int) (((long) i7) - j7);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        int i7 = this.f7254e;
        return i7 == Integer.MIN_VALUE ? super.available() : Math.min(i7, super.available());
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i7) {
        super.mark(i7);
        this.f7254e = i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (m(1L) == -1) {
            return -1;
        }
        int i7 = super.read();
        z(1L);
        return i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() {
        super.reset();
        this.f7254e = Integer.MIN_VALUE;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j7) throws IOException {
        long jM = m(j7);
        if (jM == -1) {
            return 0L;
        }
        long jSkip = super.skip(jM);
        z(jSkip);
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i7, int i8) throws IOException {
        int iM = (int) m(i8);
        if (iM == -1) {
            return -1;
        }
        int i9 = super.read(bArr, i7, iM);
        z(i9);
        return i9;
    }
}
