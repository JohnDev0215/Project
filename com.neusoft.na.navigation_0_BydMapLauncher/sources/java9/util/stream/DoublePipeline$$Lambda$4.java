package java9.util.stream;

import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoublePipeline$$Lambda$4 implements Supplier {
    private static final DoublePipeline$$Lambda$4 instance = new DoublePipeline$$Lambda$4();

    private DoublePipeline$$Lambda$4() {
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return DoublePipeline.lambda$sum$1();
    }
}
