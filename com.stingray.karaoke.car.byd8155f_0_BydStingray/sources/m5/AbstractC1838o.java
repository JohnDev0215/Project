package m5;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import o5.AbstractC2047c;
import r5.C2297a;
import r5.C2298b;

/* JADX INFO: renamed from: m5.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1838o extends j5.y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1840q f19240a;

    public AbstractC1838o(C1840q c1840q) {
        this.f19240a = c1840q;
    }

    @Override // j5.y
    public final Object a(C2297a c2297a) throws IOException {
        if (c2297a.Q() == 9) {
            c2297a.M();
            return null;
        }
        Object objC = c();
        Map map = this.f19240a.f19242a;
        try {
            c2297a.g();
            while (c2297a.v()) {
                C1837n c1837n = (C1837n) map.get(c2297a.K());
                if (c1837n == null) {
                    c2297a.W();
                } else {
                    e(objC, c2297a, c1837n);
                }
            }
            c2297a.m();
            return d(objC);
        } catch (IllegalAccessException e10) {
            h6.r rVar = AbstractC2047c.f20529a;
            throw new RuntimeException("Unexpected IllegalAccessException occurred (Gson 2.11.0). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", e10);
        } catch (IllegalStateException e11) {
            throw new j5.p(e11);
        }
    }

    @Override // j5.y
    public final void b(C2298b c2298b, Object obj) throws IOException {
        if (obj == null) {
            c2298b.v();
            return;
        }
        c2298b.i();
        try {
            Iterator it = this.f19240a.f19243b.iterator();
            while (it.hasNext()) {
                ((C1837n) it.next()).a(c2298b, obj);
            }
            c2298b.m();
        } catch (IllegalAccessException e10) {
            h6.r rVar = AbstractC2047c.f20529a;
            throw new RuntimeException("Unexpected IllegalAccessException occurred (Gson 2.11.0). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", e10);
        }
    }

    public abstract Object c();

    public abstract Object d(Object obj);

    public abstract void e(Object obj, C2297a c2297a, C1837n c1837n);
}
