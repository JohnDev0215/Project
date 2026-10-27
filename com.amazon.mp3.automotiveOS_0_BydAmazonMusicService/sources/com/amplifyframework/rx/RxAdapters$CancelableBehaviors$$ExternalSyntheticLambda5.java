package com.amplifyframework.rx;

import com.amplifyframework.core.Consumer;
import io.reactivex.rxjava3.core.ObservableEmitter;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ ObservableEmitter f$0;

    @Override // com.amplifyframework.core.Consumer
    public final void accept(Object obj) {
        this.f$0.onNext(obj);
    }
}
