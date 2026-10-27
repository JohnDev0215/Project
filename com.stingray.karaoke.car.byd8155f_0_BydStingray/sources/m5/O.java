package m5;

import h2.AbstractC1347d;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Iterator;
import r5.C2297a;
import r5.C2298b;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public class O extends j5.y {
    public static j5.o c(C2297a c2297a, int i7) throws IOException {
        int iC = AbstractC2641j.c(i7);
        if (iC == 5) {
            return new j5.s(c2297a.O());
        }
        if (iC == 6) {
            return new j5.s(new l5.j(c2297a.O()));
        }
        if (iC == 7) {
            return new j5.s(Boolean.valueOf(c2297a.C()));
        }
        if (iC != 8) {
            throw new IllegalStateException("Unexpected token: ".concat(AbstractC1347d.v(i7)));
        }
        c2297a.M();
        return j5.q.l;
    }

    public static void d(C2298b c2298b, j5.o oVar) throws IOException {
        if (oVar == null || (oVar instanceof j5.q)) {
            c2298b.v();
            return;
        }
        boolean z6 = oVar instanceof j5.s;
        if (z6) {
            if (!z6) {
                throw new IllegalStateException("Not a JSON Primitive: " + oVar);
            }
            j5.s sVar = (j5.s) oVar;
            Serializable serializable = sVar.l;
            if (serializable instanceof Number) {
                c2298b.K(sVar.d());
                return;
            } else if (serializable instanceof Boolean) {
                c2298b.M(serializable instanceof Boolean ? ((Boolean) serializable).booleanValue() : Boolean.parseBoolean(sVar.l()));
                return;
            } else {
                c2298b.L(sVar.l());
                return;
            }
        }
        boolean z10 = oVar instanceof j5.n;
        if (z10) {
            c2298b.g();
            if (!z10) {
                throw new IllegalStateException("Not a JSON Array: " + oVar);
            }
            Iterator it = ((j5.n) oVar).l.iterator();
            while (it.hasNext()) {
                d(c2298b, (j5.o) it.next());
            }
            c2298b.k();
            return;
        }
        boolean z11 = oVar instanceof j5.r;
        if (!z11) {
            throw new IllegalArgumentException("Couldn't write " + oVar.getClass());
        }
        c2298b.i();
        if (!z11) {
            throw new IllegalStateException("Not a JSON Object: " + oVar);
        }
        Iterator it2 = ((l5.l) ((j5.r) oVar).l.entrySet()).iterator();
        while (((E5.C) it2).hasNext()) {
            l5.m mVarB = ((l5.k) it2).b();
            c2298b.o((String) mVarB.getKey());
            d(c2298b, (j5.o) mVarB.getValue());
        }
        c2298b.m();
    }

    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        j5.o nVar;
        j5.o nVar2;
        int iQ = c2297a.Q();
        int iC = AbstractC2641j.c(iQ);
        if (iC == 0) {
            c2297a.b();
            nVar = new j5.n();
        } else if (iC != 2) {
            nVar = null;
        } else {
            c2297a.g();
            nVar = new j5.r();
        }
        if (nVar == null) {
            return c(c2297a, iQ);
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        while (true) {
            if (c2297a.v()) {
                String strK = nVar instanceof j5.r ? c2297a.K() : null;
                int iQ2 = c2297a.Q();
                int iC2 = AbstractC2641j.c(iQ2);
                if (iC2 == 0) {
                    c2297a.b();
                    nVar2 = new j5.n();
                } else if (iC2 != 2) {
                    nVar2 = null;
                } else {
                    c2297a.g();
                    nVar2 = new j5.r();
                }
                boolean z6 = nVar2 != null;
                if (nVar2 == null) {
                    nVar2 = c(c2297a, iQ2);
                }
                if (nVar instanceof j5.n) {
                    ((j5.n) nVar).l.add(nVar2);
                } else {
                    ((j5.r) nVar).l.put(strK, nVar2);
                }
                if (z6) {
                    arrayDeque.addLast(nVar);
                    nVar = nVar2;
                }
            } else {
                if (nVar instanceof j5.n) {
                    c2297a.k();
                } else {
                    c2297a.m();
                }
                if (arrayDeque.isEmpty()) {
                    return nVar;
                }
                nVar = (j5.o) arrayDeque.removeLast();
            }
        }
    }

    @Override // j5.y
    public final /* bridge */ /* synthetic */ void b(C2298b c2298b, Object obj) throws IOException {
        d(c2298b, (j5.o) obj);
    }
}
