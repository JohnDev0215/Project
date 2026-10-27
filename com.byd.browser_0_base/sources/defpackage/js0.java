package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;

/* JADX INFO: compiled from: GestureEventFilter.java */
/* JADX INFO: loaded from: classes.dex */
public final class js0 extends GestureDetector.SimpleOnGestureListener {
    public float a;
    public float b;
    public final /* synthetic */ ks0 c;

    public js0(ks0 ks0Var) {
        this.c = ks0Var;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onDown(MotionEvent motionEvent) {
        int buttonState = motionEvent.getButtonState();
        ks0 ks0Var = this.c;
        ks0Var.m = buttonState;
        ks0Var.k = false;
        ks0Var.l = false;
        if (ks0Var.j) {
            ks0Var.g.l(ks0Var.m, motionEvent.getX() * ks0Var.a, motionEvent.getY() * ks0Var.a, motionEvent.getToolType(0) == 3);
        }
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        ks0 ks0Var = this.c;
        if (!ks0Var.j) {
            return true;
        }
        motionEvent.getX();
        motionEvent.getY();
        float f3 = ks0Var.a;
        ks0Var.g.t(f * f3, f2 * f3);
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final void onLongPress(MotionEvent motionEvent) {
        ks0 ks0Var = this.c;
        if (ks0Var.j) {
            ks0Var.k = true;
            float x = motionEvent.getX();
            float f = ks0Var.a;
            float y = motionEvent.getY() * f;
            ks0Var.g.r(x * f, y);
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        ks0 ks0Var = this.c;
        if (!ks0Var.l) {
            ks0Var.l = true;
            float fSqrt = (float) Math.sqrt((f2 * f2) + (f * f));
            if (fSqrt > 0.0f) {
                float fMax = Math.max(0.0f, fSqrt - ks0Var.i) / fSqrt;
                float f3 = 1.0f - fMax;
                this.a = (f * f3) + motionEvent.getX();
                this.b = (f3 * f2) + motionEvent.getY();
                f *= fMax;
            }
        }
        if (ks0Var.j) {
            float x = motionEvent2.getX() - this.a;
            float y = motionEvent2.getY() - this.b;
            ls0 ls0Var = ks0Var.g;
            float x2 = motionEvent2.getX();
            float f4 = ks0Var.a;
            ls0Var.s(f4 * x2, motionEvent2.getY() * f4, (-f) * f4, x * f4, y * f4);
        }
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        ks0 ks0Var = this.c;
        if (ks0Var.j && !ks0Var.k) {
            ks0Var.g.j(ks0Var.m, motionEvent.getX() * ks0Var.a, motionEvent.getY() * ks0Var.a, motionEvent.getToolType(0) == 3);
        }
        return true;
    }
}
