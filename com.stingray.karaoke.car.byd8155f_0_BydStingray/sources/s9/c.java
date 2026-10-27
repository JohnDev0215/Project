package s9;

import A7.m;
import java.io.EOFException;
import java.util.ArrayList;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import r9.C2319i;
import r9.C2322l;
import r9.z;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2322l f22382a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2322l f22383b;
    public static final C2322l c;
    public static final C2322l d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C2322l f22384e;

    static {
        C2322l c2322l = C2322l.o;
        f22382a = m6.b.p("/");
        f22383b = m6.b.p("\\");
        c = m6.b.p("/\\");
        d = m6.b.p(".");
        f22384e = m6.b.p("..");
    }

    public static final int a(z zVar) {
        if (zVar.l.d() == 0) {
            return -1;
        }
        C2322l c2322l = zVar.l;
        if (c2322l.i(0) != 47) {
            if (c2322l.i(0) != 92) {
                if (c2322l.d() <= 2 || c2322l.i(1) != 58 || c2322l.i(2) != 92) {
                    return -1;
                }
                char cI = (char) c2322l.i(0);
                return (('a' > cI || cI >= '{') && ('A' > cI || cI >= '[')) ? -1 : 3;
            }
            if (c2322l.d() > 2 && c2322l.i(1) == 92) {
                C2322l c2322l2 = f22383b;
                m.f(c2322l2, "other");
                int iF = c2322l.f(2, c2322l2.l);
                return iF == -1 ? c2322l.d() : iF;
            }
        }
        return 1;
    }

    public static final z b(z zVar, z zVar2, boolean z6) {
        m.f(zVar, "<this>");
        m.f(zVar2, "child");
        if (a(zVar2) != -1 || zVar2.h() != null) {
            return zVar2;
        }
        C2322l c2322lC = c(zVar);
        if (c2322lC == null && (c2322lC = c(zVar2)) == null) {
            c2322lC = f(z.f21951m);
        }
        C2319i c2319i = new C2319i();
        c2319i.U(zVar.l);
        if (c2319i.f21932m > 0) {
            c2319i.U(c2322lC);
        }
        c2319i.U(zVar2.l);
        return d(c2319i, z6);
    }

    public static final C2322l c(z zVar) {
        C2322l c2322l = zVar.l;
        C2322l c2322l2 = f22382a;
        if (C2322l.g(c2322l, c2322l2) != -1) {
            return c2322l2;
        }
        C2322l c2322l3 = f22383b;
        if (C2322l.g(zVar.l, c2322l3) != -1) {
            return c2322l3;
        }
        return null;
    }

    public static final z d(C2319i c2319i, boolean z6) throws EOFException {
        C2322l c2322l;
        C2322l c2322l2;
        char cK;
        C2322l c2322l3;
        C2322l c2322lF;
        C2319i c2319i2 = new C2319i();
        C2322l c2322lE = null;
        int i7 = 0;
        while (true) {
            if (!c2319i.q(0L, f22382a)) {
                c2322l = f22383b;
                if (!c2319i.q(0L, c2322l)) {
                    break;
                }
            }
            byte bZ = c2319i.z();
            if (c2322lE == null) {
                c2322lE = e(bZ);
            }
            i7++;
        }
        boolean z10 = i7 >= 2 && m.a(c2322lE, c2322l);
        C2322l c2322l4 = c;
        if (z10) {
            m.c(c2322lE);
            c2319i2.U(c2322lE);
            c2319i2.U(c2322lE);
        } else if (i7 > 0) {
            m.c(c2322lE);
            c2319i2.U(c2322lE);
        } else {
            long jO = c2319i.o(c2322l4);
            if (c2322lE == null) {
                c2322lE = jO == -1 ? f(z.f21951m) : e(c2319i.k(jO));
            }
            if (m.a(c2322lE, c2322l)) {
                c2322l2 = c2322lE;
                if (c2319i.f21932m >= 2 && c2319i.k(1L) == 58 && (('a' <= (cK = (char) c2319i.k(0L)) && cK < '{') || ('A' <= cK && cK < '['))) {
                    if (jO == 2) {
                        c2319i2.t(c2319i, 3L);
                    } else {
                        c2319i2.t(c2319i, 2L);
                    }
                }
            } else {
                c2322l2 = c2322lE;
            }
            c2322lE = c2322l2;
        }
        boolean z11 = c2319i2.f21932m > 0;
        ArrayList arrayList = new ArrayList();
        while (true) {
            boolean zJ = c2319i.j();
            c2322l3 = d;
            if (zJ) {
                break;
            }
            long jO2 = c2319i.o(c2322l4);
            if (jO2 == -1) {
                c2322lF = c2319i.F(c2319i.f21932m);
            } else {
                c2322lF = c2319i.F(jO2);
                c2319i.z();
            }
            C2322l c2322l5 = f22384e;
            if (m.a(c2322lF, c2322l5)) {
                if (!z11 || !arrayList.isEmpty()) {
                    if (!z6 || (!z11 && (arrayList.isEmpty() || m.a(AbstractC1998m.h0(arrayList), c2322l5)))) {
                        arrayList.add(c2322lF);
                    } else if ((!z10 || arrayList.size() != 1) && !arrayList.isEmpty()) {
                        arrayList.remove(AbstractC1999n.C(arrayList));
                    }
                }
            } else if (!m.a(c2322lF, c2322l3) && !m.a(c2322lF, C2322l.o)) {
                arrayList.add(c2322lF);
            }
        }
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (i10 > 0) {
                c2319i2.U(c2322lE);
            }
            c2319i2.U((C2322l) arrayList.get(i10));
        }
        if (c2319i2.f21932m == 0) {
            c2319i2.U(c2322l3);
        }
        return new z(c2319i2.F(c2319i2.f21932m));
    }

    public static final C2322l e(byte b10) {
        if (b10 == 47) {
            return f22382a;
        }
        if (b10 == 92) {
            return f22383b;
        }
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(b10, "not a directory separator: "));
    }

    public static final C2322l f(String str) {
        if (m.a(str, "/")) {
            return f22382a;
        }
        if (m.a(str, "\\")) {
            return f22383b;
        }
        throw new IllegalArgumentException(M5.b.g("not a directory separator: ", str));
    }
}
