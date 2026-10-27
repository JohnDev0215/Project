package defpackage;

import J.N;
import android.view.Choreographer;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.components.viz.service.frame_sinks.ExternalBeginFrameSourceAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DN implements Choreographer.FrameCallback {
    public final /* synthetic */ FN a;

    public DN(FN fn) {
        this.a = fn;
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j) {
        TraceEvent.x("VSync", null);
        FN fn = this.a;
        if (fn.d && fn.a) {
            long j2 = j - fn.h;
            fn.c = fn.c + ((long) ((j2 - r5) * 0.1f));
        }
        FN fn2 = this.a;
        fn2.h = j;
        Objects.requireNonNull(fn2);
        System.nanoTime();
        Objects.requireNonNull(fn2);
        Boolean bool = Boolean.FALSE;
        FN.j.set(Boolean.TRUE);
        fn2.e = false;
        try {
            if (fn2.b != null) {
                long j3 = j / 1000;
                C1684vy c1684vy = (C1684vy) fn2.b;
                if (c1684vy.a.b) {
                    ExternalBeginFrameSourceAndroid externalBeginFrameSourceAndroid = c1684vy.a;
                    N.Mhc_M_H$(externalBeginFrameSourceAndroid.a, externalBeginFrameSourceAndroid, j3, externalBeginFrameSourceAndroid.c.c / 1000);
                    c1684vy.a.c.a();
                }
            }
            FN.j.set(bool);
            TraceEvent.A("VSync");
        } catch (Throwable th) {
            FN.j.set(bool);
            throw th;
        }
    }
}
