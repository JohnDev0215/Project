package h1;

import java.util.Arrays;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static boolean f15168q = false;
    public final d d;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final P f15178m;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C1342b f15180p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f15169a = 1000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f15170b = false;
    public int c = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f15171e = 32;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f15172f = 32;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f15174h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean[] f15175i = new boolean[32];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f15176j = 1;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f15177k = 0;
    public int l = 32;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public e[] f15179n = new e[1000];
    public int o = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public C1342b[] f15173g = new C1342b[32];

    public c() {
        s();
        P p10 = new P(29, false);
        p10.f18537m = new E1.c();
        p10.f18538n = new E1.c();
        p10.o = new e[32];
        this.f15178m = p10;
        d dVar = new d(p10);
        dVar.f15181f = new e[128];
        dVar.f15182g = new e[128];
        dVar.f15183h = 0;
        dVar.f15184i = new io.sentry.internal.debugmeta.c(dVar, 18);
        this.d = dVar;
        this.f15180p = new C1342b(p10);
    }

    public static int n(Object obj) {
        e eVar = ((o1.c) obj).f20256i;
        if (eVar != null) {
            return (int) (eVar.f15187p + 0.5f);
        }
        return 0;
    }

    public final e a(int i7) {
        E1.c cVar = (E1.c) this.f15178m.f18538n;
        int i10 = cVar.f1718b;
        Object obj = null;
        if (i10 > 0) {
            int i11 = i10 - 1;
            Object[] objArr = cVar.f1717a;
            Object obj2 = objArr[i11];
            objArr[i11] = null;
            cVar.f1718b = i11;
            obj = obj2;
        }
        e eVar = (e) obj;
        if (eVar == null) {
            eVar = new e(i7);
            eVar.f15194w = i7;
        } else {
            eVar.c();
            eVar.f15194w = i7;
        }
        int i12 = this.o;
        int i13 = this.f15169a;
        if (i12 >= i13) {
            int i14 = i13 * 2;
            this.f15169a = i14;
            this.f15179n = (e[]) Arrays.copyOf(this.f15179n, i14);
        }
        e[] eVarArr = this.f15179n;
        int i15 = this.o;
        this.o = i15 + 1;
        eVarArr[i15] = eVar;
        return eVar;
    }

    public final void b(e eVar, e eVar2, int i7, float f10, e eVar3, e eVar4, int i10, int i11) {
        C1342b c1342bL = l();
        if (eVar2 == eVar3) {
            c1342bL.d.g(eVar, 1.0f);
            c1342bL.d.g(eVar4, 1.0f);
            c1342bL.d.g(eVar2, -2.0f);
        } else if (f10 == 0.5f) {
            c1342bL.d.g(eVar, 1.0f);
            c1342bL.d.g(eVar2, -1.0f);
            c1342bL.d.g(eVar3, -1.0f);
            c1342bL.d.g(eVar4, 1.0f);
            if (i7 > 0 || i10 > 0) {
                c1342bL.f15166b = (-i7) + i10;
            }
        } else if (f10 <= 0.0f) {
            c1342bL.d.g(eVar, -1.0f);
            c1342bL.d.g(eVar2, 1.0f);
            c1342bL.f15166b = i7;
        } else if (f10 >= 1.0f) {
            c1342bL.d.g(eVar4, -1.0f);
            c1342bL.d.g(eVar3, 1.0f);
            c1342bL.f15166b = -i10;
        } else {
            float f11 = 1.0f - f10;
            c1342bL.d.g(eVar, f11 * 1.0f);
            c1342bL.d.g(eVar2, f11 * (-1.0f));
            c1342bL.d.g(eVar3, (-1.0f) * f10);
            c1342bL.d.g(eVar4, 1.0f * f10);
            if (i7 > 0 || i10 > 0) {
                c1342bL.f15166b = (i10 * f10) + ((-i7) * f11);
            }
        }
        if (i11 != 8) {
            c1342bL.a(this, i11);
        }
        c(c1342bL);
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01b9 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(h1.C1342b r17) {
        /*
            Method dump skipped, instruction units count: 449
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h1.c.c(h1.b):void");
    }

    public final void d(e eVar, int i7) {
        int i10 = eVar.f15186n;
        if (i10 == -1) {
            eVar.d(this, i7);
            for (int i11 = 0; i11 < this.c + 1; i11++) {
                e eVar2 = ((e[]) this.f15178m.o)[i11];
            }
            return;
        }
        if (i10 == -1) {
            C1342b c1342bL = l();
            c1342bL.f15165a = eVar;
            float f10 = i7;
            eVar.f15187p = f10;
            c1342bL.f15166b = f10;
            c1342bL.f15167e = true;
            c(c1342bL);
            return;
        }
        C1342b c1342b = this.f15173g[i10];
        if (c1342b.f15167e) {
            c1342b.f15166b = i7;
            return;
        }
        if (c1342b.d.d() == 0) {
            c1342b.f15167e = true;
            c1342b.f15166b = i7;
            return;
        }
        C1342b c1342bL2 = l();
        if (i7 < 0) {
            c1342bL2.f15166b = i7 * (-1);
            c1342bL2.d.g(eVar, 1.0f);
        } else {
            c1342bL2.f15166b = i7;
            c1342bL2.d.g(eVar, -1.0f);
        }
        c(c1342bL2);
    }

    public final void e(e eVar, e eVar2, int i7, int i10) {
        if (i10 == 8 && eVar2.f15188q && eVar.f15186n == -1) {
            eVar.d(this, eVar2.f15187p + i7);
            return;
        }
        C1342b c1342bL = l();
        boolean z6 = false;
        if (i7 != 0) {
            if (i7 < 0) {
                i7 *= -1;
                z6 = true;
            }
            c1342bL.f15166b = i7;
        }
        if (z6) {
            c1342bL.d.g(eVar, 1.0f);
            c1342bL.d.g(eVar2, -1.0f);
        } else {
            c1342bL.d.g(eVar, -1.0f);
            c1342bL.d.g(eVar2, 1.0f);
        }
        if (i10 != 8) {
            c1342bL.a(this, i10);
        }
        c(c1342bL);
    }

    public final void f(e eVar, e eVar2, int i7, int i10) {
        C1342b c1342bL = l();
        e eVarM = m();
        eVarM.o = 0;
        c1342bL.b(eVar, eVar2, eVarM, i7);
        if (i10 != 8) {
            c1342bL.d.g(j(i10), (int) (c1342bL.d.c(eVarM) * (-1.0f)));
        }
        c(c1342bL);
    }

    public final void g(e eVar, e eVar2, int i7, int i10) {
        C1342b c1342bL = l();
        e eVarM = m();
        eVarM.o = 0;
        c1342bL.c(eVar, eVar2, eVarM, i7);
        if (i10 != 8) {
            c1342bL.d.g(j(i10), (int) (c1342bL.d.c(eVarM) * (-1.0f)));
        }
        c(c1342bL);
    }

    public final void h(C1342b c1342b) {
        int i7;
        if (c1342b.f15167e) {
            c1342b.f15165a.d(this, c1342b.f15166b);
        } else {
            C1342b[] c1342bArr = this.f15173g;
            int i10 = this.f15177k;
            c1342bArr[i10] = c1342b;
            e eVar = c1342b.f15165a;
            eVar.f15186n = i10;
            this.f15177k = i10 + 1;
            eVar.e(this, c1342b);
        }
        if (this.f15170b) {
            int i11 = 0;
            while (i11 < this.f15177k) {
                if (this.f15173g[i11] == null) {
                    System.out.println("WTF");
                }
                C1342b c1342b2 = this.f15173g[i11];
                if (c1342b2 != null && c1342b2.f15167e) {
                    c1342b2.f15165a.d(this, c1342b2.f15166b);
                    ((E1.c) this.f15178m.f18537m).b(c1342b2);
                    this.f15173g[i11] = null;
                    int i12 = i11 + 1;
                    int i13 = i12;
                    while (true) {
                        i7 = this.f15177k;
                        if (i12 >= i7) {
                            break;
                        }
                        C1342b[] c1342bArr2 = this.f15173g;
                        int i14 = i12 - 1;
                        C1342b c1342b3 = c1342bArr2[i12];
                        c1342bArr2[i14] = c1342b3;
                        e eVar2 = c1342b3.f15165a;
                        if (eVar2.f15186n == i12) {
                            eVar2.f15186n = i14;
                        }
                        i13 = i12;
                        i12++;
                    }
                    if (i13 < i7) {
                        this.f15173g[i13] = null;
                    }
                    this.f15177k = i7 - 1;
                    i11--;
                }
                i11++;
            }
            this.f15170b = false;
        }
    }

    public final void i() {
        for (int i7 = 0; i7 < this.f15177k; i7++) {
            C1342b c1342b = this.f15173g[i7];
            c1342b.f15165a.f15187p = c1342b.f15166b;
        }
    }

    public final e j(int i7) {
        if (this.f15176j + 1 >= this.f15172f) {
            o();
        }
        e eVarA = a(4);
        int i10 = this.c + 1;
        this.c = i10;
        this.f15176j++;
        eVarA.f15185m = i10;
        eVarA.o = i7;
        ((e[]) this.f15178m.o)[i10] = eVarA;
        d dVar = this.d;
        dVar.f15184i.f16569m = eVarA;
        float[] fArr = eVarA.f15190s;
        Arrays.fill(fArr, 0.0f);
        fArr[eVarA.o] = 1.0f;
        dVar.j(eVarA);
        return eVarA;
    }

    public final e k(Object obj) {
        e eVar = null;
        if (obj == null) {
            return null;
        }
        if (this.f15176j + 1 >= this.f15172f) {
            o();
        }
        if (obj instanceof o1.c) {
            o1.c cVar = (o1.c) obj;
            eVar = cVar.f20256i;
            if (eVar == null) {
                cVar.k();
                eVar = cVar.f20256i;
            }
            int i7 = eVar.f15185m;
            P p10 = this.f15178m;
            if (i7 == -1 || i7 > this.c || ((e[]) p10.o)[i7] == null) {
                if (i7 != -1) {
                    eVar.c();
                }
                int i10 = this.c + 1;
                this.c = i10;
                this.f15176j++;
                eVar.f15185m = i10;
                eVar.f15194w = 1;
                ((e[]) p10.o)[i10] = eVar;
            }
        }
        return eVar;
    }

    public final C1342b l() {
        Object obj;
        P p10 = this.f15178m;
        E1.c cVar = (E1.c) p10.f18537m;
        int i7 = cVar.f1718b;
        if (i7 > 0) {
            int i10 = i7 - 1;
            Object[] objArr = cVar.f1717a;
            obj = objArr[i10];
            objArr[i10] = null;
            cVar.f1718b = i10;
        } else {
            obj = null;
        }
        C1342b c1342b = (C1342b) obj;
        if (c1342b == null) {
            return new C1342b(p10);
        }
        c1342b.f15165a = null;
        c1342b.d.b();
        c1342b.f15166b = 0.0f;
        c1342b.f15167e = false;
        return c1342b;
    }

    public final e m() {
        if (this.f15176j + 1 >= this.f15172f) {
            o();
        }
        e eVarA = a(3);
        int i7 = this.c + 1;
        this.c = i7;
        this.f15176j++;
        eVarA.f15185m = i7;
        ((e[]) this.f15178m.o)[i7] = eVarA;
        return eVarA;
    }

    public final void o() {
        int i7 = this.f15171e * 2;
        this.f15171e = i7;
        this.f15173g = (C1342b[]) Arrays.copyOf(this.f15173g, i7);
        P p10 = this.f15178m;
        p10.o = (e[]) Arrays.copyOf((e[]) p10.o, this.f15171e);
        int i10 = this.f15171e;
        this.f15175i = new boolean[i10];
        this.f15172f = i10;
        this.l = i10;
    }

    public final void p() {
        d dVar = this.d;
        if (dVar.e()) {
            i();
            return;
        }
        if (!this.f15174h) {
            q(dVar);
            return;
        }
        for (int i7 = 0; i7 < this.f15177k; i7++) {
            if (!this.f15173g[i7].f15167e) {
                q(dVar);
                return;
            }
        }
        i();
    }

    public final void q(d dVar) {
        int i7 = 0;
        while (true) {
            if (i7 >= this.f15177k) {
                break;
            }
            C1342b c1342b = this.f15173g[i7];
            int i10 = 1;
            if (c1342b.f15165a.f15194w != 1) {
                float f10 = 0.0f;
                if (c1342b.f15166b < 0.0f) {
                    boolean z6 = false;
                    int i11 = 0;
                    while (!z6) {
                        i11 += i10;
                        float f11 = Float.MAX_VALUE;
                        int i12 = -1;
                        int i13 = -1;
                        int i14 = 0;
                        int i15 = 0;
                        while (i14 < this.f15177k) {
                            C1342b c1342b2 = this.f15173g[i14];
                            if (c1342b2.f15165a.f15194w != i10 && !c1342b2.f15167e && c1342b2.f15166b < f10) {
                                int iD = c1342b2.d.d();
                                int i16 = 0;
                                while (i16 < iD) {
                                    e eVarE = c1342b2.d.e(i16);
                                    float fC = c1342b2.d.c(eVarE);
                                    if (fC > f10) {
                                        for (int i17 = 0; i17 < 9; i17++) {
                                            float f12 = eVarE.f15189r[i17] / fC;
                                            if ((f12 < f11 && i17 == i15) || i17 > i15) {
                                                i15 = i17;
                                                i13 = eVarE.f15185m;
                                                i12 = i14;
                                                f11 = f12;
                                            }
                                        }
                                    }
                                    i16++;
                                    f10 = 0.0f;
                                }
                            }
                            i14++;
                            f10 = 0.0f;
                            i10 = 1;
                        }
                        if (i12 != -1) {
                            C1342b c1342b3 = this.f15173g[i12];
                            c1342b3.f15165a.f15186n = -1;
                            c1342b3.g(((e[]) this.f15178m.o)[i13]);
                            e eVar = c1342b3.f15165a;
                            eVar.f15186n = i12;
                            eVar.e(this, c1342b3);
                        } else {
                            z6 = true;
                        }
                        if (i11 > this.f15176j / 2) {
                            z6 = true;
                        }
                        f10 = 0.0f;
                        i10 = 1;
                    }
                }
            }
            i7++;
        }
        r(dVar);
        i();
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0090 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void r(h1.C1342b r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = 0
            r3 = r2
        L6:
            int r4 = r0.f15176j
            if (r3 >= r4) goto L11
            boolean[] r4 = r0.f15175i
            r4[r3] = r2
            int r3 = r3 + 1
            goto L6
        L11:
            r3 = r2
            r4 = r3
        L13:
            if (r3 != 0) goto Lae
            r5 = 1
            int r4 = r4 + r5
            int r6 = r0.f15176j
            int r6 = r6 * 2
            if (r4 < r6) goto L1e
            return
        L1e:
            h1.e r6 = r1.f15165a
            if (r6 == 0) goto L28
            boolean[] r7 = r0.f15175i
            int r6 = r6.f15185m
            r7[r6] = r5
        L28:
            boolean[] r6 = r0.f15175i
            h1.e r6 = r1.d(r6)
            if (r6 == 0) goto L3b
            boolean[] r7 = r0.f15175i
            int r8 = r6.f15185m
            boolean r9 = r7[r8]
            if (r9 == 0) goto L39
            return
        L39:
            r7[r8] = r5
        L3b:
            if (r6 == 0) goto Laa
            r7 = -1
            r8 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r9 = r2
            r10 = r7
        L43:
            int r11 = r0.f15177k
            if (r9 >= r11) goto L95
            h1.b[] r11 = r0.f15173g
            r11 = r11[r9]
            h1.e r12 = r11.f15165a
            int r12 = r12.f15194w
            if (r12 != r5) goto L52
            goto L90
        L52:
            boolean r12 = r11.f15167e
            if (r12 == 0) goto L57
            goto L90
        L57:
            h1.a r12 = r11.d
            int r13 = r12.f15162h
            r15 = -1
            if (r13 != r15) goto L60
        L5e:
            r14 = 0
            goto L79
        L60:
            r2 = 0
        L61:
            if (r13 == r15) goto L5e
            int r5 = r12.f15157a
            if (r2 >= r5) goto L5e
            int[] r5 = r12.f15159e
            r5 = r5[r13]
            int r14 = r6.f15185m
            if (r5 != r14) goto L71
            r14 = 1
            goto L79
        L71:
            int[] r5 = r12.f15160f
            r13 = r5[r13]
            int r2 = r2 + 1
            r5 = 1
            goto L61
        L79:
            if (r14 == 0) goto L90
            h1.a r2 = r11.d
            float r2 = r2.c(r6)
            r5 = 0
            int r5 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r5 >= 0) goto L90
            float r5 = r11.f15166b
            float r5 = -r5
            float r5 = r5 / r2
            int r2 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r2 >= 0) goto L90
            r8 = r5
            r10 = r9
        L90:
            int r9 = r9 + 1
            r2 = 0
            r5 = 1
            goto L43
        L95:
            if (r10 <= r7) goto Lab
            h1.b[] r2 = r0.f15173g
            r2 = r2[r10]
            h1.e r5 = r2.f15165a
            r5.f15186n = r7
            r2.g(r6)
            h1.e r5 = r2.f15165a
            r5.f15186n = r10
            r5.e(r0, r2)
            goto Lab
        Laa:
            r3 = 1
        Lab:
            r2 = 0
            goto L13
        Lae:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: h1.c.r(h1.b):void");
    }

    public final void s() {
        for (int i7 = 0; i7 < this.f15177k; i7++) {
            C1342b c1342b = this.f15173g[i7];
            if (c1342b != null) {
                ((E1.c) this.f15178m.f18537m).b(c1342b);
            }
            this.f15173g[i7] = null;
        }
    }

    public final void t() {
        P p10;
        int i7 = 0;
        while (true) {
            p10 = this.f15178m;
            e[] eVarArr = (e[]) p10.o;
            if (i7 >= eVarArr.length) {
                break;
            }
            e eVar = eVarArr[i7];
            if (eVar != null) {
                eVar.c();
            }
            i7++;
        }
        E1.c cVar = (E1.c) p10.f18538n;
        e[] eVarArr2 = this.f15179n;
        int length = this.o;
        cVar.getClass();
        if (length > eVarArr2.length) {
            length = eVarArr2.length;
        }
        for (int i10 = 0; i10 < length; i10++) {
            e eVar2 = eVarArr2[i10];
            int i11 = cVar.f1718b;
            Object[] objArr = cVar.f1717a;
            if (i11 < objArr.length) {
                objArr[i11] = eVar2;
                cVar.f1718b = i11 + 1;
            }
        }
        this.o = 0;
        Arrays.fill((e[]) p10.o, (Object) null);
        this.c = 0;
        d dVar = this.d;
        dVar.f15183h = 0;
        dVar.f15166b = 0.0f;
        this.f15176j = 1;
        for (int i12 = 0; i12 < this.f15177k; i12++) {
            C1342b c1342b = this.f15173g[i12];
        }
        s();
        this.f15177k = 0;
        this.f15180p = new C1342b(p10);
    }
}
