package d2;

import A7.m;
import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import q7.InterfaceC2194h;

/* JADX INFO: renamed from: d2.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1063a implements AutoCloseable, InterfaceC0725w {
    public final InterfaceC2194h l;

    public C1063a(InterfaceC2194h interfaceC2194h) {
        m.f(interfaceC2194h, "coroutineContext");
        this.l = interfaceC2194h;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        AbstractC0727y.e(this.l, null);
    }

    @Override // S8.InterfaceC0725w
    public final InterfaceC2194h getCoroutineContext() {
        return this.l;
    }
}
