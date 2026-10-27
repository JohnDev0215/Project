package p;

/* JADX INFO: loaded from: classes.dex */
public final class mq0 {
    public final gq0 a;

    public mq0(gq0 gq0Var) {
        this.a = gq0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof mq0) && v03.b(this.a, ((mq0) obj).a);
    }

    public final int hashCode() {
        gq0 gq0Var = this.a;
        if (gq0Var == null) {
            return 0;
        }
        return gq0Var.hashCode();
    }

    public final String toString() {
        return "OnUserLibraryEpisodeResponse(episode=" + this.a + ')';
    }
}
