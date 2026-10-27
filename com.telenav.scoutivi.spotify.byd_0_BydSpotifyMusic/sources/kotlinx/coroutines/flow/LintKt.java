package kotlinx.coroutines.flow;

import g5.e;
import java.util.concurrent.CancellationException;
import k5.g;

/* JADX INFO: compiled from: Lint.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LintKt {
    public static final void cancel(FlowCollector<?> flowCollector, CancellationException cancellationException) {
        FlowKt.noImpl();
        throw new e();
    }

    public static /* synthetic */ void cancel$default(FlowCollector flowCollector, CancellationException cancellationException, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            cancellationException = null;
        }
        cancel(flowCollector, cancellationException);
    }

    public static final <T> Flow<T> cancellable(SharedFlow<? extends T> sharedFlow) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> conflate(StateFlow<? extends T> stateFlow) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> distinctUntilChanged(StateFlow<? extends T> stateFlow) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final <T> Flow<T> flowOn(SharedFlow<? extends T> sharedFlow, g gVar) {
        FlowKt.noImpl();
        throw new e();
    }

    public static final g getCoroutineContext(FlowCollector<?> flowCollector) {
        FlowKt.noImpl();
        throw new e();
    }

    public static /* synthetic */ void getCoroutineContext$annotations(FlowCollector flowCollector) {
    }

    public static final boolean isActive(FlowCollector<?> flowCollector) {
        FlowKt.noImpl();
        throw new e();
    }

    public static /* synthetic */ void isActive$annotations(FlowCollector flowCollector) {
    }
}
