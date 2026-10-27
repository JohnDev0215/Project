package Z8;

import S8.AbstractC0721s;
import X8.n;
import q7.InterfaceC2194h;

/* JADX INFO: loaded from: classes.dex */
public final class l extends AbstractC0721s {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final l f11019n = new l();

    @Override // S8.AbstractC0721s
    public final void L(InterfaceC2194h interfaceC2194h, Runnable runnable) {
        e.o.f11012n.g(runnable, true, false);
    }

    @Override // S8.AbstractC0721s
    public final void M(InterfaceC2194h interfaceC2194h, Runnable runnable) {
        e.o.f11012n.g(runnable, true, true);
    }

    @Override // S8.AbstractC0721s
    public final AbstractC0721s O(int i7, String str) {
        X8.a.c(i7);
        return i7 >= k.d ? str != null ? new n(this, str) : this : super.O(i7, str);
    }

    @Override // S8.AbstractC0721s
    public final String toString() {
        return "Dispatchers.IO";
    }
}
