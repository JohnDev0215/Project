package p;

/* JADX INFO: loaded from: classes.dex */
public final class ee {
    public final String a;
    public final i31 b;

    public ee(String str, i31 i31Var) {
        this.a = str;
        this.b = i31Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ee)) {
            return false;
        }
        ee eeVar = (ee) obj;
        return v03.b(this.a, eeVar.a) && v03.b(this.b, eeVar.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "CoverArt(__typename=" + this.a + ", image=" + this.b + ')';
    }
}
