package h3;

import B2.C0073m;
import java.io.EOFException;
import java.io.InterruptedIOException;
import u2.T;

/* JADX INFO: renamed from: h3.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1353d implements B2.r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1352c f15307a = new C1352c(null, 0, 1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h2.t f15308b = new h2.t(16384);
    public boolean c;

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(B2.t tVar) {
        this.f15307a.f(tVar, new H(0, 1));
        T t10 = (T) tVar;
        t10.e();
        t10.w(new B2.w(-9223372036854775807L));
    }

    @Override // B2.r
    public final int c(B2.s sVar, B2.v vVar) throws EOFException, InterruptedIOException {
        h2.t tVar = this.f15308b;
        int iN = ((C0073m) sVar).n(tVar.f15241a, 0, 16384);
        if (iN == -1) {
            return -1;
        }
        tVar.H(0);
        tVar.G(iN);
        boolean z6 = this.c;
        C1352c c1352c = this.f15307a;
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
        this.f15307a.b();
    }

    @Override // B2.r
    public final boolean f(B2.s sVar) throws EOFException, InterruptedIOException {
        C0073m c0073m;
        int i7;
        h2.t tVar = new h2.t(10);
        int i10 = 0;
        while (true) {
            c0073m = (C0073m) sVar;
            c0073m.k(tVar.f15241a, 0, 10, false);
            tVar.H(0);
            if (tVar.y() != 4801587) {
                break;
            }
            tVar.I(3);
            int iU = tVar.u();
            i10 += iU + 10;
            c0073m.e(iU, false);
        }
        c0073m.f590q = 0;
        c0073m.e(i10, false);
        int i11 = 0;
        int i12 = i10;
        while (true) {
            int i13 = 7;
            c0073m.k(tVar.f15241a, 0, 7, false);
            tVar.H(0);
            int iB = tVar.B();
            if (iB == 44096 || iB == 44097) {
                i11++;
                if (i11 >= 4) {
                    return true;
                }
                byte[] bArr = tVar.f15241a;
                if (bArr.length < 7) {
                    i7 = -1;
                } else {
                    int i14 = ((bArr[2] & 255) << 8) | (bArr[3] & 255);
                    if (i14 == 65535) {
                        i14 = ((bArr[4] & 255) << 16) | ((bArr[5] & 255) << 8) | (bArr[6] & 255);
                    } else {
                        i13 = 4;
                    }
                    if (iB == 44097) {
                        i13 += 2;
                    }
                    i7 = i14 + i13;
                }
                if (i7 == -1) {
                    return false;
                }
                c0073m.e(i7 - 7, false);
            } else {
                c0073m.f590q = 0;
                i12++;
                if (i12 - i10 >= 8192) {
                    return false;
                }
                c0073m.e(i12, false);
                i11 = 0;
            }
        }
    }
}
