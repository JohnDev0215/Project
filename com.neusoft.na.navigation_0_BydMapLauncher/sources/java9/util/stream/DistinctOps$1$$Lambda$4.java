package java9.util.stream;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java9.util.function.Consumer;
import java9.util.stream.DistinctOps;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DistinctOps$1$$Lambda$4 implements Consumer {
    private final AtomicBoolean arg$1;
    private final ConcurrentMap arg$2;

    private DistinctOps$1$$Lambda$4(AtomicBoolean atomicBoolean, ConcurrentMap concurrentMap) {
        this.arg$1 = atomicBoolean;
        this.arg$2 = concurrentMap;
    }

    public static Consumer lambdaFactory$(AtomicBoolean atomicBoolean, ConcurrentMap concurrentMap) {
        return new DistinctOps$1$$Lambda$4(atomicBoolean, concurrentMap);
    }

    @Override // java9.util.function.Consumer
    public void accept(Object obj) {
        DistinctOps.AnonymousClass1.lambda$opEvaluateParallel$0(this.arg$1, this.arg$2, obj);
    }
}
