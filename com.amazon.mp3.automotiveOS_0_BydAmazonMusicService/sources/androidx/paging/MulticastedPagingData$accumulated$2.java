package androidx.paging;

import androidx.paging.ActiveFlowTracker;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: CachedPagingData.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0007\u001a\u00020\u0006\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u008a@"}, d2 = {"", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "Landroidx/paging/PageEvent;", "", "it", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
@DebugMetadata(c = "androidx.paging.MulticastedPagingData$accumulated$2", f = "CachedPagingData.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
final class MulticastedPagingData$accumulated$2<T> extends SuspendLambda implements Function3<FlowCollector<? super PageEvent<T>>, Throwable, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ MulticastedPagingData<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MulticastedPagingData$accumulated$2(MulticastedPagingData<T> multicastedPagingData, Continuation<? super MulticastedPagingData$accumulated$2> continuation) {
        super(3, continuation);
        this.this$0 = multicastedPagingData;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(FlowCollector<? super PageEvent<T>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
        return new MulticastedPagingData$accumulated$2(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ActiveFlowTracker tracker = this.this$0.getTracker();
            if (tracker != null) {
                ActiveFlowTracker.FlowType flowType = ActiveFlowTracker.FlowType.PAGE_EVENT_FLOW;
                this.label = 1;
                if (tracker.onComplete(flowType, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
