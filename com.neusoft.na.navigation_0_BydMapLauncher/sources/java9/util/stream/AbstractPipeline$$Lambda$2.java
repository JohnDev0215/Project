package java9.util.stream;

import java9.util.Spliterator;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class AbstractPipeline$$Lambda$2 implements Supplier {
    private final Spliterator arg$1;

    private AbstractPipeline$$Lambda$2(Spliterator spliterator) {
        this.arg$1 = spliterator;
    }

    public static Supplier lambdaFactory$(Spliterator spliterator) {
        return new AbstractPipeline$$Lambda$2(spliterator);
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return AbstractPipeline.lambda$wrapSpliterator$1(this.arg$1);
    }
}
