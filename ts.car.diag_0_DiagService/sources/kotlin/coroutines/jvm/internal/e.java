package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.q;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
final class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @JvmField
    @Nullable
    public static a f1498b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f1499c = new e();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final a f1497a = new a(null, null, null);

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u0000:\u0001\u0004B!\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0001\u0012\u0006\u0010\u0007\u001a\u00020\u0001\u0012\u0006\u0010\b\u001a\u00020\u0001¢\u0006\u0004\b\t\u0010\nR\u0016\u0010\u0002\u001a\u00020\u00018\u0000@\u0000X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u00020\u00018\u0000@\u0000X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0003R\u0016\u0010\u0005\u001a\u00020\u00018\u0000@\u0000X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0003¨\u0006\u000b"}, d2 = {"Lkotlin/coroutines/jvm/internal/e$a;", "Ljava/lang/reflect/Method;", "c", "Ljava/lang/reflect/Method;", "a", "b", "p0", "p1", "p2", "<init>", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    private static final class a {

        @JvmField
        @Nullable
        public final Method a;

        @JvmField
        @Nullable
        public final Method b;

        @JvmField
        @Nullable
        public final Method c;

        public a(@Nullable Method method, @Nullable Method method2, @Nullable Method method3) {
            this.a = method;
            this.b = method2;
            this.c = method3;
        }
    }

    private e() {
    }

    private final a a(BaseContinuationImpl baseContinuationImpl) {
        try {
            a aVar = new a(Class.class.getDeclaredMethod("getModule", new Class[0]), baseContinuationImpl.getClass().getClassLoader().loadClass("java.lang.Module").getDeclaredMethod("getDescriptor", new Class[0]), baseContinuationImpl.getClass().getClassLoader().loadClass("java.lang.module.ModuleDescriptor").getDeclaredMethod("name", new Class[0]));
            f1498b = aVar;
            return aVar;
        } catch (Exception unused) {
            a aVar2 = f1497a;
            f1498b = aVar2;
            return aVar2;
        }
    }

    @Nullable
    public final String b(@NotNull BaseContinuationImpl baseContinuationImpl) {
        Method method;
        Object objInvoke;
        Method method2;
        Object objInvoke2;
        q.c(baseContinuationImpl, "continuation");
        a aVarA = f1498b;
        if (aVarA == null) {
            aVarA = a(baseContinuationImpl);
        }
        if (aVarA == f1497a || (method = aVarA.a) == null || (objInvoke = method.invoke(baseContinuationImpl.getClass(), new Object[0])) == null || (method2 = aVarA.b) == null || (objInvoke2 = method2.invoke(objInvoke, new Object[0])) == null) {
            return null;
        }
        Method method3 = aVarA.c;
        Object objInvoke3 = method3 != null ? method3.invoke(objInvoke2, new Object[0]) : null;
        return (String) (objInvoke3 instanceof String ? objInvoke3 : null);
    }
}
