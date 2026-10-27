package p;

/* JADX INFO: loaded from: classes.dex */
public final class a92 {
    public final String a;
    public final me b;

    public a92(String str, me meVar) {
        this.a = str;
        this.b = meVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a92)) {
            return false;
        }
        a92 a92Var = (a92) obj;
        return v03.b(this.a, a92Var.a) && v03.b(this.b, a92Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "OnAudiobookResponseWrapper(__typename=" + this.a + ", audiobookWrapper=" + this.b + ')';
    }
}
