package g.a.a.x2;

import g.a.b.j0.x;
import java.util.Enumeration;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static void a(Vector vector, Enumeration enumeration) {
        while (enumeration.hasMoreElements()) {
            vector.addElement(enumeration.nextElement());
        }
    }

    public static g b(x xVar) {
        if (xVar == null) {
            return null;
        }
        return new g(xVar.a(), xVar.b(), xVar.e(), xVar.c(), xVar.f());
    }

    public static g c(String str) {
        g gVarB = d.b(str);
        if (gVarB == null) {
            gVarB = g.a.a.r2.b.h(str);
        }
        if (gVarB == null) {
            gVarB = g.a.a.l2.a.b(str);
        }
        if (gVarB == null) {
            gVarB = g.a.a.s2.a.d(str);
        }
        if (gVarB == null) {
            gVarB = g.a.a.z1.a.f(str);
        }
        if (gVarB == null) {
            gVarB = b(g.a.a.d2.b.a(str));
        }
        return gVarB == null ? g.a.a.f2.a.f(str) : gVarB;
    }

    public static g d(g.a.a.m mVar) {
        g gVarC = d.c(mVar);
        if (gVarC == null) {
            gVarC = g.a.a.r2.b.i(mVar);
        }
        if (gVarC == null) {
            gVarC = g.a.a.s2.a.e(mVar);
        }
        if (gVarC == null) {
            gVarC = g.a.a.z1.a.g(mVar);
        }
        if (gVarC == null) {
            gVarC = b(g.a.a.d2.b.b(mVar));
        }
        return gVarC == null ? g.a.a.f2.a.g(mVar) : gVarC;
    }

    public static String e(g.a.a.m mVar) {
        String strD = d.d(mVar);
        if (strD == null) {
            strD = g.a.a.r2.b.j(mVar);
        }
        if (strD == null) {
            strD = g.a.a.l2.a.d(mVar);
        }
        if (strD == null) {
            strD = g.a.a.s2.a.f(mVar);
        }
        if (strD == null) {
            strD = g.a.a.z1.a.h(mVar);
        }
        if (strD == null) {
            strD = g.a.a.d2.b.c(mVar);
        }
        return strD == null ? g.a.a.f2.a.h(mVar) : strD;
    }

    public static Enumeration f() {
        Vector vector = new Vector();
        a(vector, d.e());
        a(vector, g.a.a.r2.b.k());
        a(vector, g.a.a.l2.a.e());
        a(vector, g.a.a.s2.a.g());
        a(vector, g.a.a.z1.a.i());
        a(vector, g.a.a.d2.b.d());
        a(vector, g.a.a.f2.a.i());
        return vector.elements();
    }

    public static g.a.a.m g(String str) {
        g.a.a.m mVarF = d.f(str);
        if (mVarF == null) {
            mVarF = g.a.a.r2.b.l(str);
        }
        if (mVarF == null) {
            mVarF = g.a.a.l2.a.f(str);
        }
        if (mVarF == null) {
            mVarF = g.a.a.s2.a.h(str);
        }
        if (mVarF == null) {
            mVarF = g.a.a.z1.a.j(str);
        }
        if (mVarF == null) {
            mVarF = g.a.a.d2.b.e(str);
        }
        return mVarF == null ? g.a.a.f2.a.j(str) : mVarF;
    }
}
