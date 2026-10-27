package p;

/* JADX INFO: loaded from: classes.dex */
public final class ie0 {
    public final String a;
    public final ke0 b;

    public ie0(String str, ke0 ke0Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = ke0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ie0)) {
            return false;
        }
        ie0 ie0Var = (ie0) obj;
        return v03.b(this.a, ie0Var.a) && v03.b(this.b, ie0Var.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        ke0 ke0Var = this.b;
        return iHashCode + (ke0Var == null ? 0 : ke0Var.a.hashCode());
    }

    public final String toString() {
        return "Data(__typename=" + this.a + ", onPodcast=" + this.b + ')';
    }
}
