package h0;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes.dex */
public class d<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f6589a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f6590b;

    public d(F f7, S s6) {
        this.f6589a = f7;
        this.f6590b = s6;
    }

    public static <A, B> d<A, B> a(A a7, B b7) {
        return new d<>(a7, b7);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return c.a(dVar.f6589a, this.f6589a) && c.a(dVar.f6590b, this.f6590b);
    }

    public int hashCode() {
        F f7 = this.f6589a;
        int iHashCode = f7 == null ? 0 : f7.hashCode();
        S s6 = this.f6590b;
        return iHashCode ^ (s6 != null ? s6.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + this.f6589a + " " + this.f6590b + "}";
    }
}
