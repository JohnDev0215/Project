package B2;

import h2.AbstractC1343A;
import java.io.EOFException;
import java.io.InterruptedIOException;
import java.util.Arrays;

/* JADX INFO: renamed from: B2.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0073m implements s {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final k2.h f587m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f588n;
    public long o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f590q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f591r;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public byte[] f589p = new byte[65536];
    public final byte[] l = new byte[4096];

    static {
        e2.G.a("media3.extractor");
    }

    public C0073m(k2.h hVar, long j3, long j10) {
        this.f587m = hVar;
        this.o = j3;
        this.f588n = j10;
    }

    @Override // B2.s
    public final void a(int i7, int i10, byte[] bArr) {
        k(bArr, i7, i10, false);
    }

    @Override // B2.s
    public final boolean c(byte[] bArr, int i7, int i10, boolean z6) throws EOFException, InterruptedIOException {
        int iMin;
        int i11 = this.f591r;
        if (i11 == 0) {
            iMin = 0;
        } else {
            iMin = Math.min(i11, i10);
            System.arraycopy(this.f589p, 0, bArr, i7, iMin);
            t(iMin);
        }
        int iR = iMin;
        while (iR < i10 && iR != -1) {
            iR = r(bArr, i7, i10, iR, z6);
        }
        if (iR != -1) {
            this.o += (long) iR;
        }
        return iR != -1;
    }

    @Override // B2.s
    public final long d() {
        return this.f588n;
    }

    public final boolean e(int i7, boolean z6) throws EOFException, InterruptedIOException {
        p(i7);
        int iR = this.f591r - this.f590q;
        while (iR < i7) {
            iR = r(this.f589p, this.f590q, i7, iR, z6);
            if (iR == -1) {
                return false;
            }
            this.f591r = this.f590q + iR;
        }
        this.f590q += i7;
        return true;
    }

    @Override // B2.s
    public final void h() {
        this.f590q = 0;
    }

    @Override // B2.s
    public final void i(int i7) {
        int iMin = Math.min(this.f591r, i7);
        t(iMin);
        int iR = iMin;
        while (iR < i7 && iR != -1) {
            byte[] bArr = this.l;
            iR = r(bArr, -iR, Math.min(i7, bArr.length + iR), iR, false);
        }
        if (iR != -1) {
            this.o += (long) iR;
        }
    }

    @Override // B2.s
    public final boolean k(byte[] bArr, int i7, int i10, boolean z6) {
        if (!e(i10, z6)) {
            return false;
        }
        System.arraycopy(this.f589p, this.f590q - i10, bArr, i7, i10);
        return true;
    }

    @Override // B2.s
    public final long l() {
        return this.o + ((long) this.f590q);
    }

    @Override // B2.s
    public final void m(int i7) throws EOFException, InterruptedIOException {
        e(i7, false);
    }

    @Override // e2.InterfaceC1201i
    public final int n(byte[] bArr, int i7, int i10) throws EOFException, InterruptedIOException {
        int i11 = this.f591r;
        int iR = 0;
        if (i11 != 0) {
            int iMin = Math.min(i11, i10);
            System.arraycopy(this.f589p, 0, bArr, i7, iMin);
            t(iMin);
            iR = iMin;
        }
        if (iR == 0) {
            iR = r(bArr, i7, i10, 0, true);
        }
        if (iR != -1) {
            this.o += (long) iR;
        }
        return iR;
    }

    @Override // B2.s
    public final long o() {
        return this.o;
    }

    public final void p(int i7) {
        int i10 = this.f590q + i7;
        byte[] bArr = this.f589p;
        if (i10 > bArr.length) {
            this.f589p = Arrays.copyOf(this.f589p, AbstractC1343A.i(bArr.length * 2, 65536 + i10, i10 + 524288));
        }
    }

    public final int q(int i7, int i10, byte[] bArr) throws EOFException, InterruptedIOException {
        int iMin;
        p(i10);
        int i11 = this.f591r;
        int i12 = this.f590q;
        int i13 = i11 - i12;
        if (i13 == 0) {
            iMin = r(this.f589p, i12, i10, 0, true);
            if (iMin == -1) {
                return -1;
            }
            this.f591r += iMin;
        } else {
            iMin = Math.min(i10, i13);
        }
        System.arraycopy(this.f589p, this.f590q, bArr, i7, iMin);
        this.f590q += iMin;
        return iMin;
    }

    public final int r(byte[] bArr, int i7, int i10, int i11, boolean z6) throws EOFException, InterruptedIOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException();
        }
        int iN = this.f587m.n(bArr, i7 + i11, i10 - i11);
        if (iN != -1) {
            return i11 + iN;
        }
        if (i11 == 0 && z6) {
            return -1;
        }
        throw new EOFException();
    }

    @Override // B2.s
    public final void readFully(byte[] bArr, int i7, int i10) throws EOFException, InterruptedIOException {
        c(bArr, i7, i10, false);
    }

    public final int s(int i7) throws EOFException, InterruptedIOException {
        int iMin = Math.min(this.f591r, i7);
        t(iMin);
        if (iMin == 0) {
            byte[] bArr = this.l;
            iMin = r(bArr, 0, Math.min(i7, bArr.length), 0, true);
        }
        if (iMin != -1) {
            this.o += (long) iMin;
        }
        return iMin;
    }

    public final void t(int i7) {
        int i10 = this.f591r - i7;
        this.f591r = i10;
        this.f590q = 0;
        byte[] bArr = this.f589p;
        byte[] bArr2 = i10 < bArr.length - 524288 ? new byte[65536 + i10] : bArr;
        System.arraycopy(bArr, i7, bArr2, 0, i10);
        this.f589p = bArr2;
    }
}
