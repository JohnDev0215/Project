package p;

/* JADX INFO: loaded from: classes.dex */
public final class br0 {
    public final cr0 a;

    public br0(cr0 cr0Var) {
        this.a = cr0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof br0) && v03.b(this.a, ((br0) obj).a);
    }

    public final int hashCode() {
        cr0 cr0Var = this.a;
        if (cr0Var == null) {
            return 0;
        }
        return cr0Var.hashCode();
    }

    public final String toString() {
        return "OnUserLibraryTrackResponse(track=" + this.a + ')';
    }
}
