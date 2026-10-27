package r0;

import A7.m;
import E0.H;
import a1.k;
import h6.j;
import l3.C1764u;
import m0.d;
import m0.f;
import n0.AbstractC1861K;
import n0.C1878g;
import n0.C1883l;
import n0.InterfaceC1889r;
import p0.C2071b;

/* JADX INFO: renamed from: r0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2268a {
    public C1878g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f21575m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C1883l f21576n;
    public float o = 1.0f;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public k f21577p = k.l;

    public abstract boolean b(float f10);

    public boolean e(C1883l c1883l) {
        return false;
    }

    public void f(k kVar) {
    }

    public final void g(H h10, long j3, float f10, C1883l c1883l) {
        if (this.o != f10) {
            if (!b(f10)) {
                if (f10 == 1.0f) {
                    C1878g c1878g = this.l;
                    if (c1878g != null) {
                        c1878g.c(f10);
                    }
                    this.f21575m = false;
                } else {
                    C1878g c1878gH = this.l;
                    if (c1878gH == null) {
                        c1878gH = AbstractC1861K.h();
                        this.l = c1878gH;
                    }
                    c1878gH.c(f10);
                    this.f21575m = true;
                }
            }
            this.o = f10;
        }
        if (!m.a(this.f21576n, c1883l)) {
            if (!e(c1883l)) {
                if (c1883l == null) {
                    C1878g c1878g2 = this.l;
                    if (c1878g2 != null) {
                        c1878g2.f(null);
                    }
                    this.f21575m = false;
                } else {
                    C1878g c1878gH2 = this.l;
                    if (c1878gH2 == null) {
                        c1878gH2 = AbstractC1861K.h();
                        this.l = c1878gH2;
                    }
                    c1878gH2.f(c1883l);
                    this.f21575m = true;
                }
            }
            this.f21576n = c1883l;
        }
        k layoutDirection = h10.getLayoutDirection();
        if (this.f21577p != layoutDirection) {
            f(layoutDirection);
            this.f21577p = layoutDirection;
        }
        C2071b c2071b = h10.l;
        float fD = f.d(c2071b.e()) - f.d(j3);
        float fB = f.b(c2071b.e()) - f.b(j3);
        ((C1764u) c2071b.f20638m.f15499m).N(0.0f, 0.0f, fD, fB);
        if (f10 > 0.0f) {
            try {
                if (f.d(j3) > 0.0f && f.b(j3) > 0.0f) {
                    if (this.f21575m) {
                        d dVarA = h6.f.a(0L, j.f(f.d(j3), f.b(j3)));
                        InterfaceC1889r interfaceC1889rM = h10.l.f20638m.m();
                        C1878g c1878gH3 = this.l;
                        if (c1878gH3 == null) {
                            c1878gH3 = AbstractC1861K.h();
                            this.l = c1878gH3;
                        }
                        try {
                            interfaceC1889rM.g(dVarA, c1878gH3);
                            i(h10);
                            interfaceC1889rM.k();
                        } catch (Throwable th) {
                            interfaceC1889rM.k();
                            throw th;
                        }
                    } else {
                        i(h10);
                    }
                }
            } catch (Throwable th2) {
                ((C1764u) c2071b.f20638m.f15499m).N(-0.0f, -0.0f, -fD, -fB);
                throw th2;
            }
        }
        ((C1764u) c2071b.f20638m.f15499m).N(-0.0f, -0.0f, -fD, -fB);
    }

    public abstract long h();

    public abstract void i(H h10);
}
