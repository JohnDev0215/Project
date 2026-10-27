package java9.util.stream;

import java.util.List;
import java9.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class SpinedBuffer$$Lambda$1 implements Consumer {
    private final List arg$1;

    private SpinedBuffer$$Lambda$1(List list) {
        this.arg$1 = list;
    }

    public static Consumer lambdaFactory$(List list) {
        return new SpinedBuffer$$Lambda$1(list);
    }

    @Override // java9.util.function.Consumer
    public void accept(Object obj) {
        this.arg$1.add(obj);
    }
}
