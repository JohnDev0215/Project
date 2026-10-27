package io.reactivex.rxjava3.internal.operators.flowable;

import spi.k4;
import spi.ps;

/* JADX INFO: loaded from: classes2.dex */
public enum FlowableInternalHelper$RequestMax implements k4<ps> {
    INSTANCE;

    @Override // spi.k4
    public void accept(ps psVar) {
        psVar.request(Long.MAX_VALUE);
    }
}
