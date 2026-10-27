package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import java.util.Objects;

/* JADX INFO: loaded from: classes7.dex */
abstract class AbstractFlowableWithUpstream<T, R> extends Flowable<R> {
    protected final Flowable<T> source;

    AbstractFlowableWithUpstream(Flowable<T> source) {
        Objects.requireNonNull(source, "source is null");
        this.source = source;
    }
}
