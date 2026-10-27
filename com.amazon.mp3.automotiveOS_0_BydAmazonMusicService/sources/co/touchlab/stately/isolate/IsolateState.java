package co.touchlab.stately.isolate;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IsoState.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u0015\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u000e\u0010\u000fJ%\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005\"\b\b\u0001\u0010\u0003*\u00020\u00012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\n\u001a\u00028\u0001\"\u0004\b\u0001\u0010\u00032\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b¢\u0006\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lco/touchlab/stately/isolate/IsolateState;", "", "T", "R", "r", "Lco/touchlab/stately/isolate/StateHolder;", "fork", "(Ljava/lang/Object;)Lco/touchlab/stately/isolate/StateHolder;", "Lkotlin/Function1;", "block", "access", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "stateHolder", "Lco/touchlab/stately/isolate/StateHolder;", "<init>", "(Lco/touchlab/stately/isolate/StateHolder;)V", "stately-isolate"}, k = 1, mv = {1, 4, 0})
public class IsolateState<T> {
    private final StateHolder<T> stateHolder;

    /* JADX WARN: Multi-variable type inference failed */
    public IsolateState(StateHolder<? extends T> stateHolder) {
        Intrinsics.checkNotNullParameter(stateHolder, "stateHolder");
        this.stateHolder = stateHolder;
    }

    public final <R> StateHolder<R> fork(R r) {
        Intrinsics.checkNotNullParameter(r, "r");
        if (this.stateHolder.getMyThread()) {
            return new StateHolder<>(r, this.stateHolder.getStateRunner());
        }
        throw new IllegalStateException("Must fork state from the state thread");
    }

    public final <R> R access(final Function1<? super T, ? extends R> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (this.stateHolder.getMyThread()) {
            return block.invoke(this.stateHolder.getMyState());
        }
        return (R) this.stateHolder.getStateRunner().stateRun(new Function0<R>() { // from class: co.touchlab.stately.isolate.IsolateState.access.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final R invoke() {
                return (R) block.invoke(IsolateState.this.stateHolder.getMyState());
            }
        });
    }
}
