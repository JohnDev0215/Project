package b.g.i;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes.dex */
public class d<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f1085a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f1086b;

    public d(F f, S s) {
        this.f1085a = f;
        this.f1086b = s;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return c.a(dVar.f1085a, this.f1085a) && c.a(dVar.f1086b, this.f1086b);
    }

    public int hashCode() {
        F f = this.f1085a;
        int iHashCode = f == null ? 0 : f.hashCode();
        S s = this.f1086b;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f1085a) + " " + String.valueOf(this.f1086b) + "}";
    }
}
