package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Consumers$$Lambda$1 implements Consumer {
    private final Consumer arg$1;
    private final Consumer arg$2;

    private Consumers$$Lambda$1(Consumer consumer, Consumer consumer2) {
        this.arg$1 = consumer;
        this.arg$2 = consumer2;
    }

    public static Consumer lambdaFactory$(Consumer consumer, Consumer consumer2) {
        return new Consumers$$Lambda$1(consumer, consumer2);
    }

    @Override // java9.util.function.Consumer
    public void accept(Object obj) {
        Consumers.lambda$andThen$0(this.arg$1, this.arg$2, obj);
    }
}
