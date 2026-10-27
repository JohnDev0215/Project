package kotlin.collections;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Collections.kt */
/* JADX INFO: loaded from: classes.dex */
public class k extends j {
    public static <T> ArrayList<T> c(T... tArr) {
        kotlin.jvm.internal.h.d(tArr, "elements");
        return tArr.length == 0 ? new ArrayList<>() : new ArrayList<>(new a(tArr, true));
    }

    public static final <T> List<T> d() {
        return EmptyList.INSTANCE;
    }

    public static final <T> int e(List<? extends T> list) {
        kotlin.jvm.internal.h.d(list, "$this$lastIndex");
        return list.size() - 1;
    }

    public static <T> List<T> f(T... tArr) {
        kotlin.jvm.internal.h.d(tArr, "elements");
        return tArr.length > 0 ? e.a(tArr) : d();
    }

    public static <T> List<T> g(T... tArr) {
        kotlin.jvm.internal.h.d(tArr, "elements");
        return tArr.length == 0 ? new ArrayList() : new ArrayList(new a(tArr, true));
    }

    public static void h() {
        throw new ArithmeticException("Index overflow has happened.");
    }
}
