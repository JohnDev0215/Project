package z2;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.Choreographer;
import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class q implements Choreographer.FrameCallback, Handler.Callback {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final q f24352p = new q();
    public volatile long l = -9223372036854775807L;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Handler f24353m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Choreographer f24354n;
    public int o;

    public q() {
        HandlerThread handlerThread = new HandlerThread("ExoPlayer:FrameReleaseChoreographer");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        int i7 = AbstractC1343A.f15195a;
        Handler handler = new Handler(looper, this);
        this.f24353m = handler;
        handler.sendEmptyMessage(1);
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j3) {
        this.l = j3;
        Choreographer choreographer = this.f24354n;
        choreographer.getClass();
        choreographer.postFrameCallbackDelayed(this, 500L);
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i7 = message.what;
        if (i7 == 1) {
            try {
                this.f24354n = Choreographer.getInstance();
            } catch (RuntimeException e10) {
                AbstractC1346c.A("VideoFrameReleaseHelper", "Vsync sampling disabled due to platform error", e10);
            }
            return true;
        }
        if (i7 == 2) {
            Choreographer choreographer = this.f24354n;
            if (choreographer != null) {
                int i10 = this.o + 1;
                this.o = i10;
                if (i10 == 1) {
                    choreographer.postFrameCallback(this);
                }
            }
            return true;
        }
        if (i7 != 3) {
            return false;
        }
        Choreographer choreographer2 = this.f24354n;
        if (choreographer2 != null) {
            int i11 = this.o - 1;
            this.o = i11;
            if (i11 == 0) {
                choreographer2.removeFrameCallback(this);
                this.l = -9223372036854775807L;
            }
        }
        return true;
    }
}
