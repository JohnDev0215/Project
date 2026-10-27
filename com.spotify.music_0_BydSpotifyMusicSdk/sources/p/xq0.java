package p;

/* JADX INFO: loaded from: classes.dex */
public final class xq0 {
    public final String a;
    public final br0 b;

    public xq0(String str, br0 br0Var) {
        this.a = str;
        this.b = br0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof xq0)) {
            return false;
        }
        xq0 xq0Var = (xq0) obj;
        return v03.b(this.a, xq0Var.a) && v03.b(this.b, xq0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item(__typename=" + this.a + ", onUserLibraryTrackResponse=" + this.b + ')';
    }
}
