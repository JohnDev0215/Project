package g.a.b.j0;

/* JADX INFO: loaded from: classes.dex */
public class e0 extends b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public f0 f5019d;

    public e0(boolean z, f0 f0Var) {
        super(z);
        this.f5019d = f0Var;
    }

    public f0 b() {
        return this.f5019d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof e0)) {
            return false;
        }
        f0 f0Var = this.f5019d;
        f0 f0VarB = ((e0) obj).b();
        return f0Var == null ? f0VarB == null : f0Var.equals(f0VarB);
    }

    public int hashCode() {
        f0 f0Var = this.f5019d;
        if (f0Var != null) {
            return f0Var.hashCode();
        }
        return 0;
    }
}
