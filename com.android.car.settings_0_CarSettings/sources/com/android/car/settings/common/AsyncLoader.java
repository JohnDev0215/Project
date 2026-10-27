package com.android.car.settings.common;

import android.content.Context;
import androidx.loader.content.AsyncTaskLoader;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AsyncLoader<T> extends AsyncTaskLoader<T> {
    private T mResult;

    public AsyncLoader(Context context) {
        super(context);
    }

    @Override // androidx.loader.content.Loader
    protected void onStartLoading() {
        T t = this.mResult;
        if (t != null) {
            deliverResult(t);
        }
        if (takeContentChanged() || this.mResult == null) {
            forceLoad();
        }
    }

    @Override // androidx.loader.content.Loader
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override // androidx.loader.content.Loader
    public void deliverResult(T data) {
        if (isReset()) {
            return;
        }
        this.mResult = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override // androidx.loader.content.Loader
    protected void onReset() {
        super.onReset();
        onStopLoading();
        this.mResult = null;
    }
}
