package p;

/* JADX INFO: loaded from: classes.dex */
public final class eh extends l30 {
    public final String a;
    public final String b;
    public final String c;

    public eh(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof l30)) {
            return false;
        }
        l30 l30Var = (l30) obj;
        if (this.a.equals(((eh) l30Var).a)) {
            eh ehVar = (eh) l30Var;
            if (this.b.equals(ehVar.b) && this.c.equals(ehVar.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BuildIdMappingForArch{arch=");
        sb.append(this.a);
        sb.append(", libraryName=");
        sb.append(this.b);
        sb.append(", buildId=");
        return u4.m(sb, this.c, "}");
    }
}
