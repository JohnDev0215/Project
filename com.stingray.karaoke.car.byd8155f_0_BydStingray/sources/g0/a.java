package g0;

import A.G0;
import T.C0761q;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f14659a = new d(-1.0f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final d f14660b = new d(1.0f);
    public static final StackTraceElement[] c = new StackTraceElement[0];

    public static final boolean a(Object obj, Object obj2) {
        return obj.getClass() == obj2.getClass();
    }

    public static final o b(o oVar, InterfaceC2786o interfaceC2786o) {
        return oVar.k(new j(interfaceC2786o));
    }

    public static final o c(C0761q c0761q, o oVar) {
        if (oVar.e(k.f14683m)) {
            return oVar;
        }
        c0761q.R(1219399079);
        o oVar2 = (o) oVar.h(l.f14684a, new G0(c0761q, 12));
        c0761q.p(false);
        return oVar2;
    }

    public static final o d(C0761q c0761q, o oVar) {
        c0761q.Q(439770924);
        o oVarC = c(c0761q, oVar);
        c0761q.p(false);
        return oVarC;
    }
}
