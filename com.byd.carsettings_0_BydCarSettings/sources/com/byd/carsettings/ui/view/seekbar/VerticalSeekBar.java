package com.byd.carsettings.ui.view.seekbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import com.byd.carsettings.R;

/* JADX INFO: loaded from: classes2.dex */
public class VerticalSeekBar extends View {
    public int A;
    public a B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3348a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f3349b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f3350c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f3351d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public RectF f3352e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public RectF f3353f;
    public RectF g;
    public Paint h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public float u;
    public float v;
    public float w;
    public float x;
    public float y;
    public float z;

    public interface a {
        void a(int i);
    }

    public VerticalSeekBar(Context context) {
        super(context);
        this.f3348a = "VerticalSeekBar";
        this.i = 4;
        this.j = 0;
        this.k = 0;
        this.l = 6;
        this.m = 12;
        this.n = 0;
        this.o = 5;
        this.p = 10;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 4;
        this.u = 4.0f;
        this.v = 4.0f;
        this.w = 4.0f;
        this.x = 6.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.A = 0;
        a(context, null);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        this.f3351d = context;
        this.f3352e = new RectF();
        this.f3353f = new RectF();
        this.g = new RectF();
        this.h = new Paint(1);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.VerticalSeekBar);
        this.t = typedArrayObtainStyledAttributes.getInt(0, 4);
        this.o = (int) typedArrayObtainStyledAttributes.getDimension(5, 5.0f);
        this.p = (int) typedArrayObtainStyledAttributes.getDimension(8, 5.0f);
        this.q = typedArrayObtainStyledAttributes.getColor(6, -7829368);
        this.r = typedArrayObtainStyledAttributes.getColor(7, -7829368);
        this.l = (int) typedArrayObtainStyledAttributes.getDimension(10, 6.0f);
        this.m = (int) typedArrayObtainStyledAttributes.getDimension(11, 12.0f);
        this.n = typedArrayObtainStyledAttributes.getColor(9, -16776961);
        this.j = typedArrayObtainStyledAttributes.getColor(2, -16776961);
        this.i = (int) typedArrayObtainStyledAttributes.getDimension(3, 4.0f);
        this.x = (int) typedArrayObtainStyledAttributes.getDimension(4, 6.0f);
        this.k = typedArrayObtainStyledAttributes.getColor(1, -7829368);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        this.f3349b = getWidth();
        float height = getHeight();
        this.f3350c = height;
        float f2 = this.f3349b / 2.0f;
        this.u = f2;
        int i = this.l;
        this.v = i;
        float f3 = height - i;
        this.w = f3;
        float f4 = height - this.m;
        this.y = f4;
        this.z = f4 / this.t;
        RectF rectF = this.f3352e;
        float f5 = this.x;
        rectF.set(f2 - (f5 / 2.0f), i, f2 + (f5 / 2.0f), f3);
        this.h.setStyle(Paint.Style.FILL);
        this.h.setColor(this.k);
        RectF rectF2 = this.f3352e;
        int i2 = this.i;
        canvas.drawRoundRect(rectF2, i2, i2, this.h);
        RectF rectF3 = this.g;
        float f6 = this.u;
        float f7 = this.x;
        rectF3.set(f6 - (f7 / 2.0f), this.l, f6 + (f7 / 2.0f), r5 + this.s);
        this.h.setStyle(Paint.Style.FILL);
        this.h.setColor(this.j);
        RectF rectF4 = this.g;
        int i3 = this.i;
        canvas.drawRoundRect(rectF4, i3, i3, this.h);
        for (int i4 = 0; i4 <= this.t; i4++) {
            RectF rectF5 = this.f3353f;
            float f8 = this.u;
            int i5 = this.o;
            float f9 = this.z;
            float f10 = i4;
            rectF5.set(f8 - i5, ((f9 * f10) + this.l) - i5, f8 + i5, this.v + (f9 * f10) + i5);
            if (i4 <= this.A) {
                this.h.setColor(this.q);
            } else {
                this.h.setColor(this.r);
            }
            RectF rectF6 = this.f3353f;
            int i6 = this.o;
            canvas.drawRoundRect(rectF6, i6, i6, this.h);
        }
        RectF rectF7 = this.f3353f;
        float f11 = this.u;
        int i7 = this.l;
        rectF7.set(f11 - i7, this.s, f11 + i7, this.m + r4);
        this.h.setColor(this.n);
        RectF rectF8 = this.f3353f;
        int i8 = this.l;
        canvas.drawRoundRect(rectF8, i8, i8, this.h);
        super.onDraw(canvas);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        motionEvent.getX();
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 1) {
            for (int i = 0; i <= this.t; i++) {
                float f2 = this.z;
                float f3 = i;
                int i2 = this.l;
                float f4 = (f2 * f3) + i2;
                int i3 = this.o;
                if (y < f4 - i3 || y >= ((((i + 1) * f2) + i2) - i3) - (f2 / 2.0f)) {
                    int i4 = i + 1;
                    float f5 = i4;
                    if (y >= (((f2 * f5) + i2) - i3) - (f2 / 2.0f) && y < ((f2 * f5) + i2) - i3) {
                        this.s = (int) (((f2 * f5) + i2) - i3);
                        this.A = i4;
                    }
                } else {
                    this.s = (int) (((f2 * f3) + i2) - i3);
                    this.A = i;
                }
                invalidate();
            }
            a aVar = this.B;
            if (aVar != null) {
                aVar.a(this.A);
            }
        } else if (action == 2) {
            int y2 = (int) (motionEvent.getY() - 0.0f);
            this.s = y2;
            if (y2 > 0 && y2 < this.f3350c - this.m) {
                this.A = (int) (((y - this.l) + this.o) / this.z);
                invalidate();
            }
        }
        return true;
    }

    public void setSeekBarProgressChangeListener(a aVar) {
        this.B = aVar;
    }

    public VerticalSeekBar(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3348a = "VerticalSeekBar";
        this.i = 4;
        this.j = 0;
        this.k = 0;
        this.l = 6;
        this.m = 12;
        this.n = 0;
        this.o = 5;
        this.p = 10;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 4;
        this.u = 4.0f;
        this.v = 4.0f;
        this.w = 4.0f;
        this.x = 6.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.A = 0;
        a(context, attributeSet);
    }

    public VerticalSeekBar(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3348a = "VerticalSeekBar";
        this.i = 4;
        this.j = 0;
        this.k = 0;
        this.l = 6;
        this.m = 12;
        this.n = 0;
        this.o = 5;
        this.p = 10;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 4;
        this.u = 4.0f;
        this.v = 4.0f;
        this.w = 4.0f;
        this.x = 6.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.A = 0;
        a(context, attributeSet);
    }
}
