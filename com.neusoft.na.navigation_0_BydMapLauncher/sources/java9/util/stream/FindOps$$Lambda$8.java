package java9.util.stream;

import java9.util.function.Supplier;
import java9.util.stream.FindOps;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class FindOps$$Lambda$8 implements Supplier {
    private static final FindOps$$Lambda$8 instance = new FindOps$$Lambda$8();

    private FindOps$$Lambda$8() {
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return new FindOps.FindSink.OfDouble();
    }
}
