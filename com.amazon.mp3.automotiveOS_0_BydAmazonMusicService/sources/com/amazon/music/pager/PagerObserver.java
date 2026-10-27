package com.amazon.music.pager;

/* JADX INFO: loaded from: classes4.dex */
public interface PagerObserver<T> {
    void onCompleted();

    void onError(Throwable th);

    void onNext(T t);
}
