package m5;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import n0.C1886o;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: m5.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1833j extends j5.y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1843u f19226a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1843u f19227b;
    public final l5.o c;
    public final /* synthetic */ C1827d d;

    public C1833j(C1827d c1827d, j5.m mVar, Type type, j5.y yVar, Type type2, j5.y yVar2, l5.o oVar) {
        this.d = c1827d;
        this.f19226a = new C1843u(mVar, yVar, type);
        this.f19227b = new C1843u(mVar, yVar2, type2);
        this.c = oVar;
    }

    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        int iQ = c2297a.Q();
        if (iQ == 9) {
            c2297a.M();
            return null;
        }
        Map map = (Map) this.c.H();
        if (iQ == 1) {
            c2297a.b();
            while (c2297a.v()) {
                c2297a.b();
                Object objA = ((j5.y) this.f19226a.c).a(c2297a);
                if (map.put(objA, ((j5.y) this.f19227b.c).a(c2297a)) != null) {
                    throw new j5.p("duplicate key: " + objA);
                }
                c2297a.k();
            }
            c2297a.k();
        } else {
            c2297a.g();
            while (c2297a.v()) {
                C1886o.f19443m.getClass();
                int iJ = c2297a.f21849r;
                if (iJ == 0) {
                    iJ = c2297a.j();
                }
                if (iJ == 13) {
                    c2297a.f21849r = 9;
                } else if (iJ == 12) {
                    c2297a.f21849r = 8;
                } else {
                    if (iJ != 14) {
                        throw c2297a.Y("a name");
                    }
                    c2297a.f21849r = 10;
                }
                Object objA2 = ((j5.y) this.f19226a.c).a(c2297a);
                if (map.put(objA2, ((j5.y) this.f19227b.c).a(c2297a)) != null) {
                    throw new j5.p("duplicate key: " + objA2);
                }
            }
            c2297a.m();
        }
        return map;
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        Map map = (Map) obj;
        if (map == null) {
            c2298b.v();
            return;
        }
        this.d.getClass();
        C1843u c1843u = this.f19227b;
        c2298b.i();
        for (Map.Entry entry : map.entrySet()) {
            c2298b.o(String.valueOf(entry.getKey()));
            c1843u.b(c2298b, entry.getValue());
        }
        c2298b.m();
    }
}
