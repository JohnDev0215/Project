package T;

import E0.C0171v;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import u.C2476p;
import u.C2482v;

/* JADX INFO: loaded from: classes.dex */
public final class B0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z0 f8157a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f8158b;
    public Object[] c;
    public ArrayList d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public HashMap f8159e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public C2476p f8160f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f8161g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f8162h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f8163i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f8164j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f8165k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8166m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f8167n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C0171v f8168p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C0171v f8169q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C0171v f8170r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public C2476p f8171s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f8172t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f8173u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f8174v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f8175w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public C0746i0 f8176x;

    public B0(z0 z0Var) {
        this.f8157a = z0Var;
        int[] iArr = z0Var.l;
        this.f8158b = iArr;
        Object[] objArr = z0Var.f8401n;
        this.c = objArr;
        this.d = z0Var.f8405s;
        this.f8159e = z0Var.f8406t;
        this.f8160f = z0Var.f8407u;
        int i7 = z0Var.f8400m;
        this.f8161g = i7;
        this.f8162h = (iArr.length / 5) - i7;
        int i10 = z0Var.o;
        this.f8165k = i10;
        this.l = objArr.length - i10;
        this.f8166m = i7;
        this.f8168p = new C0171v();
        this.f8169q = new C0171v();
        this.f8170r = new C0171v();
        this.f8173u = i7;
        this.f8174v = -1;
    }

    public static int h(int i7, int i10, int i11, int i12) {
        return i7 > i10 ? -(((i12 - i11) - i7) + 1) : i7;
    }

    public final boolean A() {
        if (this.f8167n != 0) {
            C0735d.y("Cannot remove group while inserting");
            throw null;
        }
        int i7 = this.f8172t;
        int i10 = this.f8163i;
        int iF = f(this.f8158b, p(i7));
        int iP = p(this.f8172t);
        int iL = C0735d.l(this.f8158b, iP) + this.f8172t;
        this.f8172t = iL;
        this.f8163i = f(this.f8158b, p(iL));
        int iQ = C0735d.o(this.f8158b, iP) ? 1 : C0735d.q(this.f8158b, iP);
        G(this.f8174v);
        C0746i0 c0746i0 = this.f8176x;
        if (c0746i0 != null) {
            while (true) {
                List list = c0746i0.l;
                if (list.isEmpty() || ((Number) AbstractC1998m.Y(list)).intValue() < i7) {
                    break;
                }
                c0746i0.e();
            }
        }
        boolean zB = B(i7, this.f8172t - i7);
        C(iF, this.f8163i - iF, i7 - 1);
        this.f8172t = i7;
        this.f8163i = i10;
        this.o -= iQ;
        return zB;
    }

    public final boolean B(int i7, int i10) {
        if (i10 > 0) {
            ArrayList arrayList = this.d;
            u(i7);
            if (!arrayList.isEmpty()) {
                HashMap map = this.f8159e;
                int i11 = i7 + i10;
                int iP = C0735d.p(this.d, i11, m() - this.f8162h);
                if (iP >= this.d.size()) {
                    iP--;
                }
                int i12 = iP + 1;
                int i13 = 0;
                while (iP >= 0) {
                    C0733c c0733c = (C0733c) this.d.get(iP);
                    int iC = c(c0733c);
                    if (iC < i7) {
                        break;
                    }
                    if (iC < i11) {
                        c0733c.f8235a = Integer.MIN_VALUE;
                        if (map != null) {
                        }
                        if (i13 == 0) {
                            i13 = iP + 1;
                        }
                        i12 = iP;
                    }
                    iP--;
                }
                z = i12 < i13;
                if (z) {
                    this.d.subList(i12, i13).clear();
                }
            }
            this.f8161g = i7;
            this.f8162h += i10;
            int i14 = this.f8166m;
            if (i14 > i7) {
                this.f8166m = Math.max(i7, i14 - i10);
            }
            int i15 = this.f8173u;
            if (i15 >= this.f8161g) {
                this.f8173u = i15 - i10;
            }
            int i16 = this.f8174v;
            if (i16 >= 0 && C0735d.j(this.f8158b, p(i16))) {
                L(i16);
            }
        }
        return z;
    }

    public final void C(int i7, int i10, int i11) {
        if (i10 > 0) {
            int i12 = this.l;
            int i13 = i7 + i10;
            v(i13, i11);
            this.f8165k = i7;
            this.l = i12 + i10;
            Arrays.fill(this.c, i7, i13, (Object) null);
            int i14 = this.f8164j;
            if (i14 >= i7) {
                this.f8164j = i14 - i10;
            }
        }
    }

    public final void D() {
        int i7 = this.f8173u;
        this.f8172t = i7;
        this.f8163i = f(this.f8158b, p(i7));
    }

    public final int E(int[] iArr, int i7) {
        if (i7 >= m()) {
            return this.c.length - this.l;
        }
        int iT = C0735d.t(iArr, i7);
        return iT < 0 ? (this.c.length - this.l) + iT + 1 : iT;
    }

    public final int F(int i7, int i10) {
        int iE = E(this.f8158b, p(i7));
        int i11 = iE + i10;
        if (i11 >= iE && i11 < f(this.f8158b, p(i7 + 1))) {
            return i11;
        }
        C0735d.y("Write to an invalid slot index " + i10 + " for group " + i7);
        throw null;
    }

    public final K G(int i7) {
        C0733c c0733cJ;
        HashMap map = this.f8159e;
        if (map == null || (c0733cJ = J(i7)) == null) {
            return null;
        }
        return (K) map.get(c0733cJ);
    }

    public final void H() {
        if (this.f8167n != 0) {
            C0735d.y("Key must be supplied when inserting");
            throw null;
        }
        S s6 = C0753m.f8270a;
        I(0, s6, s6, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void I(int i7, Object obj, Object obj2, boolean z6) {
        int i10;
        int i11 = this.f8174v;
        Object[] objArr = this.f8167n > 0;
        this.f8170r.b(this.o);
        S s6 = C0753m.f8270a;
        if (objArr == true) {
            int i12 = this.f8172t;
            int iF = f(this.f8158b, p(i12));
            r(1);
            this.f8163i = iF;
            this.f8164j = iF;
            int iP = p(i12);
            int i13 = obj != s6 ? 1 : 0;
            int i14 = (z6 || obj2 == s6) ? 0 : 1;
            int iH = h(iF, this.f8165k, this.l, this.c.length);
            if (iH >= 0 && this.f8166m < i12) {
                iH = -(((this.c.length - this.l) - iH) + 1);
            }
            int[] iArr = this.f8158b;
            int i15 = this.f8174v;
            int i16 = z6 ? TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED : 0;
            int i17 = i13 != 0 ? 536870912 : 0;
            int i18 = i14 != 0 ? 268435456 : 0;
            int i19 = iP * 5;
            iArr[i19] = i7;
            iArr[i19 + 1] = i16 | i17 | i18;
            iArr[i19 + 2] = i15;
            iArr[i19 + 3] = 0;
            iArr[i19 + 4] = iH;
            int i20 = (z6 ? 1 : 0) + i13 + i14;
            if (i20 > 0) {
                s(i20, i12);
                Object[] objArr2 = this.c;
                int i21 = this.f8163i;
                if (z6) {
                    objArr2[i21] = obj2;
                    i21++;
                }
                if (i13 != 0) {
                    objArr2[i21] = obj;
                    i21++;
                }
                if (i14 != 0) {
                    objArr2[i21] = obj2;
                    i21++;
                }
                this.f8163i = i21;
            }
            this.o = 0;
            i10 = i12 + 1;
            this.f8174v = i12;
            this.f8172t = i10;
            if (i11 >= 0) {
                G(i11);
            }
        } else {
            this.f8168p.b(i11);
            this.f8169q.b((m() - this.f8162h) - this.f8173u);
            int i22 = this.f8172t;
            int iP2 = p(i22);
            if (!A7.m.a(obj2, s6)) {
                if (z6) {
                    M(this.f8172t, obj2);
                } else {
                    K(obj2);
                }
            }
            this.f8163i = E(this.f8158b, iP2);
            this.f8164j = f(this.f8158b, p(this.f8172t + 1));
            this.o = C0735d.q(this.f8158b, iP2);
            this.f8174v = i22;
            this.f8172t = i22 + 1;
            i10 = i22 + this.f8158b[(iP2 * 5) + 3];
        }
        this.f8173u = i10;
    }

    public final C0733c J(int i7) {
        ArrayList arrayList;
        int iU;
        if (i7 < 0 || i7 >= n() || (iU = C0735d.U((arrayList = this.d), i7, n())) < 0) {
            return null;
        }
        return (C0733c) arrayList.get(iU);
    }

    public final void K(Object obj) {
        int iP = p(this.f8172t);
        if (!C0735d.m(this.f8158b, iP)) {
            C0735d.y("Updating the data of a group that was not created with a data slot");
            throw null;
        }
        Object[] objArr = this.c;
        int[] iArr = this.f8158b;
        objArr[g(C0735d.A(iArr[(iP * 5) + 1] >> 29) + f(iArr, iP))] = obj;
    }

    public final void L(int i7) {
        if (i7 >= 0) {
            C0746i0 c0746i0 = this.f8176x;
            if (c0746i0 == null) {
                c0746i0 = new C0746i0();
                this.f8176x = c0746i0;
            }
            c0746i0.b(i7);
        }
    }

    public final void M(int i7, Object obj) {
        int iP = p(i7);
        int[] iArr = this.f8158b;
        if (iP < iArr.length && C0735d.o(iArr, iP)) {
            this.c[g(f(this.f8158b, iP))] = obj;
            return;
        }
        C0735d.y("Updating the node of a group at " + i7 + " that was not created with as a node group");
        throw null;
    }

    public final void a(int i7) {
        boolean z6 = false;
        if (!(i7 >= 0)) {
            C0735d.y("Cannot seek backwards");
            throw null;
        }
        if (!(this.f8167n <= 0)) {
            C0735d.Y("Cannot call seek() while inserting");
            throw null;
        }
        if (i7 == 0) {
            return;
        }
        int i10 = this.f8172t + i7;
        if (i10 >= this.f8174v && i10 <= this.f8173u) {
            z6 = true;
        }
        if (z6) {
            this.f8172t = i10;
            int iF = f(this.f8158b, p(i10));
            this.f8163i = iF;
            this.f8164j = iF;
            return;
        }
        C0735d.y("Cannot seek outside the current group (" + this.f8174v + '-' + this.f8173u + ')');
        throw null;
    }

    public final C0733c b(int i7) {
        ArrayList arrayList = this.d;
        int iU = C0735d.U(arrayList, i7, n());
        if (iU >= 0) {
            return (C0733c) arrayList.get(iU);
        }
        if (i7 > this.f8161g) {
            i7 = -(n() - i7);
        }
        C0733c c0733c = new C0733c(i7);
        arrayList.add(-(iU + 1), c0733c);
        return c0733c;
    }

    public final int c(C0733c c0733c) {
        int i7 = c0733c.f8235a;
        return i7 < 0 ? i7 + n() : i7;
    }

    public final void d() {
        int i7 = this.f8167n;
        this.f8167n = i7 + 1;
        if (i7 == 0) {
            this.f8169q.b((m() - this.f8162h) - this.f8173u);
        }
    }

    public final void e(boolean z6) {
        this.f8175w = true;
        if (z6 && this.f8168p.f1706b == 0) {
            u(n());
            v(this.c.length - this.l, this.f8161g);
            int i7 = this.f8165k;
            Arrays.fill(this.c, i7, this.l + i7, (Object) null);
            z();
        }
        int[] iArr = this.f8158b;
        int i10 = this.f8161g;
        Object[] objArr = this.c;
        int i11 = this.f8165k;
        ArrayList arrayList = this.d;
        HashMap map = this.f8159e;
        C2476p c2476p = this.f8160f;
        z0 z0Var = this.f8157a;
        z0Var.getClass();
        if (!z0Var.f8403q) {
            C0735d.X("Unexpected writer close()");
            throw null;
        }
        z0Var.f8403q = false;
        z0Var.l = iArr;
        z0Var.f8400m = i10;
        z0Var.f8401n = objArr;
        z0Var.o = i11;
        z0Var.f8405s = arrayList;
        z0Var.f8406t = map;
        z0Var.f8407u = c2476p;
    }

    public final int f(int[] iArr, int i7) {
        if (i7 >= m()) {
            return this.c.length - this.l;
        }
        int iK = C0735d.k(iArr, i7);
        return iK < 0 ? (this.c.length - this.l) + iK + 1 : iK;
    }

    public final int g(int i7) {
        return i7 < this.f8165k ? i7 : i7 + this.l;
    }

    public final void i() {
        C2482v c2482v;
        boolean z6 = this.f8167n > 0;
        int i7 = this.f8172t;
        int i10 = this.f8173u;
        int i11 = this.f8174v;
        int iP = p(i11);
        int i12 = this.o;
        int i13 = i7 - i11;
        boolean zO = C0735d.o(this.f8158b, iP);
        C0171v c0171v = this.f8170r;
        if (z6) {
            C2476p c2476p = this.f8171s;
            if (c2476p != null && (c2482v = (C2482v) c2476p.f(i11)) != null) {
                Object[] objArr = c2482v.f22637a;
                int i14 = c2482v.f22638b;
                for (int i15 = 0; i15 < i14; i15++) {
                    y(objArr[i15]);
                }
            }
            C0735d.u(iP, i13, this.f8158b);
            C0735d.v(iP, i12, this.f8158b);
            int iA = c0171v.a();
            if (zO) {
                i12 = 1;
            }
            this.o = iA + i12;
            int iX = x(this.f8158b, i11);
            this.f8174v = iX;
            int iN = iX < 0 ? n() : p(iX + 1);
            int iF = iN >= 0 ? f(this.f8158b, iN) : 0;
            this.f8163i = iF;
            this.f8164j = iF;
            return;
        }
        if (i7 != i10) {
            C0735d.y("Expected to be at the end of a group");
            throw null;
        }
        int[] iArr = this.f8158b;
        int i16 = iArr[(iP * 5) + 3];
        int iQ = C0735d.q(iArr, iP);
        C0735d.u(iP, i13, this.f8158b);
        C0735d.v(iP, i12, this.f8158b);
        int iA2 = this.f8168p.a();
        this.f8173u = (m() - this.f8162h) - this.f8169q.a();
        this.f8174v = iA2;
        int iX2 = x(this.f8158b, i11);
        int iA3 = c0171v.a();
        this.o = iA3;
        if (iX2 == iA2) {
            this.o = iA3 + (zO ? 0 : i12 - iQ);
            return;
        }
        int i17 = i13 - i16;
        int i18 = zO ? 0 : i12 - iQ;
        if (i17 != 0 || i18 != 0) {
            while (iX2 != 0 && iX2 != iA2 && (i18 != 0 || i17 != 0)) {
                int iP2 = p(iX2);
                if (i17 != 0) {
                    int[] iArr2 = this.f8158b;
                    C0735d.u(iP2, iArr2[(iP2 * 5) + 3] + i17, iArr2);
                }
                if (i18 != 0) {
                    int[] iArr3 = this.f8158b;
                    C0735d.v(iP2, C0735d.q(iArr3, iP2) + i18, iArr3);
                }
                if (C0735d.o(this.f8158b, iP2)) {
                    i18 = 0;
                }
                iX2 = x(this.f8158b, iX2);
            }
        }
        this.o += i18;
    }

    public final void j() {
        int i7 = this.f8167n;
        if (!(i7 > 0)) {
            C0735d.Y("Unbalanced begin/end insert");
            throw null;
        }
        int i10 = i7 - 1;
        this.f8167n = i10;
        if (i10 == 0) {
            if (this.f8170r.f1706b == this.f8168p.f1706b) {
                this.f8173u = (m() - this.f8162h) - this.f8169q.a();
            } else {
                C0735d.y("startGroup/endGroup mismatch while inserting");
                throw null;
            }
        }
    }

    public final void k(int i7) {
        boolean z6 = false;
        if (!(this.f8167n <= 0)) {
            C0735d.y("Cannot call ensureStarted() while inserting");
            throw null;
        }
        int i10 = this.f8174v;
        if (i10 != i7) {
            if (i7 >= i10 && i7 < this.f8173u) {
                z6 = true;
            }
            if (!z6) {
                C0735d.y("Started group at " + i7 + " must be a subgroup of the group at " + i10);
                throw null;
            }
            int i11 = this.f8172t;
            int i12 = this.f8163i;
            int i13 = this.f8164j;
            this.f8172t = i7;
            H();
            this.f8172t = i11;
            this.f8163i = i12;
            this.f8164j = i13;
        }
    }

    public final void l(int i7, int i10, int i11) {
        if (i7 >= this.f8161g) {
            i7 = -((n() - i7) + 2);
        }
        while (i11 < i10) {
            this.f8158b[(p(i11) * 5) + 2] = i7;
            int i12 = this.f8158b[(p(i11) * 5) + 3] + i11;
            l(i11, i12, i11 + 1);
            i11 = i12;
        }
    }

    public final int m() {
        return this.f8158b.length / 5;
    }

    public final int n() {
        return m() - this.f8162h;
    }

    public final int o() {
        return this.c.length - this.l;
    }

    public final int p(int i7) {
        return i7 < this.f8161g ? i7 : i7 + this.f8162h;
    }

    public final int q(int i7) {
        return C0735d.l(this.f8158b, p(i7));
    }

    public final void r(int i7) {
        if (i7 > 0) {
            int i10 = this.f8172t;
            u(i10);
            int i11 = this.f8161g;
            int i12 = this.f8162h;
            int[] iArr = this.f8158b;
            int length = iArr.length / 5;
            int i13 = length - i12;
            if (i12 < i7) {
                int iMax = Math.max(Math.max(length * 2, i13 + i7), 32);
                int[] iArr2 = new int[iMax * 5];
                int i14 = iMax - i13;
                AbstractC1996k.C(0, 0, i11 * 5, iArr, iArr2);
                AbstractC1996k.C((i11 + i14) * 5, (i12 + i11) * 5, length * 5, iArr, iArr2);
                this.f8158b = iArr2;
                i12 = i14;
            }
            int i15 = this.f8173u;
            if (i15 >= i11) {
                this.f8173u = i15 + i7;
            }
            int i16 = i11 + i7;
            this.f8161g = i16;
            this.f8162h = i12 - i7;
            int iH = h(i13 > 0 ? f(this.f8158b, p(i10 + i7)) : 0, this.f8166m >= i11 ? this.f8165k : 0, this.l, this.c.length);
            for (int i17 = i11; i17 < i16; i17++) {
                this.f8158b[(i17 * 5) + 4] = iH;
            }
            int i18 = this.f8166m;
            if (i18 >= i11) {
                this.f8166m = i18 + i7;
            }
        }
    }

    public final void s(int i7, int i10) {
        if (i7 > 0) {
            v(this.f8163i, i10);
            int i11 = this.f8165k;
            int i12 = this.l;
            if (i12 < i7) {
                Object[] objArr = this.c;
                int length = objArr.length;
                int i13 = length - i12;
                int iMax = Math.max(Math.max(length * 2, i13 + i7), 32);
                Object[] objArr2 = new Object[iMax];
                for (int i14 = 0; i14 < iMax; i14++) {
                    objArr2[i14] = null;
                }
                int i15 = iMax - i13;
                AbstractC1996k.D(0, 0, i11, objArr, objArr2);
                AbstractC1996k.D(i11 + i15, i12 + i11, length, objArr, objArr2);
                this.c = objArr2;
                i12 = i15;
            }
            int i16 = this.f8164j;
            if (i16 >= i11) {
                this.f8164j = i16 + i7;
            }
            this.f8165k = i11 + i7;
            this.l = i12 - i7;
        }
    }

    public final void t(z0 z0Var, int i7) {
        C0735d.T(this.f8167n > 0);
        if (i7 == 0 && this.f8172t == 0 && this.f8157a.f8400m == 0) {
            int[] iArr = z0Var.l;
            int i10 = iArr[(i7 * 5) + 3];
            int i11 = z0Var.f8400m;
            if (i10 == i11) {
                int[] iArr2 = this.f8158b;
                Object[] objArr = this.c;
                ArrayList arrayList = this.d;
                HashMap map = this.f8159e;
                C2476p c2476p = this.f8160f;
                Object[] objArr2 = z0Var.f8401n;
                int i12 = z0Var.o;
                HashMap map2 = z0Var.f8406t;
                C2476p c2476p2 = z0Var.f8407u;
                this.f8158b = iArr;
                this.c = objArr2;
                this.d = z0Var.f8405s;
                this.f8161g = i11;
                this.f8162h = (iArr.length / 5) - i11;
                this.f8165k = i12;
                this.l = objArr2.length - i12;
                this.f8166m = i11;
                this.f8159e = map2;
                this.f8160f = c2476p2;
                z0Var.l = iArr2;
                z0Var.f8400m = 0;
                z0Var.f8401n = objArr;
                z0Var.o = 0;
                z0Var.f8405s = arrayList;
                z0Var.f8406t = map;
                z0Var.f8407u = c2476p;
                return;
            }
        }
        B0 b0R = z0Var.r();
        try {
            C0735d.K(b0R, i7, this, true, true, false);
            b0R.e(true);
        } catch (Throwable th) {
            b0R.e(false);
            throw th;
        }
    }

    public final String toString() {
        return "SlotWriter(current = " + this.f8172t + " end=" + this.f8173u + " size = " + n() + " gap=" + this.f8161g + '-' + (this.f8161g + this.f8162h) + ')';
    }

    public final void u(int i7) {
        C0733c c0733c;
        int i10;
        C0733c c0733c2;
        int i11;
        int i12;
        int i13 = this.f8162h;
        int i14 = this.f8161g;
        if (i14 != i7) {
            if (!this.d.isEmpty()) {
                int iM = m() - this.f8162h;
                if (i14 < i7) {
                    for (int iP = C0735d.p(this.d, i14, iM); iP < this.d.size() && (i11 = (c0733c2 = (C0733c) this.d.get(iP)).f8235a) < 0 && (i12 = i11 + iM) < i7; iP++) {
                        c0733c2.f8235a = i12;
                    }
                } else {
                    for (int iP2 = C0735d.p(this.d, i7, iM); iP2 < this.d.size() && (i10 = (c0733c = (C0733c) this.d.get(iP2)).f8235a) >= 0; iP2++) {
                        c0733c.f8235a = -(iM - i10);
                    }
                }
            }
            if (i13 > 0) {
                int[] iArr = this.f8158b;
                int i15 = i7 * 5;
                int i16 = i13 * 5;
                int i17 = i14 * 5;
                if (i7 < i14) {
                    AbstractC1996k.C(i16 + i15, i15, i17, iArr, iArr);
                } else {
                    AbstractC1996k.C(i17, i17 + i16, i15 + i16, iArr, iArr);
                }
            }
            if (i7 < i14) {
                i14 = i7 + i13;
            }
            int iM2 = m();
            C0735d.T(i14 < iM2);
            while (i14 < iM2) {
                int i18 = (i14 * 5) + 2;
                int i19 = this.f8158b[i18];
                int iN = i19 > -2 ? i19 : (n() + i19) - (-2);
                if (iN >= i7) {
                    iN = -((n() - iN) - (-2));
                }
                if (iN != i19) {
                    this.f8158b[i18] = iN;
                }
                i14++;
                if (i14 == i7) {
                    i14 += i13;
                }
            }
        }
        this.f8161g = i7;
    }

    public final void v(int i7, int i10) {
        int i11 = this.l;
        int i12 = this.f8165k;
        int i13 = this.f8166m;
        if (i12 != i7) {
            Object[] objArr = this.c;
            if (i7 < i12) {
                AbstractC1996k.D(i7 + i11, i7, i12, objArr, objArr);
            } else {
                AbstractC1996k.D(i12, i12 + i11, i7 + i11, objArr, objArr);
            }
        }
        int iMin = Math.min(i10 + 1, n());
        if (i13 != iMin) {
            int length = this.c.length - i11;
            if (iMin < i13) {
                int iP = p(iMin);
                int iP2 = p(i13);
                int i14 = this.f8161g;
                while (iP < iP2) {
                    int[] iArr = this.f8158b;
                    int i15 = (iP * 5) + 4;
                    int i16 = iArr[i15];
                    if (i16 < 0) {
                        C0735d.y("Unexpected anchor value, expected a positive anchor");
                        throw null;
                    }
                    iArr[i15] = -((length - i16) + 1);
                    iP++;
                    if (iP == i14) {
                        iP += this.f8162h;
                    }
                }
            } else {
                int iP3 = p(i13);
                int iP4 = p(iMin);
                while (iP3 < iP4) {
                    int[] iArr2 = this.f8158b;
                    int i17 = (iP3 * 5) + 4;
                    int i18 = iArr2[i17];
                    if (i18 >= 0) {
                        C0735d.y("Unexpected anchor value, expected a negative anchor");
                        throw null;
                    }
                    iArr2[i17] = i18 + length + 1;
                    iP3++;
                    if (iP3 == this.f8161g) {
                        iP3 += this.f8162h;
                    }
                }
            }
            this.f8166m = iMin;
        }
        this.f8165k = i7;
    }

    public final Object w(int i7) {
        int iP = p(i7);
        if (C0735d.o(this.f8158b, iP)) {
            return this.c[g(f(this.f8158b, iP))];
        }
        return null;
    }

    public final int x(int[] iArr, int i7) {
        int iR = C0735d.r(iArr, p(i7));
        return iR > -2 ? iR : n() + iR + 2;
    }

    public final Object y(Object obj) {
        if (this.f8167n > 0) {
            s(1, this.f8174v);
        }
        Object[] objArr = this.c;
        int i7 = this.f8163i;
        this.f8163i = i7 + 1;
        Object obj2 = objArr[g(i7)];
        int i10 = this.f8163i;
        if (i10 <= this.f8164j) {
            this.c[g(i10 - 1)] = obj;
            return obj2;
        }
        C0735d.y("Writing to an invalid slot");
        throw null;
    }

    public final void z() {
        boolean z6;
        C0746i0 c0746i0 = this.f8176x;
        if (c0746i0 != null) {
            while (!c0746i0.l.isEmpty()) {
                int iE = c0746i0.e();
                int iP = p(iE);
                int iQ = iE + 1;
                int iQ2 = q(iE) + iE;
                while (true) {
                    if (iQ >= iQ2) {
                        z6 = false;
                        break;
                    } else {
                        if ((this.f8158b[(p(iQ) * 5) + 1] & 201326592) != 0) {
                            z6 = true;
                            break;
                        }
                        iQ += q(iQ);
                    }
                }
                if (C0735d.j(this.f8158b, iP) != z6) {
                    int[] iArr = this.f8158b;
                    int i7 = (iP * 5) + 1;
                    if (z6) {
                        iArr[i7] = iArr[i7] | 67108864;
                    } else {
                        iArr[i7] = iArr[i7] & (-67108865);
                    }
                    int iX = x(iArr, iE);
                    if (iX >= 0) {
                        c0746i0.b(iX);
                    }
                }
            }
        }
    }
}
