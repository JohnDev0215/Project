package p;

/* JADX INFO: loaded from: classes.dex */
public final class gn2 {
    public final String a;
    public final um2 b;

    public gn2(String str, um2 um2Var) {
        this.a = str;
        this.b = um2Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof gn2)) {
            return false;
        }
        gn2 gn2Var = (gn2) obj;
        return v03.b(this.a, gn2Var.a) && v03.b(this.b, gn2Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "OnTrack(__typename=" + this.a + ", track=" + this.b + ')';
    }
}
