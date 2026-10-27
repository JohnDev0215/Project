package java9.util.stream;

import java9.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class ReferencePipeline$9$1$$Lambda$1 implements DoubleConsumer {
    private final Sink arg$1;

    private ReferencePipeline$9$1$$Lambda$1(Sink sink) {
        this.arg$1 = sink;
    }

    public static DoubleConsumer lambdaFactory$(Sink sink) {
        return new ReferencePipeline$9$1$$Lambda$1(sink);
    }

    @Override // java9.util.function.DoubleConsumer
    public void accept(double d) {
        this.arg$1.accept(d);
    }
}
