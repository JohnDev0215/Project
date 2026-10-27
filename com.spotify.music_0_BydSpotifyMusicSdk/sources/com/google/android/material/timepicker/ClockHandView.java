package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.spotify.music.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import p.it2;
import p.xz1;
import p.zt;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
class ClockHandView extends View {
    public final ArrayList c;
    public final int e;
    public final float k;
    public final Paint l;
    public final RectF m;
    public final int n;
    public float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f36p;
    public double q;
    public int r;

    public ClockHandView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.materialClockStyle);
        this.c = new ArrayList();
        Paint paint = new Paint();
        this.l = paint;
        this.m = new RectF();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, xz1.e, R.attr.materialClockStyle, R.style.Widget_MaterialComponents_TimePicker_Clock);
        this.r = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.e = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, 0);
        this.n = getResources().getDimensionPixelSize(R.dimen.material_clock_hand_stroke_width);
        this.k = r4.getDimensionPixelSize(R.dimen.material_clock_hand_center_dot_radius);
        int color = typedArrayObtainStyledAttributes.getColor(0, 0);
        paint.setAntiAlias(true);
        paint.setColor(color);
        a(0.0f);
        ViewConfiguration.get(context).getScaledTouchSlop();
        WeakHashMap weakHashMap = zt2.a;
        it2.s(this, 2);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void a(float f) {
        b(f);
    }

    public final void b(float f) {
        float f2 = f % 360.0f;
        this.o = f2;
        this.q = Math.toRadians(f2 - 90.0f);
        int height = getHeight() / 2;
        float fCos = (this.r * ((float) Math.cos(this.q))) + (getWidth() / 2);
        float fSin = (this.r * ((float) Math.sin(this.q))) + height;
        float f3 = this.e;
        this.m.set(fCos - f3, fSin - f3, fCos + f3, fSin + f3);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ClockFaceView clockFaceView = (ClockFaceView) ((zt) it.next());
            if (Math.abs(clockFaceView.N - f2) > 0.001f) {
                clockFaceView.N = f2;
                clockFaceView.o();
            }
        }
        invalidate();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight() / 2;
        float width = getWidth() / 2;
        float fCos = (this.r * ((float) Math.cos(this.q))) + width;
        float f = height;
        float fSin = (this.r * ((float) Math.sin(this.q))) + f;
        Paint paint = this.l;
        paint.setStrokeWidth(0.0f);
        canvas.drawCircle(fCos, fSin, this.e, paint);
        double dSin = Math.sin(this.q);
        double dCos = Math.cos(this.q);
        paint.setStrokeWidth(this.n);
        canvas.drawLine(width, f, r1 + ((int) (dCos * d)), height + ((int) (d * dSin)), paint);
        canvas.drawCircle(width, f, this.k, paint);
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a(this.o);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        int actionMasked = motionEvent.getActionMasked();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        boolean z3 = false;
        if (actionMasked == 0) {
            this.f36p = false;
            z = true;
            z2 = false;
        } else if (actionMasked == 1 || actionMasked == 2) {
            z2 = this.f36p;
            z = false;
        } else {
            z2 = false;
            z = false;
        }
        boolean z4 = this.f36p;
        int degrees = (int) Math.toDegrees(Math.atan2(y - (getHeight() / 2), x - (getWidth() / 2)));
        int i = degrees + 90;
        if (i < 0) {
            i = degrees + 450;
        }
        float f = i;
        boolean z5 = this.o != f;
        if (z && z5) {
            z3 = true;
        } else if (z5 || z2) {
            a(f);
            z3 = true;
        }
        this.f36p = z4 | z3;
        return true;
    }
}
