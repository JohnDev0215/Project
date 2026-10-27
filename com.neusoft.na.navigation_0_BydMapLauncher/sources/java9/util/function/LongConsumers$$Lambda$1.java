package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongConsumers$$Lambda$1 implements LongConsumer {
    private final LongConsumer arg$1;
    private final LongConsumer arg$2;

    private LongConsumers$$Lambda$1(LongConsumer longConsumer, LongConsumer longConsumer2) {
        this.arg$1 = longConsumer;
        this.arg$2 = longConsumer2;
    }

    public static LongConsumer lambdaFactory$(LongConsumer longConsumer, LongConsumer longConsumer2) {
        return new LongConsumers$$Lambda$1(longConsumer, longConsumer2);
    }

    @Override // java9.util.function.LongConsumer
    public void accept(long j) {
        LongConsumers.lambda$andThen$0(this.arg$1, this.arg$2, j);
    }
}
