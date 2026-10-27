package h3;

import B2.AbstractC0062b;
import B2.C0073m;
import java.io.EOFException;
import java.io.InterruptedIOException;
import u2.T;

/* JADX INFO: renamed from: h3.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1351b implements B2.r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1352c f15294a = new C1352c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h2.t f15295b = new h2.t(2786);
    public boolean c;

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(B2.t tVar) {
        this.f15294a.f(tVar, new H(0, 1));
        T t10 = (T) tVar;
        t10.e();
        t10.w(new B2.w(-9223372036854775807L));
    }

    @Override // B2.r
    public final int c(B2.s sVar, B2.v vVar) throws EOFException, InterruptedIOException {
        h2.t tVar = this.f15295b;
        int iN = ((C0073m) sVar).n(tVar.f15241a, 0, 2786);
        if (iN == -1) {
            return -1;
        }
        tVar.H(0);
        tVar.G(iN);
        boolean z6 = this.c;
        C1352c c1352c = this.f15294a;
        if (!z6) {
            c1352c.f15306n = 0L;
            this.c = true;
        }
        c1352c.c(tVar);
        return 0;
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        this.c = false;
        this.f15294a.b();
    }

    @Override // B2.r
    public final boolean f(B2.s sVar) throws EOFException, InterruptedIOException {
        C0073m c0073m;
        int iF;
        h2.t tVar = new h2.t(10);
        int i7 = 0;
        while (true) {
            c0073m = (C0073m) sVar;
            c0073m.k(tVar.f15241a, 0, 10, false);
            tVar.H(0);
            if (tVar.y() != 4801587) {
                break;
            }
            tVar.I(3);
            int iU = tVar.u();
            i7 += iU + 10;
            c0073m.e(iU, false);
        }
        c0073m.f590q = 0;
        c0073m.e(i7, false);
        int i10 = 0;
        int i11 = i7;
        while (true) {
            c0073m.k(tVar.f15241a, 0, 6, false);
            tVar.H(0);
            if (tVar.B() != 2935) {
                c0073m.f590q = 0;
                i11++;
                if (i11 - i7 >= 8192) {
                    return false;
                }
                c0073m.e(i11, false);
                i10 = 0;
            } else {
                i10++;
                if (i10 >= 4) {
                    return true;
                }
                byte[] bArr = tVar.f15241a;
                if (bArr.length < 6) {
                    iF = -1;
                } else if (((bArr[5] & 248) >> 3) > 10) {
                    iF = ((((bArr[2] & 7) << 8) | (bArr[3] & 255)) + 1) * 2;
                } else {
                    byte b10 = bArr[4];
                    iF = AbstractC0062b.f((b10 & 192) >> 6, b10 & 63);
                }
                if (iF == -1) {
                    return false;
                }
                c0073m.e(iF - 6, false);
            }
        }
    }
}
