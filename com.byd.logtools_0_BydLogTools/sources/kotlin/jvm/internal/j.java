package kotlin.jvm.internal;

/* JADX INFO: compiled from: Reflection.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final k f5121a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final kotlin.reflect.c[] f5122b;

    static {
        k kVar = null;
        try {
            kVar = (k) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (kVar == null) {
            kVar = new k();
        }
        f5121a = kVar;
        f5122b = new kotlin.reflect.c[0];
    }

    public static kotlin.reflect.e a(FunctionReference functionReference) {
        return f5121a.a(functionReference);
    }

    public static kotlin.reflect.c b(Class cls) {
        return f5121a.b(cls);
    }

    public static kotlin.reflect.g c(MutablePropertyReference0 mutablePropertyReference0) {
        return f5121a.c(mutablePropertyReference0);
    }

    public static String d(Lambda lambda) {
        return f5121a.e(lambda);
    }
}
