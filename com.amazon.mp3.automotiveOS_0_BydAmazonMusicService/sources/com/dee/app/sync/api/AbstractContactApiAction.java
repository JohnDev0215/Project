package com.dee.app.sync.api;

import android.content.SharedPreferences;
import rx.Observable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbstractContactApiAction<I, R> {
    private final SharedPreferences mSharedPreferences;

    protected abstract Observable<R> handleAction(I i);

    public AbstractContactApiAction(SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
    }

    public Observable<R> performAction(I i) {
        return handleAction(i);
    }
}
