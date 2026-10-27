package A4;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class n extends FilterInputStream {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f428m;

    public n(f fVar) {
        super(fVar);
        this.f428m = Integer.MIN_VALUE;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int available() {
        switch (this.l) {
            case 0:
                int i7 = this.f428m;
                return i7 == Integer.MIN_VALUE ? super.available() : Math.min(i7, super.available());
            default:
                return Math.min(super.available(), this.f428m);
        }
    }

    public long b(long j3) {
        int i7 = this.f428m;
        if (i7 == 0) {
            return -1L;
        }
        return (i7 == Integer.MIN_VALUE || j3 <= ((long) i7)) ? j3 : i7;
    }

    public void g(long j3) {
        int i7 = this.f428m;
        if (i7 == Integer.MIN_VALUE || j3 == -1) {
            return;
        }
        this.f428m = (int) (((long) i7) - j3);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i7) {
        switch (this.l) {
            case 0:
                synchronized (this) {
                    super.mark(i7);
                    this.f428m = i7;
                }
                return;
            default:
                super.mark(i7);
                return;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        switch (this.l) {
            case 0:
                if (b(1L) == -1) {
                    return -1;
                }
                int i7 = super.read();
                g(1L);
                return i7;
            default:
                if (this.f428m <= 0) {
                    return -1;
                }
                int i10 = super.read();
                if (i10 >= 0) {
                    this.f428m--;
                }
                return i10;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        switch (this.l) {
            case 0:
                synchronized (this) {
                    super.reset();
                    this.f428m = Integer.MIN_VALUE;
                }
                return;
            default:
                super.reset();
                return;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j3) throws IOException {
        switch (this.l) {
            case 0:
                long jB = b(j3);
                if (jB == -1) {
                    return 0L;
                }
                long jSkip = super.skip(jB);
                g(jSkip);
                return jSkip;
            default:
                long jSkip2 = super.skip(Math.min(j3, this.f428m));
                if (jSkip2 >= 0) {
                    this.f428m = (int) (((long) this.f428m) - jSkip2);
                }
                return jSkip2;
        }
    }

    public n(ByteArrayInputStream byteArrayInputStream, int i7) {
        super(byteArrayInputStream);
        this.f428m = i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i7, int i10) throws IOException {
        switch (this.l) {
            case 0:
                int iB = (int) b(i10);
                if (iB == -1) {
                    return -1;
                }
                int i11 = super.read(bArr, i7, iB);
                g(i11);
                return i11;
            default:
                int i12 = this.f428m;
                if (i12 <= 0) {
                    return -1;
                }
                int i13 = super.read(bArr, i7, Math.min(i10, i12));
                if (i13 >= 0) {
                    this.f428m -= i13;
                }
                return i13;
        }
    }
}
