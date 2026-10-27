package p;

/* JADX INFO: loaded from: classes.dex */
public final class si {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final int e;
    public final g90 f;

    public si(String str, String str2, String str3, String str4, int i, g90 g90Var) {
        if (str == null) {
            throw new NullPointerException("Null appIdentifier");
        }
        this.a = str;
        if (str2 == null) {
            throw new NullPointerException("Null versionCode");
        }
        this.b = str2;
        if (str3 == null) {
            throw new NullPointerException("Null versionName");
        }
        this.c = str3;
        if (str4 == null) {
            throw new NullPointerException("Null installUuid");
        }
        this.d = str4;
        this.e = i;
        if (g90Var == null) {
            throw new NullPointerException("Null developmentPlatformProvider");
        }
        this.f = g90Var;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof si)) {
            return false;
        }
        si siVar = (si) obj;
        return this.a.equals(siVar.a) && this.b.equals(siVar.b) && this.c.equals(siVar.c) && this.d.equals(siVar.d) && this.e == siVar.e && this.f.equals(siVar.f);
    }

    public final int hashCode() {
        return ((((((((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e) * 1000003) ^ this.f.hashCode();
    }

    public final String toString() {
        return "AppData{appIdentifier=" + this.a + ", versionCode=" + this.b + ", versionName=" + this.c + ", installUuid=" + this.d + ", deliveryMechanism=" + this.e + ", developmentPlatformProvider=" + this.f + "}";
    }
}
