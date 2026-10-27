package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: DebugMetadata.kt */
/* JADX INFO: loaded from: classes.dex */
final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f7485a = new i();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final a f7486b = new a(null, null, null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static a f7487c;

    /* JADX INFO: compiled from: DebugMetadata.kt */
    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Method f7488a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Method f7489b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Method f7490c;

        public a(Method method, Method method2, Method method3) {
            this.f7488a = method;
            this.f7489b = method2;
            this.f7490c = method3;
        }
    }

    private i() {
    }

    private final a a(kotlin.coroutines.jvm.internal.a aVar) {
        try {
            a aVar2 = new a(Class.class.getDeclaredMethod("getModule", new Class[0]), aVar.getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", new Class[0]), aVar.getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", new Class[0]));
            f7487c = aVar2;
            return aVar2;
        } catch (Exception unused) {
            a aVar3 = f7486b;
            f7487c = aVar3;
            return aVar3;
        }
    }

    public final String b(kotlin.coroutines.jvm.internal.a continuation) {
        kotlin.jvm.internal.l.e(continuation, "continuation");
        a aVarA = f7487c;
        if (aVarA == null) {
            aVarA = a(continuation);
        }
        if (aVarA == f7486b) {
            return null;
        }
        Method method = aVarA.f7488a;
        Object objInvoke = method != null ? method.invoke(continuation.getClass(), new Object[0]) : null;
        if (objInvoke == null) {
            return null;
        }
        Method method2 = aVarA.f7489b;
        Object objInvoke2 = method2 != null ? method2.invoke(objInvoke, new Object[0]) : null;
        if (objInvoke2 == null) {
            return null;
        }
        Method method3 = aVarA.f7490c;
        Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, new Object[0]) : null;
        if (objInvoke3 instanceof String) {
            return (String) objInvoke3;
        }
        return null;
    }
}
