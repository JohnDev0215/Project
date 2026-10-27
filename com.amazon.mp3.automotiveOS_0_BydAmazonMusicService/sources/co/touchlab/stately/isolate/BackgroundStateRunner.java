package co.touchlab.stately.isolate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BackgroundStateRunner.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\r\u0010\u000eJ#\u0010\u0005\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016¢\u0006\u0004\b\u0005\u0010\u0006R\"\u0010\t\u001a\n \b*\u0004\u0018\u00010\u00070\u00078\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lco/touchlab/stately/isolate/BackgroundStateRunner;", "Lco/touchlab/stately/isolate/StateRunner;", "R", "Lkotlin/Function0;", "block", "stateRun", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "stateExecutor", "Ljava/util/concurrent/ExecutorService;", "getStateExecutor$stately_isolate", "()Ljava/util/concurrent/ExecutorService;", "<init>", "()V", "stately-isolate"}, k = 1, mv = {1, 4, 0})
public final class BackgroundStateRunner implements StateRunner {
    private final ExecutorService stateExecutor = Executors.newSingleThreadExecutor();

    @Override // co.touchlab.stately.isolate.StateRunner
    public <R> R stateRun(final Function0<? extends R> block) throws Throwable {
        Intrinsics.checkNotNullParameter(block, "block");
        RunResult runResult = (RunResult) this.stateExecutor.submit(new Callable<RunResult>() { // from class: co.touchlab.stately.isolate.BackgroundStateRunner$stateRun$result$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public final RunResult call() {
                try {
                    return new Ok(block.invoke());
                } catch (Throwable th) {
                    return new Thrown(th);
                }
            }
        }).get();
        if (runResult instanceof Ok) {
            return (R) ((Ok) runResult).getResult();
        }
        if (runResult instanceof Thrown) {
            throw ((Thrown) runResult).getThrowable();
        }
        throw new NoWhenBranchMatchedException();
    }
}
