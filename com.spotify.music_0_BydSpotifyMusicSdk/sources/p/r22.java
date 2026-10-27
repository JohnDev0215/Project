package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class r22 {
    public static final t22 a;

    static {
        t22 t22Var = null;
        try {
            t22Var = (t22) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (t22Var == null) {
            t22Var = new t22();
        }
        a = t22Var;
    }

    public static us a(Class cls) {
        a.getClass();
        return new us(cls);
    }
}
