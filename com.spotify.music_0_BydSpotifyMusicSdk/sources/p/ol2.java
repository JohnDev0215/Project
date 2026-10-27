package p;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;

/* JADX INFO: loaded from: classes.dex */
public final class ol2 implements View.OnTouchListener {
    public final /* synthetic */ GestureDetector c;

    public ol2(GestureDetector gestureDetector) {
        this.c = gestureDetector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (((Checkable) view).isChecked()) {
            return this.c.onTouchEvent(motionEvent);
        }
        return false;
    }
}
