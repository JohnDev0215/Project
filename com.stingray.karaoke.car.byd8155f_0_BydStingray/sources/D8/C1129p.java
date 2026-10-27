package d8;

import Q7.N;
import c8.C1036a;

/* JADX INFO: renamed from: d8.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1129p extends T7.B {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final /* synthetic */ H7.w[] f13614y;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final W7.x f13615s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final N0.q f13616t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final E8.i f13617u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final C1117d f13618v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final E8.c f13619w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final R7.h f13620x;

    static {
        A7.B b10 = A7.A.f443a;
        f13614y = new H7.w[]{b10.h(new A7.s(b10.b(C1129p.class), "binaryClasses", "getBinaryClasses$descriptors_jvm()Ljava/util/Map;")), b10.h(new A7.s(b10.b(C1129p.class), "partToFacade", "getPartToFacade()Ljava/util/HashMap;"))};
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public C1129p(N0.q qVar, W7.x xVar) {
        A7.m.f(qVar, "outerContext");
        C1036a c1036a = (C1036a) qVar.f4399m;
        super(c1036a.o, xVar.f9900a);
        this.f13615s = xVar;
        N0.q qVarJ = l9.d.J(qVar, this, null, 6);
        this.f13616t = qVarJ;
        A7.m.f(c1036a.d.c().c, "<this>");
        n8.f fVar = n8.f.f20166g;
        C1036a c1036a2 = (C1036a) qVarJ.f4399m;
        E8.l lVar = c1036a2.f12411a;
        C1128o c1128o = new C1128o(this, 0);
        lVar.getClass();
        this.f13617u = new E8.i(lVar, c1128o);
        this.f13618v = new C1117d(qVarJ, xVar, this);
        C1128o c1128o2 = new C1128o(this, 2);
        lVar.getClass();
        this.f13619w = new E8.c(lVar, c1128o2);
        this.f13620x = c1036a2.f12428v.f10957b ? R7.g.f7535a : l9.l.Y(qVarJ, xVar);
        lVar.a(new C1128o(this, 1));
    }

    @Override // T7.B, T7.AbstractC0795n, Q7.InterfaceC0658k
    public final N g() {
        return new e7.f(this);
    }

    @Override // A9.b, R7.a
    public final R7.h k() {
        return this.f13620x;
    }

    @Override // T7.B, T7.AbstractC0794m, A9.b
    public final String toString() {
        return "Lazy Java package fragment: " + this.f8767q + " of module " + ((C1036a) this.f13616t.f4399m).o;
    }

    @Override // Q7.D
    public final y8.n z0() {
        return this.f13618v;
    }
}
