package com.amplifyframework.api.aws;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class MutiAuthSubscriptionOperation$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MutiAuthSubscriptionOperation f$0;

    public /* synthetic */ MutiAuthSubscriptionOperation$$ExternalSyntheticLambda0(MutiAuthSubscriptionOperation mutiAuthSubscriptionOperation) {
        this.f$0 = mutiAuthSubscriptionOperation;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.dispatchRequest();
    }
}
