package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: MutableCollections.kt */
/* JADX INFO: loaded from: classes.dex */
public class p extends o {
    public static <T> boolean r(Collection<? super T> collection, Iterable<? extends T> iterable) {
        kotlin.jvm.internal.i.c(collection, "$this$addAll");
        kotlin.jvm.internal.i.c(iterable, "elements");
        if (iterable instanceof Collection) {
            return collection.addAll((Collection) iterable);
        }
        boolean z = false;
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            if (collection.add(it.next())) {
                z = true;
            }
        }
        return z;
    }

    public static <T> boolean s(Collection<? super T> collection, T[] tArr) {
        kotlin.jvm.internal.i.c(collection, "$this$addAll");
        kotlin.jvm.internal.i.c(tArr, "elements");
        return collection.addAll(f.b(tArr));
    }

    private static final <T> boolean t(Iterable<? extends T> iterable, kotlin.jvm.b.l<? super T, Boolean> lVar, boolean z) {
        Iterator<? extends T> it = iterable.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (lVar.invoke(it.next()).booleanValue() == z) {
                it.remove();
                z2 = true;
            }
        }
        return z2;
    }

    public static <T> boolean u(Iterable<? extends T> iterable, kotlin.jvm.b.l<? super T, Boolean> lVar) {
        kotlin.jvm.internal.i.c(iterable, "$this$retainAll");
        kotlin.jvm.internal.i.c(lVar, "predicate");
        return t(iterable, lVar, false);
    }
}
