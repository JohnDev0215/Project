package Q5;

import z7.InterfaceC2772a;

/* JADX INFO: renamed from: Q5.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0569g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final X0 f6809a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC2772a f6810b;
    public final boolean c;

    public C0569g(X0 x02, InterfaceC2772a interfaceC2772a, boolean z6) {
        A7.m.f(interfaceC2772a, "action");
        this.f6809a = x02;
        this.f6810b = interfaceC2772a;
        this.c = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0569g)) {
            return false;
        }
        C0569g c0569g = (C0569g) obj;
        return this.f6809a == c0569g.f6809a && A7.m.a(this.f6810b, c0569g.f6810b) && this.c == c0569g.c;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.c) + ((this.f6810b.hashCode() + (this.f6809a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "BarItem(type=" + this.f6809a + ", action=" + this.f6810b + ", enabled=" + this.c + ")";
    }
}
