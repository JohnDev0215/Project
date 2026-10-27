package n2;

import android.util.Pair;
import d5.C1077F;
import e2.C1194b;
import e2.c0;
import e2.e0;
import e2.f0;
import h2.AbstractC1346c;
import java.util.ArrayList;
import u2.C2492C;

/* JADX INFO: loaded from: classes.dex */
public final class K {
    public final o2.e c;
    public final h2.x d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final G2.a f19559e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f19560f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f19561g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f19562h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public I f19563i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public I f19564j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public I f19565k;
    public I l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19566m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f19567n;
    public long o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c0 f19557a = new c0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e0 f19558b = new e0();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList f19568p = new ArrayList();

    public K(o2.e eVar, h2.x xVar, G2.a aVar) {
        this.c = eVar;
        this.d = xVar;
        this.f19559e = aVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static u2.C2492C n(e2.f0 r15, java.lang.Object r16, long r17, long r19, e2.e0 r21, e2.c0 r22) {
        /*
            r0 = r15
            r1 = r17
            r3 = r21
            r4 = r16
            r5 = r22
            r15.g(r4, r5)
            int r6 = r5.c
            r15.n(r6, r3)
            int r6 = r15.b(r16)
            r8 = r4
        L16:
            e2.b r4 = r5.f14063g
            int r4 = r4.f14043a
            r7 = -1
            if (r4 == 0) goto L70
            r9 = 1
            r10 = 0
            if (r4 != r9) goto L27
            boolean r11 = r5.g(r10)
            if (r11 != 0) goto L70
        L27:
            e2.b r11 = r5.f14063g
            int r11 = r11.d
            boolean r11 = r5.h(r11)
            if (r11 == 0) goto L70
            r11 = 0
            int r13 = r5.c(r11)
            if (r13 == r7) goto L3a
            goto L70
        L3a:
            long r13 = r5.d
            int r13 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r13 != 0) goto L41
            goto L61
        L41:
            int r13 = r4 + (-1)
            boolean r13 = r5.g(r13)
            if (r13 == 0) goto L4b
            r13 = 2
            goto L4c
        L4b:
            r13 = r9
        L4c:
            int r4 = r4 - r13
        L4d:
            if (r10 > r4) goto L5b
            e2.b r13 = r5.f14063g
            e2.a r13 = r13.a(r10)
            long r13 = r13.f14033h
            long r11 = r11 + r13
            int r10 = r10 + 1
            goto L4d
        L5b:
            long r13 = r5.d
            int r4 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r4 > 0) goto L70
        L61:
            int r4 = r3.o
            if (r6 > r4) goto L70
            r15.f(r6, r5, r9)
            java.lang.Object r8 = r5.f14060b
            r8.getClass()
            int r6 = r6 + 1
            goto L16
        L70:
            r15.g(r8, r5)
            int r9 = r5.c(r1)
            if (r9 != r7) goto L85
            int r0 = r5.b(r1)
            u2.C r1 = new u2.C
            r2 = r19
            r1.<init>(r0, r2, r8)
            return r1
        L85:
            r2 = r19
            int r10 = r5.f(r9)
            u2.C r0 = new u2.C
            r13 = -1
            r7 = r0
            r11 = r19
            r7.<init>(r8, r9, r10, r11, r13)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: n2.K.n(e2.f0, java.lang.Object, long, long, e2.e0, e2.c0):u2.C");
    }

    public final I a() {
        I i7 = this.f19563i;
        if (i7 == null) {
            return null;
        }
        if (i7 == this.f19564j) {
            this.f19564j = i7.f19547m;
        }
        i7.i();
        int i10 = this.f19566m - 1;
        this.f19566m = i10;
        if (i10 == 0) {
            this.f19565k = null;
            I i11 = this.f19563i;
            this.f19567n = i11.f19539b;
            this.o = i11.f19542g.f19550a.d;
        }
        this.f19563i = this.f19563i.f19547m;
        l();
        return this.f19563i;
    }

    public final void b() {
        if (this.f19566m == 0) {
            return;
        }
        I i7 = this.f19563i;
        AbstractC1346c.k(i7);
        this.f19567n = i7.f19539b;
        this.o = i7.f19542g.f19550a.d;
        while (i7 != null) {
            i7.i();
            i7 = i7.f19547m;
        }
        this.f19563i = null;
        this.f19565k = null;
        this.f19564j = null;
        this.f19566m = 0;
        l();
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final n2.J c(e2.f0 r22, n2.I r23, long r24) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n2.K.c(e2.f0, n2.I, long):n2.J");
    }

    public final J d(f0 f0Var, I i7, long j3) {
        J j10 = i7.f19542g;
        long j11 = (i7.f19549p + j10.f19552e) - j3;
        if (j10.f19554g) {
            return c(f0Var, i7, j11);
        }
        C2492C c2492c = j10.f19550a;
        Object obj = c2492c.f22663a;
        c0 c0Var = this.f19557a;
        f0Var.g(obj, c0Var);
        boolean zC = c2492c.c();
        Object obj2 = c2492c.f22663a;
        if (!zC) {
            int i10 = c2492c.f22665e;
            if (i10 != -1 && c0Var.g(i10)) {
                return c(f0Var, i7, j11);
            }
            int iF = c0Var.f(i10);
            boolean z6 = c0Var.h(i10) && c0Var.e(i10, iF) == 3;
            if (iF != c0Var.f14063g.a(i10).f14029b && !z6) {
                return f(f0Var, c2492c.f22663a, c2492c.f22665e, iF, j10.f19552e, c2492c.d);
            }
            f0Var.g(obj2, c0Var);
            long jD = c0Var.d(i10);
            return g(f0Var, c2492c.f22663a, jD == Long.MIN_VALUE ? c0Var.d : c0Var.f14063g.a(i10).f14033h + jD, j10.f19552e, c2492c.d);
        }
        C1194b c1194b = c0Var.f14063g;
        int i11 = c2492c.f22664b;
        int i12 = c1194b.a(i11).f14029b;
        if (i12 != -1) {
            int iA = c0Var.f14063g.a(i11).a(c2492c.c);
            if (iA < i12) {
                return f(f0Var, c2492c.f22663a, i11, iA, j10.c, c2492c.d);
            }
            long jLongValue = j10.c;
            if (jLongValue == -9223372036854775807L) {
                Pair pairJ = f0Var.j(this.f19558b, c0Var, c0Var.c, -9223372036854775807L, Math.max(0L, j11));
                if (pairJ != null) {
                    jLongValue = ((Long) pairJ.second).longValue();
                }
            }
            f0Var.g(obj2, c0Var);
            int i13 = c2492c.f22664b;
            long jD2 = c0Var.d(i13);
            return g(f0Var, c2492c.f22663a, Math.max(jD2 == Long.MIN_VALUE ? c0Var.d : c0Var.f14063g.a(i13).f14033h + jD2, jLongValue), j10.c, c2492c.d);
        }
        return null;
    }

    public final J e(f0 f0Var, C2492C c2492c, long j3, long j10) {
        f0Var.g(c2492c.f22663a, this.f19557a);
        if (!c2492c.c()) {
            return g(f0Var, c2492c.f22663a, j10, j3, c2492c.d);
        }
        return f(f0Var, c2492c.f22663a, c2492c.f22664b, c2492c.c, j3, c2492c.d);
    }

    public final J f(f0 f0Var, Object obj, int i7, int i10, long j3, long j10) {
        C2492C c2492c = new C2492C(obj, i7, i10, j10, -1);
        c0 c0Var = this.f19557a;
        long jA = f0Var.g(obj, c0Var).a(i7, i10);
        long j11 = i10 == c0Var.f(i7) ? c0Var.f14063g.f14044b : 0L;
        return new J(c2492c, (jA == -9223372036854775807L || j11 < jA) ? j11 : Math.max(0L, jA - 1), j3, -9223372036854775807L, jA, c0Var.h(i7), false, false, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final n2.J g(e2.f0 r27, java.lang.Object r28, long r29, long r31, long r33) {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n2.K.g(e2.f0, java.lang.Object, long, long, long):n2.J");
    }

    public final J h(f0 f0Var, J j3) {
        C2492C c2492c = j3.f19550a;
        boolean zC = c2492c.c();
        int i7 = c2492c.f22665e;
        boolean z6 = !zC && i7 == -1;
        boolean zJ = j(f0Var, c2492c);
        boolean zI = i(f0Var, c2492c, z6);
        Object obj = c2492c.f22663a;
        c0 c0Var = this.f19557a;
        f0Var.g(obj, c0Var);
        long jD = (c2492c.c() || i7 == -1) ? -9223372036854775807L : c0Var.d(i7);
        boolean zC2 = c2492c.c();
        int i10 = c2492c.f22664b;
        return new J(c2492c, j3.f19551b, j3.c, jD, zC2 ? c0Var.a(i10, c2492c.c) : (jD == -9223372036854775807L || jD == Long.MIN_VALUE) ? c0Var.d : jD, c2492c.c() ? c0Var.h(i10) : i7 != -1 && c0Var.h(i7), z6, zJ, zI);
    }

    public final boolean i(f0 f0Var, C2492C c2492c, boolean z6) {
        int iB = f0Var.b(c2492c.f22663a);
        if (f0Var.m(f0Var.f(iB, this.f19557a, false).c, this.f19558b, 0L).f14091i) {
            return false;
        }
        return f0Var.d(iB, this.f19557a, this.f19558b, this.f19561g, this.f19562h) == -1 && z6;
    }

    public final boolean j(f0 f0Var, C2492C c2492c) {
        if (!(!c2492c.c() && c2492c.f22665e == -1)) {
            return false;
        }
        Object obj = c2492c.f22663a;
        return f0Var.m(f0Var.g(obj, this.f19557a).c, this.f19558b, 0L).o == f0Var.b(obj);
    }

    public final void k() {
        I i7 = this.l;
        if (i7 == null || i7.h()) {
            this.l = null;
            for (int i10 = 0; i10 < this.f19568p.size(); i10++) {
                I i11 = (I) this.f19568p.get(i10);
                if (!i11.h()) {
                    this.l = i11;
                    return;
                }
            }
        }
    }

    public final void l() {
        C1077F c1077fU = d5.I.u();
        for (I i7 = this.f19563i; i7 != null; i7 = i7.f19547m) {
            c1077fU.a(i7.f19542g.f19550a);
        }
        I i10 = this.f19564j;
        this.d.c(new T1.k(this, c1077fU, i10 == null ? null : i10.f19542g.f19550a, 14));
    }

    public final boolean m(I i7) {
        AbstractC1346c.k(i7);
        boolean z6 = false;
        if (i7.equals(this.f19565k)) {
            return false;
        }
        this.f19565k = i7;
        while (true) {
            i7 = i7.f19547m;
            if (i7 == null) {
                break;
            }
            if (i7 == this.f19564j) {
                this.f19564j = this.f19563i;
                z6 = true;
            }
            i7.i();
            this.f19566m--;
        }
        I i10 = this.f19565k;
        i10.getClass();
        if (i10.f19547m != null) {
            i10.b();
            i10.f19547m = null;
            i10.c();
        }
        l();
        return z6;
    }

    public final C2492C o(f0 f0Var, Object obj, long j3) {
        long jP;
        int iB;
        Object obj2 = obj;
        c0 c0Var = this.f19557a;
        int i7 = f0Var.g(obj2, c0Var).c;
        Object obj3 = this.f19567n;
        if (obj3 == null || (iB = f0Var.b(obj3)) == -1 || f0Var.f(iB, c0Var, false).c != i7) {
            I i10 = this.f19563i;
            while (true) {
                if (i10 == null) {
                    I i11 = this.f19563i;
                    while (true) {
                        if (i11 != null) {
                            int iB2 = f0Var.b(i11.f19539b);
                            if (iB2 != -1 && f0Var.f(iB2, c0Var, false).c == i7) {
                                jP = i11.f19542g.f19550a.d;
                                break;
                            }
                            i11 = i11.f19547m;
                        } else {
                            jP = p(obj2);
                            if (jP == -1) {
                                jP = this.f19560f;
                                this.f19560f = 1 + jP;
                                if (this.f19563i == null) {
                                    this.f19567n = obj2;
                                    this.o = jP;
                                }
                            }
                        }
                    }
                } else {
                    if (i10.f19539b.equals(obj2)) {
                        jP = i10.f19542g.f19550a.d;
                        break;
                    }
                    i10 = i10.f19547m;
                }
            }
        } else {
            jP = this.o;
        }
        f0Var.g(obj2, c0Var);
        int i12 = c0Var.c;
        e0 e0Var = this.f19558b;
        f0Var.n(i12, e0Var);
        boolean z6 = false;
        for (int iB3 = f0Var.b(obj); iB3 >= e0Var.f14095n; iB3--) {
            f0Var.f(iB3, c0Var, true);
            boolean z10 = c0Var.f14063g.f14043a > 0;
            z6 |= z10;
            if (c0Var.c(c0Var.d) != -1) {
                obj2 = c0Var.f14060b;
                obj2.getClass();
            }
            if (z6 && (!z10 || c0Var.d != 0)) {
                break;
            }
        }
        return n(f0Var, obj2, j3, jP, this.f19558b, this.f19557a);
    }

    public final long p(Object obj) {
        for (int i7 = 0; i7 < this.f19568p.size(); i7++) {
            I i10 = (I) this.f19568p.get(i7);
            if (i10.f19539b.equals(obj)) {
                return i10.f19542g.f19550a.d;
            }
        }
        return -1L;
    }

    public final boolean q(f0 f0Var) {
        I i7;
        I i10 = this.f19563i;
        if (i10 == null) {
            return true;
        }
        int iB = f0Var.b(i10.f19539b);
        while (true) {
            iB = f0Var.d(iB, this.f19557a, this.f19558b, this.f19561g, this.f19562h);
            while (true) {
                i10.getClass();
                i7 = i10.f19547m;
                if (i7 == null || i10.f19542g.f19554g) {
                    break;
                }
                i10 = i7;
            }
            if (iB == -1 || i7 == null || f0Var.b(i7.f19539b) != iB) {
                break;
            }
            i10 = i7;
        }
        boolean zM = m(i10);
        i10.f19542g = h(f0Var, i10.f19542g);
        return !zM;
    }

    public final boolean r(f0 f0Var, long j3, long j10) {
        J jH;
        I i7 = this.f19563i;
        I i10 = null;
        while (i7 != null) {
            J j11 = i7.f19542g;
            if (i10 == null) {
                jH = h(f0Var, j11);
            } else {
                J jD = d(f0Var, i10, j3);
                if (jD == null) {
                    return !m(i10);
                }
                if (j11.f19551b != jD.f19551b || !j11.f19550a.equals(jD.f19550a)) {
                    return !m(i10);
                }
                jH = jD;
            }
            i7.f19542g = jH.a(j11.c);
            long j12 = j11.f19552e;
            if (j12 != -9223372036854775807L) {
                long j13 = jH.f19552e;
                if (j12 != j13) {
                    i7.k();
                    return (m(i7) || (i7 == this.f19564j && !i7.f19542g.f19553f && ((j10 > Long.MIN_VALUE ? 1 : (j10 == Long.MIN_VALUE ? 0 : -1)) == 0 || (j10 > ((j13 > (-9223372036854775807L) ? 1 : (j13 == (-9223372036854775807L) ? 0 : -1)) == 0 ? Long.MAX_VALUE : i7.f19549p + j13) ? 1 : (j10 == ((j13 > (-9223372036854775807L) ? 1 : (j13 == (-9223372036854775807L) ? 0 : -1)) == 0 ? Long.MAX_VALUE : i7.f19549p + j13) ? 0 : -1)) >= 0))) ? false : true;
                }
            }
            i10 = i7;
            i7 = i7.f19547m;
        }
        return true;
    }
}
