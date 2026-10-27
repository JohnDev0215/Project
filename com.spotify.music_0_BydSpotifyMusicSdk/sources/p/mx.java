package p;

/* JADX INFO: loaded from: classes.dex */
public final class mx {
    public final boolean a;
    public final boolean b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;

    public mx(boolean z, boolean z2, String str, String str2, String str3, String str4) {
        this.a = z;
        this.b = z2;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof mx)) {
            return false;
        }
        mx mxVar = (mx) obj;
        return this.a == mxVar.a && this.b == mxVar.b && v03.b(this.c, mxVar.c) && v03.b(this.d, mxVar.d) && v03.b(this.e, mxVar.e) && v03.b(this.f, mxVar.f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    public final int hashCode() {
        boolean z = this.a;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = r1 * 31;
        boolean z2 = this.b;
        int i2 = (i + (z2 ? 1 : z2)) * 31;
        String str = this.c;
        int iHashCode = (i2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.d;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.e;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.f;
        return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ConnectDevice(isActive=");
        sb.append(this.a);
        sb.append(", isSelf=");
        sb.append(this.b);
        sb.append(", name=");
        sb.append(this.c);
        sb.append(", identifier=");
        sb.append(this.d);
        sb.append(", physicalIdentifier=");
        sb.append(this.e);
        sb.append(", type=");
        return hf2.d(sb, this.f, ')');
    }
}
