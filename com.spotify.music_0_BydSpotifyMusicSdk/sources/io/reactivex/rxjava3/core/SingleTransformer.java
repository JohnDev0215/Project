package io.reactivex.rxjava3.core;

/* JADX INFO: loaded from: classes.dex */
@FunctionalInterface
public interface SingleTransformer<Upstream, Downstream> {
    Single e(Single single);
}
