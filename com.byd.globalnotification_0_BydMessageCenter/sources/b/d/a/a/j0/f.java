package b.d.a.a.j0;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import com.google.android.material.timepicker.TimePickerView;

/* JADX INFO: loaded from: classes.dex */
public class f implements View.OnTouchListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ GestureDetector f1961a;

    public f(TimePickerView timePickerView, GestureDetector gestureDetector) {
        this.f1961a = gestureDetector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (((Checkable) view).isChecked()) {
            return this.f1961a.onTouchEvent(motionEvent);
        }
        return false;
    }
}
