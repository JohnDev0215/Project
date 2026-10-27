package com.amplifyframework.datastore.syncengine;

import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.functions.Consumer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class RetryHandler$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ SingleEmitter f$0;

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        this.f$0.onSuccess(obj);
    }
}
