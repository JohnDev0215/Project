package p;

/* JADX INFO: loaded from: classes.dex */
public final class lt0 {
    public final String a;
    public final ud b;
    public final qv1 c;

    public lt0(String str, ud udVar, qv1 qv1Var) {
        v03.j(str, "__typename");
        this.a = str;
        this.b = udVar;
        this.c = qv1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof lt0)) {
            return false;
        }
        lt0 lt0Var = (lt0) obj;
        return v03.b(this.a, lt0Var.a) && v03.b(this.b, lt0Var.b) && v03.b(this.c, lt0Var.c);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        ud udVar = this.b;
        int iHashCode2 = (iHashCode + (udVar == null ? 0 : udVar.hashCode())) * 31;
        qv1 qv1Var = this.c;
        return iHashCode2 + (qv1Var != null ? qv1Var.hashCode() : 0);
    }

    public final String toString() {
        return "PodcastUnionV2(__typename=" + this.a + ", audiobook=" + this.b + ", podcast=" + this.c + ')';
    }
}
