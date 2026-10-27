package b.g.j;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes.dex */
public class d<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f2659a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f2660b;

    public d(F f2, S s) {
        this.f2659a = f2;
        this.f2660b = s;
    }

    public static <A, B> d<A, B> a(A a2, B b2) {
        return new d<>(a2, b2);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return c.a(dVar.f2659a, this.f2659a) && c.a(dVar.f2660b, this.f2660b);
    }

    public int hashCode() {
        F f2 = this.f2659a;
        int iHashCode = f2 == null ? 0 : f2.hashCode();
        S s = this.f2660b;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f2659a) + " " + String.valueOf(this.f2660b) + "}";
    }
}
