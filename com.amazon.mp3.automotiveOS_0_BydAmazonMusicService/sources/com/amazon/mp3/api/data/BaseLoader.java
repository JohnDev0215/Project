package com.amazon.mp3.api.data;

import android.content.Context;
import androidx.loader.content.AsyncTaskLoader;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseLoader<D> extends AsyncTaskLoader<D> {
    private D mData;

    public BaseLoader(Context context) {
        super(context);
    }

    @Override // androidx.loader.content.Loader
    protected void onStartLoading() {
        D d = this.mData;
        if (d != null) {
            deliverResult(d);
        }
        if (takeContentChanged() || this.mData == null) {
            forceLoad();
        }
    }

    @Override // androidx.loader.content.Loader
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override // androidx.loader.content.Loader
    protected void onReset() {
        onStopLoading();
        if (this.mData != null) {
            this.mData = null;
        }
    }

    @Override // androidx.loader.content.Loader
    public void deliverResult(D data) {
        if (isReset()) {
            return;
        }
        this.mData = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }
}
