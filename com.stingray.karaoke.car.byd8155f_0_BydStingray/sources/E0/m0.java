package E0;

/* JADX INFO: loaded from: classes.dex */
public final class m0 implements j0 {
    public final C0.L l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Q f1688m;

    public m0(C0.L l, Q q4) {
        this.l = l;
        this.f1688m = q4;
    }

    @Override // E0.j0
    public final boolean A() {
        return this.f1688m.w0().D();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m0)) {
            return false;
        }
        m0 m0Var = (m0) obj;
        return A7.m.a(this.l, m0Var.l) && A7.m.a(this.f1688m, m0Var.f1688m);
    }

    public final int hashCode() {
        return this.f1688m.hashCode() + (this.l.hashCode() * 31);
    }

    public final String toString() {
        return "PlaceableResult(result=" + this.l + ", placeable=" + this.f1688m + ')';
    }
}
