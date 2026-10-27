package O;

import K.G0;
import K.X;
import N0.C0411f;
import T0.C0775a;
import a.AbstractC0904a;
import h8.AbstractC1387d;
import java.util.List;
import n7.AbstractC1999n;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class H {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0411f f4901a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f4902b;
    public final N0.H c;
    public final T0.s d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Q f4903e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f4904f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C0411f f4905g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final T0.y f4906h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final G0 f4907i;

    public H(T0.y yVar, T0.s sVar, G0 g02, Q q4) {
        C0411f c0411f = yVar.f8481a;
        N0.H h10 = g02 != null ? g02.f3161a : null;
        long j3 = yVar.f8482b;
        this.f4901a = c0411f;
        this.f4902b = j3;
        this.c = h10;
        this.d = sVar;
        this.f4903e = q4;
        this.f4904f = j3;
        this.f4905g = c0411f;
        this.f4906h = yVar;
        this.f4907i = g02;
    }

    public final List a(InterfaceC2782k interfaceC2782k) {
        if (!N0.K.b(this.f4904f)) {
            return AbstractC1999n.D(new C0775a("", 0), new T0.x(N0.K.e(this.f4904f), N0.K.e(this.f4904f)));
        }
        T0.i iVar = (T0.i) interfaceC2782k.b(this);
        if (iVar != null) {
            return AbstractC1387d.j(iVar);
        }
        return null;
    }

    public final Integer b() {
        N0.H h10 = this.c;
        if (h10 == null) {
            return null;
        }
        int iD = N0.K.d(this.f4904f);
        T0.s sVar = this.d;
        return Integer.valueOf(sVar.a(h10.e(h10.f(sVar.b(iD)), true)));
    }

    public final Integer c() {
        N0.H h10 = this.c;
        if (h10 == null) {
            return null;
        }
        int iE = N0.K.e(this.f4904f);
        T0.s sVar = this.d;
        return Integer.valueOf(sVar.a(h10.i(h10.f(sVar.b(iE)))));
    }

    public final Integer d() {
        int length;
        N0.H h10 = this.c;
        if (h10 == null) {
            return null;
        }
        int iQ = q();
        while (true) {
            C0411f c0411f = this.f4901a;
            if (iQ < c0411f.l.length()) {
                int length2 = this.f4905g.l.length() - 1;
                if (iQ <= length2) {
                    length2 = iQ;
                }
                long jL = h10.l(length2);
                int i7 = N0.K.c;
                int i10 = (int) (jL & 4294967295L);
                if (i10 > iQ) {
                    length = this.d.a(i10);
                    break;
                }
                iQ++;
            } else {
                length = c0411f.l.length();
                break;
            }
        }
        return Integer.valueOf(length);
    }

    public final Integer e() {
        int iA;
        N0.H h10 = this.c;
        if (h10 == null) {
            return null;
        }
        int iQ = q();
        while (true) {
            if (iQ <= 0) {
                iA = 0;
                break;
            }
            int length = this.f4905g.l.length() - 1;
            if (iQ <= length) {
                length = iQ;
            }
            long jL = h10.l(length);
            int i7 = N0.K.c;
            int i10 = (int) (jL >> 32);
            if (i10 < iQ) {
                iA = this.d.a(i10);
                break;
            }
            iQ--;
        }
        return Integer.valueOf(iA);
    }

    public final boolean f() {
        N0.H h10 = this.c;
        return (h10 != null ? h10.j(q()) : null) != Y0.h.f10616m;
    }

    public final int g(N0.H h10, int i7) {
        int iQ = q();
        Q q4 = this.f4903e;
        if (q4.f4938a == null) {
            q4.f4938a = Float.valueOf(h10.c(iQ).f18882a);
        }
        int iF = h10.f(iQ) + i7;
        if (iF < 0) {
            return 0;
        }
        N0.o oVar = h10.f4349b;
        if (iF >= oVar.f4394f) {
            return this.f4905g.l.length();
        }
        float fB = oVar.b(iF) - 1;
        Float f10 = q4.f4938a;
        A7.m.c(f10);
        float fFloatValue = f10.floatValue();
        if ((f() && fFloatValue >= h10.h(iF)) || (!f() && fFloatValue <= h10.g(iF))) {
            return h10.e(iF, true);
        }
        return this.d.a(oVar.e(h6.e.a(f10.floatValue(), fB)));
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int h(K.G0 r6, int r7) {
        /*
            r5 = this;
            C0.r r0 = r6.f3162b
            if (r0 == 0) goto L11
            C0.r r1 = r6.c
            if (r1 == 0) goto Le
            r2 = 1
            m0.d r0 = r1.o(r0, r2)
            goto Lf
        Le:
            r0 = 0
        Lf:
            if (r0 != 0) goto L13
        L11:
            m0.d r0 = m0.d.f18881e
        L13:
            T0.y r1 = r5.f4906h
            long r1 = r1.f8482b
            int r3 = N0.K.c
            r3 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r1 = r1 & r3
            int r1 = (int) r1
            T0.s r5 = r5.d
            int r1 = r5.b(r1)
            N0.H r6 = r6.f3161a
            m0.d r1 = r6.c(r1)
            float r2 = r0.c()
            float r0 = r0.b()
            long r2 = h6.j.f(r2, r0)
            float r0 = m0.f.b(r2)
            float r7 = (float) r7
            float r0 = r0 * r7
            float r7 = r1.f18883b
            float r0 = r0 + r7
            float r7 = r1.f18882a
            long r0 = h6.e.a(r7, r0)
            N0.o r6 = r6.f4349b
            int r6 = r6.e(r0)
            int r5 = r5.a(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: O.H.h(K.G0, int):int");
    }

    public final void i() {
        C0411f c0411f = this.f4905g;
        Q q4 = this.f4903e;
        q4.f4938a = null;
        if (c0411f.l.length() > 0) {
            if (f()) {
                q4.f4938a = null;
                if (c0411f.l.length() > 0) {
                    String str = c0411f.l;
                    long j3 = this.f4904f;
                    int i7 = N0.K.c;
                    int iS = X.s((int) (j3 & 4294967295L), str);
                    if (iS != -1) {
                        p(iS, iS);
                        return;
                    }
                    return;
                }
                return;
            }
            q4.f4938a = null;
            if (c0411f.l.length() > 0) {
                String str2 = c0411f.l;
                long j10 = this.f4904f;
                int i10 = N0.K.c;
                int iP = X.p((int) (j10 & 4294967295L), str2);
                if (iP != -1) {
                    p(iP, iP);
                }
            }
        }
    }

    public final void j() {
        this.f4903e.f4938a = null;
        C0411f c0411f = this.f4905g;
        if (c0411f.l.length() > 0) {
            int iD = N0.K.d(this.f4904f);
            String str = c0411f.l;
            int iQ = X.q(iD, str);
            if (iQ == N0.K.d(this.f4904f) && iQ != str.length()) {
                iQ = X.q(iQ + 1, str);
            }
            p(iQ, iQ);
        }
    }

    public final void k() {
        this.f4903e.f4938a = null;
        C0411f c0411f = this.f4905g;
        if (c0411f.l.length() > 0) {
            int iE = N0.K.e(this.f4904f);
            String str = c0411f.l;
            int iR = X.r(iE, str);
            if (iR == N0.K.e(this.f4904f) && iR != 0) {
                iR = X.r(iR - 1, str);
            }
            p(iR, iR);
        }
    }

    public final void l() {
        C0411f c0411f = this.f4905g;
        Q q4 = this.f4903e;
        q4.f4938a = null;
        if (c0411f.l.length() > 0) {
            if (f()) {
                q4.f4938a = null;
                if (c0411f.l.length() > 0) {
                    String str = c0411f.l;
                    long j3 = this.f4904f;
                    int i7 = N0.K.c;
                    int iP = X.p((int) (j3 & 4294967295L), str);
                    if (iP != -1) {
                        p(iP, iP);
                        return;
                    }
                    return;
                }
                return;
            }
            q4.f4938a = null;
            if (c0411f.l.length() > 0) {
                String str2 = c0411f.l;
                long j10 = this.f4904f;
                int i10 = N0.K.c;
                int iS = X.s((int) (j10 & 4294967295L), str2);
                if (iS != -1) {
                    p(iS, iS);
                }
            }
        }
    }

    public final void m() {
        Integer numB;
        this.f4903e.f4938a = null;
        if (this.f4905g.l.length() <= 0 || (numB = b()) == null) {
            return;
        }
        int iIntValue = numB.intValue();
        p(iIntValue, iIntValue);
    }

    public final void n() {
        Integer numC;
        this.f4903e.f4938a = null;
        if (this.f4905g.l.length() <= 0 || (numC = c()) == null) {
            return;
        }
        int iIntValue = numC.intValue();
        p(iIntValue, iIntValue);
    }

    public final void o() {
        if (this.f4905g.l.length() > 0) {
            int i7 = N0.K.c;
            this.f4904f = AbstractC0904a.q((int) (this.f4902b >> 32), (int) (this.f4904f & 4294967295L));
        }
    }

    public final void p(int i7, int i10) {
        this.f4904f = AbstractC0904a.q(i7, i10);
    }

    public final int q() {
        long j3 = this.f4904f;
        int i7 = N0.K.c;
        return this.d.b((int) (j3 & 4294967295L));
    }
}
