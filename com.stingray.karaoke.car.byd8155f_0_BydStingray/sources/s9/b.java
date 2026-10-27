package s9;

import A7.m;
import A7.v;
import E5.y;
import Q8.o;
import g1.AbstractC1274a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import n0.C1886o;
import n7.AbstractC1998m;
import r9.C2319i;
import r9.D;
import r9.G;
import r9.z;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f22381a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static final int a(char c) {
        if ('0' <= c && c < ':') {
            return c - '0';
        }
        if ('a' <= c && c < 'g') {
            return c - 'W';
        }
        if ('A' <= c && c < 'G') {
            return c - '7';
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c);
    }

    public static final LinkedHashMap b(ArrayList arrayList) {
        String str = z.f21951m;
        z zVarI = C1886o.i("/", false);
        m7.i[] iVarArr = {new m7.i(zVarI, new g(zVarI, true, null, 0L, 0L, 0L, 0, 0L, 0, 0, null, null, null, 65532))};
        LinkedHashMap linkedHashMap = new LinkedHashMap(n7.z.q(1));
        n7.z.v(linkedHashMap, iVarArr);
        for (g gVar : AbstractC1998m.u0(arrayList, new y(11))) {
            if (((g) linkedHashMap.put(gVar.f22391a, gVar)) == null) {
                while (true) {
                    z zVar = gVar.f22391a;
                    z zVarC = zVar.c();
                    if (zVarC != null) {
                        g gVar2 = (g) linkedHashMap.get(zVarC);
                        if (gVar2 != null) {
                            gVar2.f22403q.add(zVar);
                            break;
                        }
                        g gVar3 = new g(zVarC, true, null, 0L, 0L, 0L, 0, 0L, 0, 0, null, null, null, 65532);
                        linkedHashMap.put(zVarC, gVar3);
                        gVar3.f22403q.add(zVar);
                        gVar = gVar3;
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public static final String c(int i7) {
        AbstractC1274a.y(16);
        String string = Integer.toString(i7, 16);
        m.e(string, "toString(...)");
        return "0x".concat(string);
    }

    public static final g d(D d) throws IOException {
        String str;
        long j3;
        int iV = d.v();
        if (iV != 33639248) {
            throw new IOException("bad zip: expected " + c(33639248) + " but was " + c(iV));
        }
        d.K(4L);
        short sC = d.C();
        int i7 = sC & 65535;
        if ((sC & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + c(i7));
        }
        int iC = d.C() & 65535;
        int iC2 = d.C() & 65535;
        int iC3 = d.C() & 65535;
        long jV = ((long) d.v()) & 4294967295L;
        A7.y yVar = new A7.y();
        yVar.l = ((long) d.v()) & 4294967295L;
        A7.y yVar2 = new A7.y();
        yVar2.l = ((long) d.v()) & 4294967295L;
        int iC4 = d.C() & 65535;
        int iC5 = d.C() & 65535;
        int iC6 = d.C() & 65535;
        d.K(8L);
        A7.y yVar3 = new A7.y();
        yVar3.l = ((long) d.v()) & 4294967295L;
        String strF = d.F(iC4);
        if (Q8.h.D0(strF, (char) 0)) {
            throw new IOException("bad zip: filename contains 0x00");
        }
        if (yVar2.l == 4294967295L) {
            j3 = 8;
            str = strF;
        } else {
            str = strF;
            j3 = 0;
        }
        if (yVar.l == 4294967295L) {
            j3 += (long) 8;
        }
        if (yVar3.l == 4294967295L) {
            j3 += (long) 8;
        }
        long j10 = j3;
        A7.z zVar = new A7.z();
        A7.z zVar2 = new A7.z();
        A7.z zVar3 = new A7.z();
        v vVar = new v();
        String str2 = str;
        e(d, iC5, new i(vVar, j10, yVar2, d, yVar, yVar3, zVar, zVar2, zVar3));
        if (j10 > 0 && !vVar.l) {
            throw new IOException("bad zip: zip64 extra required but absent");
        }
        String strF2 = d.F(iC6);
        String str3 = z.f21951m;
        return new g(C1886o.i("/", false).e(str2), o.s0(str2, "/", false), strF2, jV, yVar.l, yVar2.l, iC, yVar3.l, iC3, iC2, (Long) zVar.l, (Long) zVar2.l, (Long) zVar3.l, 57344);
    }

    public static final void e(D d, int i7, InterfaceC2785n interfaceC2785n) throws IOException {
        long j3 = i7;
        while (j3 != 0) {
            if (j3 < 4) {
                throw new IOException("bad zip: truncated header in extra field");
            }
            int iC = d.C() & 65535;
            long jC = ((long) d.C()) & 65535;
            long j10 = j3 - ((long) 4);
            if (j10 < jC) {
                throw new IOException("bad zip: truncated value in extra field");
            }
            d.I(jC);
            C2319i c2319i = d.f21897m;
            long j11 = c2319i.f21932m;
            interfaceC2785n.invoke(Integer.valueOf(iC), Long.valueOf(jC));
            long j12 = (c2319i.f21932m + jC) - j11;
            if (j12 < 0) {
                throw new IOException(android.hardware.bydauto.ac.tsimpl.a.e(iC, "unsupported zip: too many bytes processed for "));
            }
            if (j12 > 0) {
                c2319i.R(j12);
            }
            j3 = j10 - jC;
        }
    }

    public static final g f(D d, g gVar) throws IOException {
        int iV = d.v();
        if (iV != 67324752) {
            throw new IOException("bad zip: expected " + c(67324752) + " but was " + c(iV));
        }
        d.K(2L);
        short sC = d.C();
        int i7 = sC & 65535;
        if ((sC & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + c(i7));
        }
        d.K(18L);
        long jC = ((long) d.C()) & 65535;
        int iC = d.C() & 65535;
        d.K(jC);
        if (gVar == null) {
            d.K(iC);
            return null;
        }
        A7.z zVar = new A7.z();
        A7.z zVar2 = new A7.z();
        A7.z zVar3 = new A7.z();
        e(d, iC, new h(d, zVar, zVar2, zVar3));
        return new g(gVar.f22391a, gVar.f22392b, gVar.c, gVar.d, gVar.f22393e, gVar.f22394f, gVar.f22395g, gVar.f22396h, gVar.f22397i, gVar.f22398j, gVar.f22399k, gVar.l, gVar.f22400m, (Integer) zVar.l, (Integer) zVar2.l, (Integer) zVar3.l);
    }

    public static final int g(G g7, int i7) {
        int i10;
        m.f(g7, "<this>");
        int i11 = i7 + 1;
        int length = g7.f21906p.length;
        int[] iArr = g7.f21907q;
        m.f(iArr, "<this>");
        int i12 = length - 1;
        int i13 = 0;
        while (true) {
            if (i13 <= i12) {
                i10 = (i13 + i12) >>> 1;
                int i14 = iArr[i10];
                if (i14 >= i11) {
                    if (i14 <= i11) {
                        break;
                    }
                    i12 = i10 - 1;
                } else {
                    i13 = i10 + 1;
                }
            } else {
                i10 = (-i13) - 1;
                break;
            }
        }
        return i10 >= 0 ? i10 : ~i10;
    }
}
