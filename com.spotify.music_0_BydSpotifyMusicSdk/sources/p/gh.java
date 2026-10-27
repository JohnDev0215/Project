package p;

/* JADX INFO: loaded from: classes.dex */
public final class gh extends p30 {
    public final z31 a;
    public final String b;

    public gh(z31 z31Var, String str) {
        this.a = z31Var;
        this.b = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof p30)) {
            return false;
        }
        p30 p30Var = (p30) obj;
        if (this.a.c.equals(((gh) p30Var).a)) {
            String str = this.b;
            if (str == null) {
                if (((gh) p30Var).b == null) {
                    return true;
                }
            } else if (str.equals(((gh) p30Var).b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.a.c.hashCode() ^ 1000003) * 1000003;
        String str = this.b;
        return iHashCode ^ (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FilesPayload{files=");
        sb.append(this.a);
        sb.append(", orgId=");
        return u4.m(sb, this.b, "}");
    }
}
