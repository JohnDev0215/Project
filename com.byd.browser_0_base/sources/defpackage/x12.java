package defpackage;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;
import java.util.ArrayList;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;

/* JADX INFO: compiled from: PlayerFrameGestureDetector.java */
/* JADX INFO: loaded from: classes3.dex */
public final class x12 implements GestureDetector.OnGestureListener, ScaleGestureDetector.OnScaleGestureListener {
    public final GestureDetector a;
    public final ScaleGestureDetector b;
    public final boolean c;
    public final gq0 d;
    public x12 e;
    public float f;
    public float g;

    public x12(Context context, boolean z, gq0 gq0Var) {
        this.a = new GestureDetector(context, this);
        this.b = new ScaleGestureDetector(context, this);
        this.c = z;
        this.d = gq0Var;
    }

    public final boolean a(MotionEvent motionEvent) {
        TraceEvent.q("PlayerFrameGestureDetector.onTouchEvent", null);
        if (this.c) {
            this.b.onTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 1) {
            c22 c22Var = (c22) this.d.b;
            qt1 qt1Var = c22Var.a;
            if (qt1Var != null && c22Var.b) {
                ((j22) qt1Var).a.d(true);
                c22Var.b = false;
                c22Var.c = 0.0f;
            }
            x12 x12Var = this.e;
            if (x12Var != null) {
                x12Var.a(motionEvent);
            }
        }
        boolean zOnTouchEvent = this.a.onTouchEvent(motionEvent);
        TraceEvent.C("PlayerFrameGestureDetector.onTouchEvent");
        return zOnTouchEvent;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        boolean z;
        Runnable runnable;
        c22 c22Var = (c22) this.d.b;
        if (c22Var.k) {
            e22 e22Var = c22Var.f;
            float fB = e22Var.b();
            Size size = c22Var.g;
            int width = (int) (size.getWidth() * fB);
            int height = (int) (size.getHeight() * fB);
            OverScroller overScroller = c22Var.d;
            overScroller.forceFinished(true);
            Rect rectA = e22Var.a();
            c22Var.d.fling(rectA.left, rectA.top, (int) (-f), (int) (-f2), 0, width - rectA.width(), 0, height - rectA.height());
            if (!overScroller.isFinished() && (runnable = c22Var.j) != null) {
                runnable.run();
            }
            c22Var.e.post(new i22(2, c22Var));
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        x12 x12Var = this.e;
        if (x12Var != null) {
            return x12Var.onFling(motionEvent, motionEvent2, f, f2);
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final void onLongPress(MotionEvent motionEvent) {
        motionEvent.getX();
        motionEvent.getY();
        v72 v72Var = ((y12) ((d22) this.d.c)).q.c;
        if (v72Var != null) {
            ((Runnable) v72Var.a).run();
        }
        HashMap map = k22.a;
        n3.X0("PaintPreview.Player.LongPress");
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public final boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        nw2<Boolean> nw2Var;
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        float focusX = scaleGestureDetector.getFocusX();
        float focusY = scaleGestureDetector.getFocusY();
        b22 b22Var = (b22) this.d.a;
        if (!b22Var.h || ((nw2Var = b22Var.f) != null && nw2Var.get().booleanValue())) {
            return false;
        }
        float f = b22Var.a;
        z12 z12Var = b22Var.e;
        e22 e22Var = b22Var.b;
        if (f == 0.0f) {
            b22Var.a = e22Var.b();
            ((y12) z12Var).b();
        }
        y12 y12Var = (y12) z12Var;
        float f2 = y12Var.m;
        float f3 = b22Var.a;
        float f4 = f3 * scaleFactor;
        b22Var.a = f4;
        if (f4 < f2 && f3 > f2) {
            scaleFactor = f2 / f3;
        } else if (f4 > 5.0f && f3 < 5.0f) {
            scaleFactor = 5.0f / f3;
        } else if (f4 > f2 && f3 < f2) {
            scaleFactor = f4 / f2;
        } else {
            if (f4 >= 5.0f || f3 <= 5.0f) {
                if (f4 >= f2 && f3 <= 5.0f) {
                }
                return true;
            }
            scaleFactor = f4 / 5.0f;
        }
        float f5 = f3 * scaleFactor;
        e22Var.b.postScale(scaleFactor, scaleFactor, -focusX, -focusY);
        Matrix matrix = b22Var.d;
        matrix.postScale(scaleFactor, scaleFactor, focusX, focusY);
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        float fC = e22Var.c();
        float fD = e22Var.d();
        Size size = b22Var.c;
        float fMax = Math.max(0.0f, Math.min(fC, (size.getWidth() * f5) - e22Var.a.getWidth()));
        float fMax2 = Math.max(0.0f, Math.min(fD, (size.getHeight() * f5) - e22Var.a.getHeight()));
        if (fC != fMax || fD != fMax2) {
            e22Var.g(fMax, fMax2);
            fArr[2] = fArr[2] + (fC - fMax);
            fArr[5] = fArr[5] + (fD - fMax2);
            matrix.setValues(fArr);
        }
        y12Var.i(e22Var.a(), e22Var.b());
        y12Var.d(f5, matrix);
        Callback<Boolean> callback = b22Var.g;
        if (callback != null) {
            callback.onResult(Boolean.FALSE);
        }
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public final boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public final void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        ArrayList arrayList;
        scaleGestureDetector.getScaleFactor();
        scaleGestureDetector.getFocusX();
        scaleGestureDetector.getFocusY();
        b22 b22Var = (b22) this.d.a;
        float fB = b22Var.b.b();
        y12 y12Var = (y12) b22Var.e;
        int i = 0;
        int i2 = 0;
        while (true) {
            int size = y12Var.c.size();
            arrayList = y12Var.e;
            if (i2 >= size) {
                break;
            }
            ((y12) arrayList.get(i2)).g(fB);
            i2++;
        }
        y12Var.j(true);
        while (true) {
            ArrayList arrayList2 = y12Var.c;
            if (i >= arrayList2.size()) {
                break;
            }
            if (((View) arrayList2.get(i)).getVisibility() == 0) {
                ((y12) arrayList.get(i)).a();
            }
            i++;
        }
        b22Var.a = 0.0f;
        Callback<Boolean> callback = b22Var.g;
        if (callback != null) {
            callback.onResult(Boolean.TRUE);
        }
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        Runnable runnable;
        c22 c22Var = (c22) this.d.b;
        c22Var.d.forceFinished(true);
        boolean zB = c22Var.b(f, f2);
        if (zB && (runnable = c22Var.i) != null) {
            runnable.run();
        }
        if (zB) {
            this.f = 0.0f;
            this.g = 0.0f;
            return true;
        }
        float f3 = this.f + f;
        this.f = f3;
        float f4 = this.g + f2;
        this.g = f4;
        x12 x12Var = this.e;
        if (x12Var != null && x12Var.onScroll(motionEvent, motionEvent2, f3, f4)) {
            return true;
        }
        this.f = 0.0f;
        this.g = 0.0f;
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        ((y12) ((d22) this.d.c)).c((int) motionEvent.getX(), (int) motionEvent.getY(), false);
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public final void onShowPress(MotionEvent motionEvent) {
    }
}
