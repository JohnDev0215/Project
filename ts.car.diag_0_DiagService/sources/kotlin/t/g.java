package kotlin.t;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u001b\u0010\u0003\u001a\u00020\u0002*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001b\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001b\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\b\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\t\u0010\n\u001a\u001b\u0010\f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lkotlin/Int;", "to", "Lkotlin/t/a;", "c", "(II)Lkotlin/t/a;", "Lkotlin/t/c;", "d", "(II)Lkotlin/t/c;", "minimumValue", "a", "(II)I", "maximumValue", "b", "kotlin-stdlib"}, k = 5, mv = {1, 1, 15}, pn = "", xi = 0, xs = "kotlin/t/d")
public class g extends f {
    public static int a(int i, int i2) {
        return i < i2 ? i2 : i;
    }

    public static int b(int i, int i2) {
        return i > i2 ? i2 : i;
    }

    @NotNull
    public static a c(int i, int i2) {
        return a.f.a(i, i2, -1);
    }

    @NotNull
    public static c d(int i, int i2) {
        return i2 <= Integer.MIN_VALUE ? c.h.a() : new c(i, i2 - 1);
    }
}
