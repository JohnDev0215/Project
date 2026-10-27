package io.reactivex;

/* JADX INFO: loaded from: classes5.dex */
public interface MaybeTransformer<Upstream, Downstream> {
    MaybeSource<Downstream> apply(Maybe<Upstream> maybe);
}
