package java9.util.stream;

import java9.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class StreamSpliterators$WrappingSpliterator$$Lambda$3 implements Consumer {
    private final Consumer arg$1;

    private StreamSpliterators$WrappingSpliterator$$Lambda$3(Consumer consumer) {
        this.arg$1 = consumer;
    }

    public static Consumer lambdaFactory$(Consumer consumer) {
        return new StreamSpliterators$WrappingSpliterator$$Lambda$3(consumer);
    }

    @Override // java9.util.function.Consumer
    public void accept(Object obj) {
        this.arg$1.accept(obj);
    }
}
