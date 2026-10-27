package W7;

import f8.InterfaceC1266b;
import g1.AbstractC1274a;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public final class B extends r implements InterfaceC1266b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TypeVariable f9868a;

    public B(TypeVariable typeVariable) {
        A7.m.f(typeVariable, "typeVariable");
        this.f9868a = typeVariable;
    }

    @Override // f8.InterfaceC1266b
    public final C0840d a(C2058c c2058c) {
        Annotation[] declaredAnnotations;
        A7.m.f(c2058c, "fqName");
        TypeVariable typeVariable = this.f9868a;
        AnnotatedElement annotatedElement = typeVariable instanceof AnnotatedElement ? (AnnotatedElement) typeVariable : null;
        if (annotatedElement == null || (declaredAnnotations = annotatedElement.getDeclaredAnnotations()) == null) {
            return null;
        }
        return AbstractC1274a.H(declaredAnnotations, c2058c);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof B) {
            if (A7.m.a(this.f9868a, ((B) obj).f9868a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f9868a.hashCode();
    }

    @Override // f8.InterfaceC1266b
    public final Collection k() {
        Annotation[] declaredAnnotations;
        TypeVariable typeVariable = this.f9868a;
        AnnotatedElement annotatedElement = typeVariable instanceof AnnotatedElement ? (AnnotatedElement) typeVariable : null;
        return (annotatedElement == null || (declaredAnnotations = annotatedElement.getDeclaredAnnotations()) == null) ? n7.u.l : AbstractC1274a.K(declaredAnnotations);
    }

    public final String toString() {
        return B.class.getName() + ": " + this.f9868a;
    }
}
