package y;

import A.C0043v;
import A.K0;
import T.C0732b0;
import T.C0735d;
import q0.C2162a;
import r7.EnumC2303a;
import s7.AbstractC2410c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class y0 implements K0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final io.sentry.internal.debugmeta.c f24027i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0732b0 f24028a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f24030e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0732b0 f24029b = C0735d.M(0);
    public final C.k c = new C.k();
    public final C0732b0 d = C0735d.M(Integer.MAX_VALUE);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0043v f24031f = new C0043v(new C2162a(this, 10));

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final T.D f24032g = C0735d.G(new x0(this, 1));

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final T.D f24033h = C0735d.G(new x0(this, 0));

    static {
        w0 w0Var = w0.f24021m;
        r rVar = r.f23993r;
        io.sentry.internal.debugmeta.c cVar = d0.o.f13275a;
        f24027i = new io.sentry.internal.debugmeta.c(w0Var, 15, rVar);
    }

    public y0(int i7) {
        this.f24028a = C0735d.M(i7);
    }

    @Override // A.K0
    public final boolean a() {
        return ((Boolean) this.f24033h.getValue()).booleanValue();
    }

    @Override // A.K0
    public final boolean b() {
        return this.f24031f.b();
    }

    @Override // A.K0
    public final boolean c() {
        return ((Boolean) this.f24032g.getValue()).booleanValue();
    }

    @Override // A.K0
    public final float d(float f10) {
        return this.f24031f.d(f10);
    }

    @Override // A.K0
    public final Object e(j0 j0Var, InterfaceC2785n interfaceC2785n, AbstractC2410c abstractC2410c) {
        Object objE = this.f24031f.e(j0Var, interfaceC2785n, abstractC2410c);
        return objE == EnumC2303a.l ? objE : m7.q.f19267a;
    }
}
