package org.chromium.chrome.browser.layouts;

import android.content.Context;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
public abstract class EventFilter {
    public final float a;
    public final boolean b;
    public float c;
    public float d;

    public EventFilter(Context context) {
        this(context, true);
    }

    public final boolean a(MotionEvent motionEvent, boolean z) {
        MotionEvent motionEventObtain;
        if (!this.b || (this.c == 0.0f && this.d == 0.0f)) {
            motionEventObtain = motionEvent;
        } else {
            motionEventObtain = MotionEvent.obtain(motionEvent);
            motionEventObtain.offsetLocation(this.c, this.d);
        }
        boolean zB = b(motionEventObtain);
        if (motionEventObtain != motionEvent) {
            motionEventObtain.recycle();
        }
        return zB;
    }

    public abstract boolean b(MotionEvent motionEvent);

    public abstract void c(MotionEvent motionEvent);

    public EventFilter(Context context, boolean z) {
        this.a = 1.0f / context.getResources().getDisplayMetrics().density;
        this.b = z;
    }
}
