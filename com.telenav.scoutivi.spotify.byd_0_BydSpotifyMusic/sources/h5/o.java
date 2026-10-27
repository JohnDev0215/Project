package h5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: CollectionsJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public class o {
    public static <E> List<E> a(List<E> builder) {
        kotlin.jvm.internal.l.e(builder, "builder");
        return ((i5.b) builder).j();
    }

    public static final <T> Object[] b(T[] tArr, boolean z6) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        if (z6 && kotlin.jvm.internal.l.a(tArr.getClass(), Object[].class)) {
            return tArr;
        }
        Object[] objArrCopyOf = Arrays.copyOf(tArr, tArr.length, Object[].class);
        kotlin.jvm.internal.l.d(objArrCopyOf, "copyOf(this, this.size, Array<Any?>::class.java)");
        return objArrCopyOf;
    }

    public static <E> List<E> c(int i7) {
        return new i5.b(i7);
    }

    public static <T> List<T> d(T t6) {
        List<T> listSingletonList = Collections.singletonList(t6);
        kotlin.jvm.internal.l.d(listSingletonList, "singletonList(element)");
        return listSingletonList;
    }
}
