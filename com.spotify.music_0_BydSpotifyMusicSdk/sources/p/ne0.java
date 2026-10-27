package p;

/* JADX INFO: loaded from: classes.dex */
public final class ne0 {
    public final ie0 a;

    public ne0(ie0 ie0Var) {
        this.a = ie0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ne0) && v03.b(this.a, ((ne0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "PodcastV2(data=" + this.a + ')';
    }
}
