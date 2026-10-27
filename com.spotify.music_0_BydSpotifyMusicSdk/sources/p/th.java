package p;

/* JADX INFO: loaded from: classes.dex */
public final class th extends x30 {
    public final String a;
    public final int b;
    public final z31 c;

    public th(String str, int i, z31 z31Var) {
        this.a = str;
        this.b = i;
        this.c = z31Var;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof x30)) {
            return false;
        }
        x30 x30Var = (x30) obj;
        if (this.a.equals(((th) x30Var).a)) {
            th thVar = (th) x30Var;
            if (this.b == thVar.b && this.c.c.equals(thVar.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b) * 1000003) ^ this.c.c.hashCode();
    }

    public final String toString() {
        return "Thread{name=" + this.a + ", importance=" + this.b + ", frames=" + this.c + "}";
    }
}
