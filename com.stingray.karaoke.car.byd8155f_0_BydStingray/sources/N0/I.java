package N0;

/* JADX INFO: loaded from: classes.dex */
public final class I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final D f4352a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final D f4353b;
    public final D c;
    public final D d;

    public I(D d, D d10, D d11, D d12) {
        this.f4352a = d;
        this.f4353b = d10;
        this.c = d11;
        this.d = d12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof I)) {
            return false;
        }
        I i7 = (I) obj;
        return A7.m.a(this.f4352a, i7.f4352a) && A7.m.a(this.f4353b, i7.f4353b) && A7.m.a(this.c, i7.c) && A7.m.a(this.d, i7.d);
    }

    public final int hashCode() {
        D d = this.f4352a;
        int iHashCode = (d != null ? d.hashCode() : 0) * 31;
        D d10 = this.f4353b;
        int iHashCode2 = (iHashCode + (d10 != null ? d10.hashCode() : 0)) * 31;
        D d11 = this.c;
        int iHashCode3 = (iHashCode2 + (d11 != null ? d11.hashCode() : 0)) * 31;
        D d12 = this.d;
        return iHashCode3 + (d12 != null ? d12.hashCode() : 0);
    }
}
