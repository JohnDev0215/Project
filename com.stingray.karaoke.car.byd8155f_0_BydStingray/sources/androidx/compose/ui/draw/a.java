package androidx.compose.ui.draw;

import C0.InterfaceC0103j;
import g0.b;
import g0.c;
import g0.o;
import n0.C1883l;
import r0.AbstractC2268a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final o a(o oVar, InterfaceC2782k interfaceC2782k) {
        return oVar.k(new DrawBehindElement(interfaceC2782k));
    }

    public static final o b(o oVar, InterfaceC2782k interfaceC2782k) {
        return oVar.k(new DrawWithCacheElement(interfaceC2782k));
    }

    public static final o c(o oVar, InterfaceC2782k interfaceC2782k) {
        return oVar.k(new DrawWithContentElement(interfaceC2782k));
    }

    public static o d(o oVar, AbstractC2268a abstractC2268a, c cVar, InterfaceC0103j interfaceC0103j, float f10, C1883l c1883l, int i7) {
        if ((i7 & 4) != 0) {
            cVar = b.f14664p;
        }
        c cVar2 = cVar;
        if ((i7 & 16) != 0) {
            f10 = 1.0f;
        }
        return oVar.k(new PainterElement(abstractC2268a, true, cVar2, interfaceC0103j, f10, c1883l));
    }
}
