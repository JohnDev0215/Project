package k0;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes.dex */
public class d<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f3579a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f3580b;

    public d(F f3, S s3) {
        this.f3579a = f3;
        this.f3580b = s3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return c.a(dVar.f3579a, this.f3579a) && c.a(dVar.f3580b, this.f3580b);
    }

    public int hashCode() {
        F f3 = this.f3579a;
        int iHashCode = f3 == null ? 0 : f3.hashCode();
        S s3 = this.f3580b;
        return iHashCode ^ (s3 != null ? s3.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f3579a) + " " + String.valueOf(this.f3580b) + "}";
    }
}
