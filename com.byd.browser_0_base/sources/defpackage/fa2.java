package defpackage;

/* JADX INFO: compiled from: Reflection.java */
/* JADX INFO: loaded from: classes.dex */
public final class fa2 {
    public static final ga2 a;
    public static final w31[] b;

    static {
        ga2 ga2Var = null;
        try {
            ga2Var = (ga2) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (ga2Var == null) {
            ga2Var = new ga2();
        }
        a = ga2Var;
        b = new w31[0];
    }
}
