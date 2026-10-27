package o2;

import d5.C1077F;
import d5.I;
import d5.d0;
import e2.C1208p;
import e2.g0;
import java.io.IOException;
import n2.C1906f;
import s2.n;
import s2.u;
import s2.v;
import u2.C2516t;
import u2.C2521y;
import w2.C2592f;
import w2.C2595i;

/* JADX INFO: renamed from: o2.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C2026c implements h2.l, u, w2.l {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f20404m;

    public /* synthetic */ C2026c(Object obj, int i7) {
        this.l = i7;
        this.f20404m = obj;
    }

    @Override // s2.u
    public int a(Object obj) {
        n nVar = (n) obj;
        nVar.getClass();
        C1208p c1208p = (C1208p) this.f20404m;
        String str = c1208p.f14316n;
        String str2 = nVar.f22096b;
        return ((str2.equals(str) || str2.equals(v.b(c1208p))) && nVar.c(c1208p, false)) ? 1 : 0;
    }

    @Override // h2.l
    public void b(Object obj) {
        j jVar = (j) obj;
        switch (this.l) {
            case 0:
                int i7 = jVar.f20444x;
                C1906f c1906f = (C1906f) this.f20404m;
                jVar.f20444x = i7 + c1906f.f19653g;
                jVar.f20445y += c1906f.f19651e;
                break;
            case 1:
                jVar.getClass();
                break;
            default:
                jVar.getClass();
                jVar.f20442v = ((C2521y) this.f20404m).f22916a;
                break;
        }
    }

    @Override // w2.l
    public d0 g(int i7, g0 g0Var, int[] iArr) {
        C1077F c1077fU = I.u();
        for (int i10 = 0; i10 < g0Var.f14104a; i10++) {
            int i11 = i10;
            c1077fU.a(new C2592f(i7, g0Var, i11, (C2595i) this.f20404m, iArr[i10]));
        }
        return c1077fU.h();
    }

    public /* synthetic */ C2026c(C2024a c2024a, Object obj, long j3) {
        this.l = 1;
        this.f20404m = obj;
    }

    public /* synthetic */ C2026c(C2024a c2024a, C1906f c1906f) {
        this.l = 0;
        this.f20404m = c1906f;
    }

    public /* synthetic */ C2026c(C2024a c2024a, C2516t c2516t, C2521y c2521y, IOException iOException, boolean z6) {
        this.l = 2;
        this.f20404m = c2521y;
    }
}
