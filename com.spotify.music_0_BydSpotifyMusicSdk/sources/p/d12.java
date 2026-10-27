package p;

/* JADX INFO: loaded from: classes.dex */
public final class d12 implements rd2 {
    public final qd2 a;

    public d12(qd2 qd2Var) {
        this.a = qd2Var;
    }

    @Override // p.rd2
    public final Object a(c00 c00Var) {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d12) {
            if (v03.b(this.a, ((d12) obj).a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
