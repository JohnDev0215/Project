package h5;

import java.util.Collections;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: MutableCollectionsJVM.kt */
/* JADX INFO: loaded from: classes.dex */
public class t extends s {
    public static <T extends Comparable<? super T>> void q(List<T> list) {
        kotlin.jvm.internal.l.e(list, "<this>");
        if (list.size() > 1) {
            Collections.sort(list);
        }
    }
}
