package K7;

import Q7.InterfaceC0650c;

/* JADX INFO: loaded from: classes.dex */
public abstract class i0 extends g0 implements H7.p {
    public static final /* synthetic */ H7.w[] o;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final t0 f3793m = v0.n(null, new h0(this, 1));

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f3794n = h6.r.n(m7.g.l, new h0(this, 0));

    static {
        A7.B b10 = A7.A.f443a;
        o = new H7.w[]{b10.h(new A7.s(b10.b(i0.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/PropertyGetterDescriptor;"))};
    }

    public final boolean equals(Object obj) {
        return (obj instanceof i0) && A7.m.a(t(), ((i0) obj).t());
    }

    @Override // H7.InterfaceC0317b
    public final String getName() {
        return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("<get-"), t().f3813n, '>');
    }

    public final int hashCode() {
        return t().hashCode();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // K7.r
    public final L7.e i() {
        return (L7.e) this.f3794n.getValue();
    }

    @Override // K7.r
    public final InterfaceC0650c n() {
        H7.w wVar = o[0];
        Object objA = this.f3793m.a();
        A7.m.e(objA, "<get-descriptor>(...)");
        return (T7.I) objA;
    }

    @Override // K7.g0
    public final Q7.K s() {
        H7.w wVar = o[0];
        Object objA = this.f3793m.a();
        A7.m.e(objA, "<get-descriptor>(...)");
        return (T7.I) objA;
    }

    public final String toString() {
        return "getter of " + t();
    }
}
