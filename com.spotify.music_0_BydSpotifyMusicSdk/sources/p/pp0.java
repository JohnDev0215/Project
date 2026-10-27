package p;

/* JADX INFO: loaded from: classes.dex */
public final class pp0 {
    public final String a;
    public final up0 b;

    public pp0(String str, up0 up0Var) {
        this.a = str;
        this.b = up0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof pp0)) {
            return false;
        }
        pp0 pp0Var = (pp0) obj;
        return v03.b(this.a, pp0Var.a) && v03.b(this.b, pp0Var.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Audiobooks(__typename=" + this.a + ", onUserLibraryAudiobookPage=" + this.b + ')';
    }
}
