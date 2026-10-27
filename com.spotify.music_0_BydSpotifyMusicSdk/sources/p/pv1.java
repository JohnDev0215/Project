package p;

/* JADX INFO: loaded from: classes.dex */
public final class pv1 {
    public final lv1 a;

    public pv1(lv1 lv1Var) {
        this.a = lv1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof pv1) && v03.b(this.a, ((pv1) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "OnContextEpisodeV2(entity=" + this.a + ')';
    }
}
