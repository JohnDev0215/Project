package p;

/* JADX INFO: loaded from: classes.dex */
public final class q82 {
    public final String a;
    public final me b;

    public q82(String str, me meVar) {
        this.a = str;
        this.b = meVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q82)) {
            return false;
        }
        q82 q82Var = (q82) obj;
        return v03.b(this.a, q82Var.a) && v03.b(this.b, q82Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item3(__typename=" + this.a + ", audiobookWrapper=" + this.b + ')';
    }
}
