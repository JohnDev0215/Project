package java9.util.stream;

import java9.util.function.Consumer;
import java9.util.stream.StreamSpliterators;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class StreamSpliterators$SliceSpliterator$OfRef$$Lambda$2 implements Consumer {
    private static final StreamSpliterators$SliceSpliterator$OfRef$$Lambda$2 instance = new StreamSpliterators$SliceSpliterator$OfRef$$Lambda$2();

    private StreamSpliterators$SliceSpliterator$OfRef$$Lambda$2() {
    }

    @Override // java9.util.function.Consumer
    public void accept(Object obj) {
        StreamSpliterators.SliceSpliterator.OfRef.lambda$forEachRemaining$1(obj);
    }
}
