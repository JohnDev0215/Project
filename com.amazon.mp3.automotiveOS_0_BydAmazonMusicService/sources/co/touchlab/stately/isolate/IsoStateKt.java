package co.touchlab.stately.isolate;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IsoState.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"createState", "Lco/touchlab/stately/isolate/StateHolder;", "T", "", "producer", "Lkotlin/Function0;", "stateRunner", "Lco/touchlab/stately/isolate/StateRunner;", "stately-isolate"}, k = 2, mv = {1, 4, 0})
public final class IsoStateKt {
    public static /* synthetic */ StateHolder createState$default(Function0 function0, StateRunner stateRunner, int i, Object obj) {
        if ((i & 2) != 0) {
            stateRunner = StateHolderKt.getDefaultStateRunner();
        }
        return createState(function0, stateRunner);
    }

    public static final <T> StateHolder<T> createState(final Function0<? extends T> producer, final StateRunner stateRunner) {
        Intrinsics.checkNotNullParameter(producer, "producer");
        Intrinsics.checkNotNullParameter(stateRunner, "stateRunner");
        return (StateHolder) stateRunner.stateRun(new Function0<StateHolder<? extends T>>() { // from class: co.touchlab.stately.isolate.IsoStateKt.createState.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final StateHolder<T> invoke() {
                return new StateHolder<>(producer.invoke(), stateRunner);
            }
        });
    }
}
