package C0;

/* JADX INFO: loaded from: classes.dex */
public final class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i0 f897a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public G f898b;
    public final e0 c = new e0(this, 2);
    public final e0 d = new e0(this, 0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e0 f899e = new e0(this, 1);

    public f0(i0 i0Var) {
        this.f897a = i0Var;
    }

    public final G a() {
        G g7 = this.f898b;
        if (g7 != null) {
            return g7;
        }
        throw new IllegalArgumentException("SubcomposeLayoutState is not attached to SubcomposeLayout");
    }
}
