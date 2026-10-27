package androidx.compose.foundation;

import A7.o;
import C.k;
import L0.g;
import T.C0753m;
import T.C0761q;
import g0.l;
import y.Z;
import z7.InterfaceC2772a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class b extends o implements InterfaceC2786o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Z f11429m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f11430n;
    public final /* synthetic */ String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ g f11431p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f11432q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(Z z6, boolean z10, String str, g gVar, InterfaceC2772a interfaceC2772a) {
        super(3);
        this.f11429m = z6;
        this.f11430n = z10;
        this.o = str;
        this.f11431p = gVar;
        this.f11432q = interfaceC2772a;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        C0761q c0761q = (C0761q) obj2;
        ((Number) obj3).intValue();
        c0761q.Q(-1525724089);
        Object objG = c0761q.G();
        if (objG == C0753m.f8270a) {
            objG = new k();
            c0761q.a0(objG);
        }
        k kVar = (k) objG;
        g0.o oVarK = d.a(l.f14684a, kVar, this.f11429m).k(new ClickableElement(kVar, null, this.f11430n, this.o, this.f11431p, this.f11432q));
        c0761q.p(false);
        return oVarK;
    }
}
