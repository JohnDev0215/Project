package a.g.j;

/* JADX INFO: loaded from: classes.dex */
public class d<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f366a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f367b;

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return c.a(dVar.f366a, this.f366a) && c.a(dVar.f367b, this.f367b);
    }

    public int hashCode() {
        F f = this.f366a;
        int iHashCode = f == null ? 0 : f.hashCode();
        S s = this.f367b;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f366a) + " " + String.valueOf(this.f367b) + "}";
    }
}
