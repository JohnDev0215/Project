package org.chromium.ui.base;

import J.N;
import android.view.MotionEvent;
import defpackage.z7;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class EventForwarder {
    public final boolean a;
    public long b;
    public float c;
    public float d;
    public int e;
    public int f;
    public a g;

    public interface a {
    }

    public EventForwarder(long j, boolean z) {
        this.b = j;
        this.a = z;
    }

    @CalledByNative
    public static EventForwarder create(long j, boolean z) {
        return new EventForwarder(j, z);
    }

    public final MotionEvent a(MotionEvent motionEvent) {
        if (!b()) {
            return motionEvent;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(this.c, this.d);
        return motionEventObtain;
    }

    public final boolean b() {
        return (this.c == 0.0f && this.d == 0.0f) ? false : true;
    }

    public final void c(int i, long j, float f) {
        long j2 = this.b;
        if (j2 == 0) {
            return;
        }
        N.MtyC4Bd2(j2, this, i, j, f);
    }

    public final void d(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 1) {
            return;
        }
        N.M$2oj6EQ(this.b, this, z7.s(motionEvent), actionMasked, motionEvent.getX(), motionEvent.getY(), motionEvent.getPointerId(0), motionEvent.getPressure(0), motionEvent.getOrientation(0), motionEvent.getAxisValue(25, 0), motionEvent.getActionButton(), motionEvent.getButtonState(), motionEvent.getMetaState(), motionEvent.getToolType(0));
    }

    @CalledByNative
    public final void destroy() {
        this.b = 0L;
    }
}
