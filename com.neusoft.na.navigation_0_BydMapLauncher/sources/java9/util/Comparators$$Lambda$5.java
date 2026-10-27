package java9.util;

import java.io.Serializable;
import java.util.Comparator;
import java9.util.function.ToDoubleFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Comparators$$Lambda$5 implements Comparator, Serializable {
    private final ToDoubleFunction arg$1;

    private Comparators$$Lambda$5(ToDoubleFunction toDoubleFunction) {
        this.arg$1 = toDoubleFunction;
    }

    public static Comparator lambdaFactory$(ToDoubleFunction toDoubleFunction) {
        return new Comparators$$Lambda$5(toDoubleFunction);
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return Comparators.lambda$comparingDouble$57709dbc$1(this.arg$1, obj, obj2);
    }
}
