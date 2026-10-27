package com.amazon.music.pager;

/* JADX INFO: loaded from: classes4.dex */
interface Replay<T> {
    void replay(PagerObserver<T> pagerObserver);

    void setComplete();

    void setError(Throwable th);

    void setNext(T t);
}
