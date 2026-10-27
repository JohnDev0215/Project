package K;

import C0.InterfaceC0115w;
import x.AbstractC2641j;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class O0 implements InterfaceC0115w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final E0 f3192a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3193b;
    public final T0.F c;
    public final InterfaceC2772a d;

    public O0(E0 e02, int i7, T0.F f10, InterfaceC2772a interfaceC2772a) {
        this.f3192a = e02;
        this.f3193b = i7;
        this.c = f10;
        this.d = interfaceC2772a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof O0)) {
            return false;
        }
        O0 o02 = (O0) obj;
        return A7.m.a(this.f3192a, o02.f3192a) && this.f3193b == o02.f3193b && A7.m.a(this.c, o02.c) && A7.m.a(this.d, o02.d);
    }

    @Override // C0.InterfaceC0115w
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        C0.V vB = j3.b(a1.a.a(j10, 0, 0, 0, Integer.MAX_VALUE, 7));
        int iMin = Math.min(vB.f871m, a1.a.g(j10));
        return m10.m0(vB.l, iMin, n7.v.l, new D.Q(iMin, 2, m10, this, vB));
    }

    public final int hashCode() {
        return this.d.hashCode() + ((this.c.hashCode() + AbstractC2641j.b(this.f3193b, this.f3192a.hashCode() * 31, 31)) * 31);
    }

    public final String toString() {
        return "VerticalScrollLayoutModifier(scrollerPosition=" + this.f3192a + ", cursorOffset=" + this.f3193b + ", transformedText=" + this.c + ", textLayoutResultProvider=" + this.d + ')';
    }
}
