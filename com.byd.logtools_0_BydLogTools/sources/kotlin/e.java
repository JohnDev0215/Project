package kotlin;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: LazyJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public class e {
    public static <T> c<T> a(kotlin.jvm.b.a<? extends T> aVar) {
        kotlin.jvm.internal.i.c(aVar, "initializer");
        kotlin.jvm.internal.f fVar = null;
        return new SynchronizedLazyImpl(aVar, fVar, 2, fVar);
    }
}
