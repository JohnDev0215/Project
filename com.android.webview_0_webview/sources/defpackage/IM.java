package defpackage;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class IM implements InvocationHandler {
    public final Object a;

    public IM(Object obj) {
        this.a = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        try {
            return Class.forName(method.getDeclaringClass().getName(), true, this.a.getClass().getClassLoader()).getDeclaredMethod(method.getName(), method.getParameterTypes()).invoke(this.a, objArr);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (ReflectiveOperationException e2) {
            throw new RuntimeException("Reflection failed for method " + method, e2);
        }
    }
}
