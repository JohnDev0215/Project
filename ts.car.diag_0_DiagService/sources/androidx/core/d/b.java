package androidx.core.d;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class b<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f475a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f476b;

    public b(F f, S s) {
        this.f475a = f;
        this.f476b = s;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return a.a(bVar.f475a, this.f475a) && a.a(bVar.f476b, this.f476b);
    }

    public int hashCode() {
        F f = this.f475a;
        int iHashCode = f == null ? 0 : f.hashCode();
        S s = this.f476b;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    @NonNull
    public String toString() {
        return "Pair{" + this.f475a + " " + this.f476b + "}";
    }
}
