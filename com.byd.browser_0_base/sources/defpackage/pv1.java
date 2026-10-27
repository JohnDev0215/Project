package defpackage;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes3.dex */
public class pv1<F, S> {
    public final F a;
    public final S b;

    public pv1(F f, S s) {
        this.a = f;
        this.b = s;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof pv1)) {
            return false;
        }
        pv1 pv1Var = (pv1) obj;
        F f = pv1Var.a;
        F f2 = this.a;
        if (!(f2 == null ? f == null : f2.equals(f))) {
            return false;
        }
        S s = this.b;
        S s2 = pv1Var.b;
        return s == null ? s2 == null : s.equals(s2);
    }

    public final int hashCode() {
        F f = this.a;
        int iHashCode = f == null ? 0 : f.hashCode();
        S s = this.b;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }
}
