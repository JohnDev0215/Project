package kotlin.collections;

import java.util.Collections;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: MutableCollectionsJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public class o extends n {
    public static <T extends Comparable<? super T>> void q(List<T> list) {
        kotlin.jvm.internal.i.c(list, "$this$sort");
        if (list.size() > 1) {
            Collections.sort(list);
        }
    }
}
