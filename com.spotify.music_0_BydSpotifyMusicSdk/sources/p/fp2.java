package p;

import com.spotify.ubi.model.PageInstanceId;

/* JADX INFO: loaded from: classes.dex */
public final class fp2 {
    public final String a;
    public final PageInstanceId b;
    public final String c;
    public final String d;
    public final String e;

    public fp2(String str, String str2, String str3) {
        v03.j(str3, "playContextUri");
        this.a = str;
        this.b = null;
        this.c = str2;
        this.d = "";
        this.e = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof fp2)) {
            return false;
        }
        fp2 fp2Var = (fp2) obj;
        return v03.b(this.a, fp2Var.a) && v03.b(this.b, fp2Var.b) && v03.b(this.c, fp2Var.c) && v03.b(this.d, fp2Var.d) && v03.b(this.e, fp2Var.e);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        PageInstanceId pageInstanceId = this.b;
        return this.e.hashCode() + hf2.b(this.d, hf2.b(this.c, (iHashCode + (pageInstanceId == null ? 0 : pageInstanceId.hashCode())) * 31, 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("UbiEventContext(id=");
        sb.append(this.a);
        sb.append(", pageInstanceId=");
        sb.append(this.b);
        sb.append(", playbackId=");
        sb.append(this.c);
        sb.append(", pageUri=");
        sb.append(this.d);
        sb.append(", playContextUri=");
        return hf2.d(sb, this.e, ')');
    }
}
