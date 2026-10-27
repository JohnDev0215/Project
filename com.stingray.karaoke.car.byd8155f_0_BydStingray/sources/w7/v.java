package W7;

import Q7.Z;
import Q7.c0;
import Q7.f0;
import f8.InterfaceC1266b;
import f8.InterfaceC1267c;
import g1.AbstractC1274a;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.Collection;
import o8.AbstractC2063h;
import o8.C2058c;
import o8.C2061f;

/* JADX INFO: loaded from: classes.dex */
public abstract class v extends r implements InterfaceC1266b, InterfaceC1267c {
    @Override // f8.InterfaceC1266b
    public final C0840d a(C2058c c2058c) {
        A7.m.f(c2058c, "fqName");
        Member memberB = b();
        A7.m.d(memberB, "null cannot be cast to non-null type java.lang.reflect.AnnotatedElement");
        Annotation[] declaredAnnotations = ((AnnotatedElement) memberB).getDeclaredAnnotations();
        if (declaredAnnotations != null) {
            return AbstractC1274a.H(declaredAnnotations, c2058c);
        }
        return null;
    }

    public abstract Member b();

    public final C2061f c() {
        String name = b().getName();
        C2061f c2061fE = name != null ? C2061f.e(name) : null;
        return c2061fE == null ? AbstractC2063h.f20575a : c2061fE;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0129  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.ArrayList d(java.lang.reflect.Type[] r13, java.lang.annotation.Annotation[][] r14, boolean r15) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: W7.v.d(java.lang.reflect.Type[], java.lang.annotation.Annotation[][], boolean):java.util.ArrayList");
    }

    public final f0 e() {
        int modifiers = b().getModifiers();
        return Modifier.isPublic(modifiers) ? c0.c : Modifier.isPrivate(modifiers) ? Z.c : Modifier.isProtected(modifiers) ? Modifier.isStatic(modifiers) ? U7.c.c : U7.b.c : U7.a.c;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof v) && A7.m.a(b(), ((v) obj).b());
    }

    public final int hashCode() {
        return b().hashCode();
    }

    @Override // f8.InterfaceC1266b
    public final Collection k() {
        Member memberB = b();
        A7.m.d(memberB, "null cannot be cast to non-null type java.lang.reflect.AnnotatedElement");
        Annotation[] declaredAnnotations = ((AnnotatedElement) memberB).getDeclaredAnnotations();
        return declaredAnnotations != null ? AbstractC1274a.K(declaredAnnotations) : n7.u.l;
    }

    public final String toString() {
        return getClass().getName() + ": " + b();
    }
}
