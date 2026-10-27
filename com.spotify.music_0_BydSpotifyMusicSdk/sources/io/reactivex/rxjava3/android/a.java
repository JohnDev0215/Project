package io.reactivex.rxjava3.android;

import android.os.Looper;
import io.reactivex.rxjava3.android.schedulers.c;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;
import p.nv;
import p.pn;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements Disposable {
    public final AtomicBoolean c = new AtomicBoolean();

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        if (this.c.compareAndSet(false, true)) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                c.a().c(new nv(14, this));
                return;
            }
            pn pnVar = (pn) this;
            pnVar.k.unregisterReceiver(pnVar.l);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return this.c.get();
    }
}
