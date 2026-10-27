package com.amplifyframework.datastore.storage.sqlite;

import com.amplifyframework.core.Consumer;
import com.amplifyframework.datastore.DataStoreException;
import io.reactivex.rxjava3.core.SingleEmitter;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class PersistentModelVersion$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ SingleEmitter f$0;

    @Override // com.amplifyframework.core.Consumer
    public final void accept(Object obj) {
        this.f$0.onError((DataStoreException) obj);
    }
}
