package androidx.lifecycle;

import java.io.Closeable;
import r.C2257p;

/* JADX INFO: loaded from: classes.dex */
public final class Y implements InterfaceC0986y, Closeable {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final X f11962m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f11963n;

    public Y(String str, X x8) {
        this.l = str;
        this.f11962m = x8;
    }

    @Override // androidx.lifecycle.InterfaceC0986y
    public final void b(A a10, EnumC0980s enumC0980s) {
        if (enumC0980s == EnumC0980s.ON_DESTROY) {
            this.f11963n = false;
            a10.g().V0(this);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    public final void g(A9.b bVar, C2257p c2257p) {
        A7.m.f(c2257p, "registry");
        A7.m.f(bVar, "lifecycle");
        if (this.f11963n) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        this.f11963n = true;
        bVar.M0(this);
        c2257p.d(this.l, this.f11962m.f11961e);
    }
}
