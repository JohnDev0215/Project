package V7;

import A7.m;
import Q8.o;
import l9.l;
import o8.C2057b;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ClassLoader f9547a;

    public /* synthetic */ b(ClassLoader classLoader) {
        this.f9547a = classLoader;
    }

    public e7.f a(C2057b c2057b, n8.f fVar) {
        c cVarI;
        m.f(c2057b, "classId");
        m.f(fVar, "jvmMetadataVersion");
        String strW0 = o.w0(c2057b.h().b(), '.', '$');
        if (!c2057b.g().d()) {
            strW0 = c2057b.g() + '.' + strW0;
        }
        Class clsJ0 = l9.d.j0(this.f9547a, strW0);
        if (clsJ0 == null || (cVarI = l.I(clsJ0)) == null) {
            return null;
        }
        return new e7.f(cVarI, 6);
    }
}
