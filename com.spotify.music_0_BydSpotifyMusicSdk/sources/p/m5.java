package p;

/* JADX INFO: loaded from: classes.dex */
public final class m5 {
    public final String a;
    public final String b;
    public final i5 c;
    public final h5 d;
    public final j5 e;

    public m5(String str, String str2, i5 i5Var, h5 h5Var, j5 j5Var) {
        this.a = str;
        this.b = str2;
        this.c = i5Var;
        this.d = h5Var;
        this.e = j5Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m5)) {
            return false;
        }
        m5 m5Var = (m5) obj;
        return v03.b(this.a, m5Var.a) && v03.b(this.b, m5Var.b) && v03.b(this.c, m5Var.c) && v03.b(this.d, m5Var.d) && v03.b(this.e, m5Var.e);
    }

    public final int hashCode() {
        int iB = hf2.b(this.b, this.a.hashCode() * 31, 31);
        i5 i5Var = this.c;
        int iHashCode = (this.d.a.hashCode() + ((iB + (i5Var == null ? 0 : i5Var.hashCode())) * 31)) * 31;
        j5 j5Var = this.e;
        return iHashCode + (j5Var != null ? Integer.hashCode(j5Var.a) : 0);
    }

    public final String toString() {
        return "Album(uri=" + this.a + ", name=" + this.b + ", coverArt=" + this.c + ", artists=" + this.d + ", date=" + this.e + ')';
    }
}
