package p;

/* JADX INFO: loaded from: classes.dex */
public final class rp0 {
    public final String a;
    public final vp0 b;

    public rp0(String str, vp0 vp0Var) {
        this.a = str;
        this.b = vp0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof rp0)) {
            return false;
        }
        rp0 rp0Var = (rp0) obj;
        return v03.b(this.a, rp0Var.a) && v03.b(this.b, rp0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item(__typename=" + this.a + ", onUserLibraryAudiobookResponse=" + this.b + ')';
    }
}
