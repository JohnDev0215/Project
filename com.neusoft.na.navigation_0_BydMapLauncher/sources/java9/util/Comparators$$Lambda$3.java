package java9.util;

import java.io.Serializable;
import java.util.Comparator;
import java9.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Comparators$$Lambda$3 implements Comparator, Serializable {
    private final ToIntFunction arg$1;

    private Comparators$$Lambda$3(ToIntFunction toIntFunction) {
        this.arg$1 = toIntFunction;
    }

    public static Comparator lambdaFactory$(ToIntFunction toIntFunction) {
        return new Comparators$$Lambda$3(toIntFunction);
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return Comparators.lambda$comparingInt$134fc06a$1(this.arg$1, obj, obj2);
    }
}
