package java9.util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java9.util.Maps;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Maps$Entry$$Lambda$2 implements Comparator, Serializable {
    private static final Maps$Entry$$Lambda$2 instance = new Maps$Entry$$Lambda$2();

    private Maps$Entry$$Lambda$2() {
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return Maps.Entry.lambda$comparingByValue$1065357e$1((Map.Entry) obj, (Map.Entry) obj2);
    }
}
