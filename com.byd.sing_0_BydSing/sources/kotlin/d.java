package kotlin;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: LazyJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static <T> a<T> a(LazyThreadSafetyMode lazyThreadSafetyMode, kotlin.jvm.b.a<? extends T> aVar) {
        kotlin.jvm.internal.h.d(lazyThreadSafetyMode, "mode");
        kotlin.jvm.internal.h.d(aVar, "initializer");
        int i = b.f4065a[lazyThreadSafetyMode.ordinal()];
        if (i == 1) {
            return new SynchronizedLazyImpl(aVar, null, 2, null);
        }
        if (i == 2) {
            return new SafePublicationLazyImpl(aVar);
        }
        if (i == 3) {
            return new UnsafeLazyImpl(aVar);
        }
        throw new NoWhenBranchMatchedException();
    }

    public static <T> a<T> b(kotlin.jvm.b.a<? extends T> aVar) {
        kotlin.jvm.internal.h.d(aVar, "initializer");
        return new SynchronizedLazyImpl(aVar, null, 2, null);
    }
}
