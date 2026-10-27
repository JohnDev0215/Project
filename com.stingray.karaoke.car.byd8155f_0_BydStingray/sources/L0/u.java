package L0;

import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3965a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC2785n f3966b;
    public boolean c;

    public u(String str, InterfaceC2785n interfaceC2785n) {
        this.f3965a = str;
        this.f3966b = interfaceC2785n;
    }

    public final void a(j jVar, Object obj) {
        jVar.l(this, obj);
    }

    public final String toString() {
        return "AccessibilityKey: " + this.f3965a;
    }

    public /* synthetic */ u(String str) {
        this(str, q.f3933y);
    }

    public u(String str, boolean z6, InterfaceC2785n interfaceC2785n) {
        this(str, interfaceC2785n);
        this.c = z6;
    }
}
