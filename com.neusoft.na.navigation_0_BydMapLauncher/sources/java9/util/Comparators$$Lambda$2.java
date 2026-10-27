package java9.util;

import java.io.Serializable;
import java.util.Comparator;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Comparators$$Lambda$2 implements Comparator, Serializable {
    private final Function arg$1;

    private Comparators$$Lambda$2(Function function) {
        this.arg$1 = function;
    }

    public static Comparator lambdaFactory$(Function function) {
        return new Comparators$$Lambda$2(function);
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return Comparators.lambda$comparing$628f93f7$1(this.arg$1, obj, obj2);
    }
}
