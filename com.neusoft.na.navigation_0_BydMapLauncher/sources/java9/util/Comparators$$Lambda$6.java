package java9.util;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Comparators$$Lambda$6 implements Comparator, Serializable {
    private final Comparator arg$1;
    private final Comparator arg$2;

    private Comparators$$Lambda$6(Comparator comparator, Comparator comparator2) {
        this.arg$1 = comparator;
        this.arg$2 = comparator2;
    }

    public static Comparator lambdaFactory$(Comparator comparator, Comparator comparator2) {
        return new Comparators$$Lambda$6(comparator, comparator2);
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return Comparators.lambda$thenComparing$aafead48$1(this.arg$1, this.arg$2, obj, obj2);
    }
}
