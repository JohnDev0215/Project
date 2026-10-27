package java9.util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java9.util.Maps;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Maps$Entry$$Lambda$3 implements Comparator, Serializable {
    private final Comparator arg$1;

    private Maps$Entry$$Lambda$3(Comparator comparator) {
        this.arg$1 = comparator;
    }

    public static Comparator lambdaFactory$(Comparator comparator) {
        return new Maps$Entry$$Lambda$3(comparator);
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return Maps.Entry.lambda$comparingByKey$6d558cbf$1(this.arg$1, (Map.Entry) obj, (Map.Entry) obj2);
    }
}
