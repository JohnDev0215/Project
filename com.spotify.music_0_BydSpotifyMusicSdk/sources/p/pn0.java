package p;

/* JADX INFO: loaded from: classes.dex */
public final class pn0 {
    public final ln0 a;

    public pn0(ln0 ln0Var) {
        this.a = ln0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof pn0) && v03.b(this.a, ((pn0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "OnArtist(discography=" + this.a + ')';
    }
}
