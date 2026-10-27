package com.amazon.mp3.billing;

import com.visualon.OSMPUtils.voOSType;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: BillingClientWrapper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.amazon.mp3.billing.BillingClientWrapper", f = "BillingClientWrapper.kt", i = {0, 0, 0}, l = {voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_MIXING_BALANCE}, m = "retryBillingServiceConnection$DigitalMusicAndroid3P_automotiveProdRelease", n = {"this", "callback", "pastDuration"}, s = {"L$0", "L$1", "J$0"})
final class BillingClientWrapper$retryBillingServiceConnection$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BillingClientWrapper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BillingClientWrapper$retryBillingServiceConnection$1(BillingClientWrapper billingClientWrapper, Continuation<? super BillingClientWrapper$retryBillingServiceConnection$1> continuation) {
        super(continuation);
        this.this$0 = billingClientWrapper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.retryBillingServiceConnection$DigitalMusicAndroid3P_automotiveProdRelease(null, this);
    }
}
