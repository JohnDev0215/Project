package b.g.j;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes.dex */
public class d<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f2504a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f2505b;

    public d(F f2, S s) {
        this.f2504a = f2;
        this.f2505b = s;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return c.a(dVar.f2504a, this.f2504a) && c.a(dVar.f2505b, this.f2505b);
    }

    public int hashCode() {
        F f2 = this.f2504a;
        int iHashCode = f2 == null ? 0 : f2.hashCode();
        S s = this.f2505b;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f2504a) + " " + String.valueOf(this.f2505b) + "}";
    }
}
