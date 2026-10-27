package A2;

import android.content.Context;
import android.graphics.PointF;
import android.opengl.Matrix;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class p extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener, d {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final n f366n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final GestureDetector f367p;
    public final PointF l = new PointF();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final PointF f365m = new PointF();
    public final float o = 25.0f;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public volatile float f368q = 3.1415927f;

    public p(Context context, n nVar) {
        this.f366n = nVar;
        this.f367p = new GestureDetector(context, this);
    }

    @Override // A2.d
    public final void a(float[] fArr, float f10) {
        this.f368q = -f10;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onDown(MotionEvent motionEvent) {
        this.l.set(motionEvent.getX(), motionEvent.getY());
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f10, float f11) {
        float x8 = (motionEvent2.getX() - this.l.x) / this.o;
        float y10 = motionEvent2.getY();
        PointF pointF = this.l;
        float f12 = (y10 - pointF.y) / this.o;
        pointF.set(motionEvent2.getX(), motionEvent2.getY());
        double d = this.f368q;
        float fCos = (float) Math.cos(d);
        float fSin = (float) Math.sin(d);
        PointF pointF2 = this.f365m;
        pointF2.x -= (fCos * x8) - (fSin * f12);
        float f13 = (fCos * f12) + (fSin * x8) + pointF2.y;
        pointF2.y = f13;
        pointF2.y = Math.max(-45.0f, Math.min(45.0f, f13));
        n nVar = this.f366n;
        PointF pointF3 = this.f365m;
        synchronized (nVar) {
            float f14 = pointF3.y;
            nVar.f351r = f14;
            Matrix.setRotateM(nVar.f349p, 0, -f14, (float) Math.cos(nVar.f352s), (float) Math.sin(nVar.f352s), 0.0f);
            Matrix.setRotateM(nVar.f350q, 0, -pointF3.x, 0.0f, 1.0f, 0.0f);
        }
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        return this.f366n.f355v.performClick();
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return this.f367p.onTouchEvent(motionEvent);
    }
}
