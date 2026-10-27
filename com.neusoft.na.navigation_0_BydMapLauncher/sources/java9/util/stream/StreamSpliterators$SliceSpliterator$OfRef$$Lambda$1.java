package java9.util.stream;

import java9.util.function.Consumer;
import java9.util.stream.StreamSpliterators;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class StreamSpliterators$SliceSpliterator$OfRef$$Lambda$1 implements Consumer {
    private static final StreamSpliterators$SliceSpliterator$OfRef$$Lambda$1 instance = new StreamSpliterators$SliceSpliterator$OfRef$$Lambda$1();

    private StreamSpliterators$SliceSpliterator$OfRef$$Lambda$1() {
    }

    @Override // java9.util.function.Consumer
    public void accept(Object obj) {
        StreamSpliterators.SliceSpliterator.OfRef.lambda$tryAdvance$0(obj);
    }
}
