package p;

import com.spotify.automotive.ubi.Referrer;

/* JADX INFO: loaded from: classes.dex */
public final class rp2 {
    public final String a;
    public final Integer b;
    public final vp2 c;
    public final Referrer d;

    public rp2(String str, Integer num, vp2 vp2Var, Referrer referrer) {
        v03.j(str, "uri");
        this.a = str;
        this.b = num;
        this.c = vp2Var;
        this.d = referrer;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof rp2)) {
            return false;
        }
        rp2 rp2Var = (rp2) obj;
        return v03.b(this.a, rp2Var.a) && v03.b(this.b, rp2Var.b) && v03.b(null, null) && v03.b(this.c, rp2Var.c) && this.d == rp2Var.d;
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        Integer num = this.b;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 961;
        vp2 vp2Var = this.c;
        int iHashCode3 = (iHashCode2 + (vp2Var == null ? 0 : vp2Var.a.hashCode())) * 31;
        Referrer referrer = this.d;
        return iHashCode3 + (referrer != null ? referrer.hashCode() : 0);
    }

    public final String toString() {
        return "UbiInteractionInfo(uri=" + this.a + ", position=" + this.b + ", sectionInfo=null, parentInfo=" + this.c + ", referrer=" + this.d + ')';
    }
}
