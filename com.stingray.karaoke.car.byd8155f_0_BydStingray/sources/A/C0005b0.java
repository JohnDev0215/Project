package A;

import S8.AbstractC0727y;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: A.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0005b0 extends V {

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public InterfaceC0007c0 f148I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public EnumC0017h0 f149J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f150K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public InterfaceC2786o f151L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public InterfaceC2786o f152M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public boolean f153N;

    @Override // A.V
    public final Object O0(T t10, U u4) {
        Object objA = this.f148I.a(new Y(t10, this, null), u4);
        return objA == EnumC2303a.l ? objA : m7.q.f19267a;
    }

    @Override // A.V
    public final void P0(long j3) {
        if (!this.f14695x || A7.m.a(this.f151L, X.f130a)) {
            return;
        }
        AbstractC0727y.w(v0(), null, null, new Z(this, j3, null), 3);
    }

    @Override // A.V
    public final void Q0(long j3) {
        if (!this.f14695x || A7.m.a(this.f152M, X.f131b)) {
            return;
        }
        AbstractC0727y.w(v0(), null, null, new C0003a0(this, j3, null), 3);
    }

    @Override // A.V
    public final boolean R0() {
        return this.f150K;
    }
}
