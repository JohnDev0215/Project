package java9.util.stream;

import java9.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class StreamSpliterators$WrappingSpliterator$$Lambda$1 implements Consumer {
    private final SpinedBuffer arg$1;

    private StreamSpliterators$WrappingSpliterator$$Lambda$1(SpinedBuffer spinedBuffer) {
        this.arg$1 = spinedBuffer;
    }

    public static Consumer lambdaFactory$(SpinedBuffer spinedBuffer) {
        return new StreamSpliterators$WrappingSpliterator$$Lambda$1(spinedBuffer);
    }

    @Override // java9.util.function.Consumer
    public void accept(Object obj) {
        this.arg$1.accept(obj);
    }
}
