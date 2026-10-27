package p;

/* JADX INFO: loaded from: classes.dex */
public final class uu1 {
    public final String a;
    public final i31 b;

    public uu1(String str, i31 i31Var) {
        this.a = str;
        this.b = i31Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof uu1)) {
            return false;
        }
        uu1 uu1Var = (uu1) obj;
        return v03.b(this.a, uu1Var.a) && v03.b(this.b, uu1Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item(__typename=" + this.a + ", image=" + this.b + ')';
    }
}
