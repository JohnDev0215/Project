package p;

/* JADX INFO: loaded from: classes.dex */
public final class gr {
    public final String a;
    public final kz b;

    public gr(String str, kz kzVar) {
        this.a = str;
        this.b = kzVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof gr)) {
            return false;
        }
        gr grVar = (gr) obj;
        return v03.b(this.a, grVar.a) && v03.b(this.b, grVar.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "ContentRating(__typename=" + this.a + ", contentRating=" + this.b + ')';
    }
}
