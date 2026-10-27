package K;

import C0.InterfaceC0115w;
import x.AbstractC2641j;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class S implements InterfaceC0115w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final E0 f3198a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3199b;
    public final T0.F c;
    public final InterfaceC2772a d;

    public S(E0 e02, int i7, T0.F f10, InterfaceC2772a interfaceC2772a) {
        this.f3198a = e02;
        this.f3199b = i7;
        this.c = f10;
        this.d = interfaceC2772a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof S)) {
            return false;
        }
        S s6 = (S) obj;
        return A7.m.a(this.f3198a, s6.f3198a) && this.f3199b == s6.f3199b && A7.m.a(this.c, s6.c) && A7.m.a(this.d, s6.d);
    }

    @Override // C0.InterfaceC0115w
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        C0.V vB = j3.b(j3.V(a1.a.g(j10)) < a1.a.h(j10) ? j10 : a1.a.a(j10, 0, Integer.MAX_VALUE, 0, 0, 13));
        int iMin = Math.min(vB.l, a1.a.h(j10));
        return m10.m0(iMin, vB.f871m, n7.v.l, new D.Q(iMin, 1, m10, this, vB));
    }

    public final int hashCode() {
        return this.d.hashCode() + ((this.c.hashCode() + AbstractC2641j.b(this.f3199b, this.f3198a.hashCode() * 31, 31)) * 31);
    }

    public final String toString() {
        return "HorizontalScrollLayoutModifier(scrollerPosition=" + this.f3198a + ", cursorOffset=" + this.f3199b + ", transformedText=" + this.c + ", textLayoutResultProvider=" + this.d + ')';
    }
}
