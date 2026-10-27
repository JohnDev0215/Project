package r9;

import java.io.FileNotFoundException;
import java.util.List;
import n0.C1886o;
import n7.C1995j;
import p1.C2078f;

/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final v f21939a;

    static {
        v vVar;
        try {
            Class.forName("java.nio.file.Files");
            vVar = new w();
        } catch (ClassNotFoundException unused) {
            vVar = new v();
        }
        f21939a = vVar;
        String str = z.f21951m;
        String property = System.getProperty("java.io.tmpdir");
        A7.m.e(property, "getProperty(...)");
        C1886o.i(property, false);
        ClassLoader classLoader = s9.f.class.getClassLoader();
        A7.m.e(classLoader, "getClassLoader(...)");
        new s9.f(classLoader);
    }

    public final void a(z zVar) {
        C1995j<z> c1995j = new C1995j();
        while (zVar != null && !e(zVar)) {
            c1995j.addFirst(zVar);
            zVar = zVar.c();
        }
        for (z zVar2 : c1995j) {
            A7.m.f(zVar2, "dir");
            b(zVar2);
        }
    }

    public abstract void b(z zVar);

    public abstract void c(z zVar);

    public final void d(z zVar) {
        A7.m.f(zVar, "path");
        c(zVar);
    }

    public final boolean e(z zVar) {
        A7.m.f(zVar, "path");
        return h(zVar) != null;
    }

    public abstract List f(z zVar);

    public final C2078f g(z zVar) throws FileNotFoundException {
        A7.m.f(zVar, "path");
        C2078f c2078fH = h(zVar);
        if (c2078fH != null) {
            return c2078fH;
        }
        throw new FileNotFoundException("no such file: " + zVar);
    }

    public abstract C2078f h(z zVar);

    public abstract u i(z zVar);

    public abstract H j(z zVar);

    public abstract J k(z zVar);
}
