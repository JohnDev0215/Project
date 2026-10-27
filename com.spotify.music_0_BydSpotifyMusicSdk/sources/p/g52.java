package p;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class g52 implements Comparator {
    public static final g52 c = new g52();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Comparable comparable = (Comparable) obj;
        Comparable comparable2 = (Comparable) obj2;
        v03.j(comparable, "a");
        v03.j(comparable2, "b");
        return comparable2.compareTo(comparable);
    }

    @Override // java.util.Comparator
    public final Comparator reversed() {
        return jm1.c;
    }
}
