package com.amplifyframework.datastore.syncengine;

import io.reactivex.rxjava3.functions.Action;
import java.util.concurrent.Semaphore;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class PersistentMutationOutbox$$ExternalSyntheticLambda2 implements Action {
    public final /* synthetic */ Semaphore f$0;

    @Override // io.reactivex.rxjava3.functions.Action
    public final void run() {
        this.f$0.release();
    }
}
