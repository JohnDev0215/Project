package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;

/* JADX INFO: compiled from: TimePickerView.java */
/* JADX INFO: loaded from: classes.dex */
public final class ra3 implements View.OnTouchListener {
    public final /* synthetic */ GestureDetector e;

    public ra3(GestureDetector gestureDetector) {
        this.e = gestureDetector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (((Checkable) view).isChecked()) {
            return this.e.onTouchEvent(motionEvent);
        }
        return false;
    }
}
