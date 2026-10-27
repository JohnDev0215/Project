package p;

/* JADX INFO: loaded from: classes.dex */
public final class ft0 implements pq1 {
    public final gt0 a;

    public ft0(gt0 gt0Var) {
        this.a = gt0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ft0) && v03.b(this.a, ((ft0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Data(podcastUnionV2=" + this.a + ')';
    }
}
