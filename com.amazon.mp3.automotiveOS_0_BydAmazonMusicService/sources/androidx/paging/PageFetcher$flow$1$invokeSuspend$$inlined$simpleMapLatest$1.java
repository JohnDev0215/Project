package androidx.paging;

import androidx.paging.PageFetcher;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Add missing generic type declarations: [Value, Key] */
/* JADX INFO: compiled from: FlowExt.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0006\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010\u0003\u001a\u00028\u0000H\u008a@¨\u0006\u0005"}, d2 = {"T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "androidx/paging/FlowExtKt$simpleMapLatest$1", "<anonymous>"}, k = 3, mv = {1, 5, 1})
@DebugMetadata(c = "androidx.paging.PageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1", f = "PageFetcher.kt", i = {}, l = {226}, m = "invokeSuspend", n = {}, s = {})
public final class PageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1<Key, Value> extends SuspendLambda implements Function3<FlowCollector<? super PagingData<Value>>, PageFetcher.GenerationInfo<Key, Value>, Continuation<? super Unit>, Object> {
    final /* synthetic */ RemoteMediatorAccessor $remoteMediatorAccessor$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ PageFetcher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1(Continuation continuation, PageFetcher pageFetcher, RemoteMediatorAccessor remoteMediatorAccessor) {
        super(3, continuation);
        this.this$0 = pageFetcher;
        this.$remoteMediatorAccessor$inlined = remoteMediatorAccessor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(FlowCollector<? super PagingData<Value>> flowCollector, PageFetcher.GenerationInfo<Key, Value> generationInfo, Continuation<? super Unit> continuation) {
        PageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1 pageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1 = new PageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1(continuation, this.this$0, this.$remoteMediatorAccessor$inlined);
        pageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1.L$0 = flowCollector;
        pageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1.L$1 = generationInfo;
        return pageFetcher$flow$1$invokeSuspend$$inlined$simpleMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            PageFetcher.GenerationInfo generationInfo = (PageFetcher.GenerationInfo) this.L$1;
            PagingData pagingData = new PagingData(this.this$0.injectRemoteEvents(generationInfo.getSnapshot(), generationInfo.getJob(), this.$remoteMediatorAccessor$inlined), new PageFetcher.PagerUiReceiver(this.this$0, generationInfo.getSnapshot(), this.this$0.retryEvents));
            this.label = 1;
            if (flowCollector.emit(pagingData, this) == coroutine_suspended) {
                return coroutine_suspended;
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
