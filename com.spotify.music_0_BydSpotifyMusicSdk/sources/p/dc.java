package p;

/* JADX INFO: loaded from: classes.dex */
public final class dc {
    public final String a;
    public final wb b;

    public dc(String str, wb wbVar) {
        this.a = str;
        this.b = wbVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof dc)) {
            return false;
        }
        dc dcVar = (dc) obj;
        return v03.b(this.a, dcVar.a) && v03.b(this.b, dcVar.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "OnArtist(__typename=" + this.a + ", artist=" + this.b + ')';
    }
}
