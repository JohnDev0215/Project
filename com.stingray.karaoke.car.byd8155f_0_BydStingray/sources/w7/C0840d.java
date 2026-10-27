package W7;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import o8.C2061f;

/* JADX INFO: renamed from: W7.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0840d extends r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Annotation f9880a;

    public C0840d(Annotation annotation) {
        A7.m.f(annotation, "annotation");
        this.f9880a = annotation;
    }

    public final ArrayList b() throws IllegalAccessException, InvocationTargetException {
        Annotation annotation = this.f9880a;
        Method[] declaredMethods = n7.z.i(n7.z.g(annotation)).getDeclaredMethods();
        A7.m.e(declaredMethods, "annotation.annotationClass.java.declaredMethods");
        ArrayList arrayList = new ArrayList(declaredMethods.length);
        for (Method method : declaredMethods) {
            Object objInvoke = method.invoke(annotation, null);
            A7.m.e(objInvoke, "method.invoke(annotation)");
            C2061f c2061fE = C2061f.e(method.getName());
            Class<?> cls = objInvoke.getClass();
            List list = AbstractC0839c.f9878a;
            arrayList.add(Enum.class.isAssignableFrom(cls) ? new s(c2061fE, (Enum) objInvoke) : objInvoke instanceof Annotation ? new f(c2061fE, (Annotation) objInvoke) : objInvoke instanceof Object[] ? new g(c2061fE, (Object[]) objInvoke) : objInvoke instanceof Class ? new o(c2061fE, (Class) objInvoke) : new u(c2061fE, objInvoke));
        }
        return arrayList;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0840d) {
            if (this.f9880a == ((C0840d) obj).f9880a) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return System.identityHashCode(this.f9880a);
    }

    public final String toString() {
        return C0840d.class.getName() + ": " + this.f9880a;
    }
}
