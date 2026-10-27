package p;

/* JADX INFO: loaded from: classes.dex */
public final class en0 {
    public final String a;
    public final wb b;

    public en0(String str, wb wbVar) {
        this.a = str;
        this.b = wbVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof en0)) {
            return false;
        }
        en0 en0Var = (en0) obj;
        return v03.b(this.a, en0Var.a) && v03.b(this.b, en0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "OnArtist(__typename=" + this.a + ", artist=" + this.b + ')';
    }
}
