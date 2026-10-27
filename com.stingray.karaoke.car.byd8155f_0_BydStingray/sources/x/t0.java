package x;

/* JADX INFO: loaded from: classes.dex */
public final class t0 implements s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f23615a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f23616b;

    public t0(Object obj, Object obj2) {
        this.f23615a = obj;
        this.f23616b = obj2;
    }

    @Override // x.s0
    public final Object a() {
        return this.f23615a;
    }

    @Override // x.s0
    public final Object c() {
        return this.f23616b;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof s0) {
            s0 s0Var = (s0) obj;
            if (A7.m.a(this.f23615a, s0Var.a())) {
                if (A7.m.a(this.f23616b, s0Var.c())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.f23615a;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Object obj2 = this.f23616b;
        return iHashCode + (obj2 != null ? obj2.hashCode() : 0);
    }
}
