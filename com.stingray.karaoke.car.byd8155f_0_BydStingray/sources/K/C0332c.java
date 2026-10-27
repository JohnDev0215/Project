package K;

import T.C0753m;
import T.C0761q;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: K.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0332c extends A7.o implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C0332c f3241m = new C0332c(3);

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        g0.o oVar = (g0.o) obj;
        C0761q c0761q = (C0761q) obj2;
        ((Number) obj3).intValue();
        c0761q.Q(-2126899193);
        long j3 = ((O.S) c0761q.k(O.T.f4941a)).f4939a;
        g0.l lVar = g0.l.f14684a;
        boolean zE = c0761q.e(j3);
        Object objG = c0761q.G();
        if (zE || objG == C0753m.f8270a) {
            objG = new C0330b(j3, 0);
            c0761q.a0(objG);
        }
        g0.o oVarK = oVar.k(androidx.compose.ui.draw.a.b(lVar, (InterfaceC2782k) objG));
        c0761q.p(false);
        return oVarK;
    }
}
