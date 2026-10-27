package b.g.j;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes.dex */
public class d<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f2292a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f2293b;

    public d(F f, S s) {
        this.f2292a = f;
        this.f2293b = s;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return c.a(dVar.f2292a, this.f2292a) && c.a(dVar.f2293b, this.f2293b);
    }

    public int hashCode() {
        F f = this.f2292a;
        int iHashCode = f == null ? 0 : f.hashCode();
        S s = this.f2293b;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f2292a) + " " + String.valueOf(this.f2293b) + "}";
    }
}
