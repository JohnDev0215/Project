package p;

/* JADX INFO: loaded from: classes.dex */
public final class en2 {
    public final String a;
    public final fn2 b;
    public final gn2 c;

    public en2(String str, fn2 fn2Var, gn2 gn2Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = fn2Var;
        this.c = gn2Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof en2)) {
            return false;
        }
        en2 en2Var = (en2) obj;
        return v03.b(this.a, en2Var.a) && v03.b(this.b, en2Var.b) && v03.b(this.c, en2Var.c);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        fn2 fn2Var = this.b;
        int iHashCode2 = (iHashCode + (fn2Var == null ? 0 : fn2Var.hashCode())) * 31;
        gn2 gn2Var = this.c;
        return iHashCode2 + (gn2Var != null ? gn2Var.hashCode() : 0);
    }

    public final String toString() {
        return "Data(__typename=" + this.a + ", onError=" + this.b + ", onTrack=" + this.c + ')';
    }
}
