package m6;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: CloseGuard.kt */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f8424d = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Method f8425a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Method f8426b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Method f8427c;

    /* JADX INFO: compiled from: CloseGuard.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final h a() throws NoSuchMethodException {
            Method method;
            Method method2;
            Method method3 = null;
            try {
                Class<?> cls = Class.forName("dalvik.system.CloseGuard");
                Method method4 = cls.getMethod("get", new Class[0]);
                method2 = cls.getMethod("open", String.class);
                method = cls.getMethod("warnIfOpen", new Class[0]);
                method3 = method4;
            } catch (Exception unused) {
                method = null;
                method2 = null;
            }
            return new h(method3, method2, method);
        }
    }

    public h(Method method, Method method2, Method method3) {
        this.f8425a = method;
        this.f8426b = method2;
        this.f8427c = method3;
    }

    public final Object a(String closer) {
        kotlin.jvm.internal.l.e(closer, "closer");
        Method method = this.f8425a;
        if (method != null) {
            try {
                Object objInvoke = method.invoke(null, new Object[0]);
                Method method2 = this.f8426b;
                kotlin.jvm.internal.l.c(method2);
                method2.invoke(objInvoke, closer);
                return objInvoke;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public final boolean b(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            Method method = this.f8427c;
            kotlin.jvm.internal.l.c(method);
            method.invoke(obj, new Object[0]);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
