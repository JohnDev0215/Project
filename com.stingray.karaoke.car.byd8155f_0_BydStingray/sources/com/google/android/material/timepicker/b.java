package com.google.android.material.timepicker;

import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final class b implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ ClockFaceView l;

    public b(ClockFaceView clockFaceView) {
        this.l = clockFaceView;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        ClockFaceView clockFaceView = this.l;
        if (!clockFaceView.isShown()) {
            return true;
        }
        clockFaceView.getViewTreeObserver().removeOnPreDrawListener(this);
        int height = ((clockFaceView.getHeight() / 2) - clockFaceView.f12948E.f12962m) - clockFaceView.f12955L;
        if (height != clockFaceView.f12971C) {
            clockFaceView.f12971C = height;
            clockFaceView.k();
            int i7 = clockFaceView.f12971C;
            ClockHandView clockHandView = clockFaceView.f12948E;
            clockHandView.f12969u = i7;
            clockHandView.invalidate();
        }
        return true;
    }
}
