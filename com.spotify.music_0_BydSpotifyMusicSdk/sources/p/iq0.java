package p;

/* JADX INFO: loaded from: classes.dex */
public final class iq0 {
    public final String a;
    public final mq0 b;

    public iq0(String str, mq0 mq0Var) {
        this.a = str;
        this.b = mq0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof iq0)) {
            return false;
        }
        iq0 iq0Var = (iq0) obj;
        return v03.b(this.a, iq0Var.a) && v03.b(this.b, iq0Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "Item(__typename=" + this.a + ", onUserLibraryEpisodeResponse=" + this.b + ')';
    }
}
