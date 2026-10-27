package kotlin.collections;

import java.util.Arrays;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: renamed from: kotlin.collections.h, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a-\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlin/Array;", "Lkotlin/Any;", "element", "Lkotlin/Int;", "fromIndex", "toIndex", "Lkotlin/Unit;", "a", "([Ljava/lang/Object;Ljava/lang/Object;II)V", "kotlin-stdlib"}, k = 5, mv = {1, 1, 15}, pn = "", xi = 0, xs = "kotlin/collections/e")
class C0117h extends C0116g {
    public static final <T> void a(@NotNull T[] tArr, T t, int i, int i2) {
        kotlin.jvm.internal.q.c(tArr, "$this$fill");
        Arrays.fill(tArr, i, i2, t);
    }
}
