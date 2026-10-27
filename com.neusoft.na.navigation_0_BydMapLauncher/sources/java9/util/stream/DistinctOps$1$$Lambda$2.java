package java9.util.stream;

import java.util.LinkedHashSet;
import java9.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DistinctOps$1$$Lambda$2 implements BiConsumer {
    private static final DistinctOps$1$$Lambda$2 instance = new DistinctOps$1$$Lambda$2();

    private DistinctOps$1$$Lambda$2() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        ((LinkedHashSet) obj).add(obj2);
    }
}
