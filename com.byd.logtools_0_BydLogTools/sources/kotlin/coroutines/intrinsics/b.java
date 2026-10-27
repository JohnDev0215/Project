package kotlin.coroutines.intrinsics;

import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.i;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: IntrinsicsJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public class b {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> kotlin.l.a<T> a(kotlin.l.a<? super T> aVar) {
        kotlin.l.a<T> aVar2;
        i.c(aVar, "$this$intercepted");
        ContinuationImpl continuationImpl = (ContinuationImpl) (!(aVar instanceof ContinuationImpl) ? null : aVar);
        return (continuationImpl == null || (aVar2 = (kotlin.l.a<T>) continuationImpl.intercepted()) == null) ? aVar : aVar2;
    }
}
