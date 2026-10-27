package defpackage;

import java.util.Objects;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes.dex */
public final class qv1<F, S> {
    public final F a;
    public final S b;

    public qv1(F f, S s) {
        this.a = f;
        this.b = s;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof qv1)) {
            return false;
        }
        qv1 qv1Var = (qv1) obj;
        return Objects.equals(qv1Var.a, this.a) && Objects.equals(qv1Var.b, this.b);
    }

    public final int hashCode() {
        F f = this.a;
        int iHashCode = f == null ? 0 : f.hashCode();
        S s = this.b;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    public final String toString() {
        return "Pair{" + this.a + " " + this.b + "}";
    }
}
