package p;

import androidx.constraintlayout.core.SolverVariable$Type;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class ra1 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static boolean f178p = false;
    public static int q = 1000;
    public final fx1 c;
    public pb[] f;
    public final gh2 l;
    public pb o;
    public boolean a = false;
    public int b = 0;
    public int d = 32;
    public int e = 32;
    public boolean g = false;
    public boolean[] h = new boolean[32];
    public int i = 1;
    public int j = 0;
    public int k = 32;
    public ce2[] m = new ce2[q];
    public int n = 0;

    public ra1() {
        this.f = null;
        this.f = new pb[32];
        s();
        gh2 gh2Var = new gh2(1);
        this.l = gh2Var;
        fx1 fx1Var = new fx1(gh2Var);
        fx1Var.f = new ce2[128];
        fx1Var.g = new ce2[128];
        fx1Var.h = 0;
        fx1Var.i = new kk0(fx1Var, fx1Var);
        this.c = fx1Var;
        this.o = new pb(gh2Var);
    }

    public static int n(xx xxVar) {
        ce2 ce2Var = xxVar.i;
        if (ce2Var != null) {
            return (int) (ce2Var.m + 0.5f);
        }
        return 0;
    }

    public final ce2 a(SolverVariable$Type solverVariable$Type) {
        ce2 ce2Var = (ce2) ((nw1) this.l.l).a();
        if (ce2Var == null) {
            ce2Var = new ce2(solverVariable$Type);
            ce2Var.q = solverVariable$Type;
        } else {
            ce2Var.c();
            ce2Var.q = solverVariable$Type;
        }
        int i = this.n;
        int i2 = q;
        if (i >= i2) {
            int i3 = i2 * 2;
            q = i3;
            this.m = (ce2[]) Arrays.copyOf(this.m, i3);
        }
        ce2[] ce2VarArr = this.m;
        int i4 = this.n;
        this.n = i4 + 1;
        ce2VarArr[i4] = ce2Var;
        return ce2Var;
    }

    public final void b(ce2 ce2Var, ce2 ce2Var2, int i, float f, ce2 ce2Var3, ce2 ce2Var4, int i2, int i3) {
        pb pbVarL = l();
        if (ce2Var2 == ce2Var3) {
            pbVarL.d.d(ce2Var, 1.0f);
            pbVarL.d.d(ce2Var4, 1.0f);
            pbVarL.d.d(ce2Var2, -2.0f);
        } else if (f == 0.5f) {
            pbVarL.d.d(ce2Var, 1.0f);
            pbVarL.d.d(ce2Var2, -1.0f);
            pbVarL.d.d(ce2Var3, -1.0f);
            pbVarL.d.d(ce2Var4, 1.0f);
            if (i > 0 || i2 > 0) {
                pbVarL.b = (-i) + i2;
            }
        } else if (f <= 0.0f) {
            pbVarL.d.d(ce2Var, -1.0f);
            pbVarL.d.d(ce2Var2, 1.0f);
            pbVarL.b = i;
        } else if (f >= 1.0f) {
            pbVarL.d.d(ce2Var4, -1.0f);
            pbVarL.d.d(ce2Var3, 1.0f);
            pbVarL.b = -i2;
        } else {
            float f2 = 1.0f - f;
            pbVarL.d.d(ce2Var, f2 * 1.0f);
            pbVarL.d.d(ce2Var2, f2 * (-1.0f));
            pbVarL.d.d(ce2Var3, (-1.0f) * f);
            pbVarL.d.d(ce2Var4, 1.0f * f);
            if (i > 0 || i2 > 0) {
                pbVarL.b = (i2 * f) + ((-i) * f2);
            }
        }
        if (i3 != 8) {
            pbVarL.a(this, i3);
        }
        c(pbVarL);
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01aa A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(p.pb r17) {
        /*
            Method dump skipped, instruction units count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ra1.c(p.pb):void");
    }

    public final void d(ce2 ce2Var, int i) {
        int i2 = ce2Var.k;
        if (i2 == -1) {
            ce2Var.d(this, i);
            for (int i3 = 0; i3 < this.b + 1; i3++) {
                ce2 ce2Var2 = ((ce2[]) this.l.m)[i3];
            }
            return;
        }
        if (i2 == -1) {
            pb pbVarL = l();
            pbVarL.a = ce2Var;
            float f = i;
            ce2Var.m = f;
            pbVarL.b = f;
            pbVarL.e = true;
            c(pbVarL);
            return;
        }
        pb pbVar = this.f[i2];
        if (pbVar.e) {
            pbVar.b = i;
            return;
        }
        if (pbVar.d.g() == 0) {
            pbVar.e = true;
            pbVar.b = i;
            return;
        }
        pb pbVarL2 = l();
        if (i < 0) {
            pbVarL2.b = i * (-1);
            pbVarL2.d.d(ce2Var, 1.0f);
        } else {
            pbVarL2.b = i;
            pbVarL2.d.d(ce2Var, -1.0f);
        }
        c(pbVarL2);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(p.ce2 r6, p.ce2 r7, int r8, int r9) {
        /*
            r5 = this;
            r0 = 8
            if (r9 != r0) goto L15
            boolean r1 = r7.n
            if (r1 == 0) goto L15
            int r1 = r6.k
            r2 = -1
            if (r1 != r2) goto L15
            float r7 = r7.m
            float r8 = (float) r8
            float r7 = r7 + r8
            r6.d(r5, r7)
            return
        L15:
            p.pb r1 = r5.l()
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r8 == 0) goto L37
            if (r8 >= 0) goto L25
            int r8 = r8 * (-1)
            r4 = 1
            goto L26
        L25:
            r4 = 0
        L26:
            float r8 = (float) r8
            r1.b = r8
            if (r4 != 0) goto L2c
            goto L37
        L2c:
            p.ob r8 = r1.d
            r8.d(r6, r2)
            p.ob r6 = r1.d
            r6.d(r7, r3)
            goto L41
        L37:
            p.ob r8 = r1.d
            r8.d(r6, r3)
            p.ob r6 = r1.d
            r6.d(r7, r2)
        L41:
            if (r9 == r0) goto L46
            r1.a(r5, r9)
        L46:
            r5.c(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ra1.e(p.ce2, p.ce2, int, int):void");
    }

    public final void f(ce2 ce2Var, ce2 ce2Var2, int i, int i2) {
        pb pbVarL = l();
        ce2 ce2VarM = m();
        ce2VarM.l = 0;
        pbVarL.b(ce2Var, ce2Var2, ce2VarM, i);
        if (i2 != 8) {
            pbVarL.d.d(j(i2), (int) (pbVarL.d.i(ce2VarM) * (-1.0f)));
        }
        c(pbVarL);
    }

    public final void g(ce2 ce2Var, ce2 ce2Var2, int i, int i2) {
        pb pbVarL = l();
        ce2 ce2VarM = m();
        ce2VarM.l = 0;
        pbVarL.c(ce2Var, ce2Var2, ce2VarM, i);
        if (i2 != 8) {
            pbVarL.d.d(j(i2), (int) (pbVarL.d.i(ce2VarM) * (-1.0f)));
        }
        c(pbVarL);
    }

    public final void h(pb pbVar) {
        int i;
        if (pbVar.e) {
            pbVar.a.d(this, pbVar.b);
        } else {
            pb[] pbVarArr = this.f;
            int i2 = this.j;
            pbVarArr[i2] = pbVar;
            ce2 ce2Var = pbVar.a;
            ce2Var.k = i2;
            this.j = i2 + 1;
            ce2Var.e(this, pbVar);
        }
        if (this.a) {
            int i3 = 0;
            while (i3 < this.j) {
                if (this.f[i3] == null) {
                    System.out.println("WTF");
                }
                pb pbVar2 = this.f[i3];
                if (pbVar2 != null && pbVar2.e) {
                    pbVar2.a.d(this, pbVar2.b);
                    ((nw1) this.l.k).b(pbVar2);
                    this.f[i3] = null;
                    int i4 = i3 + 1;
                    int i5 = i4;
                    while (true) {
                        i = this.j;
                        if (i4 >= i) {
                            break;
                        }
                        pb[] pbVarArr2 = this.f;
                        int i6 = i4 - 1;
                        pb pbVar3 = pbVarArr2[i4];
                        pbVarArr2[i6] = pbVar3;
                        ce2 ce2Var2 = pbVar3.a;
                        if (ce2Var2.k == i4) {
                            ce2Var2.k = i6;
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
            pb pbVar = this.f[i];
            pbVar.a.m = pbVar.b;
        }
    }

    public final ce2 j(int i) {
        if (this.i + 1 >= this.e) {
            o();
        }
        ce2 ce2VarA = a(SolverVariable$Type.k);
        int i2 = this.b + 1;
        this.b = i2;
        this.i++;
        ce2VarA.e = i2;
        ce2VarA.l = i;
        ((ce2[]) this.l.m)[i2] = ce2VarA;
        fx1 fx1Var = this.c;
        fx1Var.i.e = ce2VarA;
        float[] fArr = ce2VarA.f111p;
        Arrays.fill(fArr, 0.0f);
        fArr[ce2VarA.l] = 1.0f;
        fx1Var.j(ce2VarA);
        return ce2VarA;
    }

    public final ce2 k(Object obj) {
        ce2 ce2Var = null;
        if (obj == null) {
            return null;
        }
        if (this.i + 1 >= this.e) {
            o();
        }
        if (obj instanceof xx) {
            xx xxVar = (xx) obj;
            ce2Var = xxVar.i;
            if (ce2Var == null) {
                xxVar.h();
                ce2Var = xxVar.i;
            }
            int i = ce2Var.e;
            gh2 gh2Var = this.l;
            if (i == -1 || i > this.b || ((ce2[]) gh2Var.m)[i] == null) {
                if (i != -1) {
                    ce2Var.c();
                }
                int i2 = this.b + 1;
                this.b = i2;
                this.i++;
                ce2Var.e = i2;
                ce2Var.q = SolverVariable$Type.c;
                ((ce2[]) gh2Var.m)[i2] = ce2Var;
            }
        }
        return ce2Var;
    }

    public final pb l() {
        gh2 gh2Var = this.l;
        pb pbVar = (pb) ((nw1) gh2Var.k).a();
        if (pbVar == null) {
            return new pb(gh2Var);
        }
        pbVar.a = null;
        pbVar.d.clear();
        pbVar.b = 0.0f;
        pbVar.e = false;
        return pbVar;
    }

    public final ce2 m() {
        if (this.i + 1 >= this.e) {
            o();
        }
        ce2 ce2VarA = a(SolverVariable$Type.e);
        int i = this.b + 1;
        this.b = i;
        this.i++;
        ce2VarA.e = i;
        ((ce2[]) this.l.m)[i] = ce2VarA;
        return ce2VarA;
    }

    public final void o() {
        int i = this.d * 2;
        this.d = i;
        this.f = (pb[]) Arrays.copyOf(this.f, i);
        gh2 gh2Var = this.l;
        gh2Var.m = (ce2[]) Arrays.copyOf((ce2[]) gh2Var.m, this.d);
        int i2 = this.d;
        this.h = new boolean[i2];
        this.e = i2;
        this.k = i2;
    }

    public final void p() {
        fx1 fx1Var = this.c;
        if (fx1Var.e()) {
            i();
            return;
        }
        if (!this.g) {
            q(fx1Var);
            return;
        }
        for (int i = 0; i < this.j; i++) {
            if (!this.f[i].e) {
                q(fx1Var);
                return;
            }
        }
        i();
    }

    public final void q(fx1 fx1Var) {
        int i = 0;
        while (true) {
            if (i >= this.j) {
                break;
            }
            pb pbVar = this.f[i];
            SolverVariable$Type solverVariable$Type = pbVar.a.q;
            SolverVariable$Type solverVariable$Type2 = SolverVariable$Type.c;
            if (solverVariable$Type != solverVariable$Type2) {
                float f = 0.0f;
                if (pbVar.b < 0.0f) {
                    boolean z = false;
                    int i2 = 0;
                    while (!z) {
                        i2++;
                        float f2 = Float.MAX_VALUE;
                        int i3 = 0;
                        int i4 = -1;
                        int i5 = -1;
                        int i6 = 0;
                        while (i3 < this.j) {
                            pb pbVar2 = this.f[i3];
                            if (pbVar2.a.q != solverVariable$Type2 && !pbVar2.e && pbVar2.b < f) {
                                int iG = pbVar2.d.g();
                                int i7 = 0;
                                while (i7 < iG) {
                                    ce2 ce2VarH = pbVar2.d.h(i7);
                                    float fI = pbVar2.d.i(ce2VarH);
                                    if (fI > f) {
                                        for (int i8 = 0; i8 < 9; i8++) {
                                            float f3 = ce2VarH.o[i8] / fI;
                                            if ((f3 < f2 && i8 == i6) || i8 > i6) {
                                                i6 = i8;
                                                i5 = ce2VarH.e;
                                                i4 = i3;
                                                f2 = f3;
                                            }
                                        }
                                    }
                                    i7++;
                                    f = 0.0f;
                                }
                            }
                            i3++;
                            f = 0.0f;
                        }
                        if (i4 != -1) {
                            pb pbVar3 = this.f[i4];
                            pbVar3.a.k = -1;
                            pbVar3.g(((ce2[]) this.l.m)[i5]);
                            ce2 ce2Var = pbVar3.a;
                            ce2Var.k = i4;
                            ce2Var.e(this, pbVar3);
                        } else {
                            z = true;
                        }
                        if (i2 > this.i / 2) {
                            z = true;
                        }
                        f = 0.0f;
                    }
                }
            }
            i++;
        }
        r(fx1Var);
        i();
    }

    public final void r(pb pbVar) {
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
            ce2 ce2Var = pbVar.a;
            if (ce2Var != null) {
                this.h[ce2Var.e] = true;
            }
            ce2 ce2VarD = pbVar.d(this.h);
            if (ce2VarD != null) {
                boolean[] zArr = this.h;
                int i3 = ce2VarD.e;
                if (zArr[i3]) {
                    return;
                } else {
                    zArr[i3] = true;
                }
            }
            if (ce2VarD != null) {
                float f = Float.MAX_VALUE;
                int i4 = -1;
                for (int i5 = 0; i5 < this.j; i5++) {
                    pb pbVar2 = this.f[i5];
                    if (pbVar2.a.q != SolverVariable$Type.c && !pbVar2.e && pbVar2.d.e(ce2VarD)) {
                        float fI = pbVar2.d.i(ce2VarD);
                        if (fI < 0.0f) {
                            float f2 = (-pbVar2.b) / fI;
                            if (f2 < f) {
                                i4 = i5;
                                f = f2;
                            }
                        }
                    }
                }
                if (i4 > -1) {
                    pb pbVar3 = this.f[i4];
                    pbVar3.a.k = -1;
                    pbVar3.g(ce2VarD);
                    ce2 ce2Var2 = pbVar3.a;
                    ce2Var2.k = i4;
                    ce2Var2.e(this, pbVar3);
                }
            } else {
                z = true;
            }
        }
    }

    public final void s() {
        for (int i = 0; i < this.j; i++) {
            pb pbVar = this.f[i];
            if (pbVar != null) {
                ((nw1) this.l.k).b(pbVar);
            }
            this.f[i] = null;
        }
    }

    public final void t() {
        gh2 gh2Var;
        int i = 0;
        while (true) {
            gh2Var = this.l;
            ce2[] ce2VarArr = (ce2[]) gh2Var.m;
            if (i >= ce2VarArr.length) {
                break;
            }
            ce2 ce2Var = ce2VarArr[i];
            if (ce2Var != null) {
                ce2Var.c();
            }
            i++;
        }
        nw1 nw1Var = (nw1) gh2Var.l;
        ce2[] ce2VarArr2 = this.m;
        int length = this.n;
        nw1Var.getClass();
        if (length > ce2VarArr2.length) {
            length = ce2VarArr2.length;
        }
        for (int i2 = 0; i2 < length; i2++) {
            ce2 ce2Var2 = ce2VarArr2[i2];
            int i3 = nw1Var.c;
            Object[] objArr = nw1Var.b;
            if (i3 < objArr.length) {
                objArr[i3] = ce2Var2;
                nw1Var.c = i3 + 1;
            }
        }
        this.n = 0;
        Arrays.fill((ce2[]) gh2Var.m, (Object) null);
        this.b = 0;
        fx1 fx1Var = this.c;
        fx1Var.h = 0;
        fx1Var.b = 0.0f;
        this.i = 1;
        for (int i4 = 0; i4 < this.j; i4++) {
            pb pbVar = this.f[i4];
        }
        s();
        this.j = 0;
        this.o = new pb(gh2Var);
    }
}
