package kotlin.jvm.internal;

/* JADX INFO: compiled from: Reflection.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final t f7509a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final w5.c[] f7510b;

    static {
        t tVar = null;
        try {
            tVar = (t) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (tVar == null) {
            tVar = new t();
        }
        f7509a = tVar;
        f7510b = new w5.c[0];
    }

    public static w5.e a(i iVar) {
        return f7509a.a(iVar);
    }

    public static w5.c b(Class cls) {
        return f7509a.b(cls);
    }

    public static w5.d c(Class cls) {
        return f7509a.c(cls, "");
    }

    public static String d(h hVar) {
        return f7509a.d(hVar);
    }

    public static String e(m mVar) {
        return f7509a.e(mVar);
    }
}
