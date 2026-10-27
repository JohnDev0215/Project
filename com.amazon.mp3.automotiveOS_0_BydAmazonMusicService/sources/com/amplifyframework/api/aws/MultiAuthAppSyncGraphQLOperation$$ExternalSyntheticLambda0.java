package com.amplifyframework.api.aws;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class MultiAuthAppSyncGraphQLOperation$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MultiAuthAppSyncGraphQLOperation f$0;

    public /* synthetic */ MultiAuthAppSyncGraphQLOperation$$ExternalSyntheticLambda0(MultiAuthAppSyncGraphQLOperation multiAuthAppSyncGraphQLOperation) {
        this.f$0 = multiAuthAppSyncGraphQLOperation;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.dispatchRequest();
    }
}
