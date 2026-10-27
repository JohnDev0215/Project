package p;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class ht1 {
    public static final ht1 c;
    public final boolean a = true;
    public final Constructor b;

    static {
        c = "Dalvik".equals(System.getProperty("java.vm.name")) ? new ft1() : new ht1();
    }

    public ht1() {
        Constructor declaredConstructor = null;
        try {
            declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
            declaredConstructor.setAccessible(true);
        } catch (NoClassDefFoundError | NoSuchMethodException unused) {
        }
        this.b = declaredConstructor;
    }

    public Executor a() {
        return null;
    }

    public Object b(Method method, Class cls, Object obj, Object... objArr) {
        Constructor constructor = this.b;
        return (constructor != null ? (MethodHandles.Lookup) constructor.newInstance(cls, -1) : MethodHandles.lookup()).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
    }
}
