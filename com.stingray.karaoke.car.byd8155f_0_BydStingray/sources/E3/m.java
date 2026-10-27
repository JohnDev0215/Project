package E3;

/* JADX INFO: loaded from: classes.dex */
public final class m extends e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final B3.m f1742a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1743b;
    public final B3.f c;

    public m(B3.m mVar, String str, B3.f fVar) {
        this.f1742a = mVar;
        this.f1743b = str;
        this.c = fVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof m) {
            m mVar = (m) obj;
            if (A7.m.a(this.f1742a, mVar.f1742a) && A7.m.a(this.f1743b, mVar.f1743b) && this.c == mVar.c) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = this.f1742a.hashCode() * 31;
        String str = this.f1743b;
        return this.c.hashCode() + ((iHashCode + (str != null ? str.hashCode() : 0)) * 31);
    }
}
