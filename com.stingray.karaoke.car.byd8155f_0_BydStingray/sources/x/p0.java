package x;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class p0 implements InterfaceC2639i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F0 f23600a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final D0 f23601b;
    public Object c;
    public Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AbstractC2652s f23602e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public AbstractC2652s f23603f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AbstractC2652s f23604g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f23605h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public AbstractC2652s f23606i;

    public p0(InterfaceC2647m interfaceC2647m, D0 d02, Object obj, Object obj2, AbstractC2652s abstractC2652s) {
        this.f23600a = interfaceC2647m.a(d02);
        this.f23601b = d02;
        this.c = obj2;
        this.d = obj;
        this.f23602e = (AbstractC2652s) d02.f23394a.b(obj);
        InterfaceC2782k interfaceC2782k = d02.f23394a;
        this.f23603f = (AbstractC2652s) interfaceC2782k.b(obj2);
        this.f23604g = abstractC2652s != null ? AbstractC2631e.i(abstractC2652s) : ((AbstractC2652s) interfaceC2782k.b(obj)).c();
        this.f23605h = -1L;
    }

    @Override // x.InterfaceC2639i
    public final boolean a() {
        return this.f23600a.a();
    }

    @Override // x.InterfaceC2639i
    public final Object b(long j3) {
        if (g(j3)) {
            return this.c;
        }
        AbstractC2652s abstractC2652sO = this.f23600a.o(j3, this.f23602e, this.f23603f, this.f23604g);
        int iB = abstractC2652sO.b();
        for (int i7 = 0; i7 < iB; i7++) {
            if (Float.isNaN(abstractC2652sO.a(i7))) {
                throw new IllegalStateException("AnimationVector cannot contain a NaN. " + abstractC2652sO + ". Animation: " + this + ", playTimeNanos: " + j3);
            }
        }
        return this.f23601b.f23395b.b(abstractC2652sO);
    }

    @Override // x.InterfaceC2639i
    public final long c() {
        if (this.f23605h < 0) {
            this.f23605h = this.f23600a.b(this.f23602e, this.f23603f, this.f23604g);
        }
        return this.f23605h;
    }

    @Override // x.InterfaceC2639i
    public final D0 d() {
        return this.f23601b;
    }

    @Override // x.InterfaceC2639i
    public final Object e() {
        return this.c;
    }

    @Override // x.InterfaceC2639i
    public final AbstractC2652s f(long j3) {
        if (!g(j3)) {
            return this.f23600a.c(j3, this.f23602e, this.f23603f, this.f23604g);
        }
        AbstractC2652s abstractC2652sT = this.f23606i;
        if (abstractC2652sT == null) {
            abstractC2652sT = this.f23600a.t(this.f23602e, this.f23603f, this.f23604g);
            this.f23606i = abstractC2652sT;
        }
        return abstractC2652sT;
    }

    public final void h(Object obj) {
        if (A7.m.a(obj, this.d)) {
            return;
        }
        this.d = obj;
        this.f23602e = (AbstractC2652s) this.f23601b.f23394a.b(obj);
        this.f23606i = null;
        this.f23605h = -1L;
    }

    public final void i(Object obj) {
        if (A7.m.a(this.c, obj)) {
            return;
        }
        this.c = obj;
        this.f23603f = (AbstractC2652s) this.f23601b.f23394a.b(obj);
        this.f23606i = null;
        this.f23605h = -1L;
    }

    public final String toString() {
        return "TargetBasedAnimation: " + this.d + " -> " + this.c + ",initial velocity: " + this.f23604g + ", duration: " + (c() / 1000000) + " ms,animationSpec: " + this.f23600a;
    }
}
