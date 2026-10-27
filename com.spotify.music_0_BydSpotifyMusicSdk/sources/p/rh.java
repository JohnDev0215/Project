package p;

/* JADX INFO: loaded from: classes.dex */
public final class rh extends u30 {
    public final String a;
    public final String b;
    public final z31 c;
    public final u30 d;
    public final int e;

    public rh(String str, String str2, z31 z31Var, u30 u30Var, int i) {
        this.a = str;
        this.b = str2;
        this.c = z31Var;
        this.d = u30Var;
        this.e = i;
    }

    public final boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof u30)) {
            return false;
        }
        rh rhVar = (rh) ((u30) obj);
        if (this.a.equals(rhVar.a) && ((str = this.b) != null ? str.equals(rhVar.b) : rhVar.b == null) && this.c.c.equals(rhVar.c)) {
            u30 u30Var = rhVar.d;
            u30 u30Var2 = this.d;
            if (u30Var2 != null ? u30Var2.equals(u30Var) : u30Var == null) {
                if (this.e == rhVar.e) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.a.hashCode() ^ 1000003) * 1000003;
        String str = this.b;
        int iHashCode2 = (((iHashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.c.c.hashCode()) * 1000003;
        u30 u30Var = this.d;
        return ((iHashCode2 ^ (u30Var != null ? u30Var.hashCode() : 0)) * 1000003) ^ this.e;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Exception{type=");
        sb.append(this.a);
        sb.append(", reason=");
        sb.append(this.b);
        sb.append(", frames=");
        sb.append(this.c);
        sb.append(", causedBy=");
        sb.append(this.d);
        sb.append(", overflowCount=");
        return u4.l(sb, this.e, "}");
    }
}
