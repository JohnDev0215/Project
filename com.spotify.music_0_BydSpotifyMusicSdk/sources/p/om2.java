package p;

/* JADX INFO: loaded from: classes.dex */
public final class om2 {
    public final String a;
    public final kz b;

    public om2(String str, kz kzVar) {
        this.a = str;
        this.b = kzVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof om2)) {
            return false;
        }
        om2 om2Var = (om2) obj;
        return v03.b(this.a, om2Var.a) && v03.b(this.b, om2Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "ContentRating(__typename=" + this.a + ", contentRating=" + this.b + ')';
    }
}
