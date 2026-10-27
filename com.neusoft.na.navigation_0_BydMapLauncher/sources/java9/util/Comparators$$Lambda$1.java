package java9.util;

import java.io.Serializable;
import java.util.Comparator;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Comparators$$Lambda$1 implements Comparator, Serializable {
    private final Comparator arg$1;
    private final Function arg$2;

    private Comparators$$Lambda$1(Comparator comparator, Function function) {
        this.arg$1 = comparator;
        this.arg$2 = function;
    }

    public static Comparator lambdaFactory$(Comparator comparator, Function function) {
        return new Comparators$$Lambda$1(comparator, function);
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return Comparators.lambda$comparing$b1478148$1(this.arg$1, this.arg$2, obj, obj2);
    }
}
