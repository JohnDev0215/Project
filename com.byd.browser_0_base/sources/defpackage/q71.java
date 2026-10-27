package defpackage;

import java.util.Arrays;

/* JADX INFO: compiled from: LinearSystem.java */
/* JADX INFO: loaded from: classes.dex */
public final class q71 {
    public static boolean p = false;
    public static int q = 1000;
    public final m42 c;
    public p8[] f;
    public final pk l;
    public p8 o;
    public boolean a = false;
    public int b = 0;
    public int d = 32;
    public int e = 32;
    public boolean g = false;
    public boolean[] h = new boolean[32];
    public int i = 1;
    public int j = 0;
    public int k = 32;
    public hq2[] m = new hq2[q];
    public int n = 0;

    /* JADX INFO: compiled from: LinearSystem.java */
    public interface a {
        hq2 a(boolean[] zArr);
    }

    public q71() {
        this.f = null;
        this.f = new p8[32];
        s();
        pk pkVar = new pk(0);
        this.l = pkVar;
        this.c = new m42(pkVar);
        this.o = new p8(pkVar);
    }

    public static int n(nx nxVar) {
        hq2 hq2Var = nxVar.i;
        if (hq2Var != null) {
            return (int) (hq2Var.i + 0.5f);
        }
        return 0;
    }

    public final hq2 a(int i) {
        hq2 hq2Var = (hq2) ((t22) this.l.c).a();
        if (hq2Var == null) {
            hq2Var = new hq2(i);
            hq2Var.m = i;
        } else {
            hq2Var.f();
            hq2Var.m = i;
        }
        int i2 = this.n;
        int i3 = q;
        if (i2 >= i3) {
            int i4 = i3 * 2;
            q = i4;
            this.m = (hq2[]) Arrays.copyOf(this.m, i4);
        }
        hq2[] hq2VarArr = this.m;
        int i5 = this.n;
        this.n = i5 + 1;
        hq2VarArr[i5] = hq2Var;
        return hq2Var;
    }

