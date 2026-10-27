package F0;

import android.view.Choreographer;
import java.util.ArrayList;

/* JADX INFO: renamed from: F0.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ChoreographerFrameCallbackC0202f0 implements Choreographer.FrameCallback, Runnable {
    public final /* synthetic */ C0204g0 l;

    public ChoreographerFrameCallbackC0202f0(C0204g0 c0204g0) {
        this.l = c0204g0;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j3) {
        this.l.o.removeCallbacks(this);
        C0204g0.P(this.l);
        C0204g0 c0204g0 = this.l;
        synchronized (c0204g0.f2197p) {
            if (c0204g0.f2202u) {
                c0204g0.f2202u = false;
                ArrayList arrayList = c0204g0.f2199r;
                c0204g0.f2199r = c0204g0.f2200s;
                c0204g0.f2200s = arrayList;
                int size = arrayList.size();
                for (int i7 = 0; i7 < size; i7++) {
                    ((Choreographer.FrameCallback) arrayList.get(i7)).doFrame(j3);
                }
                arrayList.clear();
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0204g0.P(this.l);
        C0204g0 c0204g0 = this.l;
        synchronized (c0204g0.f2197p) {
            if (c0204g0.f2199r.isEmpty()) {
                c0204g0.f2196n.removeFrameCallback(this);
                c0204g0.f2202u = false;
            }
        }
    }
}
