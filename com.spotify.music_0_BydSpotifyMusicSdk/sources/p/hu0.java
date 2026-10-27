package p;

import java.io.FileNotFoundException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class hu0 {
    public static final c81 a;

    static {
        c81 c81Var;
        try {
            Class.forName("java.nio.file.Files");
            c81Var = new kn1();
        } catch (ClassNotFoundException unused) {
            c81Var = new c81();
        }
        a = c81Var;
        String str = os1.e;
        String property = System.getProperty("java.io.tmpdir");
        v03.i(property, "getProperty(...)");
        u11.z(property, false);
        ClassLoader classLoader = okio.internal.a.class.getClassLoader();
        v03.i(classLoader, "getClassLoader(...)");
        new okio.internal.a(classLoader);
    }

    public abstract pd2 a(os1 os1Var);

    public abstract void b(os1 os1Var, os1 os1Var2);

    public abstract void c(os1 os1Var);

    public abstract void d(os1 os1Var);

    public final void e(os1 os1Var) {
        v03.j(os1Var, "path");
        d(os1Var);
    }

    public final boolean f(os1 os1Var) {
        v03.j(os1Var, "path");
        return i(os1Var) != null;
    }

    public abstract List g(os1 os1Var);

    public final x50 h(os1 os1Var) throws FileNotFoundException {
        v03.j(os1Var, "path");
        x50 x50VarI = i(os1Var);
        if (x50VarI != null) {
            return x50VarI;
        }
        throw new FileNotFoundException("no such file: " + os1Var);
    }

    public abstract x50 i(os1 os1Var);

    public abstract b81 j(os1 os1Var);

    public abstract pd2 k(os1 os1Var);

    public abstract de2 l(os1 os1Var);
}
