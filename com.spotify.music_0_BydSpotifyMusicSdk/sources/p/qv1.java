package p;

/* JADX INFO: loaded from: classes.dex */
public final class qv1 {
    public final mv1 a;

    public qv1(mv1 mv1Var) {
        this.a = mv1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof qv1) && v03.b(this.a, ((qv1) obj).a);
    }

    public final int hashCode() {
        mv1 mv1Var = this.a;
        if (mv1Var == null) {
            return 0;
        }
        return mv1Var.hashCode();
    }

    public final String toString() {
        return "Podcast(episodesV2=" + this.a + ')';
    }
}
