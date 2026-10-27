package c.a.a.b;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.function.Function;

/* JADX INFO: compiled from: CollUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class e {
    @SafeVarargs
    public static <T> HashSet<T> a(T... tArr) {
        return b(false, tArr);
    }

    @SafeVarargs
    public static <T> HashSet<T> b(boolean z, T... tArr) {
        if (tArr == null) {
            return z ? new LinkedHashSet() : new HashSet<>();
        }
        int iMax = Math.max(((int) (tArr.length / 0.75f)) + 1, 16);
        HashSet<T> linkedHashSet = z ? new LinkedHashSet<>(iMax) : new HashSet<>(iMax);
        Collections.addAll(linkedHashSet, tArr);
        return linkedHashSet;
    }

    public static <F, T> Collection<T> c(Collection<F> collection, Function<? super F, ? extends T> function) {
        return new h(collection, function);
    }
}
