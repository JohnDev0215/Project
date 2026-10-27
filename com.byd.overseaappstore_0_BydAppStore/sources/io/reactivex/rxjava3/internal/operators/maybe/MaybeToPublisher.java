package io.reactivex.rxjava3.internal.operators.maybe;

import spi.ei;
import spi.fi;
import spi.ml;
import spi.uc;

/* JADX INFO: loaded from: classes2.dex */
public enum MaybeToPublisher implements uc<ei<Object>, ml<Object>> {
    INSTANCE;

    public static <T> uc<ei<T>, ml<T>> instance() {
        return INSTANCE;
    }

    @Override // spi.uc
    public ml<Object> apply(ei<Object> eiVar) {
        return new fi(eiVar);
    }
}
