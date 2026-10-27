package T;

import E0.C0171v;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z0 f8389a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f8390b;
    public final int c;
    public final Object[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8391e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f8392f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f8393g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f8394h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f8395i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C0171v f8396j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f8397k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8398m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f8399n;

    public y0(z0 z0Var) {
        this.f8389a = z0Var;
        this.f8390b = z0Var.l;
        int i7 = z0Var.f8400m;
        this.c = i7;
        this.d = z0Var.f8401n;
        this.f8391e = z0Var.o;
        this.f8394h = i7;
        this.f8395i = -1;
        this.f8396j = new C0171v();
    }

    public final C0733c a(int i7) {
        ArrayList arrayList = this.f8389a.f8405s;
        int iU = C0735d.U(arrayList, i7, this.c);
        if (iU >= 0) {
            return (C0733c) arrayList.get(iU);
        }
        C0733c c0733c = new C0733c(i7);
        arrayList.add(-(iU + 1), c0733c);
        return c0733c;
    }

    public final Object b(int[] iArr, int i7) {
        int iA;
        if (!C0735d.m(iArr, i7)) {
            return C0753m.f8270a;
        }
        int i10 = i7 * 5;
        if (i10 >= iArr.length) {
            iA = iArr.length;
        } else {
            iA = C0735d.A(iArr[i10 + 1] >> 29) + iArr[i10 + 4];
        }
        return this.d[iA];
    }

    public final void c() {
        int i7;
        this.f8392f = true;
        z0 z0Var = this.f8389a;
        z0Var.getClass();
        if (this.f8389a != z0Var || (i7 = z0Var.f8402p) <= 0) {
            C0735d.y("Unexpected reader close()");
            throw null;
        }
        z0Var.f8402p = i7 - 1;
    }

    public final void d() {
        if (this.f8397k == 0) {
            if (!(this.f8393g == this.f8394h)) {
                C0735d.y("endGroup() not called at the end of a group");
                throw null;
            }
            int i7 = this.f8395i;
            int[] iArr = this.f8390b;
            int iR = C0735d.r(iArr, i7);
            this.f8395i = iR;
            int i10 = this.c;
            this.f8394h = iR < 0 ? i10 : C0735d.l(iArr, iR) + iR;
            int iA = this.f8396j.a();
            if (iA < 0) {
                this.l = 0;
                this.f8398m = 0;
            } else {
                this.l = iA;
                this.f8398m = iR >= i10 - 1 ? this.f8391e : C0735d.k(iArr, iR + 1);
            }
        }
    }

    public final Object e() {
        int i7 = this.f8393g;
        if (i7 < this.f8394h) {
            return b(this.f8390b, i7);
        }
        return 0;
    }

    public final int f() {
        int i7 = this.f8393g;
        if (i7 >= this.f8394h) {
            return 0;
        }
        return this.f8390b[i7 * 5];
    }

    public final Object g(int i7, int i10) {
        int[] iArr = this.f8390b;
        int iT = C0735d.t(iArr, i7);
        int i11 = i7 + 1;
        int i12 = iT + i10;
        return i12 < (i11 < this.c ? iArr[(i11 * 5) + 4] : this.f8391e) ? this.d[i12] : C0753m.f8270a;
    }

    public final Object h() {
        int i7;
        if (this.f8397k > 0 || (i7 = this.l) >= this.f8398m) {
            this.f8399n = false;
            return C0753m.f8270a;
        }
        this.f8399n = true;
        this.l = i7 + 1;
        return this.d[i7];
    }

    public final Object i(int i7) {
        int[] iArr = this.f8390b;
        if (!C0735d.o(iArr, i7)) {
            return null;
        }
        if (!C0735d.o(iArr, i7)) {
            return C0753m.f8270a;
        }
        return this.d[iArr[(i7 * 5) + 4]];
    }

    public final Object j(int[] iArr, int i7) {
        if (!C0735d.n(iArr, i7)) {
            return null;
        }
        int i10 = i7 * 5;
        return this.d[C0735d.A(iArr[i10 + 1] >> 30) + iArr[i10 + 4]];
    }

    public final void k(int i7) {
        if (!(this.f8397k == 0)) {
            C0735d.y("Cannot reposition while in an empty region");
            throw null;
        }
        this.f8393g = i7;
        int[] iArr = this.f8390b;
        int i10 = this.c;
        int iR = i7 < i10 ? C0735d.r(iArr, i7) : -1;
        this.f8395i = iR;
        if (iR < 0) {
            this.f8394h = i10;
        } else {
            this.f8394h = C0735d.l(iArr, iR) + iR;
        }
        this.l = 0;
        this.f8398m = 0;
    }

    public final int l() {
        if (!(this.f8397k == 0)) {
            C0735d.y("Cannot skip while in an empty region");
            throw null;
        }
        int i7 = this.f8393g;
        int[] iArr = this.f8390b;
        int iQ = C0735d.o(iArr, i7) ? 1 : C0735d.q(iArr, this.f8393g);
        int i10 = this.f8393g;
        this.f8393g = C0735d.l(iArr, i10) + i10;
        return iQ;
    }

    public final void m() {
        if (!(this.f8397k == 0)) {
            C0735d.y("Cannot skip the enclosing group while in an empty region");
            throw null;
        }
        this.f8393g = this.f8394h;
        this.l = 0;
        this.f8398m = 0;
    }

    public final void n() {
        if (this.f8397k <= 0) {
            int i7 = this.f8395i;
            int i10 = this.f8393g;
            int[] iArr = this.f8390b;
            if (!(C0735d.r(iArr, i10) == i7)) {
                C0735d.X("Invalid slot table detected");
                throw null;
            }
            int i11 = this.l;
            int i12 = this.f8398m;
            C0171v c0171v = this.f8396j;
            if (i11 == 0 && i12 == 0) {
                c0171v.b(-1);
            } else {
                c0171v.b(i11);
            }
            this.f8395i = i10;
            this.f8394h = C0735d.l(iArr, i10) + i10;
            int i13 = i10 + 1;
            this.f8393g = i13;
            this.l = C0735d.t(iArr, i10);
            this.f8398m = i10 >= this.c - 1 ? this.f8391e : C0735d.k(iArr, i13);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SlotReader(current=");
        sb.append(this.f8393g);
        sb.append(", key=");
        sb.append(f());
        sb.append(", parent=");
        sb.append(this.f8395i);
        sb.append(", end=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f8394h, ')');
    }
}
