package K8;

import A7.m;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f3858a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f3859b;

    public a(Object obj, Object obj2) {
        this.f3858a = obj;
        this.f3859b = obj2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return m.a(this.f3858a, aVar.f3858a) && m.a(this.f3859b, aVar.f3859b);
    }

    public final int hashCode() {
        Object obj = this.f3858a;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f3859b;
        return iHashCode + (obj2 != null ? obj2.hashCode() : 0);
    }

    public final String toString() {
        return "ApproximationBounds(lower=" + this.f3858a + ", upper=" + this.f3859b + ')';
    }
}
