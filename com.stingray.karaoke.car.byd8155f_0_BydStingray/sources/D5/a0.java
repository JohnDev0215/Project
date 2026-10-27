package d5;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class a0 extends b0 implements Serializable {
    public static final a0 l = new a0();

    @Override // d5.b0
    public final b0 a() {
        return k0.l;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Comparable comparable = (Comparable) obj;
        Comparable comparable2 = (Comparable) obj2;
        comparable.getClass();
        comparable2.getClass();
        return comparable.compareTo(comparable2);
    }

    public final String toString() {
        return "Ordering.natural()";
    }
}
