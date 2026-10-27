package java9.util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Maps$Entry$$Lambda$1 implements Comparator, Serializable {
    private static final Maps$Entry$$Lambda$1 instance = new Maps$Entry$$Lambda$1();

    private Maps$Entry$$Lambda$1() {
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return ((Comparable) ((Map.Entry) obj).getKey()).compareTo(((Map.Entry) obj2).getKey());
    }
}
