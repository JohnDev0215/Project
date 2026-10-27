package c.g.j;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes.dex */
public class d<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f2269a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f2270b;

    public d(F f, S s) {
        this.f2269a = f;
        this.f2270b = s;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return c.a(dVar.f2269a, this.f2269a) && c.a(dVar.f2270b, this.f2270b);
    }

    public int hashCode() {
        F f = this.f2269a;
        int iHashCode = f == null ? 0 : f.hashCode();
        S s = this.f2270b;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f2269a) + " " + String.valueOf(this.f2270b) + "}";
    }
}
