package com.google.android.material.timepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.in3;
import defpackage.om3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class ClockHandView extends View {
    public final ArrayList e;
    public final int f;
    public final float g;
    public final Paint h;
    public final RectF i;
    public final int j;
    public float k;
    public boolean l;
    public double m;
    public int n;

    public interface a {
    }

    public interface b {
        void a(float f);
    }

    public ClockHandView(Context context) {
        this(context, null);
    }

    public final void a(float f) {
        b(false, f);
    }

    public void addOnRotateListener(b bVar) {
        this.e.add(bVar);
    }

    public final void b(boolean z, float f) {
        float f2 = f % 360.0f;
        this.k = f2;
        this.m = Math.toRadians(f2 - 90.0f);
        int height = getHeight() / 2;
        float fCos = (this.n * ((float) Math.cos(this.m))) + (getWidth() / 2);
        float fSin = (this.n * ((float) Math.sin(this.m))) + height;
        float f3 = this.f;
        this.i.set(fCos - f3, fSin - f3, fCos + f3, fSin + f3);
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((b) it.next()).a(f2);
        }
        invalidate();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight() / 2;
        float width = getWidth() / 2;
        float fCos = (this.n * ((float) Math.cos(this.m))) + width;
        float f = height;
        float fSin = (this.n * ((float) Math.sin(this.m))) + f;
        Paint paint = this.h;
        paint.setStrokeWidth(0.0f);
        canvas.drawCircle(fCos, fSin, this.f, paint);
        double dSin = Math.sin(this.m);
        double dCos = Math.cos(this.m);
        paint.setStrokeWidth(this.j);
        canvas.drawLine(width, f, r1 + ((int) (dCos * d)), height + ((int) (d * dSin)), paint);
        canvas.drawCircle(width, f, this.g, paint);
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a(this.k);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        int actionMasked = motionEvent.getActionMasked();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        boolean z3 = false;
        if (actionMasked == 0) {
            this.l = false;
            z = true;
            z2 = false;
        } else if (actionMasked == 1 || actionMasked == 2) {
            z2 = this.l;
            z = false;
        } else {
            z2 = false;
            z = false;
        }
        boolean z4 = this.l;
        int degrees = ((int) Math.toDegrees(Math.atan2(y - (getHeight() / 2), x - (getWidth() / 2)))) + 90;
        if (degrees < 0) {
            degrees += 360;
        }
        float f = degrees;
        boolean z5 = this.k != f;
        if (z && z5) {
            z3 = true;
        } else if (z5 || z2) {
            a(f);
            z3 = true;
        }
        this.l = z4 | z3;
        return true;
    }

    public ClockHandView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialClockStyle);
    }

    public ClockHandView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new ArrayList();
        Paint paint = new Paint();
        this.h = paint;
        this.i = new RectF();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ClockHandView, i, R$style.Widget_MaterialComponents_TimePicker_Clock);
        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ClockHandView_materialCircleRadius, 0);
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ClockHandView_selectorSize, 0);
        this.j = getResources().getDimensionPixelSize(R$dimen.material_clock_hand_stroke_width);
        this.g = r6.getDimensionPixelSize(R$dimen.material_clock_hand_center_dot_radius);
        int color = typedArrayObtainStyledAttributes.getColor(R$styleable.ClockHandView_clockHandColor, 0);
        paint.setAntiAlias(true);
        paint.setColor(color);
        a(0.0f);
        ViewConfiguration.get(context).getScaledTouchSlop();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.p(this, 2);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void setOnActionUpListener(a aVar) {
    }
}
