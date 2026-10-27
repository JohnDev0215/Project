package com.amplifyframework.rx;

import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.Cancelable;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class RxDataStoreBinding$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ AtomicReference f$0;

    @Override // com.amplifyframework.core.Consumer
    public final void accept(Object obj) {
        this.f$0.set((Cancelable) obj);
    }
}
