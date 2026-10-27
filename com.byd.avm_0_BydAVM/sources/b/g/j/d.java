package b.g.j;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes.dex */
public class d<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f1702a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f1703b;

    public d(F f, S s) {
        this.f1702a = f;
        this.f1703b = s;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return c.a(dVar.f1702a, this.f1702a) && c.a(dVar.f1703b, this.f1703b);
    }

    public int hashCode() {
        F f = this.f1702a;
        int iHashCode = f == null ? 0 : f.hashCode();
        S s = this.f1703b;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f1702a) + " " + String.valueOf(this.f1703b) + "}";
    }
}
