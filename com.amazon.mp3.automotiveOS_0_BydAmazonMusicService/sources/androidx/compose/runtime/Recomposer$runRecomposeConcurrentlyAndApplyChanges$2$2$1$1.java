package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Recomposer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "androidx.compose.runtime.Recomposer$runRecomposeConcurrentlyAndApplyChanges$2$2$1$1", f = "Recomposer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class Recomposer$runRecomposeConcurrentlyAndApplyChanges$2$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ControlledComposition $composition;
    int label;
    final /* synthetic */ Recomposer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Recomposer$runRecomposeConcurrentlyAndApplyChanges$2$2$1$1(Recomposer recomposer, ControlledComposition controlledComposition, Continuation<? super Recomposer$runRecomposeConcurrentlyAndApplyChanges$2$2$1$1> continuation) {
        super(2, continuation);
        this.this$0 = recomposer;
        this.$composition = controlledComposition;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Recomposer$runRecomposeConcurrentlyAndApplyChanges$2$2$1$1(this.this$0, this.$composition, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((Recomposer$runRecomposeConcurrentlyAndApplyChanges$2$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CancellableContinuation cancellableContinuationDeriveStateLocked;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ControlledComposition controlledCompositionPerformRecompose = this.this$0.performRecompose(this.$composition, null);
            Object obj2 = this.this$0.stateLock;
            Recomposer recomposer = this.this$0;
            synchronized (obj2) {
                if (controlledCompositionPerformRecompose == null) {
                    recomposer.concurrentCompositionsOutstanding--;
                    cancellableContinuationDeriveStateLocked = recomposer.deriveStateLocked();
                } else {
                    recomposer.compositionsAwaitingApply.add(controlledCompositionPerformRecompose);
                    recomposer.concurrentCompositionsOutstanding--;
                    cancellableContinuationDeriveStateLocked = recomposer.deriveStateLocked();
                }
            }
            if (cancellableContinuationDeriveStateLocked != null) {
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationDeriveStateLocked.resumeWith(Result.m2810constructorimpl(Unit.INSTANCE));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
