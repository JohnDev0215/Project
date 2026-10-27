package p;

/* JADX INFO: loaded from: classes.dex */
public final class kt0 implements pq1 {
    public final lt0 a;

    public kt0(lt0 lt0Var) {
        this.a = lt0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof kt0) && v03.b(this.a, ((kt0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Data(podcastUnionV2=" + this.a + ')';
    }
}
