package p;

/* JADX INFO: loaded from: classes.dex */
public final class ps0 {
    public final qs0 a;

    public ps0(qs0 qs0Var) {
        this.a = qs0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ps0) && v03.b(this.a, ((ps0) obj).a);
    }

    public final int hashCode() {
        qs0 qs0Var = this.a;
        if (qs0Var == null) {
            return 0;
        }
        return qs0Var.hashCode();
    }

    public final String toString() {
        return "OnUserLibraryPodcastResponse(podcast=" + this.a + ')';
    }
}
