package kotlin.collections;

import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _ArraysJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public class e extends d {
    public static <T> List<T> a(T[] tArr) {
        kotlin.jvm.internal.h.d(tArr, "$this$asList");
        List<T> listA = g.a(tArr);
        kotlin.jvm.internal.h.c(listA, "ArraysUtilJVM.asList(this)");
        return listA;
    }
}
