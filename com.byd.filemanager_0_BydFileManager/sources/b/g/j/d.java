package b.g.j;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes.dex */
public class d<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f1936a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f1937b;

    public d(F f, S s) {
        this.f1936a = f;
        this.f1937b = s;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return c.a(dVar.f1936a, this.f1936a) && c.a(dVar.f1937b, this.f1937b);
    }

    public int hashCode() {
        F f = this.f1936a;
        int iHashCode = f == null ? 0 : f.hashCode();
        S s = this.f1937b;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f1936a) + " " + String.valueOf(this.f1937b) + "}";
    }
}
