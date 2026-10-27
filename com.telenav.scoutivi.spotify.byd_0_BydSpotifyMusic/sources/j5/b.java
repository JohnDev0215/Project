package j5;

import java.util.Comparator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Comparisons.kt */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static <T extends Comparable<?>> int a(T t6, T t7) {
        if (t6 == t7) {
            return 0;
        }
        if (t6 == null) {
            return -1;
        }
        if (t7 == null) {
            return 1;
        }
        return t6.compareTo(t7);
    }

    public static <T extends Comparable<? super T>> Comparator<T> b() {
        return e.f7150a;
    }
}
