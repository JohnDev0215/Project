package j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.C2143r;

/* JADX INFO: renamed from: j8.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1568m extends AbstractC2134i implements p8.u {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f17417m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17418n;
    public List o;

    public /* synthetic */ C1568m(int i7) {
        this.f17417m = i7;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        switch (this.f17417m) {
            case 0:
                C1569n c1569nF = f();
                if (c1569nF.b()) {
                    return c1569nF;
                }
                throw new A9.k();
            case 1:
                K kG = g();
                if (kG.b()) {
                    return kG;
                }
                throw new A9.k();
            case 2:
                e0 e0VarI = i();
                if (e0VarI.b()) {
                    return e0VarI;
                }
                throw new A9.k();
            default:
                L lH = h();
                if (lH.b()) {
                    return lH;
                }
                throw new A9.k();
        }
    }

    public final Object clone() {
        switch (this.f17417m) {
            case 0:
                C1568m c1568m = new C1568m(0);
                c1568m.o = Collections.emptyList();
                c1568m.j(f());
                return c1568m;
            case 1:
                C1568m c1568m2 = new C1568m(1);
                c1568m2.o = Collections.emptyList();
                c1568m2.k(g());
                return c1568m2;
            case 2:
                C1568m c1568m3 = new C1568m(2);
                c1568m3.o = Collections.emptyList();
                c1568m3.m(i());
                return c1568m3;
            default:
                C1568m c1568m4 = new C1568m(3);
                c1568m4.o = C2143r.f20990m;
                c1568m4.l(h());
                return c1568m4;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x007d  */
    @Override // p8.AbstractC2134i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p8.AbstractC2134i d(p8.C2130e r3, p8.C2132g r4) throws java.lang.Throwable {
        /*
            r2 = this;
            int r0 = r2.f17417m
            switch(r0) {
                case 0: goto L62;
                case 1: goto L43;
                case 2: goto L24;
                default: goto L5;
            }
        L5:
            r4 = 0
            j8.a r0 = j8.L.f17148q     // Catch: java.lang.Throwable -> L14 p8.C2142q -> L16
            r0.getClass()     // Catch: java.lang.Throwable -> L14 p8.C2142q -> L16
            j8.L r0 = new j8.L     // Catch: java.lang.Throwable -> L14 p8.C2142q -> L16
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L14 p8.C2142q -> L16
            r2.l(r0)
            return r2
        L14:
            r3 = move-exception
            goto L1e
        L16:
            r3 = move-exception
            p8.a r0 = r3.l     // Catch: java.lang.Throwable -> L14
            j8.L r0 = (j8.L) r0     // Catch: java.lang.Throwable -> L14
            throw r3     // Catch: java.lang.Throwable -> L1c
        L1c:
            r3 = move-exception
            r4 = r0
        L1e:
            if (r4 == 0) goto L23
            r2.l(r4)
        L23:
            throw r3
        L24:
            r0 = 0
            j8.a r1 = j8.e0.f17331q     // Catch: java.lang.Throwable -> L33 p8.C2142q -> L35
            r1.getClass()     // Catch: java.lang.Throwable -> L33 p8.C2142q -> L35
            j8.e0 r1 = new j8.e0     // Catch: java.lang.Throwable -> L33 p8.C2142q -> L35
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> L33 p8.C2142q -> L35
            r2.m(r1)
            return r2
        L33:
            r3 = move-exception
            goto L3d
        L35:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> L33
            j8.e0 r4 = (j8.e0) r4     // Catch: java.lang.Throwable -> L33
            throw r3     // Catch: java.lang.Throwable -> L3b
        L3b:
            r3 = move-exception
            r0 = r4
        L3d:
            if (r0 == 0) goto L42
            r2.m(r0)
        L42:
            throw r3
        L43:
            r0 = 0
            j8.a r1 = j8.K.f17144q     // Catch: java.lang.Throwable -> L52 p8.C2142q -> L54
            r1.getClass()     // Catch: java.lang.Throwable -> L52 p8.C2142q -> L54
            j8.K r1 = new j8.K     // Catch: java.lang.Throwable -> L52 p8.C2142q -> L54
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> L52 p8.C2142q -> L54
            r2.k(r1)
            return r2
        L52:
            r3 = move-exception
            goto L5c
        L54:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> L52
            j8.K r4 = (j8.K) r4     // Catch: java.lang.Throwable -> L52
            throw r3     // Catch: java.lang.Throwable -> L5a
        L5a:
            r3 = move-exception
            r0 = r4
        L5c:
            if (r0 == 0) goto L61
            r2.k(r0)
        L61:
            throw r3
        L62:
            r0 = 0
            j8.a r1 = j8.C1569n.f17420q     // Catch: java.lang.Throwable -> L71 p8.C2142q -> L73
            r1.getClass()     // Catch: java.lang.Throwable -> L71 p8.C2142q -> L73
            j8.n r1 = new j8.n     // Catch: java.lang.Throwable -> L71 p8.C2142q -> L73
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> L71 p8.C2142q -> L73
            r2.j(r1)
            return r2
        L71:
            r3 = move-exception
            goto L7b
        L73:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> L71
            j8.n r4 = (j8.C1569n) r4     // Catch: java.lang.Throwable -> L71
            throw r3     // Catch: java.lang.Throwable -> L79
        L79:
            r3 = move-exception
            r0 = r4
        L7b:
            if (r0 == 0) goto L80
            r2.j(r0)
        L80:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.C1568m.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        switch (this.f17417m) {
            case 0:
                j((C1569n) abstractC2139n);
                break;
            case 1:
                k((K) abstractC2139n);
                break;
            case 2:
                m((e0) abstractC2139n);
                break;
            default:
                l((L) abstractC2139n);
                break;
        }
        return this;
    }

    public C1569n f() {
        C1569n c1569n = new C1569n(this);
        if ((this.f17418n & 1) == 1) {
            this.o = Collections.unmodifiableList(this.o);
            this.f17418n &= -2;
        }
        c1569n.f17421m = this.o;
        return c1569n;
    }

    public K g() {
        K k10 = new K(this);
        if ((this.f17418n & 1) == 1) {
            this.o = Collections.unmodifiableList(this.o);
            this.f17418n &= -2;
        }
        k10.f17145m = this.o;
        return k10;
    }

    public L h() {
        L l = new L(this);
        if ((this.f17418n & 1) == 1) {
            this.o = ((p8.s) this.o).b();
            this.f17418n &= -2;
        }
        l.f17149m = (p8.s) this.o;
        return l;
    }

    public e0 i() {
        e0 e0Var = new e0(this);
        if ((this.f17418n & 1) == 1) {
            this.o = Collections.unmodifiableList(this.o);
            this.f17418n &= -2;
        }
        e0Var.f17332m = this.o;
        return e0Var;
    }

    public void j(C1569n c1569n) {
        if (c1569n == C1569n.f17419p) {
            return;
        }
        if (!c1569n.f17421m.isEmpty()) {
            if (this.o.isEmpty()) {
                this.o = c1569n.f17421m;
                this.f17418n &= -2;
            } else {
                if ((this.f17418n & 1) != 1) {
                    this.o = new ArrayList(this.o);
                    this.f17418n |= 1;
                }
                this.o.addAll(c1569n.f17421m);
            }
        }
        this.l = this.l.d(c1569n.l);
    }

    public void k(K k10) {
        if (k10 == K.f17143p) {
            return;
        }
        if (!k10.f17145m.isEmpty()) {
            if (this.o.isEmpty()) {
                this.o = k10.f17145m;
                this.f17418n &= -2;
            } else {
                if ((this.f17418n & 1) != 1) {
                    this.o = new ArrayList(this.o);
                    this.f17418n |= 1;
                }
                this.o.addAll(k10.f17145m);
            }
        }
        this.l = this.l.d(k10.l);
    }

    public void l(L l) {
        if (l == L.f17147p) {
            return;
        }
        if (!l.f17149m.isEmpty()) {
            if (((p8.s) this.o).isEmpty()) {
                this.o = l.f17149m;
                this.f17418n &= -2;
            } else {
                if ((this.f17418n & 1) != 1) {
                    this.o = new C2143r((p8.s) this.o);
                    this.f17418n |= 1;
                }
                ((p8.s) this.o).addAll(l.f17149m);
            }
        }
        this.l = this.l.d(l.l);
    }

    public void m(e0 e0Var) {
        if (e0Var == e0.f17330p) {
            return;
        }
        if (!e0Var.f17332m.isEmpty()) {
            if (this.o.isEmpty()) {
                this.o = e0Var.f17332m;
                this.f17418n &= -2;
            } else {
                if ((this.f17418n & 1) != 1) {
                    this.o = new ArrayList(this.o);
                    this.f17418n |= 1;
                }
                this.o.addAll(e0Var.f17332m);
            }
        }
        this.l = this.l.d(e0Var.l);
    }
}
