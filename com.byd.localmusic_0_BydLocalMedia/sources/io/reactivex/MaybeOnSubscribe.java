package io.reactivex;

/* JADX INFO: loaded from: classes5.dex */
public interface MaybeOnSubscribe<T> {
    void subscribe(MaybeEmitter<T> maybeEmitter) throws Exception;
}
