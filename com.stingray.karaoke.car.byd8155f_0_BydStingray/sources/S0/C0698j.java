package S0;

/* JADX INFO: renamed from: S0.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0698j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final G f7843a;

    public C0698j(G g7) {
        this.f7843a = g7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C0698j) {
            return A7.m.a(this.f7843a, ((C0698j) obj).f7843a) && A7.m.a(null, null);
        }
        return false;
    }

    public final int hashCode() {
        return this.f7843a.hashCode() * 31;
    }

    public final String toString() {
        return "Key(font=" + this.f7843a + ", loaderKey=null)";
    }
}
