package androidx.lifecycle;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import q7.InterfaceC2194h;

/* JADX INFO: renamed from: androidx.lifecycle.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0983v implements InterfaceC0986y, InterfaceC0725w {
    public final A9.b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC2194h f12010m;

    public C0983v(A9.b bVar, InterfaceC2194h interfaceC2194h) {
        A7.m.f(interfaceC2194h, "coroutineContext");
        this.l = bVar;
        this.f12010m = interfaceC2194h;
        if (bVar.O0() == EnumC0981t.l) {
            AbstractC0727y.e(interfaceC2194h, null);
        }
    }

    @Override // androidx.lifecycle.InterfaceC0986y
    public final void b(A a10, EnumC0980s enumC0980s) {
        A9.b bVar = this.l;
        if (bVar.O0().compareTo(EnumC0981t.l) <= 0) {
            bVar.V0(this);
            AbstractC0727y.e(this.f12010m, null);
        }
    }

    @Override // S8.InterfaceC0725w
    public final InterfaceC2194h getCoroutineContext() {
        return this.f12010m;
    }
}
