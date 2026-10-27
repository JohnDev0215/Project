package p;

/* JADX INFO: loaded from: classes.dex */
public final class bh extends g40 {
    public final String b;
    public final String c;
    public final int d;
    public final String e;
    public final String f;
    public final String g;
    public final f40 h;
    public final p30 i;
    public final m30 j;

    public bh(String str, String str2, int i, String str3, String str4, String str5, f40 f40Var, p30 p30Var, m30 m30Var) {
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = f40Var;
        this.i = p30Var;
        this.j = m30Var;
    }

    public final uy2 a() {
        uy2 uy2Var = new uy2();
        uy2Var.a = this.b;
        uy2Var.b = this.c;
        uy2Var.c = Integer.valueOf(this.d);
        uy2Var.d = this.e;
        uy2Var.e = this.f;
        uy2Var.f = this.g;
        uy2Var.g = this.h;
        uy2Var.h = this.i;
        uy2Var.i = this.j;
        return uy2Var;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof g40)) {
            return false;
        }
        bh bhVar = (bh) ((g40) obj);
        if (this.b.equals(bhVar.b)) {
            if (this.c.equals(bhVar.c) && this.d == bhVar.d && this.e.equals(bhVar.e) && this.f.equals(bhVar.f) && this.g.equals(bhVar.g)) {
                f40 f40Var = bhVar.h;
                f40 f40Var2 = this.h;
                if (f40Var2 != null ? f40Var2.equals(f40Var) : f40Var == null) {
                    p30 p30Var = bhVar.i;
                    p30 p30Var2 = this.i;
                    if (p30Var2 != null ? p30Var2.equals(p30Var) : p30Var == null) {
                        m30 m30Var = bhVar.j;
                        m30 m30Var2 = this.j;
                        if (m30Var2 == null) {
                            if (m30Var == null) {
                                return true;
                            }
                        } else if (m30Var2.equals(m30Var)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (((((((((((this.b.hashCode() ^ 1000003) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d) * 1000003) ^ this.e.hashCode()) * 1000003) ^ this.f.hashCode()) * 1000003) ^ this.g.hashCode()) * 1000003;
        f40 f40Var = this.h;
        int iHashCode2 = (iHashCode ^ (f40Var == null ? 0 : f40Var.hashCode())) * 1000003;
        p30 p30Var = this.i;
        int iHashCode3 = (iHashCode2 ^ (p30Var == null ? 0 : p30Var.hashCode())) * 1000003;
        m30 m30Var = this.j;
        return iHashCode3 ^ (m30Var != null ? m30Var.hashCode() : 0);
    }

    public final String toString() {
        return "CrashlyticsReport{sdkVersion=" + this.b + ", gmpAppId=" + this.c + ", platform=" + this.d + ", installationUuid=" + this.e + ", buildVersion=" + this.f + ", displayVersion=" + this.g + ", session=" + this.h + ", ndkPayload=" + this.i + ", appExitInfo=" + this.j + "}";
    }
}
