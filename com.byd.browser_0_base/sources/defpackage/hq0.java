package defpackage;

import android.view.FrameMetrics;
import android.view.Window;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: FrameMetricsListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class hq0 implements Window.OnFrameMetricsAvailableListener {
    public final iq0 a;
    public final AtomicBoolean b = new AtomicBoolean(false);

    public hq0(iq0 iq0Var) {
        this.a = iq0Var;
    }

    @Override // android.view.Window.OnFrameMetricsAvailableListener
    public final void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i) {
        if (this.b.get()) {
            long metric = frameMetrics.getMetric(8);
            long metric2 = frameMetrics.getMetric(10);
            iq0 iq0Var = this.a;
            synchronized (iq0Var.a) {
                if (iq0Var.e.isEmpty()) {
                    return;
                }
                iq0Var.b.add(Long.valueOf(metric2));
                iq0Var.c.add(Long.valueOf(metric));
                iq0Var.d.add(Integer.valueOf(i));
            }
        }
    }
}
