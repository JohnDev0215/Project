package defpackage;

import android.os.Handler;
import android.os.SystemClock;

/* JADX INFO: compiled from: ResumableDelayedTaskRunner.java */
/* JADX INFO: loaded from: classes.dex */
public final class wb2 {
    public final Handler a = new Handler();
    public Runnable b;
    public Runnable c;
    public long d;

    /* JADX INFO: compiled from: ResumableDelayedTaskRunner.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            wb2 wb2Var = wb2.this;
            wb2Var.c.run();
            wb2Var.c = null;
            wb2Var.b = null;
        }
    }

    public wb2() {
        Thread.currentThread();
    }

    public final void a() {
        if (this.c == null || this.b != null) {
            return;
        }
        long jMax = Math.max(this.d - SystemClock.elapsedRealtime(), 0L);
        a aVar = new a();
        this.b = aVar;
        this.a.postDelayed(aVar, jMax);
    }
}
