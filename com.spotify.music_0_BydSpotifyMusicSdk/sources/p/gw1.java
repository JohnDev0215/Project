package p;

/* JADX INFO: loaded from: classes.dex */
public final class gw1 {
    public final ew1 a;

    public gw1(ew1 ew1Var) {
        this.a = ew1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof gw1) && v03.b(this.a, ((gw1) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "PodcastWrapper(data=" + this.a + ')';
    }
}
