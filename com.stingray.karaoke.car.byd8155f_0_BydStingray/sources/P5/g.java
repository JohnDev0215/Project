package P5;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5356a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f5357b;

    public g(int i7, Object obj) {
        this.f5356a = i7;
        this.f5357b = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return this.f5356a == gVar.f5356a && A7.m.a(this.f5357b, gVar.f5357b);
    }

    public final int hashCode() {
        int iHashCode = Integer.hashCode(this.f5356a) * 31;
        Object obj = this.f5357b;
        return iHashCode + (obj == null ? 0 : obj.hashCode());
    }

    public final String toString() {
        return "ItemPosition(index=" + this.f5356a + ", key=" + this.f5357b + ")";
    }
}
