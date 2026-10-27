package defpackage;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: compiled from: CompositeTouchDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cw extends TouchDelegate {
    public final ArrayList a;

    public cw(View view) {
        super(new Rect(), view);
        this.a = new ArrayList();
    }

    @Override // android.view.TouchDelegate
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        for (TouchDelegate touchDelegate : this.a) {
            motionEvent.setLocation(x, y);
            if (touchDelegate.onTouchEvent(motionEvent)) {
                return true;
            }
        }
        return false;
    }
}
