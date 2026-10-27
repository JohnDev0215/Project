package com.android.settingslib.utils;

import android.content.AsyncTaskLoader;
import android.content.Context;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public abstract class a<T> extends AsyncTaskLoader<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private T f1373a;

    public a(Context context) {
        super(context);
    }

    protected abstract void a(T t);

    @Override // android.content.Loader
    public void deliverResult(T t) {
        if (isReset()) {
            if (t != null) {
                a(t);
                return;
            }
            return;
        }
        T t2 = this.f1373a;
        this.f1373a = t;
        if (isStarted()) {
            super.deliverResult(t);
        }
        if (t2 == null || t2 == this.f1373a) {
            return;
        }
        a(t2);
    }

    @Override // android.content.AsyncTaskLoader
    public void onCanceled(T t) {
        super.onCanceled(t);
        if (t != null) {
            a(t);
        }
    }

    @Override // android.content.Loader
    protected void onReset() {
        super.onReset();
        onStopLoading();
        T t = this.f1373a;
        if (t != null) {
            a(t);
        }
        this.f1373a = null;
    }

    @Override // android.content.Loader
    protected void onStartLoading() {
        T t = this.f1373a;
        if (t != null) {
            deliverResult(t);
        }
        if (takeContentChanged() || this.f1373a == null) {
            forceLoad();
        }
    }

    @Override // android.content.Loader
    protected void onStopLoading() {
        cancelLoad();
    }
}
