package com.amazon.mp3.library.provider.source.nowplaying;

import com.amazon.mp3.util.Log;
import rx.Observer;

/* JADX INFO: loaded from: classes3.dex */
public class NowPlayingQueueObserver<T> implements Observer<T> {
    private static final String TAG = NowPlayingQueueUtil.class.getSimpleName();

    @Override // rx.Observer
    public void onError(Throwable throwable) {
        NowPlayingQueueUtil.getInstance().handleException(throwable);
    }

    @Override // rx.Observer
    public final void onCompleted() {
        Log.debug(TAG, "onCompleted called");
    }

    @Override // rx.Observer
    public void onNext(T aVoid) {
        Log.debug(TAG, "onNext called");
    }
}
