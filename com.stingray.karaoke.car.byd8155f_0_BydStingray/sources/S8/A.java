package S8;

/* JADX INFO: loaded from: classes.dex */
public abstract class A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final B f8081a;

    static {
        String property;
        T8.d dVar;
        int i7 = X8.t.f10579a;
        try {
            property = System.getProperty("kotlinx.coroutines.main.delay");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property != null ? Boolean.parseBoolean(property) : false) {
            Z8.e eVar = E.f8084a;
            T8.d dVar2 = X8.m.f10573a;
            T8.d dVar3 = dVar2.f8932q;
            dVar = !(dVar2 instanceof B) ? RunnableC0728z.f8152u : dVar2;
        } else {
            dVar = RunnableC0728z.f8152u;
        }
        f8081a = dVar;
    }
}
