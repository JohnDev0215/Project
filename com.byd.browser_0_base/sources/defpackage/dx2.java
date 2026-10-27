package defpackage;

import android.content.Context;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import org.chromium.base.ThreadUtils;
import org.chromium.components.browser_ui.widget.R$dimen;

/* JADX INFO: compiled from: SwipeGestureListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class dx2 extends GestureDetector.SimpleOnGestureListener {
    public final GestureDetector a;
    public final PointF b = new PointF();
    public int c = 0;
    public final a d;
    public final int e;
    public final int f;

    public dx2(Context context, a aVar) {
        this.a = new GestureDetector(context, this, ThreadUtils.c());
        this.e = context.getResources().getDimensionPixelOffset(R$dimen.swipe_vertical_drag_threshold);
        this.f = context.getResources().getDimensionPixelOffset(R$dimen.swipe_horizontal_drag_threshold);
        this.d = aVar;
    }

    public final boolean a(MotionEvent motionEvent) {
        boolean zOnTouchEvent = this.a.onTouchEvent(motionEvent);
        a aVar = this.d;
        if (aVar == null) {
            return zOnTouchEvent;
        }
        int action = motionEvent.getAction();
        if ((action != 1 && action != 3) || this.c == 0) {
            return zOnTouchEvent;
        }
        aVar.k();
        this.c = 0;
        return true;
    }

    public boolean b(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        a aVar = this.d;
        if (aVar == null || this.c == 0) {
            return false;
        }
        float rawX = motionEvent2.getRawX();
        PointF pointF = this.b;
        aVar.f(motionEvent2, rawX - pointF.x, motionEvent2.getRawY() - pointF.y, f, f2);
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        a aVar = this.d;
        if (aVar != null && motionEvent != null && motionEvent2 != null) {
            int i = this.c;
            PointF pointF = this.b;
            if (i == 0 && b(motionEvent)) {
                float rawX = motionEvent2.getRawX() - motionEvent.getRawX();
                float rawY = motionEvent2.getRawY() - motionEvent.getRawY();
                if (Math.abs(rawX) < this.f && Math.abs(rawY) < this.e) {
                    return false;
                }
                int i2 = Math.abs(rawX) > Math.abs(rawY) ? rawX > 0.0f ? 2 : 1 : rawY > 0.0f ? 4 : 3;
                if (i2 != 0 && aVar.q(i2)) {
                    this.c = i2;
                    aVar.v(i2);
                    pointF.set(motionEvent2.getRawX(), motionEvent2.getRawY());
                }
            }
            if (this.c != 0) {
                this.d.h(motionEvent2, motionEvent2.getRawX() - pointF.x, motionEvent2.getRawY() - pointF.y, -f, -f2);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: compiled from: SwipeGestureListener.java */
    public interface a {
        default boolean q(int i) {
            return true;
        }

        default void k() {
        }

        default void v(int i) {
        }

        default void f(MotionEvent motionEvent, float f, float f2, float f3, float f4) {
        }

        default void h(MotionEvent motionEvent, float f, float f2, float f3, float f4) {
        }
    }
}
