package h3;

import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public final class E implements I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final D f15272a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h2.t f15273b = new h2.t(32);
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f15274e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f15275f;

    public E(D d) {
        this.f15272a = d;
    }

    @Override // h3.I
    public final void a(h2.y yVar, B2.t tVar, H h10) {
        this.f15272a.a(yVar, tVar, h10);
        this.f15275f = true;
    }

    @Override // h3.I
    public final void b() {
        this.f15275f = true;
    }

    @Override // h3.I
    public final void c(int i7, h2.t tVar) {
        boolean z6 = (i7 & 1) != 0;
        int iV = z6 ? tVar.f15242b + tVar.v() : -1;
        if (this.f15275f) {
            if (!z6) {
                return;
            }
            this.f15275f = false;
            tVar.H(iV);
            this.d = 0;
        }
        while (tVar.a() > 0) {
            int i10 = this.d;
            h2.t tVar2 = this.f15273b;
            if (i10 < 3) {
                if (i10 == 0) {
                    int iV2 = tVar.v();
                    tVar.H(tVar.f15242b - 1);
                    if (iV2 == 255) {
                        this.f15275f = true;
                        return;
                    }
                }
                int iMin = Math.min(tVar.a(), 3 - this.d);
                tVar.f(this.d, iMin, tVar2.f15241a);
                int i11 = this.d + iMin;
                this.d = i11;
                if (i11 == 3) {
                    tVar2.H(0);
                    tVar2.G(3);
                    tVar2.I(1);
                    int iV3 = tVar2.v();
                    int iV4 = tVar2.v();
                    this.f15274e = (iV3 & 128) != 0;
                    int i12 = (((iV3 & 15) << 8) | iV4) + 3;
                    this.c = i12;
                    byte[] bArr = tVar2.f15241a;
                    if (bArr.length < i12) {
                        tVar2.b(Math.min(4098, Math.max(i12, bArr.length * 2)));
                    }
                }
            } else {
                int iMin2 = Math.min(tVar.a(), this.c - this.d);
                tVar.f(this.d, iMin2, tVar2.f15241a);
                int i13 = this.d + iMin2;
                this.d = i13;
                int i14 = this.c;
                if (i13 != i14) {
                    continue;
                } else {
                    if (!this.f15274e) {
                        tVar2.G(i14);
                    } else {
                        if (AbstractC1343A.k(0, i14, -1, tVar2.f15241a) != 0) {
                            this.f15275f = true;
                            return;
                        }
                        tVar2.G(this.c - 4);
                    }
                    tVar2.H(0);
                    this.f15272a.c(tVar2);
                    this.d = 0;
                }
            }
        }
    }
}
