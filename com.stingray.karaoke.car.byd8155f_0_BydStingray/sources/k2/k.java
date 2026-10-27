package k2;

import h2.AbstractC1346c;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class k extends InputStream {
    public final C l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final m f17664m;
    public boolean o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f17666p = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final byte[] f17665n = new byte[1];

    public k(C c, m mVar) {
        this.l = c;
        this.f17664m = mVar;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f17666p) {
            return;
        }
        this.l.close();
        this.f17666p = true;
    }

    @Override // java.io.InputStream
    public final int read() {
        byte[] bArr = this.f17665n;
        if (read(bArr, 0, bArr.length) == -1) {
            return -1;
        }
        return bArr[0] & 255;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i7, int i10) {
        AbstractC1346c.j(!this.f17666p);
        boolean z6 = this.o;
        C c = this.l;
        if (!z6) {
            c.g(this.f17664m);
            this.o = true;
        }
        int iN = c.n(bArr, i7, i10);
        if (iN == -1) {
            return -1;
        }
        return iN;
    }
}
