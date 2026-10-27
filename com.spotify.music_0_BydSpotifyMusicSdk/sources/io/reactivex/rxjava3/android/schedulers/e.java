package io.reactivex.rxjava3.android.schedulers;

import android.os.Handler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable, Disposable {
    public final Handler c;
    public final Runnable e;
    public volatile boolean k;

    public e(Handler handler, Runnable runnable) {
        this.c = handler;
        this.e = runnable;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.c.removeCallbacks(this);
        this.k = true;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.e.run();
        } catch (Throwable th) {
            RxJavaPlugins.b(th);
        }
    }
}
