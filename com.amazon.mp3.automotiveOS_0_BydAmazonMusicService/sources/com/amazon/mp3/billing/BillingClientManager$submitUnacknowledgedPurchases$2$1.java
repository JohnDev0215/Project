package com.amazon.mp3.billing;

import com.amazon.mp3.util.Log;
import com.android.billingclient.api.Purchase;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BillingClientManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.billing.BillingClientManager$submitUnacknowledgedPurchases$2$1", f = "BillingClientManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class BillingClientManager$submitUnacknowledgedPurchases$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $loggingContext;
    final /* synthetic */ Purchase $purchase;
    int label;
    final /* synthetic */ BillingClientManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BillingClientManager$submitUnacknowledgedPurchases$2$1(String str, Purchase purchase, BillingClientManager billingClientManager, Continuation<? super BillingClientManager$submitUnacknowledgedPurchases$2$1> continuation) {
        super(2, continuation);
        this.$loggingContext = str;
        this.$purchase = purchase;
        this.this$0 = billingClientManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BillingClientManager$submitUnacknowledgedPurchases$2$1(this.$loggingContext, this.$purchase, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BillingClientManager$submitUnacknowledgedPurchases$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Log.debug(BillingClientManager.TAG, this.$loggingContext + ": submit for un-acked purchase orderId=" + this.$purchase.getOrderId());
        this.this$0.submitSubscriptionCreation(this.$purchase);
        return Unit.INSTANCE;
    }
}
