package g.a.a.l2;

import g.a.a.m;
import g.a.a.r2.c;
import g.a.a.x2.g;
import g.a.f.i;
import java.util.Enumeration;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Hashtable f3905a = new Hashtable();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Hashtable f3906b = new Hashtable();

    static {
        a("B-571", c.F);
        a("B-409", c.D);
        a("B-283", c.n);
        a("B-233", c.t);
        a("B-163", c.l);
        a("K-571", c.E);
        a("K-409", c.C);
        a("K-283", c.m);
        a("K-233", c.s);
        a("K-163", c.f4025b);
        a("P-521", c.B);
        a("P-384", c.A);
        a("P-256", c.H);
        a("P-224", c.z);
        a("P-192", c.G);
    }

    public static void a(String str, m mVar) {
        f3905a.put(str, mVar);
        f3906b.put(mVar, str);
    }

    public static g b(String str) {
        m mVar = (m) f3905a.get(i.k(str));
        if (mVar != null) {
            return c(mVar);
        }
        return null;
    }

    public static g c(m mVar) {
        return g.a.a.r2.b.i(mVar);
    }

    public static String d(m mVar) {
        return (String) f3906b.get(mVar);
    }

    public static Enumeration e() {
        return f3905a.keys();
    }

    public static m f(String str) {
        return (m) f3905a.get(i.k(str));
    }
}
