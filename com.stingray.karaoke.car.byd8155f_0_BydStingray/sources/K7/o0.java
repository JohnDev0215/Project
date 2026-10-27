package K7;

import F8.AbstractC0287w;
import H7.InterfaceC0319d;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import W7.AbstractC0839c;
import java.lang.reflect.Array;
import java.util.List;
import n7.AbstractC1998m;
import q8.C2203g;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class o0 implements A7.n {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ H7.w[] f3823e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0287w f3824a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t0 f3825b;
    public final t0 c;
    public final t0 d;

    static {
        A7.B b10 = A7.A.f443a;
        f3823e = new H7.w[]{b10.h(new A7.s(b10.b(o0.class), "classifier", "getClassifier()Lkotlin/reflect/KClassifier;")), b10.h(new A7.s(b10.b(o0.class), "arguments", "getArguments()Ljava/util/List;"))};
    }

    public o0(AbstractC0287w abstractC0287w, InterfaceC2772a interfaceC2772a) {
        A7.m.f(abstractC0287w, "type");
        this.f3824a = abstractC0287w;
        t0 t0Var = interfaceC2772a instanceof t0 ? (t0) interfaceC2772a : null;
        this.f3825b = t0Var == null ? interfaceC2772a != null ? v0.n(null, interfaceC2772a) : null : t0Var;
        this.c = v0.n(null, new n0(this, 1));
        this.d = v0.n(null, new B8.A(this, 16, interfaceC2772a));
    }

    @Override // H7.x
    public final List a() {
        H7.w wVar = f3823e[1];
        Object objA = this.d.a();
        A7.m.e(objA, "<get-arguments>(...)");
        return (List) objA;
    }

    @Override // H7.x
    public final boolean b() {
        return this.f3824a.F0();
    }

    @Override // H7.x
    public final InterfaceC0319d c() {
        H7.w wVar = f3823e[0];
        return (InterfaceC0319d) this.c.a();
    }

    public final InterfaceC0319d d(AbstractC0287w abstractC0287w) {
        AbstractC0287w abstractC0287wB;
        InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
        if (!(interfaceC0654gN instanceof InterfaceC0652e)) {
            if (interfaceC0654gN instanceof Q7.Q) {
                return new p0(null, (Q7.Q) interfaceC0654gN);
            }
            if (interfaceC0654gN instanceof D8.v) {
                throw new m7.h("An operation is not implemented: Type alias classifiers are not yet supported");
            }
            return null;
        }
        Class clsJ = A0.j((InterfaceC0652e) interfaceC0654gN);
        if (clsJ == null) {
            return null;
        }
        if (!clsJ.isArray()) {
            if (F8.Y.e(abstractC0287w)) {
                return new C0401z(clsJ);
            }
            Class cls = (Class) AbstractC0839c.f9879b.get(clsJ);
            if (cls != null) {
                clsJ = cls;
            }
            return new C0401z(clsJ);
        }
        F8.O o = (F8.O) AbstractC1998m.t0(abstractC0287w.o0());
        if (o == null || (abstractC0287wB = o.b()) == null) {
            return new C0401z(clsJ);
        }
        InterfaceC0319d interfaceC0319dD = d(abstractC0287wB);
        if (interfaceC0319dD != null) {
            return new C0401z(Array.newInstance((Class<?>) n7.z.i(com.bumptech.glide.c.f0(interfaceC0319dD)), 0).getClass());
        }
        throw new r0("Cannot determine classifier for array element type: " + this);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof o0) {
            o0 o0Var = (o0) obj;
            if (A7.m.a(this.f3824a, o0Var.f3824a) && A7.m.a(c(), o0Var.c()) && a().equals(o0Var.a())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = this.f3824a.hashCode() * 31;
        InterfaceC0319d interfaceC0319dC = c();
        return a().hashCode() + ((iHashCode + (interfaceC0319dC != null ? interfaceC0319dC.hashCode() : 0)) * 31);
    }

    public final String toString() {
        C2203g c2203g = x0.f3852a;
        return x0.d(this.f3824a);
    }
}
