package com.amplifyframework.datastore.storage.sqlite;

import com.amplifyframework.datastore.storage.StorageItemChange;
import io.reactivex.rxjava3.functions.Consumer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class ObserveQueryExecutor$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ com.amplifyframework.core.Consumer f$0;

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        this.f$0.accept((StorageItemChange) obj);
    }
}
