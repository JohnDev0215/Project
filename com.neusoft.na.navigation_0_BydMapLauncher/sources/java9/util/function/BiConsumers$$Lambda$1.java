package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class BiConsumers$$Lambda$1 implements BiConsumer {
    private final BiConsumer arg$1;
    private final BiConsumer arg$2;

    private BiConsumers$$Lambda$1(BiConsumer biConsumer, BiConsumer biConsumer2) {
        this.arg$1 = biConsumer;
        this.arg$2 = biConsumer2;
    }

    public static BiConsumer lambdaFactory$(BiConsumer biConsumer, BiConsumer biConsumer2) {
        return new BiConsumers$$Lambda$1(biConsumer, biConsumer2);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        BiConsumers.lambda$andThen$0(this.arg$1, this.arg$2, obj, obj2);
    }
}
