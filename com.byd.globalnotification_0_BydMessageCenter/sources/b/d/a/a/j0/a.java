package b.d.a.a.j0;

import android.view.ViewTreeObserver;
import com.google.android.material.timepicker.ClockFaceView;
import com.google.android.material.timepicker.ClockHandView;

/* JADX INFO: loaded from: classes.dex */
public class a implements ViewTreeObserver.OnPreDrawListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ClockFaceView f1956a;

    public a(ClockFaceView clockFaceView) {
        this.f1956a = clockFaceView;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        if (!this.f1956a.isShown()) {
            return true;
        }
        this.f1956a.getViewTreeObserver().removeOnPreDrawListener(this);
        int height = this.f1956a.getHeight() / 2;
        ClockFaceView clockFaceView = this.f1956a;
        int i = (height - clockFaceView.x.g) - clockFaceView.E;
        if (i != clockFaceView.v) {
            clockFaceView.v = i;
            clockFaceView.m();
            ClockHandView clockHandView = clockFaceView.x;
            clockHandView.o = clockFaceView.v;
            clockHandView.invalidate();
        }
        return true;
    }
}
