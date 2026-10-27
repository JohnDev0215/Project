package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntConsumers$$Lambda$1 implements IntConsumer {
    private final IntConsumer arg$1;
    private final IntConsumer arg$2;

    private IntConsumers$$Lambda$1(IntConsumer intConsumer, IntConsumer intConsumer2) {
        this.arg$1 = intConsumer;
        this.arg$2 = intConsumer2;
    }

    public static IntConsumer lambdaFactory$(IntConsumer intConsumer, IntConsumer intConsumer2) {
        return new IntConsumers$$Lambda$1(intConsumer, intConsumer2);
    }

    @Override // java9.util.function.IntConsumer
    public void accept(int i) {
        IntConsumers.lambda$andThen$0(this.arg$1, this.arg$2, i);
    }
}
