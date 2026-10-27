package kotlin.jvm.internal;

import com.android.volley.BuildConfig;

/* JADX INFO: compiled from: Reflection.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final k f4081a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final kotlin.reflect.c[] f4082b;

    static {
        k kVar = null;
        try {
            kVar = (k) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (kVar == null) {
            kVar = new k();
        }
        f4081a = kVar;
        f4082b = new kotlin.reflect.c[0];
    }

    public static kotlin.reflect.c a(Class cls) {
        return f4081a.a(cls);
    }

    public static kotlin.reflect.d b(Class cls) {
        return f4081a.b(cls, BuildConfig.FLAVOR);
    }

    public static kotlin.reflect.g c(MutablePropertyReference1 mutablePropertyReference1) {
        return f4081a.c(mutablePropertyReference1);
    }

    public static kotlin.reflect.j d(PropertyReference1 propertyReference1) {
        return f4081a.d(propertyReference1);
    }

    public static String e(Lambda lambda) {
        return f4081a.f(lambda);
    }
}
