package n8;

import A7.m;
import T.C0746i0;
import h8.AbstractC1386c;
import h8.AbstractC1387d;
import j8.C;
import j8.C1556a;
import j8.C1565j;
import j8.C1567l;
import j8.C1579y;
import j8.G;
import j8.Q;
import j8.Z;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import l8.C1784b;
import l8.InterfaceC1788f;
import m7.i;
import m8.j;
import m8.k;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import p8.AbstractC2126a;
import p8.C2130e;
import p8.C2132g;
import p8.C2138m;
import p8.C2142q;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2132g f20171a;

    static {
        C2132g c2132g = new C2132g();
        c2132g.a(k.f19327a);
        c2132g.a(k.f19328b);
        c2132g.a(k.c);
        c2132g.a(k.d);
        c2132g.a(k.f19329e);
        c2132g.a(k.f19330f);
        c2132g.a(k.f19331g);
        c2132g.a(k.f19332h);
        c2132g.a(k.f19333i);
        c2132g.a(k.f19334j);
        c2132g.a(k.f19335k);
        c2132g.a(k.l);
        c2132g.a(k.f19336m);
        c2132g.a(k.f19337n);
        f20171a = c2132g;
    }

    public static e a(C1567l c1567l, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0) {
        String strF0;
        m.f(c1567l, "proto");
        m.f(interfaceC1788f, "nameResolver");
        m.f(c0746i0, "typeTable");
        C2138m c2138m = k.f19327a;
        m.e(c2138m, "constructorSignature");
        m8.c cVar = (m8.c) AbstractC1386c.h(c1567l, c2138m);
        String strN = (cVar == null || (cVar.f19280m & 1) != 1) ? "<init>" : interfaceC1788f.n(cVar.f19281n);
        if (cVar == null || (cVar.f19280m & 2) != 2) {
            List<Z> list = c1567l.f17413p;
            m.e(list, "proto.valueParameterList");
            ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
            for (Z z6 : list) {
                m.e(z6, "it");
                String strE = e(AbstractC1387d.y(z6, c0746i0), interfaceC1788f);
                if (strE == null) {
                    return null;
                }
                arrayList.add(strE);
            }
            strF0 = AbstractC1998m.f0(arrayList, "", "(", ")V", null, 56);
        } else {
            strF0 = interfaceC1788f.n(cVar.o);
        }
        return new e(strN, strF0);
    }

    public static d b(G g7, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0, boolean z6) {
        String strE;
        m.f(g7, "proto");
        m.f(interfaceC1788f, "nameResolver");
        m.f(c0746i0, "typeTable");
        C2138m c2138m = k.d;
        m.e(c2138m, "propertySignature");
        m8.e eVar = (m8.e) AbstractC1386c.h(g7, c2138m);
        if (eVar == null) {
            return null;
        }
        m8.b bVar = (eVar.f19291m & 1) == 1 ? eVar.f19292n : null;
        if (bVar == null && z6) {
            return null;
        }
        int i7 = (bVar == null || (bVar.f19274m & 1) != 1) ? g7.f17120q : bVar.f19275n;
        if (bVar == null || (bVar.f19274m & 2) != 2) {
            strE = e(AbstractC1387d.r(g7, c0746i0), interfaceC1788f);
            if (strE == null) {
                return null;
            }
        } else {
            strE = interfaceC1788f.n(bVar.o);
        }
        return new d(interfaceC1788f.n(i7), strE);
    }

    public static e c(C1579y c1579y, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0) {
        String strL;
        m.f(c1579y, "proto");
        m.f(interfaceC1788f, "nameResolver");
        m.f(c0746i0, "typeTable");
        C2138m c2138m = k.f19328b;
        m.e(c2138m, "methodSignature");
        m8.c cVar = (m8.c) AbstractC1386c.h(c1579y, c2138m);
        int i7 = (cVar == null || (cVar.f19280m & 1) != 1) ? c1579y.f17491q : cVar.f19281n;
        if (cVar == null || (cVar.f19280m & 2) != 2) {
            List listE = AbstractC1999n.E(AbstractC1387d.o(c1579y, c0746i0));
            List<Z> list = c1579y.f17500z;
            m.e(list, "proto.valueParameterList");
            ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
            for (Z z6 : list) {
                m.e(z6, "it");
                arrayList.add(AbstractC1387d.y(z6, c0746i0));
            }
            ArrayList arrayListN0 = AbstractC1998m.n0(listE, arrayList);
            ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayListN0, 10));
            Iterator it = arrayListN0.iterator();
            while (it.hasNext()) {
                String strE = e((Q) it.next(), interfaceC1788f);
                if (strE == null) {
                    return null;
                }
                arrayList2.add(strE);
            }
            String strE2 = e(AbstractC1387d.q(c1579y, c0746i0), interfaceC1788f);
            if (strE2 == null) {
                return null;
            }
            strL = android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder(), AbstractC1998m.f0(arrayList2, "", "(", ")", null, 56), strE2);
        } else {
            strL = interfaceC1788f.n(cVar.o);
        }
        return new e(interfaceC1788f.n(i7), strL);
    }

    public static final boolean d(G g7) {
        m.f(g7, "proto");
        C1784b c1784b = c.f20165a;
        Object objK = g7.k(k.f19329e);
        m.e(objK, "proto.getExtension(JvmProtoBuf.flags)");
        return c1784b.c(((Number) objK).intValue()).booleanValue();
    }

    public static String e(Q q4, InterfaceC1788f interfaceC1788f) {
        if (q4.p()) {
            return b.b(interfaceC1788f.u(q4.f17189t));
        }
        return null;
    }

    public static final i f(String[] strArr, String[] strArr2) throws C2142q {
        m.f(strArr2, "strings");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(a.a(strArr));
        g gVarG = g(byteArrayInputStream, strArr2);
        C1556a c1556a = C1565j.f17373V;
        c1556a.getClass();
        C2130e c2130e = new C2130e(byteArrayInputStream);
        AbstractC2126a abstractC2126a = (AbstractC2126a) c1556a.a(c2130e, f20171a);
        try {
            c2130e.a(0);
            if (abstractC2126a.b()) {
                return new i(gVarG, (C1565j) abstractC2126a);
            }
            C2142q c2142q = new C2142q(new A9.k().getMessage());
            c2142q.l = abstractC2126a;
            throw c2142q;
        } catch (C2142q e10) {
            e10.l = abstractC2126a;
            throw e10;
        }
    }

    public static g g(ByteArrayInputStream byteArrayInputStream, String[] strArr) {
        j jVar = (j) j.f19322s.b(byteArrayInputStream, f20171a);
        m.e(jVar, "parseDelimitedFrom(this, EXTENSION_REGISTRY)");
        return new g(jVar, strArr);
    }

    public static final i h(String[] strArr, String[] strArr2) throws C2142q {
        m.f(strArr, "data");
        m.f(strArr2, "strings");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(a.a(strArr));
        g gVarG = g(byteArrayInputStream, strArr2);
        C1556a c1556a = C.f17076w;
        c1556a.getClass();
        C2130e c2130e = new C2130e(byteArrayInputStream);
        AbstractC2126a abstractC2126a = (AbstractC2126a) c1556a.a(c2130e, f20171a);
        try {
            c2130e.a(0);
            if (abstractC2126a.b()) {
                return new i(gVarG, (C) abstractC2126a);
            }
            C2142q c2142q = new C2142q(new A9.k().getMessage());
            c2142q.l = abstractC2126a;
            throw c2142q;
        } catch (C2142q e10) {
            e10.l = abstractC2126a;
            throw e10;
        }
    }
}
