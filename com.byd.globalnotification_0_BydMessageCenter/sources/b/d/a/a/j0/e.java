package b.d.a.a.j0;

import android.view.GestureDetector;
import android.view.MotionEvent;
import com.google.android.material.timepicker.TimePickerView;

/* JADX INFO: loaded from: classes.dex */
public class e extends GestureDetector.SimpleOnGestureListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ TimePickerView f1960a;

    public e(TimePickerView timePickerView) {
        this.f1960a = timePickerView;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        boolean zOnDoubleTap = super.onDoubleTap(motionEvent);
        TimePickerView.b bVar = this.f1960a.A;
        if (bVar != null) {
            bVar.a();
        }
        return zOnDoubleTap;
    }
}
