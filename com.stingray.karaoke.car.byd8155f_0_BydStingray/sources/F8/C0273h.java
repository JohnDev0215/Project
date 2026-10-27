package F8;

/* JADX INFO: renamed from: F8.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0273h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final R7.h f2468a;

    public C0273h(R7.h hVar) {
        A7.m.f(hVar, "annotations");
        this.f2468a = hVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0273h) {
            return A7.m.a(((C0273h) obj).f2468a, this.f2468a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f2468a.hashCode();
    }
}
