package p;

/* JADX INFO: loaded from: classes.dex */
public final class js0 {
    public final String a;
    public final ns0 b;

    public js0(String str, ns0 ns0Var) {
        this.a = str;
        this.b = ns0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof js0)) {
            return false;
        }
        js0 js0Var = (js0) obj;
        return v03.b(this.a, js0Var.a) && v03.b(this.b, js0Var.b);
    }

    public final int hashCode() {
        return Integer.hashCode(this.b.a) + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Episodes(__typename=" + this.a + ", onUserLibraryEpisodePage=" + this.b + ')';
    }
}
