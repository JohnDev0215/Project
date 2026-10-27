package E1;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1715a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1716b;

    public b(Object obj, Object obj2) {
        this.f1715a = obj;
        this.f1716b = obj2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Objects.equals(bVar.f1715a, this.f1715a) && Objects.equals(bVar.f1716b, this.f1716b);
    }

    public final int hashCode() {
        Object obj = this.f1715a;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.f1716b;
        return iHashCode ^ (obj2 != null ? obj2.hashCode() : 0);
    }

    public final String toString() {
        return "Pair{" + this.f1715a + " " + this.f1716b + "}";
    }
}
