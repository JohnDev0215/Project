package java9.util.stream;

import java9.util.function.ObjIntConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntPipeline$$Lambda$8 implements ObjIntConsumer {
    private static final IntPipeline$$Lambda$8 instance = new IntPipeline$$Lambda$8();

    private IntPipeline$$Lambda$8() {
    }

    @Override // java9.util.function.ObjIntConsumer
    public void accept(Object obj, int i) {
        IntPipeline.lambda$average$2((long[]) obj, i);
    }
}
