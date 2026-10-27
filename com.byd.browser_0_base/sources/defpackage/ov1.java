package defpackage;

import java.io.Serializable;

/* JADX INFO: compiled from: Tuples.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ov1<A, B> implements Serializable {
    public final A e;
    public final B f;

    public ov1(A a, B b) {
        this.e = a;
        this.f = b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ov1)) {
            return false;
        }
        ov1 ov1Var = (ov1) obj;
        return i21.a(this.e, ov1Var.e) && i21.a(this.f, ov1Var.f);
    }

    public final int hashCode() {
        A a = this.e;
        int iHashCode = (a == null ? 0 : a.hashCode()) * 31;
        B b = this.f;
        return iHashCode + (b != null ? b.hashCode() : 0);
    }

    public final String toString() {
        return "(" + this.e + ", " + this.f + ')';
    }
}
