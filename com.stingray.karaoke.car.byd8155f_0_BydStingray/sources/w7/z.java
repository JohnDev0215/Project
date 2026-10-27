package W7;

import f8.InterfaceC1268d;
import io.sentry.C1489n1;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class z extends v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f9902a;

    public z(Object obj) {
        A7.m.f(obj, "recordComponent");
        this.f9902a = obj;
    }

    @Override // W7.v
    public final Member b() throws IllegalAccessException, InvocationTargetException {
        Object obj = this.f9902a;
        A7.m.f(obj, "recordComponent");
        C1489n1 c1489n1 = com.bumptech.glide.c.f12671a;
        Method method = null;
        if (c1489n1 == null) {
            Class<?> cls = obj.getClass();
            try {
                c1489n1 = new C1489n1(cls.getMethod("getType", null), 13, cls.getMethod("getAccessor", null));
            } catch (NoSuchMethodException unused) {
                c1489n1 = new C1489n1(method, 13, method);
            }
            com.bumptech.glide.c.f12671a = c1489n1;
        }
        Method method2 = (Method) c1489n1.f16625n;
        if (method2 != null) {
            Object objInvoke = method2.invoke(obj, null);
            A7.m.d(objInvoke, "null cannot be cast to non-null type java.lang.reflect.Method");
            method = (Method) objInvoke;
        }
        if (method != null) {
            return method;
        }
        throw new NoSuchMethodError("Can't find `getAccessor` method");
    }

    public final InterfaceC1268d f() throws IllegalAccessException, InvocationTargetException {
        Object obj = this.f9902a;
        A7.m.f(obj, "recordComponent");
        C1489n1 c1489n1 = com.bumptech.glide.c.f12671a;
        Class cls = null;
        if (c1489n1 == null) {
            Class<?> cls2 = obj.getClass();
            try {
                c1489n1 = new C1489n1(cls2.getMethod("getType", null), 13, cls2.getMethod("getAccessor", null));
            } catch (NoSuchMethodException unused) {
                c1489n1 = new C1489n1(cls, 13, cls);
            }
            com.bumptech.glide.c.f12671a = c1489n1;
        }
        Method method = (Method) c1489n1.f16624m;
        if (method != null) {
            Object objInvoke = method.invoke(obj, null);
            A7.m.d(objInvoke, "null cannot be cast to non-null type java.lang.Class<*>");
            cls = (Class) objInvoke;
        }
        if (cls != null) {
            return new p(cls);
        }
        throw new NoSuchMethodError("Can't find `getType` method");
    }
}
