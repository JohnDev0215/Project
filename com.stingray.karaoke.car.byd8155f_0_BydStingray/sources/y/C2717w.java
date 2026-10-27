package y;

import T.C0753m;
import T.C0761q;
import z7.InterfaceC2772a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: y.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2717w extends A7.o implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f24018m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f24019n;
    public final /* synthetic */ L0.g o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f24020p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2717w(boolean z6, String str, L0.g gVar, InterfaceC2772a interfaceC2772a) {
        super(3);
        this.f24018m = z6;
        this.f24019n = str;
        this.o = gVar;
        this.f24020p = interfaceC2772a;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        C.k kVar;
        C0761q c0761q = (C0761q) obj2;
        ((Number) obj3).intValue();
        c0761q.Q(-756081143);
        Z z6 = (Z) c0761q.k(androidx.compose.foundation.d.f11433a);
        if (z6 instanceof e0) {
            c0761q.Q(617140216);
            c0761q.p(false);
            kVar = null;
        } else {
            c0761q.Q(617248189);
            Object objG = c0761q.G();
            if (objG == C0753m.f8270a) {
                objG = new C.k();
                c0761q.a0(objG);
            }
            kVar = (C.k) objG;
            c0761q.p(false);
        }
        C.k kVar2 = kVar;
        g0.o oVarC = androidx.compose.foundation.a.c(g0.l.f14684a, kVar2, z6, this.f24018m, this.f24019n, this.o, this.f24020p);
        c0761q.p(false);
        return oVarC;
    }
}
