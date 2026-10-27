package java9.util.stream;

import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntPipeline$$Lambda$7 implements Supplier {
    private static final IntPipeline$$Lambda$7 instance = new IntPipeline$$Lambda$7();

    private IntPipeline$$Lambda$7() {
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return IntPipeline.lambda$average$1();
    }
}
