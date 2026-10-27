package kotlin.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: CollectionsJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public class j {
    public static final <T> Object[] a(T[] tArr, boolean z) {
        kotlin.jvm.internal.i.c(tArr, "$this$copyToArrayOfAny");
        if (z && kotlin.jvm.internal.i.a(tArr.getClass(), Object[].class)) {
            return tArr;
        }
        Object[] objArrCopyOf = Arrays.copyOf(tArr, tArr.length, Object[].class);
        kotlin.jvm.internal.i.b(objArrCopyOf, "java.util.Arrays.copyOf(… Array<Any?>::class.java)");
        return objArrCopyOf;
    }

    public static <T> List<T> b(T t) {
        List<T> listSingletonList = Collections.singletonList(t);
        kotlin.jvm.internal.i.b(listSingletonList, "java.util.Collections.singletonList(element)");
        return listSingletonList;
    }
}
