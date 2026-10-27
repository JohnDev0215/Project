package E5;

import io.sentry.C1489n1;
import java.lang.reflect.InvocationTargetException;
import java.util.TreeMap;

/* JADX INFO: renamed from: E5.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0187l extends q {
    public static final C0181f d = new C0181f(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final M f1818a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0186k[] f1819b;
    public final C1489n1 c;

    public C0187l(M m10, TreeMap treeMap) {
        this.f1818a = m10;
        this.f1819b = (C0186k[]) treeMap.values().toArray(new C0186k[treeMap.size()]);
        this.c = C1489n1.z((String[]) treeMap.keySet().toArray(new String[treeMap.size()]));
    }

    @Override // E5.q
    public final Object a(u uVar) {
        try {
            Object objF = this.f1818a.f();
            try {
                uVar.g();
                while (uVar.m()) {
                    int iF = uVar.F(this.c);
                    if (iF == -1) {
                        uVar.I();
                        uVar.K();
                    } else {
                        C0186k c0186k = this.f1819b[iF];
                        c0186k.f1817b.set(objF, c0186k.c.a(uVar));
                    }
                }
                uVar.j();
                return objF;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        } catch (IllegalAccessException unused2) {
            throw new AssertionError();
        } catch (InstantiationException e10) {
            throw new RuntimeException(e10);
        } catch (InvocationTargetException e11) {
            G5.f.i(e11);
            throw null;
        }
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        try {
            xVar.g();
            for (C0186k c0186k : this.f1819b) {
                xVar.k(c0186k.f1816a);
                c0186k.c.d(xVar, c0186k.f1817b.get(obj));
            }
            xVar.i();
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        }
    }

    public final String toString() {
        return "JsonAdapter(" + this.f1818a + ")";
    }
}
