package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoubleConsumers$$Lambda$1 implements DoubleConsumer {
    private final DoubleConsumer arg$1;
    private final DoubleConsumer arg$2;

    private DoubleConsumers$$Lambda$1(DoubleConsumer doubleConsumer, DoubleConsumer doubleConsumer2) {
        this.arg$1 = doubleConsumer;
        this.arg$2 = doubleConsumer2;
    }

    public static DoubleConsumer lambdaFactory$(DoubleConsumer doubleConsumer, DoubleConsumer doubleConsumer2) {
        return new DoubleConsumers$$Lambda$1(doubleConsumer, doubleConsumer2);
    }

    @Override // java9.util.function.DoubleConsumer
    public void accept(double d) {
        DoubleConsumers.lambda$andThen$0(this.arg$1, this.arg$2, d);
    }
}
