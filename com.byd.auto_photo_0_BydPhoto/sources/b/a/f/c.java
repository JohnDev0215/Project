package b.a.f;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Object f469a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Method f470b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Class<?> f471c;
    int d;

    public c(Object obj, Method method, Class<?> cls, int i) {
        this.f469a = obj;
        this.f470b = method;
        this.f471c = cls;
        this.d = i;
    }

    public <T> void a(T t) {
        try {
            this.f470b.invoke(this.f469a, t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
    }
}
