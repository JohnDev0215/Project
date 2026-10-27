package p;

/* JADX INFO: loaded from: classes.dex */
public final class no0 {
    public final go0 a;

    public no0(go0 go0Var) {
        this.a = go0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof no0) && v03.b(this.a, ((no0) obj).a);
    }

    public final int hashCode() {
        go0 go0Var = this.a;
        if (go0Var == null) {
            return 0;
        }
        return go0Var.hashCode();
    }

    public final String toString() {
        return "OnUserLibraryAlbumResponse(album=" + this.a + ')';
    }
}
