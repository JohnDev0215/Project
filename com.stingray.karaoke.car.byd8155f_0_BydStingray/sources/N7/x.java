package n7;

/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f20159a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f20160b;

    public x(int i7, Object obj) {
        this.f20159a = i7;
        this.f20160b = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        return this.f20159a == xVar.f20159a && A7.m.a(this.f20160b, xVar.f20160b);
    }

    public final int hashCode() {
        int iHashCode = Integer.hashCode(this.f20159a) * 31;
        Object obj = this.f20160b;
        return iHashCode + (obj == null ? 0 : obj.hashCode());
    }

    public final String toString() {
        return "IndexedValue(index=" + this.f20159a + ", value=" + this.f20160b + ')';
    }
}
