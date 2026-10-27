package com.android.permissioncontroller.permission.utils;

import androidx.lifecycle.LiveData;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: KotlinUtils.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004*\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "LD", "Landroidx/lifecycle/LiveData;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/android/permissioncontroller/permission/utils/KotlinUtilsKt$getInitializedValue$4$2"}, k = 3, mv = {1, 1, 16})
final class KotlinUtilsKt$getInitializedValue$$inlined$suspendCoroutine$lambda$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $isInitialized$inlined;
    final /* synthetic */ Function2 $observe$inlined;
    final /* synthetic */ AtomicReference $observer;
    final /* synthetic */ LiveData $this_getInitializedValue$inlined;
    int label;
    private CoroutineScope p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    KotlinUtilsKt$getInitializedValue$$inlined$suspendCoroutine$lambda$2(AtomicReference atomicReference, Continuation continuation, LiveData liveData, Function1 function1, Function2 function2) {
        super(2, continuation);
        this.$observer = atomicReference;
        this.$this_getInitializedValue$inlined = liveData;
        this.$isInitialized$inlined = function1;
        this.$observe$inlined = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        KotlinUtilsKt$getInitializedValue$$inlined$suspendCoroutine$lambda$2 kotlinUtilsKt$getInitializedValue$$inlined$suspendCoroutine$lambda$2 = new KotlinUtilsKt$getInitializedValue$$inlined$suspendCoroutine$lambda$2(this.$observer, completion, this.$this_getInitializedValue$inlined, this.$isInitialized$inlined, this.$observe$inlined);
        kotlinUtilsKt$getInitializedValue$$inlined$suspendCoroutine$lambda$2.p$ = (CoroutineScope) obj;
        return kotlinUtilsKt$getInitializedValue$$inlined$suspendCoroutine$lambda$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((KotlinUtilsKt$getInitializedValue$$inlined$suspendCoroutine$lambda$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure($result);
        CoroutineScope coroutineScope = this.p$;
        Function2 function2 = this.$observe$inlined;
        LiveData liveData = this.$this_getInitializedValue$inlined;
        Object obj = this.$observer.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "observer.get()");
        function2.invoke(liveData, obj);
        return Unit.INSTANCE;
    }
}
