package co.touchlab.stately.isolate;

import co.touchlab.stately.concurrency.ThreadRef;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StateHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000*\n\b\u0000\u0010\u0002 \u0001*\u00020\u00012\u00020\u0001B\u0019\b\u0000\u0012\u0006\u0010\u0013\u001a\u00028\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00028\u00008\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0012\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Lco/touchlab/stately/isolate/StateHolder;", "", "T", "myState", "Ljava/lang/Object;", "getMyState", "()Ljava/lang/Object;", "Lco/touchlab/stately/concurrency/ThreadRef;", "threadRef", "Lco/touchlab/stately/concurrency/ThreadRef;", "Lco/touchlab/stately/isolate/StateRunner;", "stateRunner", "Lco/touchlab/stately/isolate/StateRunner;", "getStateRunner", "()Lco/touchlab/stately/isolate/StateRunner;", "", "getMyThread", "()Z", "myThread", "t", "<init>", "(Ljava/lang/Object;Lco/touchlab/stately/isolate/StateRunner;)V", "stately-isolate"}, k = 1, mv = {1, 4, 0})
public final class StateHolder<T> {
    private final T myState;
    private final StateRunner stateRunner;
    private final ThreadRef threadRef;

    public StateHolder(T t, StateRunner stateRunner) {
        Intrinsics.checkNotNullParameter(t, "t");
        Intrinsics.checkNotNullParameter(stateRunner, "stateRunner");
        this.stateRunner = stateRunner;
        this.myState = t;
        this.threadRef = new ThreadRef();
    }

    public final StateRunner getStateRunner() {
        return this.stateRunner;
    }

    public final T getMyState() {
        return this.myState;
    }

    public final boolean getMyThread() {
        return this.threadRef.same();
    }
}
