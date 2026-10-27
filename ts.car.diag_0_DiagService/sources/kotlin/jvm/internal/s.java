package kotlin.jvm.internal;

import kotlin.SinceKotlin;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final t f1518a;

    static {
        t tVar = null;
        try {
            tVar = (t) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (tVar == null) {
            tVar = new t();
        }
        f1518a = tVar;
    }

    public static kotlin.reflect.f a(FunctionReference functionReference) {
        f1518a.a(functionReference);
        return functionReference;
    }

    public static kotlin.reflect.c b(Class cls) {
        return f1518a.b(cls);
    }

    public static kotlin.reflect.h c(MutablePropertyReference0 mutablePropertyReference0) {
        f1518a.c(mutablePropertyReference0);
        return mutablePropertyReference0;
    }

    public static kotlin.reflect.i d(MutablePropertyReference1 mutablePropertyReference1) {
        f1518a.d(mutablePropertyReference1);
        return mutablePropertyReference1;
    }

    public static kotlin.reflect.j e(MutablePropertyReference2 mutablePropertyReference2) {
        f1518a.e(mutablePropertyReference2);
        return mutablePropertyReference2;
    }

    public static kotlin.reflect.l f(PropertyReference0 propertyReference0) {
        f1518a.f(propertyReference0);
        return propertyReference0;
    }

    public static kotlin.reflect.m g(PropertyReference1 propertyReference1) {
        f1518a.g(propertyReference1);
        return propertyReference1;
    }

    public static kotlin.reflect.n h(PropertyReference2 propertyReference2) {
        f1518a.h(propertyReference2);
        return propertyReference2;
    }

    @SinceKotlin(version = "1.3")
    public static String i(p pVar) {
        return f1518a.i(pVar);
    }

    @SinceKotlin(version = "1.1")
    public static String j(Lambda lambda) {
        return f1518a.j(lambda);
    }
}
