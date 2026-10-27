package com.google.android.material.timepicker;

import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final class b implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ ClockFaceView c;

    public b(ClockFaceView clockFaceView) {
        this.c = clockFaceView;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        ClockFaceView clockFaceView = this.c;
        if (!clockFaceView.isShown()) {
            return true;
        }
        clockFaceView.getViewTreeObserver().removeOnPreDrawListener(this);
        int height = ((clockFaceView.getHeight() / 2) - clockFaceView.B.e) - clockFaceView.I;
        if (height != clockFaceView.z) {
            clockFaceView.z = height;
            clockFaceView.n();
            int i = clockFaceView.z;
            ClockHandView clockHandView = clockFaceView.B;
            clockHandView.r = i;
            clockHandView.invalidate();
        }
        return true;
    }
}
