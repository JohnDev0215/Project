package kotlin.collections;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: compiled from: SetsJVM.kt */
/* JADX INFO: loaded from: classes.dex */
class b0 {
    public static final <T> Set<T> a(T t) {
        Set<T> setSingleton = Collections.singleton(t);
        kotlin.jvm.internal.i.b(setSingleton, "java.util.Collections.singleton(element)");
        return setSingleton;
    }
}
