package p;

/* JADX INFO: loaded from: classes.dex */
public final class op0 {
    public final String a;
    public final me b;

    public op0(String str, me meVar) {
        this.a = str;
        this.b = meVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof op0)) {
            return false;
        }
        op0 op0Var = (op0) obj;
        return v03.b(this.a, op0Var.a) && v03.b(this.b, op0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Audiobook(__typename=" + this.a + ", audiobookWrapper=" + this.b + ')';
    }
}
