package u2;

import android.net.Uri;
import h2.AbstractC1346c;
import java.util.Map;

/* JADX INFO: renamed from: u2.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2515s implements k2.h {
    public final k2.h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f22897m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final P f22898n;
    public final byte[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f22899p;

    public C2515s(k2.h hVar, int i7, P p10) {
        AbstractC1346c.c(i7 > 0);
        this.l = hVar;
        this.f22897m = i7;
        this.f22898n = p10;
        this.o = new byte[1];
        this.f22899p = i7;
    }

    @Override // k2.h
    public final void b(k2.E e10) {
        e10.getClass();
        this.l.b(e10);
    }

    @Override // k2.h
    public final void close() {
        throw new UnsupportedOperationException();
    }

    @Override // k2.h
    public final Map f() {
        return this.l.f();
    }

    @Override // k2.h
    public final long g(k2.m mVar) {
        throw new UnsupportedOperationException();
    }

    @Override // k2.h
    public final Uri j() {
        return this.l.j();
    }

    @Override // e2.InterfaceC1201i
    public final int n(byte[] bArr, int i7, int i10) {
        int i11 = this.f22899p;
        k2.h hVar = this.l;
        if (i11 == 0) {
            byte[] bArr2 = this.o;
            if (hVar.n(bArr2, 0, 1) != -1) {
                int i12 = (bArr2[0] & 255) << 4;
                if (i12 != 0) {
                    byte[] bArr3 = new byte[i12];
                    int i13 = i12;
                    int i14 = 0;
                    while (i13 > 0) {
                        int iN = hVar.n(bArr3, i14, i13);
                        if (iN != -1) {
                            i14 += iN;
                            i13 -= iN;
                        }
                    }
                    while (i12 > 0 && bArr3[i12 - 1] == 0) {
                        i12--;
                    }
                    if (i12 > 0) {
                        h2.t tVar = new h2.t(i12, bArr3);
                        P p10 = this.f22898n;
                        long jMax = !p10.l ? p10.f22699i : Math.max(p10.f22702m.t(true), p10.f22699i);
                        int iA = tVar.a();
                        B2.L l = p10.f22701k;
                        l.getClass();
                        l.a(tVar, iA, 0);
                        l.b(jMax, 1, iA, 0, null);
                        p10.l = true;
                    }
                }
                this.f22899p = this.f22897m;
            }
            return -1;
        }
        int iN2 = hVar.n(bArr, i7, Math.min(this.f22899p, i10));
        if (iN2 != -1) {
            this.f22899p -= iN2;
        }
        return iN2;
    }
}
