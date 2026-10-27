package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Method;
import kotlin.jvm.internal.i;

/* JADX INFO: compiled from: DebugMetadata.kt */
/* JADX INFO: loaded from: classes.dex */
final class f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static a f5103b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f f5104c = new f();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final a f5102a = new a(null, null, null);

    /* JADX INFO: compiled from: DebugMetadata.kt */
    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Method f5105a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Method f5106b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Method f5107c;

        public a(Method method, Method method2, Method method3) {
            this.f5105a = method;
            this.f5106b = method2;
            this.f5107c = method3;
        }
    }

    private f() {
    }

    private final a a(BaseContinuationImpl baseContinuationImpl) {
        try {
            a aVar = new a(Class.class.getDeclaredMethod("getModule", new Class[0]), baseContinuationImpl.getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", new Class[0]), baseContinuationImpl.getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", new Class[0]));
            f5103b = aVar;
            return aVar;
        } catch (Exception unused) {
            a aVar2 = f5102a;
            f5103b = aVar2;
            return aVar2;
        }
    }

    public final String b(BaseContinuationImpl baseContinuationImpl) {
        Method method;
        Object objInvoke;
        Method method2;
        Object objInvoke2;
        i.c(baseContinuationImpl, "continuation");
        a aVarA = f5103b;
        if (aVarA == null) {
            aVarA = a(baseContinuationImpl);
        }
        if (aVarA == f5102a || (method = aVarA.f5105a) == null || (objInvoke = method.invoke(baseContinuationImpl.getClass(), new Object[0])) == null || (method2 = aVarA.f5106b) == null || (objInvoke2 = method2.invoke(objInvoke, new Object[0])) == null) {
            return null;
        }
        Method method3 = aVarA.f5107c;
        Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, new Object[0]) : null;
        return (String) (objInvoke3 instanceof String ? objInvoke3 : null);
    }
}
