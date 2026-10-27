package p;

/* JADX INFO: loaded from: classes.dex */
public final class oh extends z30 {
    public final y30 a;
    public final z31 b;
    public final z31 c;
    public final Boolean d;
    public final int e;

    public oh(y30 y30Var, z31 z31Var, z31 z31Var2, Boolean bool, int i) {
        this.a = y30Var;
        this.b = z31Var;
        this.c = z31Var2;
        this.d = bool;
        this.e = i;
    }

    public final boolean equals(Object obj) {
        z31 z31Var;
        z31 z31Var2;
        Boolean bool;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof z30)) {
            return false;
        }
        oh ohVar = (oh) ((z30) obj);
        return this.a.equals(ohVar.a) && ((z31Var = this.b) != null ? z31Var.c.equals(ohVar.b) : ohVar.b == null) && ((z31Var2 = this.c) != null ? z31Var2.c.equals(ohVar.c) : ohVar.c == null) && ((bool = this.d) != null ? bool.equals(ohVar.d) : ohVar.d == null) && this.e == ohVar.e;
    }

    public final int hashCode() {
        int iHashCode = (this.a.hashCode() ^ 1000003) * 1000003;
        z31 z31Var = this.b;
        int iHashCode2 = (iHashCode ^ (z31Var == null ? 0 : z31Var.c.hashCode())) * 1000003;
        z31 z31Var2 = this.c;
        int iHashCode3 = (iHashCode2 ^ (z31Var2 == null ? 0 : z31Var2.c.hashCode())) * 1000003;
        Boolean bool = this.d;
        return ((iHashCode3 ^ (bool != null ? bool.hashCode() : 0)) * 1000003) ^ this.e;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Application{execution=");
        sb.append(this.a);
        sb.append(", customAttributes=");
        sb.append(this.b);
        sb.append(", internalKeys=");
        sb.append(this.c);
        sb.append(", background=");
        sb.append(this.d);
        sb.append(", uiOrientation=");
        return u4.l(sb, this.e, "}");
    }
}
