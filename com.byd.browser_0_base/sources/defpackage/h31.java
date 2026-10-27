package defpackage;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.TraceEvent;

/* JADX INFO: compiled from: JankReportingScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class h31 {
    public final iq0 a;
    public HandlerThread c;
    public Handler d;
    public final a b = new a();
    public final AtomicBoolean e = new AtomicBoolean(false);

    /* JADX INFO: compiled from: JankReportingScheduler.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            h31 h31Var = h31.this;
            h31Var.a(1);
            if (h31Var.e.get()) {
                h31Var.c(1);
                h31Var.b().postDelayed(h31Var.b, 30000L);
            }
        }
    }

    public h31(iq0 iq0Var) {
        this.a = iq0Var;
    }

    public final void a(int i) {
        TraceEvent.F(((long) i) + 84186319646187624L, "JankCUJ:".concat(n3.c1(i)));
        b().post(new g31(this.a, i, false));
    }

    public final Handler b() {
        if (this.d == null) {
            HandlerThread handlerThread = new HandlerThread("Jank-Tracker");
            this.c = handlerThread;
            handlerThread.start();
            this.d = new Handler(this.c.getLooper());
        }
        return this.d;
    }

    public final void c(int i) {
        TraceEvent.I0(((long) i) + 84186319646187624L, "JankCUJ:".concat(n3.c1(i)));
        b().post(new g31(this.a, i, true));
    }
}
