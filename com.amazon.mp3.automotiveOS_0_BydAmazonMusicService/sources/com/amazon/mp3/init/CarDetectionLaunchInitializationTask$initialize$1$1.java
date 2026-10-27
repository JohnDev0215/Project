package com.amazon.mp3.init;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: CarDetectionLaunchInitializationTask.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.init.CarDetectionLaunchInitializationTask$initialize$1$1", f = "CarDetectionLaunchInitializationTask.kt", i = {}, l = {57}, m = "invokeSuspend", n = {}, s = {})
final class CarDetectionLaunchInitializationTask$initialize$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ CarDetectionLaunchInitializationTask this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CarDetectionLaunchInitializationTask$initialize$1$1(CarDetectionLaunchInitializationTask carDetectionLaunchInitializationTask, Continuation<? super CarDetectionLaunchInitializationTask$initialize$1$1> continuation) {
        super(2, continuation);
        this.this$0 = carDetectionLaunchInitializationTask;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CarDetectionLaunchInitializationTask$initialize$1$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CarDetectionLaunchInitializationTask$initialize$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CarDetectionLaunchInitializationTask carDetectionLaunchInitializationTask = this.this$0;
            this.label = 1;
            if (carDetectionLaunchInitializationTask.initializeCarDetector(this) == coroutine_suspended) {
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
