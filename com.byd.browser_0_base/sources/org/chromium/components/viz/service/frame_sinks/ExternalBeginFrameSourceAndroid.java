package org.chromium.components.viz.service.frame_sinks;

import J.N;
import android.view.Choreographer;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class ExternalBeginFrameSourceAndroid implements Choreographer.FrameCallback {
    public boolean a;
    public boolean b;
    public long c;
    public boolean d;
    public boolean e;
    public final Choreographer f;
    public long g;
    public final long h;
    public boolean i;

    @CalledByNative
    public ExternalBeginFrameSourceAndroid(long j, float f) {
        updateRefreshRate(f);
        this.f = Choreographer.getInstance();
        this.g = System.nanoTime();
        this.h = j;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        TraceEvent.q("VSync", null);
        try {
            if (this.d && this.a) {
                long j2 = j - this.g;
                this.c = this.c + ((long) ((j2 - r4) * 0.1f));
            }
            this.g = j;
            this.b = true;
            this.e = false;
            if (this.i) {
                N.Mhc_M_H$(this.h, this, j / 1000, this.c / 1000);
                if (!this.e) {
                    this.e = true;
                    this.a = this.b;
                    this.f.postFrameCallback(this);
                }
            }
        } finally {
            this.b = false;
            TraceEvent.C("VSync");
        }
    }

    @CalledByNative
    public final void setEnabled(boolean z) {
        if (this.i == z) {
            return;
        }
        this.i = z;
        if (!z || this.e) {
            return;
        }
        this.e = true;
        this.a = this.b;
        this.f.postFrameCallback(this);
    }

    @CalledByNative
    public final void updateRefreshRate(float f) {
        this.d = f < 30.0f;
        if (f <= 0.0f) {
            f = 60.0f;
        }
        this.c = (long) (1.0E9f / f);
    }
}
