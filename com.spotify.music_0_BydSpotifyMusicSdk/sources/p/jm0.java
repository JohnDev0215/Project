package p;

/* JADX INFO: loaded from: classes.dex */
public final class jm0 implements pq1 {
    public final im0 a;

    public jm0(im0 im0Var) {
        this.a = im0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof jm0) && v03.b(this.a, ((jm0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Data(albumUnion=" + this.a + ')';
    }
}
