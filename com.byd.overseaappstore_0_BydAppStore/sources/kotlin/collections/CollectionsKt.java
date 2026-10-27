package kotlin.collections;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"kotlin/collections/CollectionsKt__CollectionsJVMKt", "kotlin/collections/CollectionsKt__CollectionsKt", "kotlin/collections/CollectionsKt__IterablesKt", "kotlin/collections/CollectionsKt__IteratorsJVMKt", "kotlin/collections/CollectionsKt__IteratorsKt", "kotlin/collections/CollectionsKt__MutableCollectionsJVMKt", "kotlin/collections/CollectionsKt__MutableCollectionsKt", "kotlin/collections/CollectionsKt__ReversedViewsKt", "kotlin/collections/CollectionsKt___CollectionsJvmKt", "kotlin/collections/CollectionsKt___CollectionsKt"}, k = 4, mv = {1, 7, 1}, xi = 49)
public final class CollectionsKt extends CollectionsKt___CollectionsKt {
    private CollectionsKt() {
    }

    @PublishedApi
    public static /* bridge */ /* synthetic */ int b(@NotNull Iterable iterable) {
        return CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10);
    }

    public static /* bridge */ /* synthetic */ Appendable d(Iterable iterable, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Function1 function1, int i) throws IOException {
        return CollectionsKt___CollectionsKt.joinTo(iterable, appendable, (i & 2) != 0 ? ", " : charSequence, (i & 4) != 0 ? "" : charSequence2, (i & 8) == 0 ? charSequence3 : "", (i & 16) != 0 ? -1 : 0, (i & 32) != 0 ? "..." : null, (i & 64) != 0 ? null : function1);
    }
}
