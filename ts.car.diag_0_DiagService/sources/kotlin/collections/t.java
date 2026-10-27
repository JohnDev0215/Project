package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0016¢\u0006\u0004\b\u0004\u0010\u0005\u001a#\u0010\u0007\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Ljava/lang/Iterable;", "Lkotlin/Function1;", "predicate", "Lkotlin/Boolean;", "d", "(Ljava/lang/Iterable;Lkotlin/jvm/b/l;)Z", "predicateResultToRemove", "c", "(Ljava/lang/Iterable;Lkotlin/jvm/b/l;Z)Z", "kotlin-stdlib"}, k = 5, mv = {1, 1, 15}, pn = "", xi = 0, xs = "kotlin/collections/m")
public class t extends s {
    private static final <T> boolean c(@NotNull Iterable<? extends T> iterable, kotlin.jvm.b.l<? super T, Boolean> lVar, boolean z) {
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

    public static <T> boolean d(@NotNull Iterable<? extends T> iterable, @NotNull kotlin.jvm.b.l<? super T, Boolean> lVar) {
        kotlin.jvm.internal.q.c(iterable, "$this$retainAll");
        kotlin.jvm.internal.q.c(lVar, "predicate");
        return c(iterable, lVar, false);
    }
}
