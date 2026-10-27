package o1;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: ExifOrientationStream.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends FilterInputStream {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final byte[] f8650h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final int f8651i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final int f8652j;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final byte f8653e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f8654g;

    static {
        byte[] bArr = {-1, -31, 0, 28, 69, 120, 105, 102, 0, 0, 77, 77, 0, 0, 0, 0, 0, 8, 0, 1, 1, 18, 0, 2, 0, 0, 0, 1, 0};
        f8650h = bArr;
        int length = bArr.length;
        f8651i = length;
        f8652j = length + 2;
    }

    public g(InputStream inputStream, int i7) {
        super(inputStream);
        if (i7 >= -1 && i7 <= 8) {
            this.f8653e = (byte) i7;
            return;
        }
        throw new IllegalArgumentException("Cannot add invalid orientation: " + i7);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i7) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int i7;
        int i8 = this.f8654g;
        int i9 = (i8 < 2 || i8 > (i7 = f8652j)) ? super.read() : i8 == i7 ? this.f8653e : f8650h[i8 - 2] & 255;
        if (i9 != -1) {
            this.f8654g++;
        }
        return i9;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j7) throws IOException {
        long jSkip = super.skip(j7);
        if (jSkip > 0) {
            this.f8654g = (int) (((long) this.f8654g) + jSkip);
        }
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i7, int i8) throws IOException {
        int i9;
        int i10 = this.f8654g;
        int i11 = f8652j;
        if (i10 > i11) {
            i9 = super.read(bArr, i7, i8);
        } else if (i10 == i11) {
            bArr[i7] = this.f8653e;
            i9 = 1;
        } else if (i10 < 2) {
            i9 = super.read(bArr, i7, 2 - i10);
        } else {
            int iMin = Math.min(i11 - i10, i8);
            System.arraycopy(f8650h, this.f8654g - 2, bArr, i7, iMin);
            i9 = iMin;
        }
        if (i9 > 0) {
            this.f8654g += i9;
        }
        return i9;
    }
}
