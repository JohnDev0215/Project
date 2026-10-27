package p;

/* JADX INFO: loaded from: classes.dex */
public final class bf0 {
    public final String a;
    public final pe0 b;

    public bf0(String str, pe0 pe0Var) {
        this.a = str;
        this.b = pe0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof bf0)) {
            return false;
        }
        bf0 bf0Var = (bf0) obj;
        return v03.b(this.a, bf0Var.a) && v03.b(this.b, bf0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "OnEpisode(__typename=" + this.a + ", episode=" + this.b + ')';
    }
}
