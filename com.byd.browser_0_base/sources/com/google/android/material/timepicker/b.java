package com.google.android.material.timepicker;

import android.view.GestureDetector;
import android.view.MotionEvent;

/* JADX INFO: compiled from: TimePickerView.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends GestureDetector.SimpleOnGestureListener {
    public final /* synthetic */ TimePickerView a;

    public b(TimePickerView timePickerView) {
        this.a = timePickerView;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public final boolean onDoubleTap(MotionEvent motionEvent) {
        int i = TimePickerView.w;
        this.a.getClass();
        return false;
    }
}
