package p;

import com.spotify.automotive.ubi.Referrer;

/* JADX INFO: loaded from: classes.dex */
public final class np2 {
    public final String a;
    public final Referrer b;

    public np2(String str, Referrer referrer) {
        this.a = str;
        this.b = referrer;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof np2)) {
            return false;
        }
        np2 np2Var = (np2) obj;
        return v03.b(this.a, np2Var.a) && this.b == np2Var.b;
    }

    public final int hashCode() {
        String str = this.a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Referrer referrer = this.b;
        return iHashCode + (referrer != null ? referrer.hashCode() : 0);
    }

    public final String toString() {
        return "UbiImpressionInfo(uri=" + this.a + ", referrer=" + this.b + ')';
    }
}
