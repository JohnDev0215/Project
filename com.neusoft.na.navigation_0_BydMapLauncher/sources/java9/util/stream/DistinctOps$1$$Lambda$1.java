package java9.util.stream;

import java.util.LinkedHashSet;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DistinctOps$1$$Lambda$1 implements Supplier {
    private static final DistinctOps$1$$Lambda$1 instance = new DistinctOps$1$$Lambda$1();

    private DistinctOps$1$$Lambda$1() {
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return new LinkedHashSet();
    }
}
