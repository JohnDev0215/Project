package com.amazon.mp3.library.provider.source.nowplaying.rx;

import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.library.provider.source.nowplaying.PlayQueue;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;

/* JADX INFO: loaded from: classes3.dex */
public final class RxPlayQueue {
    private final PlayQueue playQueue;

    public RxPlayQueue(PlayQueue playQueue) {
        this.playQueue = playQueue;
    }

    public Observable<Void> replace(final Cursor cursor, final Uri uri, final int i, final boolean z) {
        return Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.mp3.library.provider.source.nowplaying.rx.RxPlayQueue.7
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Void> subscriber) {
                try {
                    RxPlayQueue.this.playQueue.replace(cursor, uri, i, z);
                    subscriber.onNext(null);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).doOnCompleted(new Action0() { // from class: com.amazon.mp3.library.provider.source.nowplaying.rx.RxPlayQueue.6
            @Override // rx.functions.Action0
            public void call() {
                cursor.close();
            }
        });
    }

    public Observable<Void> shuffle(final int i) {
        return Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.mp3.library.provider.source.nowplaying.rx.RxPlayQueue.8
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Void> subscriber) {
                try {
                    RxPlayQueue.this.playQueue.shuffle(i);
                    subscriber.onNext(null);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
