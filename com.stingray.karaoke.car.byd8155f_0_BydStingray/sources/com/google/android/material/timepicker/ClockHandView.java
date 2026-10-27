package com.google.android.material.timepicker;

import F1.H;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.stingray.drivemodedetector.R;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
class ClockHandView extends View {
    public final ArrayList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f12962m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final float f12963n;
    public final Paint o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final RectF f12964p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f12965q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f12966r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f12967s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public double f12968t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f12969u;

    public ClockHandView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.materialClockStyle);
        this.l = new ArrayList();
        Paint paint = new Paint();
        this.o = paint;
        this.f12964p = new RectF();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, K4.a.f3587e, R.attr.materialClockStyle, R.style.Widget_MaterialComponents_TimePicker_Clock);
        this.f12969u = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.f12962m = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, 0);
        this.f12965q = getResources().getDimensionPixelSize(R.dimen.material_clock_hand_stroke_width);
        this.f12963n = r4.getDimensionPixelSize(R.dimen.material_clock_hand_center_dot_radius);
        int color = typedArrayObtainStyledAttributes.getColor(0, 0);
        paint.setAntiAlias(true);
        paint.setColor(color);
        a(0.0f);
        ViewConfiguration.get(context).getScaledTouchSlop();
        int[] iArr = H.f2303a;
        setImportantForAccessibility(2);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void a(float f10) {
        float f11 = f10 % 360.0f;
        this.f12966r = f11;
        this.f12968t = Math.toRadians(f11 - 90.0f);
        int height = getHeight() / 2;
        float fCos = (this.f12969u * ((float) Math.cos(this.f12968t))) + (getWidth() / 2);
        float fSin = (this.f12969u * ((float) Math.sin(this.f12968t))) + height;
        float f12 = this.f12962m;
        this.f12964p.set(fCos - f12, fSin - f12, fCos + f12, fSin + f12);
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            ClockFaceView clockFaceView = (ClockFaceView) ((d) it.next());
            if (Math.abs(clockFaceView.f12960Q - f11) > 0.001f) {
                clockFaceView.f12960Q = f11;
                clockFaceView.l();
            }
        }
        invalidate();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight() / 2;
        float width = getWidth() / 2;
        float fCos = (this.f12969u * ((float) Math.cos(this.f12968t))) + width;
        float f10 = height;
        float fSin = (this.f12969u * ((float) Math.sin(this.f12968t))) + f10;
        Paint paint = this.o;
        paint.setStrokeWidth(0.0f);
        canvas.drawCircle(fCos, fSin, this.f12962m, paint);
        double dSin = Math.sin(this.f12968t);
        double dCos = Math.cos(this.f12968t);
        paint.setStrokeWidth(this.f12965q);
        canvas.drawLine(width, f10, r1 + ((int) (dCos * d)), height + ((int) (d * dSin)), paint);
        canvas.drawCircle(width, f10, this.f12963n, paint);
    }

    @Override // android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        super.onLayout(z6, i7, i10, i11, i12);
        a(this.f12966r);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z6;
        boolean z10;
        int actionMasked = motionEvent.getActionMasked();
        float x8 = motionEvent.getX();
        float y10 = motionEvent.getY();
        boolean z11 = false;
        if (actionMasked == 0) {
            this.f12967s = false;
            z6 = true;
            z10 = false;
        } else if (actionMasked == 1 || actionMasked == 2) {
            z10 = this.f12967s;
            z6 = false;
        } else {
            z10 = false;
            z6 = false;
        }
        boolean z12 = this.f12967s;
        int degrees = (int) Math.toDegrees(Math.atan2(y10 - (getHeight() / 2), x8 - (getWidth() / 2)));
        int i7 = degrees + 90;
        if (i7 < 0) {
            i7 = degrees + 450;
        }
        float f10 = i7;
        boolean z13 = this.f12966r != f10;
        if (z6 && z13) {
            z11 = true;
        } else if (z13 || z10) {
            a(f10);
            z11 = true;
        }
        this.f12967s = z12 | z11;
        return true;
    }
}
