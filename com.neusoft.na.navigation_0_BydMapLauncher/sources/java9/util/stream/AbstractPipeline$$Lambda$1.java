package java9.util.stream;

import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class AbstractPipeline$$Lambda$1 implements Supplier {
    private final AbstractPipeline arg$1;

    private AbstractPipeline$$Lambda$1(AbstractPipeline abstractPipeline) {
        this.arg$1 = abstractPipeline;
    }

    public static Supplier lambdaFactory$(AbstractPipeline abstractPipeline) {
        return new AbstractPipeline$$Lambda$1(abstractPipeline);
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return AbstractPipeline.lambda$spliterator$0(this.arg$1);
    }
}
