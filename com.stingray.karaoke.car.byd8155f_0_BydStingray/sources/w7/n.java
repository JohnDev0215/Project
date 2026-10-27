package W7;

import f8.InterfaceC1266b;
import f8.InterfaceC1269e;
import g1.AbstractC1274a;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import n7.AbstractC1996k;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public final class n extends r implements InterfaceC1266b, InterfaceC1269e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f9891a;

    public n(Class cls) {
        A7.m.f(cls, "klass");
        this.f9891a = cls;
    }

    @Override // f8.InterfaceC1266b
    public final C0840d a(C2058c c2058c) {
        Annotation[] declaredAnnotations;
        A7.m.f(c2058c, "fqName");
        Class cls = this.f9891a;
        if (cls == null || (declaredAnnotations = cls.getDeclaredAnnotations()) == null) {
            return null;
        }
        return AbstractC1274a.H(declaredAnnotations, c2058c);
    }

    public final List b() {
        Field[] declaredFields = this.f9891a.getDeclaredFields();
        A7.m.e(declaredFields, "klass.declaredFields");
        return P8.m.i0(P8.m.g0(new P8.g(AbstractC1996k.y(declaredFields), false, k.f9888t), l.f9889t));
    }

    public final C2058c c() {
        return AbstractC0839c.a(this.f9891a).b();
    }

    public final List d() {
        Method[] declaredMethods = this.f9891a.getDeclaredMethods();
        A7.m.e(declaredMethods, "klass.declaredMethods");
        return P8.m.i0(P8.m.g0(new P8.g(AbstractC1996k.y(declaredMethods), true, new S0.p(this, 6)), m.f9890t));
    }

    public final ArrayList e() {
        Class cls = this.f9891a;
        A7.m.f(cls, "clazz");
        B8.t tVar = com.bumptech.glide.d.f12673a;
        if (tVar == null) {
            try {
                tVar = new B8.t(Class.class.getMethod("isSealed", null), Class.class.getMethod("getPermittedSubclasses", null), Class.class.getMethod("isRecord", null), Class.class.getMethod("getRecordComponents", null), 8);
            } catch (NoSuchMethodException unused) {
                tVar = new B8.t(objArr, objArr, objArr, objArr, 8);
            }
            com.bumptech.glide.d.f12673a = tVar;
        }
        Method method = (Method) tVar.f807m;
        objArr = method != null ? (Object[]) method.invoke(cls, null) : null;
        if (objArr == null) {
            objArr = new Object[0];
        }
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(new z(obj));
        }
        return arrayList;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof n) {
            if (A7.m.a(this.f9891a, ((n) obj).f9891a)) {
                return true;
            }
        }
        return false;
    }

    public final boolean f() throws IllegalAccessException, InvocationTargetException {
        Class cls = this.f9891a;
        A7.m.f(cls, "clazz");
        B8.t tVar = com.bumptech.glide.d.f12673a;
        Boolean bool = null;
        if (tVar == null) {
            try {
                tVar = new B8.t(Class.class.getMethod("isSealed", null), Class.class.getMethod("getPermittedSubclasses", null), Class.class.getMethod("isRecord", null), Class.class.getMethod("getRecordComponents", null), 8);
            } catch (NoSuchMethodException unused) {
                tVar = new B8.t(bool, bool, bool, bool, 8);
            }
            com.bumptech.glide.d.f12673a = tVar;
        }
        Method method = (Method) tVar.f809p;
        if (method != null) {
            Object objInvoke = method.invoke(cls, null);
            A7.m.d(objInvoke, "null cannot be cast to non-null type kotlin.Boolean");
            bool = (Boolean) objInvoke;
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final boolean g() throws IllegalAccessException, InvocationTargetException {
        Class cls = this.f9891a;
        A7.m.f(cls, "clazz");
        B8.t tVar = com.bumptech.glide.d.f12673a;
        Boolean bool = null;
        if (tVar == null) {
            try {
                tVar = new B8.t(Class.class.getMethod("isSealed", null), Class.class.getMethod("getPermittedSubclasses", null), Class.class.getMethod("isRecord", null), Class.class.getMethod("getRecordComponents", null), 8);
            } catch (NoSuchMethodException unused) {
                tVar = new B8.t(bool, bool, bool, bool, 8);
            }
            com.bumptech.glide.d.f12673a = tVar;
        }
        Method method = (Method) tVar.f808n;
        if (method != null) {
            Object objInvoke = method.invoke(cls, null);
            A7.m.d(objInvoke, "null cannot be cast to non-null type kotlin.Boolean");
            bool = (Boolean) objInvoke;
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final int hashCode() {
        return this.f9891a.hashCode();
    }

    @Override // f8.InterfaceC1266b
    public final Collection k() {
        Annotation[] declaredAnnotations;
        Class cls = this.f9891a;
        return (cls == null || (declaredAnnotations = cls.getDeclaredAnnotations()) == null) ? n7.u.l : AbstractC1274a.K(declaredAnnotations);
    }

    @Override // f8.InterfaceC1269e
    public final ArrayList t() {
        TypeVariable[] typeParameters = this.f9891a.getTypeParameters();
        A7.m.e(typeParameters, "klass.typeParameters");
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable typeVariable : typeParameters) {
            arrayList.add(new B(typeVariable));
        }
        return arrayList;
    }

    public final String toString() {
        return n.class.getName() + ": " + this.f9891a;
    }
}
