package com.byd.carsettings.ui.view.seekbar;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class TwoWaysSeekBar extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f3342a = new int[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f3343b = {R.attr.state_pressed, R.attr.state_window_focused};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Drawable f3344c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Drawable f3345d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f3346e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f3347f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public double n;
    public int o;
    public int p;
    public a q;
    public int r;
    public int s;
    public int t;
    public int u;

    public interface a {
        void a(TwoWaysSeekBar twoWaysSeekBar, int i);

        void b();

        void c();
    }

    public TwoWaysSeekBar(Context context) {
        this(context, null);
    }

    public int a(MotionEvent motionEvent) {
        return (motionEvent.getY() < 0.0f || motionEvent.getY() > ((float) this.l) || motionEvent.getX() < ((float) (this.m - (this.k / 2))) || motionEvent.getX() > ((float) (this.m + (this.k / 2)))) ? 0 : 1;
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public final void b(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.byd.carsettings.R.styleable.TwoWaysSeekBar);
        this.t = typedArrayObtainStyledAttributes.getInt(1, 20);
        this.f3344c = typedArrayObtainStyledAttributes.getDrawable(0);
        this.f3345d = typedArrayObtainStyledAttributes.getDrawable(2);
        this.f3346e = typedArrayObtainStyledAttributes.getDrawable(3);
        typedArrayObtainStyledAttributes.recycle();
        this.f3347f = getResources().getDrawable(com.byd.carsettings.R.drawable.shape_two_ways_seekbar_mid_thumb);
        this.g = this.f3344c.getIntrinsicWidth();
        this.h = this.f3344c.getIntrinsicHeight();
        this.k = this.f3346e.getIntrinsicWidth();
        this.l = this.f3346e.getIntrinsicHeight();
        this.i = this.f3347f.getIntrinsicWidth();
        this.j = this.f3347f.getIntrinsicHeight();
    }

    public final int c(int i) {
        View.MeasureSpec.getMode(i);
        return View.MeasureSpec.getSize(i);
    }

    public final void d() {
        invalidate();
    }

    @Override // android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.parseColor("#4e8795"));
        paint.setAntiAlias(true);
        paint.setAlpha(255);
        paint.setTextSize(40.0f);
        int i = this.l / 2;
        int i2 = this.h;
        int i3 = i - (i2 / 2);
        int i4 = i2 + i3;
        Drawable drawable = this.f3344c;
        int i5 = this.k;
        drawable.setBounds(i5 / 2, i3, this.g - (i5 / 2), i4);
        this.f3344c.draw(canvas);
        int i6 = this.m;
        int i7 = this.g;
        if (i6 > i7 / 2) {
            this.f3345d.setBounds(i7 / 2, i3, i6, i4);
        } else if (i6 < i7 / 2) {
            this.f3345d.setBounds(i6, i3, i7 / 2, i4);
        } else {
            this.f3345d.setBounds(i6, i3, i7 / 2, i4);
        }
        this.f3345d.draw(canvas);
        int i8 = this.l / 2;
        int i9 = this.j;
        int i10 = i8 - (i9 / 2);
        Drawable drawable2 = this.f3347f;
        int i11 = this.g;
        int i12 = this.i;
        drawable2.setBounds((i11 / 2) - (i12 / 2), i10, (i11 / 2) + (i12 / 2), i9 + i10);
        this.f3347f.draw(canvas);
        Drawable drawable3 = this.f3346e;
        int i13 = this.m;
        int i14 = this.k;
        drawable3.setBounds(i13 - (i14 / 2), 0, i13 + (i14 / 2), this.l);
        this.f3346e.draw(canvas);
        int i15 = (this.m - (this.k / 2)) / this.r;
        this.s = i15;
        if (i15 == 10) {
            this.s = 0;
        } else if (i15 > 10 || i15 < 10) {
            this.s = i15 - 10;
        }
        a aVar = this.q;
        if (aVar != null) {
            int i16 = this.u;
            int i17 = this.s;
            if (i16 != i17) {
                aVar.a(this, i17);
                this.u = this.s;
            }
        }
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int iC = c(i);
        this.g = iC;
        this.m = iC / 2;
        int i3 = this.k;
        int i4 = iC - i3;
        this.o = i4;
        this.r = i4 / this.t;
        this.m = (int) (((this.n / 200.0d) * ((double) i4)) + ((double) (i3 / 2)));
        setMeasuredDimension(iC, this.l + 2);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            int iA = a(motionEvent);
            this.p = iA;
            if (iA == 1) {
                this.f3346e.setState(f3343b);
                a aVar = this.q;
                if (aVar != null) {
                    aVar.c();
                }
            }
        } else if (action != 1) {
            if (action == 2) {
                if (this.p == 1) {
                    if (motionEvent.getX() < 0.0f || motionEvent.getX() <= this.k / 2) {
                        this.m = this.k / 2;
                    } else {
                        float x = motionEvent.getX();
                        int i = this.g;
                        int i2 = this.k;
                        if (x >= i - (i2 / 2)) {
                            this.m = this.o + (i2 / 2);
                        } else {
                            this.m = (int) motionEvent.getX();
                        }
                    }
                }
                d();
            }
        } else if (this.p == 1) {
            this.f3346e.setState(f3342a);
            int i3 = this.m;
            int i4 = this.r;
            if (i3 % i4 >= i4 / 2) {
                this.m = i3 + (i4 - (i3 % i4));
            } else if (i3 % i4 < i4 / 2) {
                this.m = i3 - (i4 - (i3 % i4));
            }
            d();
            a aVar2 = this.q;
            if (aVar2 != null) {
                aVar2.b();
            }
        }
        return true;
    }

    public void setOnSeekBarChangeListener(a aVar) {
        this.q = aVar;
    }

    public void setProgress(double d2) {
        this.n = d2;
        if (d2 >= 0.0d || d2 < 0.0d) {
            this.m = (int) (((((double) (this.t / 2)) + d2) * ((double) this.r)) + ((double) (this.k / 2)));
        }
        b.b.m.c.a.d("TwoWaysSeekBar", "setProgress: mThumbOffset = " + this.m + "  -- progress = " + d2);
        b.b.m.c.a.d("TwoWaysSeekBar", "setProgress: mMaxProgress = " + this.t + "  -- mSingleDistance = " + this.r + "  -- mThumbWidth = " + this.k);
        d();
    }

    public TwoWaysSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TwoWaysSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = 0;
        this.n = 100.0d;
        this.o = 0;
        this.p = 0;
        this.s = 10;
        this.t = 20;
        this.u = -1;
        b(attributeSet);
    }
}
