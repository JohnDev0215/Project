package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: CachedPageEventFlow.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0018\u00010\u00030\u0002H\u008a@"}, d2 = {"", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/collections/IndexedValue;", "Landroidx/paging/PageEvent;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
@DebugMetadata(c = "androidx.paging.CachedPageEventFlow$sharedForDownstream$1", f = "CachedPageEventFlow.kt", i = {0, 1}, l = {63, 68}, m = "invokeSuspend", n = {"$this$onSubscription", "$this$onSubscription"}, s = {"L$0", "L$0"})
final class CachedPageEventFlow$sharedForDownstream$1<T> extends SuspendLambda implements Function2<FlowCollector<? super IndexedValue<? extends PageEvent<T>>>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ CachedPageEventFlow<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CachedPageEventFlow$sharedForDownstream$1(CachedPageEventFlow<T> cachedPageEventFlow, Continuation<? super CachedPageEventFlow$sharedForDownstream$1> continuation) {
        super(2, continuation);
        this.this$0 = cachedPageEventFlow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CachedPageEventFlow$sharedForDownstream$1 cachedPageEventFlow$sharedForDownstream$1 = new CachedPageEventFlow$sharedForDownstream$1(this.this$0, continuation);
        cachedPageEventFlow$sharedForDownstream$1.L$0 = obj;
        return cachedPageEventFlow$sharedForDownstream$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
    public final Object mo4invoke(FlowCollector<? super IndexedValue<? extends PageEvent<T>>> flowCollector, Continuation<? super Unit> continuation) {
        return ((CachedPageEventFlow$sharedForDownstream$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005b  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L2a
            if (r1 == r3) goto L22
            if (r1 != r2) goto L1a
            java.lang.Object r1 = r5.L$1
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r3 = r5.L$0
            kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
            kotlin.ResultKt.throwOnFailure(r6)
            goto L54
        L1a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L22:
            java.lang.Object r1 = r5.L$0
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L43
        L2a:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.Object r6 = r5.L$0
            r1 = r6
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            androidx.paging.CachedPageEventFlow<T> r6 = r5.this$0
            androidx.paging.FlattenedPageController r6 = androidx.paging.CachedPageEventFlow.access$getPageController$p(r6)
            r5.L$0 = r1
            r5.label = r3
            java.lang.Object r6 = r6.getStateAsEvents(r5)
            if (r6 != r0) goto L43
            return r0
        L43:
            java.util.List r6 = (java.util.List) r6
            androidx.paging.CachedPageEventFlow<T> r3 = r5.this$0
            kotlinx.coroutines.Job r3 = androidx.paging.CachedPageEventFlow.access$getJob$p(r3)
            r3.start()
            java.util.Iterator r6 = r6.iterator()
            r3 = r1
            r1 = r6
        L54:
            r6 = r5
        L55:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L6e
            java.lang.Object r4 = r1.next()
            kotlin.collections.IndexedValue r4 = (kotlin.collections.IndexedValue) r4
            r6.L$0 = r3
            r6.L$1 = r1
            r6.label = r2
            java.lang.Object r4 = r3.emit(r4, r6)
            if (r4 != r0) goto L55
            return r0
        L6e:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.CachedPageEventFlow$sharedForDownstream$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
