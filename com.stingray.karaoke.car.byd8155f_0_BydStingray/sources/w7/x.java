package W7;

import f8.InterfaceC1266b;
import java.util.Collection;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public final class x extends r implements InterfaceC1266b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2058c f9900a;

    public x(C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        this.f9900a = c2058c;
    }

    @Override // f8.InterfaceC1266b
    public final C0840d a(C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof x) {
            if (A7.m.a(this.f9900a, ((x) obj).f9900a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f9900a.hashCode();
    }

    @Override // f8.InterfaceC1266b
    public final /* bridge */ /* synthetic */ Collection k() {
        return n7.u.l;
    }

    public final String toString() {
        return x.class.getName() + ": " + this.f9900a;
    }
}
