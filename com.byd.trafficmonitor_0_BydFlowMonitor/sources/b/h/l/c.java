package b.h.l;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes.dex */
public class c<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f2575a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f2576b;

    public c(F f2, S s) {
        this.f2575a = f2;
        this.f2576b = s;
    }

    public static <A, B> c<A, B> a(A a2, B b2) {
        return new c<>(a2, b2);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return b.a(cVar.f2575a, this.f2575a) && b.a(cVar.f2576b, this.f2576b);
    }

    public int hashCode() {
        F f2 = this.f2575a;
        int iHashCode = f2 == null ? 0 : f2.hashCode();
        S s = this.f2576b;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f2575a) + " " + String.valueOf(this.f2576b) + "}";
    }
}
