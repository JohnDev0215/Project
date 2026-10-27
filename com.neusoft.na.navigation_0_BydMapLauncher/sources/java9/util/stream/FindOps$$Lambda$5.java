package java9.util.stream;

import java9.util.function.Supplier;
import java9.util.stream.FindOps;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class FindOps$$Lambda$5 implements Supplier {
    private static final FindOps$$Lambda$5 instance = new FindOps$$Lambda$5();

    private FindOps$$Lambda$5() {
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return new FindOps.FindSink.OfRef();
    }
}
