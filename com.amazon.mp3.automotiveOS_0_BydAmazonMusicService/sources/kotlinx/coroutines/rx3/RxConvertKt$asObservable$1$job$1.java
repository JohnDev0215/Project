package kotlinx.coroutines.rx3;

import io.reactivex.rxjava3.core.ObservableEmitter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: RxConvert.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00020\u0002H\u008a@"}, d2 = {"", "T", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "kotlinx.coroutines.rx3.RxConvertKt$asObservable$1$job$1", f = "RxConvert.kt", i = {0}, l = {114}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
final class RxConvertKt$asObservable$1$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ObservableEmitter $emitter;
    final /* synthetic */ Flow $this_asObservable;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RxConvertKt$asObservable$1$job$1(Flow flow, ObservableEmitter observableEmitter, Continuation continuation) {
        super(2, continuation);
        this.$this_asObservable = flow;
        this.$emitter = observableEmitter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RxConvertKt$asObservable$1$job$1 rxConvertKt$asObservable$1$job$1 = new RxConvertKt$asObservable$1$job$1(this.$this_asObservable, this.$emitter, continuation);
        rxConvertKt$asObservable$1$job$1.L$0 = obj;
        return rxConvertKt$asObservable$1$job$1;
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RxConvertKt$asObservable$1$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0057  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L1d
            if (r1 != r2) goto L15
            java.lang.Object r0 = r6.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L13
            goto L39
        L13:
            r7 = move-exception
            goto L43
        L15:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L1d:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            kotlinx.coroutines.CoroutineScope r7 = (kotlinx.coroutines.CoroutineScope) r7
            kotlinx.coroutines.flow.Flow r1 = r6.$this_asObservable     // Catch: java.lang.Throwable -> L3f
            kotlinx.coroutines.rx3.RxConvertKt$asObservable$1$job$1$1 r3 = new kotlinx.coroutines.rx3.RxConvertKt$asObservable$1$job$1$1     // Catch: java.lang.Throwable -> L3f
            io.reactivex.rxjava3.core.ObservableEmitter r4 = r6.$emitter     // Catch: java.lang.Throwable -> L3f
            r3.<init>()     // Catch: java.lang.Throwable -> L3f
            r6.L$0 = r7     // Catch: java.lang.Throwable -> L3f
            r6.label = r2     // Catch: java.lang.Throwable -> L3f
            java.lang.Object r1 = r1.collect(r3, r6)     // Catch: java.lang.Throwable -> L3f
            if (r1 != r0) goto L38
            return r0
        L38:
            r0 = r7
        L39:
            io.reactivex.rxjava3.core.ObservableEmitter r7 = r6.$emitter     // Catch: java.lang.Throwable -> L13
            r7.onComplete()     // Catch: java.lang.Throwable -> L13
            goto L5c
        L3f:
            r0 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
        L43:
            boolean r1 = r7 instanceof java.util.concurrent.CancellationException
            if (r1 != 0) goto L57
            io.reactivex.rxjava3.core.ObservableEmitter r1 = r6.$emitter
            boolean r1 = r1.tryOnError(r7)
            if (r1 != 0) goto L5c
            kotlin.coroutines.CoroutineContext r0 = r0.getCoroutineContext()
            kotlinx.coroutines.rx3.RxCancellableKt.handleUndeliverableException(r7, r0)
            goto L5c
        L57:
            io.reactivex.rxjava3.core.ObservableEmitter r7 = r6.$emitter
            r7.onComplete()
        L5c:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx3.RxConvertKt$asObservable$1$job$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
