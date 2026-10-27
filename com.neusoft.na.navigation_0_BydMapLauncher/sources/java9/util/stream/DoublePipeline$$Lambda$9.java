package java9.util.stream;

import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoublePipeline$$Lambda$9 implements Supplier {
    private static final DoublePipeline$$Lambda$9 instance = new DoublePipeline$$Lambda$9();

    private DoublePipeline$$Lambda$9() {
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return DoublePipeline.lambda$average$4();
    }
}
