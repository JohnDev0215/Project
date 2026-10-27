package java9.util;

import java.io.Serializable;
import java.util.Comparator;
import java9.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Comparators$$Lambda$4 implements Comparator, Serializable {
    private final ToLongFunction arg$1;

    private Comparators$$Lambda$4(ToLongFunction toLongFunction) {
        this.arg$1 = toLongFunction;
    }

    public static Comparator lambdaFactory$(ToLongFunction toLongFunction) {
        return new Comparators$$Lambda$4(toLongFunction);
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return Comparators.lambda$comparingLong$c3a37e6$1(this.arg$1, obj, obj2);
    }
}
