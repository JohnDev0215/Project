package com.amazon.mp3.billing;

import com.android.billingclient.api.SkuDetails;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Coroutines.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"T", "Lkotlinx/coroutines/CoroutineScope;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.billing.BillingClientWrapper$getSkuDetailsSuspend$$inlined$suspendCoroutineWithTimeout$default$1", f = "BillingClientWrapper.kt", i = {}, l = {92}, m = "invokeSuspend", n = {}, s = {})
public final class BillingClientWrapper$getSkuDetailsSuspend$$inlined$suspendCoroutineWithTimeout$default$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SkuDetails>, Object> {
    final /* synthetic */ String $skuName$inlined;
    int label;
    final /* synthetic */ BillingClientWrapper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BillingClientWrapper$getSkuDetailsSuspend$$inlined$suspendCoroutineWithTimeout$default$1(Continuation continuation, BillingClientWrapper billingClientWrapper, String str) {
        super(2, continuation);
        this.this$0 = billingClientWrapper;
        this.$skuName$inlined = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BillingClientWrapper$getSkuDetailsSuspend$$inlined$suspendCoroutineWithTimeout$default$1(continuation, this.this$0, this.$skuName$inlined);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super SkuDetails> continuation) {
        return ((BillingClientWrapper$getSkuDetailsSuspend$$inlined$suspendCoroutineWithTimeout$default$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            if (this.this$0.currentSkuDetails.isEmpty()) {
                final BillingClientWrapper billingClientWrapper = this.this$0;
                final String str = this.$skuName$inlined;
                billingClientWrapper.querySkusAsync(new Function0<Unit>() { // from class: com.amazon.mp3.billing.BillingClientWrapper$getSkuDetailsSuspend$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Continuation<SkuDetails> continuation = cancellableContinuationImpl;
                        Result.Companion companion = Result.INSTANCE;
                        continuation.resumeWith(Result.m2810constructorimpl(billingClientWrapper.getSkuDetails(str)));
                    }
                });
            } else {
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationImpl.resumeWith(Result.m2810constructorimpl(this.this$0.getSkuDetails(this.$skuName$inlined)));
            }
            obj = cancellableContinuationImpl.getResult();
            if (obj == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(this);
            }
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
