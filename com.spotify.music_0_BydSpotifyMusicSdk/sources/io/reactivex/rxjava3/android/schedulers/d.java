package io.reactivex.rxjava3.android.schedulers;

import android.os.Handler;
import android.os.Message;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class d extends Scheduler.Worker {
    public final Handler c;
    public final boolean e;
    public volatile boolean k;

    public d(Handler handler, boolean z) {
        this.c = handler;
        this.e = z;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler.Worker
    public final Disposable b(Runnable runnable, long j, TimeUnit timeUnit) {
        if (runnable == null) {
            throw new NullPointerException("run == null");
        }
        if (timeUnit == null) {
            throw new NullPointerException("unit == null");
        }
        boolean z = this.k;
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        if (z) {
            return emptyDisposable;
        }
        Handler handler = this.c;
        e eVar = new e(handler, runnable);
        Message messageObtain = Message.obtain(handler, eVar);
        messageObtain.obj = this;
        if (this.e) {
            messageObtain.setAsynchronous(true);
        }
        this.c.sendMessageDelayed(messageObtain, timeUnit.toMillis(j));
        if (!this.k) {
            return eVar;
        }
        this.c.removeCallbacks(eVar);
        return emptyDisposable;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.k = true;
        this.c.removeCallbacksAndMessages(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.k;
    }
}
