package p;

/* JADX INFO: loaded from: classes.dex */
public final class pm2 {
    public final String a;
    public final i31 b;

    public pm2(String str, i31 i31Var) {
        this.a = str;
        this.b = i31Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof pm2)) {
            return false;
        }
        pm2 pm2Var = (pm2) obj;
        return v03.b(this.a, pm2Var.a) && v03.b(this.b, pm2Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "CoverArt(__typename=" + this.a + ", image=" + this.b + ')';
    }
}
