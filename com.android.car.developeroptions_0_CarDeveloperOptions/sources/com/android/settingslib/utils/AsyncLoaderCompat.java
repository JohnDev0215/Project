package com.android.settingslib.utils;

import android.content.Context;
import androidx.loader.content.AsyncTaskLoader;

/* JADX INFO: loaded from: classes.dex */
public abstract class AsyncLoaderCompat<T> extends AsyncTaskLoader<T> {
    private T mResult;

    protected abstract void onDiscardResult(T t);

    public AsyncLoaderCompat(Context context) {
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
    public void deliverResult(T t) {
        if (isReset()) {
            if (t != null) {
                onDiscardResult(t);
                return;
            }
            return;
        }
        T t2 = this.mResult;
        this.mResult = t;
        if (isStarted()) {
            super.deliverResult(t);
        }
        if (t2 == null || t2 == this.mResult) {
            return;
        }
        onDiscardResult(t2);
    }

    @Override // androidx.loader.content.Loader
    protected void onReset() {
        super.onReset();
        onStopLoading();
        T t = this.mResult;
        if (t != null) {
            onDiscardResult(t);
        }
        this.mResult = null;
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public void onCanceled(T t) {
        super.onCanceled(t);
        if (t != null) {
            onDiscardResult(t);
        }
    }
}
