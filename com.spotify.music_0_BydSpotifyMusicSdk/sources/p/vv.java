package p;

import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class vv implements Executor, ViewTreeObserver.OnDrawListener, Runnable {
    public Runnable e;
    public final /* synthetic */ androidx.activity.a l;
    public final long c = SystemClock.uptimeMillis() + 10000;
    public boolean k = false;

    public vv(zw0 zw0Var) {
        this.l = zw0Var;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.e = runnable;
        View decorView = this.l.getWindow().getDecorView();
        if (!this.k) {
            decorView.postOnAnimation(new nv(1, this));
        } else if (Looper.myLooper() == Looper.getMainLooper()) {
            decorView.invalidate();
        } else {
            decorView.postInvalidate();
        }
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public final void onDraw() {
        boolean z;
        Runnable runnable = this.e;
        if (runnable == null) {
            if (SystemClock.uptimeMillis() > this.c) {
                this.k = false;
                this.l.getWindow().getDecorView().post(this);
                return;
            }
            return;
        }
        runnable.run();
        this.e = null;
        oy0 oy0Var = this.l.q;
        synchronized (oy0Var.a) {
            z = oy0Var.b;
        }
        if (z) {
            this.k = false;
            this.l.getWindow().getDecorView().post(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.l.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
    }
}
