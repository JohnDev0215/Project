package h;

import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: h.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeObserverOnDrawListenerC1338j implements ViewTreeObserver.OnDrawListener, Runnable, Executor {
    public final long l = SystemClock.uptimeMillis() + ((long) 10000);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Runnable f15100m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f15101n;
    public final /* synthetic */ n o;

    public ViewTreeObserverOnDrawListenerC1338j(n nVar) {
        this.o = nVar;
    }

    public final void a(View view) {
        if (this.f15101n) {
            return;
        }
        this.f15101n = true;
        view.getViewTreeObserver().addOnDrawListener(this);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        A7.m.f(runnable, "runnable");
        this.f15100m = runnable;
        View decorView = this.o.getWindow().getDecorView();
        A7.m.e(decorView, "window.decorView");
        if (!this.f15101n) {
            decorView.postOnAnimation(new A2.l(this, 12));
        } else if (A7.m.a(Looper.myLooper(), Looper.getMainLooper())) {
            decorView.invalidate();
        } else {
            decorView.postInvalidate();
        }
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public final void onDraw() {
        boolean z6;
        Runnable runnable = this.f15100m;
        if (runnable == null) {
            if (SystemClock.uptimeMillis() > this.l) {
                this.f15101n = false;
                this.o.getWindow().getDecorView().post(this);
                return;
            }
            return;
        }
        runnable.run();
        this.f15100m = null;
        o oVar = (o) this.o.f15120r.getValue();
        synchronized (oVar.f15130b) {
            z6 = oVar.c;
        }
        if (z6) {
            this.f15101n = false;
            this.o.getWindow().getDecorView().post(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.o.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
    }
}
