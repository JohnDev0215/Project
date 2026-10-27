package A;

import S8.AbstractC0727y;
import T.C0735d;
import T.C0738e0;
import r7.EnumC2303a;
import s7.AbstractC2410c;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: A.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0043v implements K0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final A7.o f281a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0041u f282b = new C0041u(this);
    public final y.n0 c = new y.n0();
    public final C0738e0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0738e0 f283e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0738e0 f284f;

    /* JADX WARN: Multi-variable type inference failed */
    public C0043v(InterfaceC2782k interfaceC2782k) {
        this.f281a = (A7.o) interfaceC2782k;
        Boolean bool = Boolean.FALSE;
        T.S s6 = T.S.f8224q;
        this.d = C0735d.O(bool, s6);
        this.f283e = C0735d.O(bool, s6);
        this.f284f = C0735d.O(bool, s6);
    }

    @Override // A.K0
    public final boolean b() {
        return ((Boolean) this.d.getValue()).booleanValue();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [A7.o, z7.k] */
    @Override // A.K0
    public final float d(float f10) {
        return ((Number) this.f281a.b(Float.valueOf(f10))).floatValue();
    }

    @Override // A.K0
    public final Object e(y.j0 j0Var, InterfaceC2785n interfaceC2785n, AbstractC2410c abstractC2410c) {
        Object objH = AbstractC0727y.h(new C0039t(this, j0Var, interfaceC2785n, null), abstractC2410c);
        return objH == EnumC2303a.l ? objH : m7.q.f19267a;
    }
}
