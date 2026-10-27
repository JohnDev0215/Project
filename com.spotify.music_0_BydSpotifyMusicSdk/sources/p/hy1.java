package p;

import android.view.Choreographer;

/* JADX INFO: loaded from: classes.dex */
public abstract class hy1 {
    public static void a(final Runnable runnable) {
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() { // from class: p.gy1
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                runnable.run();
            }
        });
    }
}
