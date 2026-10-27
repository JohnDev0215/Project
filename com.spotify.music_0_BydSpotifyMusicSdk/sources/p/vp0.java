package p;

/* JADX INFO: loaded from: classes.dex */
public final class vp0 {
    public final op0 a;

    public vp0(op0 op0Var) {
        this.a = op0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof vp0) && v03.b(this.a, ((vp0) obj).a);
    }

    public final int hashCode() {
        op0 op0Var = this.a;
        if (op0Var == null) {
            return 0;
        }
        return op0Var.hashCode();
    }

    public final String toString() {
        return "OnUserLibraryAudiobookResponse(audiobook=" + this.a + ')';
    }
}
