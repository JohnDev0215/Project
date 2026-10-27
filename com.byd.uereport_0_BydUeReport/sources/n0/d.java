package n0;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes.dex */
public class d<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f6506a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f6507b;

    public d(F f8, S s7) {
        this.f6506a = f8;
        this.f6507b = s7;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return c.a(dVar.f6506a, this.f6506a) && c.a(dVar.f6507b, this.f6507b);
    }

    public int hashCode() {
        F f8 = this.f6506a;
        int iHashCode = f8 == null ? 0 : f8.hashCode();
        S s7 = this.f6507b;
        return iHashCode ^ (s7 != null ? s7.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f6506a) + " " + String.valueOf(this.f6507b) + "}";
    }
}
