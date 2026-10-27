package java9.util.stream;

import java9.util.function.IntConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class ReferencePipeline$8$1$$Lambda$1 implements IntConsumer {
    private final Sink arg$1;

    private ReferencePipeline$8$1$$Lambda$1(Sink sink) {
        this.arg$1 = sink;
    }

    public static IntConsumer lambdaFactory$(Sink sink) {
        return new ReferencePipeline$8$1$$Lambda$1(sink);
    }

    @Override // java9.util.function.IntConsumer
    public void accept(int i) {
        this.arg$1.accept(i);
    }
}
