package androidx.compose.foundation;

import C.k;
import L0.g;
import g0.l;
import g0.o;
import n0.AbstractC1861K;
import n0.AbstractC1887p;
import n0.InterfaceC1866P;
import y.C2717w;
import y.Z;
import y.e0;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static o a(o oVar, AbstractC1887p abstractC1887p, InterfaceC1866P interfaceC1866P, int i7) {
        if ((i7 & 2) != 0) {
            interfaceC1866P = AbstractC1861K.f19381a;
        }
        return oVar.k(new BackgroundElement(0L, abstractC1887p, 1.0f, interfaceC1866P, 1));
    }

    public static final o b(o oVar, long j3, InterfaceC1866P interfaceC1866P) {
        return oVar.k(new BackgroundElement(j3, null, 1.0f, interfaceC1866P, 2));
    }

    public static final o c(o oVar, k kVar, Z z6, boolean z10, String str, g gVar, InterfaceC2772a interfaceC2772a) {
        o oVarK;
        if (z6 instanceof e0) {
            oVarK = new ClickableElement(kVar, (e0) z6, z10, str, gVar, interfaceC2772a);
        } else if (z6 == null) {
            oVarK = new ClickableElement(kVar, null, z10, str, gVar, interfaceC2772a);
        } else {
            l lVar = l.f14684a;
            oVarK = kVar != null ? d.a(lVar, kVar, z6).k(new ClickableElement(kVar, null, z10, str, gVar, interfaceC2772a)) : g0.a.b(lVar, new b(z6, z10, str, gVar, interfaceC2772a));
        }
        return oVar.k(oVarK);
    }

    public static /* synthetic */ o d(o oVar, k kVar, Z z6, boolean z10, g gVar, InterfaceC2772a interfaceC2772a, int i7) {
        if ((i7 & 16) != 0) {
            gVar = null;
        }
        return c(oVar, kVar, z6, z10, null, gVar, interfaceC2772a);
    }

    public static o e(o oVar, boolean z6, String str, InterfaceC2772a interfaceC2772a, int i7) {
        if ((i7 & 2) != 0) {
            str = null;
        }
        return g0.a.b(oVar, new C2717w(z6, str, null, interfaceC2772a));
    }

    public static o f(o oVar, k kVar, InterfaceC2772a interfaceC2772a) {
        return oVar.k(new CombinedClickableElement(kVar, true, null, null, interfaceC2772a, null, null, null));
    }

    public static o g(o oVar, k kVar) {
        return oVar.k(new HoverableElement(kVar));
    }
}
