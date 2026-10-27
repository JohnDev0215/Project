package D;

import C0.InterfaceC0108o;
import java.util.List;

/* JADX INFO: renamed from: D.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0141x implements C0.K, N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0125g f1117a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g0.e f1118b;

    public C0141x(InterfaceC0125g interfaceC0125g, g0.e eVar) {
        this.f1117a = interfaceC0125g;
        this.f1118b = eVar;
    }

    @Override // C0.K
    public final int a(InterfaceC0108o interfaceC0108o, List list, int i7) {
        int iO = interfaceC0108o.O(this.f1117a.a());
        if (list.isEmpty()) {
            return 0;
        }
        int iMin = Math.min((list.size() - 1) * iO, i7);
        int size = list.size();
        int iMax = 0;
        float f10 = 0.0f;
        for (int i10 = 0; i10 < size; i10++) {
            C0.J j3 = (C0.J) list.get(i10);
            float fE = AbstractC0120b.e(AbstractC0120b.d(j3));
            if (fE == 0.0f) {
                int iMin2 = Math.min(j3.c(Integer.MAX_VALUE), i7 == Integer.MAX_VALUE ? Integer.MAX_VALUE : i7 - iMin);
                iMin += iMin2;
                iMax = Math.max(iMax, j3.P(iMin2));
            } else if (fE > 0.0f) {
                f10 += fE;
            }
        }
        int iRound = f10 == 0.0f ? 0 : i7 == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.round(Math.max(i7 - iMin, 0) / f10);
        int size2 = list.size();
        for (int i11 = 0; i11 < size2; i11++) {
            C0.J j10 = (C0.J) list.get(i11);
            float fE2 = AbstractC0120b.e(AbstractC0120b.d(j10));
            if (fE2 > 0.0f) {
                iMax = Math.max(iMax, j10.P(iRound != Integer.MAX_VALUE ? Math.round(iRound * fE2) : Integer.MAX_VALUE));
            }
        }
        return iMax;
    }

    @Override // D.N
    public final int b(C0.V v10) {
        return v10.l;
    }

    @Override // D.N
    public final int c(C0.V v10) {
        return v10.f871m;
    }

    @Override // C0.K
    public final int d(InterfaceC0108o interfaceC0108o, List list, int i7) {
        int iO = interfaceC0108o.O(this.f1117a.a());
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        int iMax = 0;
        int i10 = 0;
        float f10 = 0.0f;
        for (int i11 = 0; i11 < size; i11++) {
            C0.J j3 = (C0.J) list.get(i11);
            float fE = AbstractC0120b.e(AbstractC0120b.d(j3));
            int iC = j3.c(i7);
            if (fE == 0.0f) {
                i10 += iC;
            } else if (fE > 0.0f) {
                f10 += fE;
                iMax = Math.max(iMax, Math.round(iC / fE));
            }
        }
        return ((list.size() - 1) * iO) + Math.round(iMax * f10) + i10;
    }

    @Override // D.N
    public final long e(int i7, int i10, int i11, boolean z6) {
        return AbstractC0139v.b(i7, i10, i11, z6);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0141x)) {
            return false;
        }
        C0141x c0141x = (C0141x) obj;
        return A7.m.a(this.f1117a, c0141x.f1117a) && this.f1118b.equals(c0141x.f1118b);
    }

    @Override // D.N
    public final C0.L f(C0.V[] vArr, C0.M m10, int[] iArr, int i7, int i10, int[] iArr2, int i11, int i12, int i13) {
        return m10.m0(i10, i7, n7.v.l, new C0140w(vArr, this, i10, m10, iArr));
    }

    @Override // D.N
    public final void g(int i7, int[] iArr, int[] iArr2, C0.M m10) {
        this.f1117a.c(m10, i7, iArr, iArr2);
    }

    @Override // C0.K
    public final C0.L h(C0.M m10, List list, long j3) {
        return AbstractC0120b.f(this, a1.a.i(j3), a1.a.j(j3), a1.a.g(j3), a1.a.h(j3), m10.O(this.f1117a.a()), m10, list, new C0.V[list.size()], 0, list.size(), null, 0);
    }

    public final int hashCode() {
        return Float.hashCode(this.f1118b.f14676a) + (this.f1117a.hashCode() * 31);
    }

    @Override // C0.K
    public final int i(InterfaceC0108o interfaceC0108o, List list, int i7) {
        int iO = interfaceC0108o.O(this.f1117a.a());
        if (list.isEmpty()) {
            return 0;
        }
        int size = list.size();
        int iMax = 0;
        int i10 = 0;
        float f10 = 0.0f;
        for (int i11 = 0; i11 < size; i11++) {
            C0.J j3 = (C0.J) list.get(i11);
            float fE = AbstractC0120b.e(AbstractC0120b.d(j3));
            int iA0 = j3.a0(i7);
            if (fE == 0.0f) {
                i10 += iA0;
            } else if (fE > 0.0f) {
                f10 += fE;
                iMax = Math.max(iMax, Math.round(iA0 / fE));
            }
        }
        return ((list.size() - 1) * iO) + Math.round(iMax * f10) + i10;
    }

    @Override // C0.K
    public final int j(InterfaceC0108o interfaceC0108o, List list, int i7) {
        int iO = interfaceC0108o.O(this.f1117a.a());
        if (list.isEmpty()) {
            return 0;
        }
        int iMin = Math.min((list.size() - 1) * iO, i7);
        int size = list.size();
        int iMax = 0;
        float f10 = 0.0f;
        for (int i10 = 0; i10 < size; i10++) {
            C0.J j3 = (C0.J) list.get(i10);
            float fE = AbstractC0120b.e(AbstractC0120b.d(j3));
            if (fE == 0.0f) {
                int iMin2 = Math.min(j3.c(Integer.MAX_VALUE), i7 == Integer.MAX_VALUE ? Integer.MAX_VALUE : i7 - iMin);
                iMin += iMin2;
                iMax = Math.max(iMax, j3.V(iMin2));
            } else if (fE > 0.0f) {
                f10 += fE;
            }
        }
        int iRound = f10 == 0.0f ? 0 : i7 == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.round(Math.max(i7 - iMin, 0) / f10);
        int size2 = list.size();
        for (int i11 = 0; i11 < size2; i11++) {
            C0.J j10 = (C0.J) list.get(i11);
            float fE2 = AbstractC0120b.e(AbstractC0120b.d(j10));
            if (fE2 > 0.0f) {
                iMax = Math.max(iMax, j10.V(iRound != Integer.MAX_VALUE ? Math.round(iRound * fE2) : Integer.MAX_VALUE));
            }
        }
        return iMax;
    }

    public final String toString() {
        return "ColumnMeasurePolicy(verticalArrangement=" + this.f1117a + ", horizontalAlignment=" + this.f1118b + ')';
    }
}
