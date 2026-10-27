package java9.util.stream;

import java9.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class SortedOps$RefSortingSink$$Lambda$1 implements Consumer {
    private final Sink arg$1;

    private SortedOps$RefSortingSink$$Lambda$1(Sink sink) {
        this.arg$1 = sink;
    }

    public static Consumer lambdaFactory$(Sink sink) {
        return new SortedOps$RefSortingSink$$Lambda$1(sink);
    }

    @Override // java9.util.function.Consumer
    public void accept(Object obj) {
        this.arg$1.accept(obj);
    }
}
