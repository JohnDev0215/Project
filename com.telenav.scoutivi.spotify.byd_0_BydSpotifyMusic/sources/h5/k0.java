package h5;

import java.util.Collections;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: SetsJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public class k0 {
    public static <E> Set<E> a(Set<E> builder) {
        kotlin.jvm.internal.l.e(builder, "builder");
        return ((i5.j) builder).d();
    }

    public static <E> Set<E> b(int i7) {
        return new i5.j(i7);
    }

    public static final <T> Set<T> c(T t6) {
        Set<T> setSingleton = Collections.singleton(t6);
        kotlin.jvm.internal.l.d(setSingleton, "singleton(element)");
        return setSingleton;
    }
}
