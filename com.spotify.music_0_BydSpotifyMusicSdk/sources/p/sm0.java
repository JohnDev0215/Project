package p;

/* JADX INFO: loaded from: classes.dex */
public final class sm0 {
    public final um0 a;

    public sm0(um0 um0Var) {
        this.a = um0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof sm0) && v03.b(this.a, ((sm0) obj).a);
    }

    public final int hashCode() {
        return this.a.a.hashCode();
    }

    public final String toString() {
        return "OnAlbum(tracks=" + this.a + ')';
    }
}
