package d5;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class k0 extends b0 implements Serializable {
    public static final k0 l = new k0();

    @Override // d5.b0
    public final b0 a() {
        return a0.l;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Comparable comparable = (Comparable) obj;
        Comparable comparable2 = (Comparable) obj2;
        comparable.getClass();
        if (comparable == comparable2) {
            return 0;
        }
        return comparable2.compareTo(comparable);
    }

    public final String toString() {
        return "Ordering.natural().reverse()";
    }
}
