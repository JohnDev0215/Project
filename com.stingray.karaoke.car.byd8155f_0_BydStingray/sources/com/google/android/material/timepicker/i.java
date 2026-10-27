package com.google.android.material.timepicker;

import android.view.GestureDetector;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
public final class i extends GestureDetector.SimpleOnGestureListener {
    public final /* synthetic */ TimePickerView l;

    public i(TimePickerView timePickerView) {
        this.l = timePickerView;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public final boolean onDoubleTap(MotionEvent motionEvent) {
        boolean zOnDoubleTap = super.onDoubleTap(motionEvent);
        int i7 = TimePickerView.f12970C;
        this.l.getClass();
        return zOnDoubleTap;
    }
}
