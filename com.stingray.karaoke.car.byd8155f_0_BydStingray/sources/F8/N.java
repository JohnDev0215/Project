package F8;

import e8.C1236a;

/* JADX INFO: loaded from: classes.dex */
public final class N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Q7.Q f2448a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1236a f2449b;

    public N(Q7.Q q4, C1236a c1236a) {
        A7.m.f(q4, "typeParameter");
        A7.m.f(c1236a, "typeAttr");
        this.f2448a = q4;
        this.f2449b = c1236a;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof N)) {
            return false;
        }
        N n10 = (N) obj;
        return A7.m.a(n10.f2448a, this.f2448a) && A7.m.a(n10.f2449b, this.f2449b);
    }

    public final int hashCode() {
        int iHashCode = this.f2448a.hashCode();
        return this.f2449b.hashCode() + (iHashCode * 31) + iHashCode;
    }

    public final String toString() {
        return "DataToEraseUpperBound(typeParameter=" + this.f2448a + ", typeAttr=" + this.f2449b + ')';
    }
}
