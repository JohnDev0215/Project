package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;

/* JADX INFO: loaded from: classes7.dex */
abstract class AbstractMaybeWithUpstream<T, R> extends Maybe<R> {
    protected final MaybeSource<T> source;

    AbstractMaybeWithUpstream(MaybeSource<T> source) {
        this.source = source;
    }
}
