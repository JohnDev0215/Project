package p;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class jm1 implements Comparator {
    public static final jm1 c = new jm1();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Comparable comparable = (Comparable) obj;
        Comparable comparable2 = (Comparable) obj2;
        v03.j(comparable, "a");
        v03.j(comparable2, "b");
        return comparable.compareTo(comparable2);
    }

    @Override // java.util.Comparator
    public final Comparator reversed() {
        return g52.c;
    }
}
