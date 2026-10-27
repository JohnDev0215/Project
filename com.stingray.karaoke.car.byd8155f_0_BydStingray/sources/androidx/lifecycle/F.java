package androidx.lifecycle;

import c0.C1016a;

/* JADX INFO: loaded from: classes.dex */
public final class F extends G implements InterfaceC0986y {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final A f11917p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C0971i f11918q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F(C0971i c0971i, A a10, C1016a c1016a) {
        super(c0971i, c1016a);
        this.f11918q = c0971i;
        this.f11917p = a10;
    }

    @Override // androidx.lifecycle.InterfaceC0986y
    public final void b(A a10, EnumC0980s enumC0980s) {
        A a11 = this.f11917p;
        EnumC0981t enumC0981tO0 = a11.g().O0();
        if (enumC0981tO0 == EnumC0981t.l) {
            this.f11918q.h(this.l);
            return;
        }
        EnumC0981t enumC0981t = null;
        while (enumC0981t != enumC0981tO0) {
            a(e());
            enumC0981t = enumC0981tO0;
            enumC0981tO0 = a11.g().O0();
        }
    }

    @Override // androidx.lifecycle.G
    public final void c() {
        this.f11917p.g().V0(this);
    }

    @Override // androidx.lifecycle.G
    public final boolean d(A a10) {
        return this.f11917p == a10;
    }

    @Override // androidx.lifecycle.G
    public final boolean e() {
        return this.f11917p.g().O0().compareTo(EnumC0981t.o) >= 0;
    }
}
