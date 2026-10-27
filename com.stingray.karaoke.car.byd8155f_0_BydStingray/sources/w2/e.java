package W2;

import B2.C0073m;
import h2.AbstractC1346c;
import h2.t;
import java.io.EOFException;
import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f9730a = new f();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t f9731b = new t(0, new byte[65025]);
    public int c = -1;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9732e;

    public final int a(int i7) {
        int i10;
        int i11 = 0;
        this.d = 0;
        do {
            int i12 = this.d;
            int i13 = i7 + i12;
            f fVar = this.f9730a;
            if (i13 >= fVar.c) {
                break;
            }
            int[] iArr = fVar.f9736f;
            this.d = i12 + 1;
            i10 = iArr[i12 + i7];
            i11 += i10;
        } while (i10 == 255);
        return i11;
    }

    public final boolean b(C0073m c0073m) throws InterruptedIOException {
        int i7;
        AbstractC1346c.j(c0073m != null);
        boolean z6 = this.f9732e;
        t tVar = this.f9731b;
        if (z6) {
            this.f9732e = false;
            tVar.E(0);
        }
        while (!this.f9732e) {
            int i10 = this.c;
            f fVar = this.f9730a;
            if (i10 < 0) {
                if (fVar.b(c0073m, -1L) && fVar.a(c0073m, true)) {
                    int iA = fVar.d;
                    if ((fVar.f9733a & 1) == 1 && tVar.c == 0) {
                        iA += a(0);
                        i7 = this.d;
                    } else {
                        i7 = 0;
                    }
                    try {
                        c0073m.i(iA);
                        this.c = i7;
                    } catch (EOFException unused) {
                    }
                }
                return false;
            }
            int iA2 = a(this.c);
            int i11 = this.c + this.d;
            if (iA2 > 0) {
                tVar.b(tVar.c + iA2);
                try {
                    c0073m.c(tVar.f15241a, tVar.c, iA2, false);
                    tVar.G(tVar.c + iA2);
                    this.f9732e = fVar.f9736f[i11 + (-1)] != 255;
                } catch (EOFException unused2) {
                    return false;
                }
            }
            if (i11 == fVar.c) {
                i11 = -1;
            }
            this.c = i11;
        }
        return true;
    }
}
