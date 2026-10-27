package com.google.android.material.timepicker;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;

/* JADX INFO: loaded from: classes.dex */
public final class j implements View.OnTouchListener {
    public final /* synthetic */ GestureDetector l;

    public j(GestureDetector gestureDetector) {
        this.l = gestureDetector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (((Checkable) view).isChecked()) {
            return this.l.onTouchEvent(motionEvent);
        }
        return false;
    }
}
