package p;

/* JADX INFO: loaded from: classes.dex */
public final class fh extends n30 {
    public final String a;
    public final String b;

    public fh(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof n30)) {
            return false;
        }
        n30 n30Var = (n30) obj;
        return this.a.equals(((fh) n30Var).a) && this.b.equals(((fh) n30Var).b);
    }

    public final int hashCode() {
        return ((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CustomAttribute{key=");
        sb.append(this.a);
        sb.append(", value=");
        return u4.m(sb, this.b, "}");
    }
}