    public final void b(hq2 hq2Var, hq2 hq2Var2, int i, float f, hq2 hq2Var3, hq2 hq2Var4, int i2, int i3) {
        p8 p8VarL = l();
        if (hq2Var2 == hq2Var3) {
            p8VarL.d.k(hq2Var, 1.0f);
            p8VarL.d.k(hq2Var4, 1.0f);
            p8VarL.d.k(hq2Var2, -2.0f);
        } else if (f == 0.5f) {
            p8VarL.d.k(hq2Var, 1.0f);
            p8VarL.d.k(hq2Var2, -1.0f);
            p8VarL.d.k(hq2Var3, -1.0f);
            p8VarL.d.k(hq2Var4, 1.0f);
            if (i > 0 || i2 > 0) {
                p8VarL.b = (-i) + i2;
            }
        } else if (f <= 0.0f) {
            p8VarL.d.k(hq2Var, -1.0f);
            p8VarL.d.k(hq2Var2, 1.0f);
            p8VarL.b = i;
        } else if (f >= 1.0f) {
            p8VarL.d.k(hq2Var4, -1.0f);
            p8VarL.d.k(hq2Var3, 1.0f);
            p8VarL.b = -i2;
        } else {
            float f2 = 1.0f - f;
            p8VarL.d.k(hq2Var, f2 * 1.0f);
            p8VarL.d.k(hq2Var2, f2 * (-1.0f));
            p8VarL.d.k(hq2Var3, (-1.0f) * f);
            p8VarL.d.k(hq2Var4, 1.0f * f);
            if (i > 0 || i2 > 0) {
                p8VarL.b = (i2 * f) + ((-i) * f2);
            }
        }
        if (i3 != 8) {
            p8VarL.b(this, i3);
        }
        c(p8VarL);
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01b1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(defpackage.p8 r17) {
        /*
            Method dump skipped, instruction units count: 443
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.q71.c(p8):void");
    }

    public final void d(hq2 hq2Var, int i) {
        int i2 = hq2Var.g;
        if (i2 == -1) {
            hq2Var.g(this, i);
            for (int i3 = 0; i3 < this.b + 1; i3++) {
                hq2 hq2Var2 = ((hq2[]) this.l.d)[i3];
            }
            return;
        }
        if (i2 == -1) {
            p8 p8VarL = l();
            p8VarL.a = hq2Var;
            float f = i;
            hq2Var.i = f;
            p8VarL.b = f;
            p8VarL.e = true;
            c(p8VarL);
            return;
        }
        p8 p8Var = this.f[i2];
        if (p8Var.e) {
            p8Var.b = i;
            return;
        }
        if (p8Var.d.c() == 0) {
            p8Var.e = true;
            p8Var.b = i;
            return;
        }
        p8 p8VarL2 = l();
        if (i < 0) {
            p8VarL2.b = i * (-1);
            p8VarL2.d.k(hq2Var, 1.0f);
        } else {
            p8VarL2.b = i;
            p8VarL2.d.k(hq2Var, -1.0f);
        }
        c(p8VarL2);
    }

    public final void e(hq2 hq2Var, hq2 hq2Var2, int i, int i2) {
        if (i2 == 8 && hq2Var2.j && hq2Var.g == -1) {
            hq2Var.g(this, hq2Var2.i + i);
            return;
        }
        p8 p8VarL = l();
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            p8VarL.b = i;
        }
        if (z) {
            p8VarL.d.k(hq2Var, 1.0f);
            p8VarL.d.k(hq2Var2, -1.0f);
        } else {
            p8VarL.d.k(hq2Var, -1.0f);
            p8VarL.d.k(hq2Var2, 1.0f);
        }
        if (i2 != 8) {
            p8VarL.b(this, i2);
        }
        c(p8VarL);
    }

    public final void f(hq2 hq2Var, hq2 hq2Var2, int i, int i2) {
        p8 p8VarL = l();
        hq2 hq2VarM = m();
        hq2VarM.h = 0;
        p8VarL.c(hq2Var, hq2Var2, hq2VarM, i);
        if (i2 != 8) {
            p8VarL.d.k(j(i2), (int) (p8VarL.d.a(hq2VarM) * (-1.0f)));
        }
        c(p8VarL);
    }

    public final void g(hq2 hq2Var, hq2 hq2Var2, int i, int i2) {
        p8 p8VarL = l();
        hq2 hq2VarM = m();
        hq2VarM.h = 0;
        p8VarL.d(hq2Var, hq2Var2, hq2VarM, i);
        if (i2 != 8) {
            p8VarL.d.k(j(i2), (int) (p8VarL.d.a(hq2VarM) * (-1.0f)));
        }
        c(p8VarL);
    }

    public final void h(p8 p8Var) {
        int i;
        if (p8Var.e) {
            p8Var.a.g(this, p8Var.b);
        } else {
            p8[] p8VarArr = this.f;
            int i2 = this.j;
            p8VarArr[i2] = p8Var;
            hq2 hq2Var = p8Var.a;
            hq2Var.g = i2;
            this.j = i2 + 1;
            hq2Var.h(this, p8Var);
        }
        if (this.a) {
            int i3 = 0;
            while (i3 < this.j) {
                if (this.f[i3] == null) {
                    System.out.println("WTF");
                }
                p8 p8Var2 = this.f[i3];
                if (p8Var2 != null && p8Var2.e) {
                    p8Var2.a.g(this, p8Var2.b);
                    ((t22) this.l.b).b(p8Var2);
                    this.f[i3] = null;
                    int i4 = i3 + 1;
                    int i5 = i4;
                    while (true) {
                        i = this.j;
                        if (i4 >= i) {
                            break;
                        }
                        p8[] p8VarArr2 = this.f;
                        int i6 = i4 - 1;
                        p8 p8Var3 = p8VarArr2[i4];
                        p8VarArr2[i6] = p8Var3;
                        hq2 hq2Var2 = p8Var3.a;
                        if (hq2Var2.g == i4) {
                            hq2Var2.g = i6;
                        }
                        i5 = i4;
                        i4++;
                    }
                    if (i5 < i) {
                        this.f[i5] = null;
                    }
                    this.j = i - 1;
                    i3--;
                }
                i3++;
            }
            this.a = false;
        }
    }

    public final void i() {
        for (int i = 0; i < this.j; i++) {
            p8 p8Var = this.f[i];
            p8Var.a.i = p8Var.b;
        }
    }

    public final hq2 j(int i) {
        if (this.i + 1 >= this.e) {
            o();
        }
        hq2 hq2VarA = a(4);
        int i2 = this.b + 1;
        this.b = i2;
        this.i++;
        hq2VarA.f = i2;
        hq2VarA.h = i;
        ((hq2[]) this.l.d)[i2] = hq2VarA;
        m42 m42Var = this.c;
        m42Var.i.a = hq2VarA;
        float[] fArr = hq2VarA.l;
        Arrays.fill(fArr, 0.0f);
        fArr[hq2VarA.h] = 1.0f;
        m42Var.j(hq2VarA);
        return hq2VarA;
    }

    public final hq2 k(Object obj) {
        hq2 hq2Var = null;
        if (obj == null) {
            return null;
        }
        if (this.i + 1 >= this.e) {
            o();
        }
        if (obj instanceof nx) {
            nx nxVar = (nx) obj;
            hq2Var = nxVar.i;
            if (hq2Var == null) {
                nxVar.k();
                hq2Var = nxVar.i;
            }
            int i = hq2Var.f;
            pk pkVar = this.l;
            if (i == -1 || i > this.b || ((hq2[]) pkVar.d)[i] == null) {
                if (i != -1) {
                    hq2Var.f();
                }
                int i2 = this.b + 1;
                this.b = i2;
                this.i++;
                hq2Var.f = i2;
                hq2Var.m = 1;
                ((hq2[]) pkVar.d)[i2] = hq2Var;
            }
        }
        return hq2Var;
    }

    public final p8 l() {
        pk pkVar = this.l;
        p8 p8Var = (p8) ((t22) pkVar.b).a();
        if (p8Var == null) {
            return new p8(pkVar);
        }
        p8Var.a = null;
        p8Var.d.clear();
        p8Var.b = 0.0f;
        p8Var.e = false;
        return p8Var;
    }

    public final hq2 m() {
        if (this.i + 1 >= this.e) {
            o();
        }
        hq2 hq2VarA = a(3);
        int i = this.b + 1;
        this.b = i;
        this.i++;
        hq2VarA.f = i;
        ((hq2[]) this.l.d)[i] = hq2VarA;
        return hq2VarA;
    }

    public final void o() {
        int i = this.d * 2;
        this.d = i;
        this.f = (p8[]) Arrays.copyOf(this.f, i);
        pk pkVar = this.l;
        pkVar.d = (hq2[]) Arrays.copyOf((hq2[]) pkVar.d, this.d);
        int i2 = this.d;
        this.h = new boolean[i2];
        this.e = i2;
        this.k = i2;
    }

    public final void p() {
        m42 m42Var = this.c;
        if (m42Var.e()) {
            i();
            return;
        }
        if (!this.g) {
            q(m42Var);
            return;
        }
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.j) {
                z = true;
                break;
            } else if (!this.f[i].e) {
                break;
            } else {
                i++;
            }
        }
        if (z) {
            i();
        } else {
            q(m42Var);
        }
    }

    public final void q(m42 m42Var) {
        float f;
        int i;
        boolean z;
        int i2 = 0;
        while (true) {
            f = 0.0f;
            i = 1;
            if (i2 >= this.j) {
                z = false;
                break;
            }
            p8 p8Var = this.f[i2];
            if (p8Var.a.m != 1 && p8Var.b < 0.0f) {
                z = true;
                break;
            }
            i2++;
        }
        if (z) {
            boolean z2 = false;
            int i3 = 0;
            while (!z2) {
                i3 += i;
                float f2 = Float.MAX_VALUE;
                int i4 = -1;
                int i5 = -1;
                int i6 = 0;
                int i7 = 0;
                while (i6 < this.j) {
                    p8 p8Var2 = this.f[i6];
                    if (p8Var2.a.m != i && !p8Var2.e && p8Var2.b < f) {
                        int iC = p8Var2.d.c();
                        int i8 = 0;
                        while (i8 < iC) {
                            hq2 hq2VarD = p8Var2.d.d(i8);
                            float fA = p8Var2.d.a(hq2VarD);
                            if (fA > f) {
                                for (int i9 = 0; i9 < 9; i9++) {
                                    float f3 = hq2VarD.k[i9] / fA;
                                    if ((f3 < f2 && i9 == i7) || i9 > i7) {
                                        i5 = hq2VarD.f;
                                        i7 = i9;
                                        f2 = f3;
                                        i4 = i6;
                                    }
                                }
                            }
                            i8++;
                            f = 0.0f;
                        }
                    }
                    i6++;
                    f = 0.0f;
                    i = 1;
                }
                if (i4 != -1) {
                    p8 p8Var3 = this.f[i4];
                    p8Var3.a.g = -1;
                    p8Var3.g(((hq2[]) this.l.d)[i5]);
                    hq2 hq2Var = p8Var3.a;
                    hq2Var.g = i4;
                    hq2Var.h(this, p8Var3);
                } else {
                    z2 = true;
                }
                if (i3 > this.i / 2) {
                    z2 = true;
                }
                f = 0.0f;
                i = 1;
            }
        }
        r(m42Var);
        i();
    }

    public final void r(p8 p8Var) {
        for (int i = 0; i < this.i; i++) {
            this.h[i] = false;
        }
        boolean z = false;
        int i2 = 0;
        while (!z) {
            i2++;
            if (i2 >= this.i * 2) {
                return;
            }
            hq2 hq2Var = p8Var.a;
            if (hq2Var != null) {
                this.h[hq2Var.f] = true;
            }
            hq2 hq2VarA = p8Var.a(this.h);
            if (hq2VarA != null) {
                boolean[] zArr = this.h;
                int i3 = hq2VarA.f;
                if (zArr[i3]) {
                    return;
                } else {
                    zArr[i3] = true;
                }
            }
            if (hq2VarA != null) {
                float f = Float.MAX_VALUE;
                int i4 = -1;
                for (int i5 = 0; i5 < this.j; i5++) {
                    p8 p8Var2 = this.f[i5];
                    if (p8Var2.a.m != 1 && !p8Var2.e && p8Var2.d.b(hq2VarA)) {
                        float fA = p8Var2.d.a(hq2VarA);
                        if (fA < 0.0f) {
                            float f2 = (-p8Var2.b) / fA;
                            if (f2 < f) {
                                i4 = i5;
                                f = f2;
                            }
                        }
                    }
                }
                if (i4 > -1) {
                    p8 p8Var3 = this.f[i4];
                    p8Var3.a.g = -1;
                    p8Var3.g(hq2VarA);
                    hq2 hq2Var2 = p8Var3.a;
                    hq2Var2.g = i4;
                    hq2Var2.h(this, p8Var3);
                }
            } else {
                z = true;
            }
        }
    }

    public final void s() {
        for (int i = 0; i < this.j; i++) {
            p8 p8Var = this.f[i];
            if (p8Var != null) {
                ((t22) this.l.b).b(p8Var);
            }
            this.f[i] = null;
        }
    }

    public final void t() {
        pk pkVar;
        int i = 0;
        while (true) {
            pkVar = this.l;
            hq2[] hq2VarArr = (hq2[]) pkVar.d;
            if (i >= hq2VarArr.length) {
                break;
            }
            hq2 hq2Var = hq2VarArr[i];
            if (hq2Var != null) {
                hq2Var.f();
            }
            i++;
        }
        t22 t22Var = (t22) pkVar.c;
        hq2[] hq2VarArr2 = this.m;
        int length = this.n;
        t22Var.getClass();
        if (length > hq2VarArr2.length) {
            length = hq2VarArr2.length;
        }
        for (int i2 = 0; i2 < length; i2++) {
            hq2 hq2Var2 = hq2VarArr2[i2];
            int i3 = t22Var.c;
            Object[] objArr = t22Var.b;
            if (i3 < objArr.length) {
                objArr[i3] = hq2Var2;
                t22Var.c = i3 + 1;
            }
        }
        this.n = 0;
        Arrays.fill((hq2[]) pkVar.d, (Object) null);
        this.b = 0;
        m42 m42Var = this.c;
        m42Var.h = 0;
        m42Var.b = 0.0f;
        this.i = 1;
        for (int i4 = 0; i4 < this.j; i4++) {
            p8 p8Var = this.f[i4];
        }
        s();
        this.j = 0;
        this.o = new p8(pkVar);
    }
}
