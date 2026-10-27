package org.chromium.components.browser_ui.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import defpackage.ii2;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class MaterialProgressBar extends View implements ValueAnimator.AnimatorUpdateListener {
    public final ValueAnimator e;
    public final Paint f;
    public final Paint g;
    public final Paint h;
    public boolean i;
    public int j;
    public int k;

    public MaterialProgressBar(Context context) {
        super(context);
        this.e = ValueAnimator.ofFloat(0.0f, 3.0f);
        this.f = new Paint();
        this.g = new Paint();
        this.h = new Paint();
        b(context, null, 0);
    }

    public final void a(float f, float f2, Canvas canvas, Paint paint) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (om3.e.c(this) != 1) {
            canvas.drawRect(f, 0.0f, f2, canvas.getHeight(), paint);
        } else {
            float width = canvas.getWidth();
            canvas.drawRect(width - f2, 0.0f, width - f, canvas.getHeight(), paint);
        }
    }

    public final void b(Context context, AttributeSet attributeSet, int i) {
        context.getResources();
        int color = context.getColor(R$color.progress_bar_bg_color_list);
        int iB = ii2.b(context);
        int color2 = context.getColor(R$color.progress_bar_secondary);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialProgressBar, i, 0);
            color = typedArrayObtainStyledAttributes.getColor(R$styleable.MaterialProgressBar_colorBackground, color);
            iB = typedArrayObtainStyledAttributes.getColor(R$styleable.MaterialProgressBar_colorProgress, iB);
            color2 = typedArrayObtainStyledAttributes.getColor(R$styleable.MaterialProgressBar_colorSecondaryProgress, color2);
            typedArrayObtainStyledAttributes.recycle();
        }
        setBackgroundColor(color);
        setProgressColor(iB);
        setSecondaryProgressColor(color2);
        ValueAnimator valueAnimator = this.e;
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setDuration(3000L);
        valueAnimator.addUpdateListener(this);
    }

    public final void c() {
        if (this.i) {
            ValueAnimator valueAnimator = this.e;
            if (valueAnimator.isRunning()) {
                return;
            }
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.g.b(this) && getVisibility() == 0) {
                valueAnimator.start();
            }
        }
    }

    public int getProgressForTesting() {
        return this.j;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        postInvalidateOnAnimation();
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        c();
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.e;
        if (valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        boolean z = this.i;
        Paint paint = this.g;
        Paint paint2 = this.f;
        if (z) {
            float width = canvas.getWidth();
            a(0.0f, width, canvas, paint2);
            float fFloatValue = ((Float) this.e.getAnimatedValue()).floatValue();
            a(((float) (Math.pow(fFloatValue, 1.5d) - 0.5d)) * width, width * fFloatValue, canvas, paint);
            if (fFloatValue >= 1.1f) {
                float f = (fFloatValue - 1.1f) / 1.0f;
                a(((float) (Math.pow(f, 2.5d) - 0.10000000149011612d)) * width, width * f, canvas, paint);
                return;
            }
            return;
        }
        float width2 = canvas.getWidth();
        a(0.0f, width2, canvas, paint2);
        int i = this.j;
        if (i > 0) {
            a(0.0f, (i / 100.0f) * width2, canvas, paint);
        }
        int i2 = this.k;
        if (i2 > 0) {
            a(0.0f, width2 * (i2 / 100.0f), canvas, this.h);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.f.setColor(i);
        postInvalidateOnAnimation();
    }

    public void setIndeterminate(boolean z) {
        if (this.i == z) {
            return;
        }
        this.i = z;
        if (z) {
            c();
        }
        postInvalidateOnAnimation();
    }

    public void setProgress(int i) {
        this.j = Math.max(0, Math.min(100, i));
        postInvalidateOnAnimation();
    }

    public void setProgressColor(int i) {
        this.g.setColor(i);
        postInvalidateOnAnimation();
    }

    public void setSecondaryProgress(int i) {
        this.k = Math.max(0, Math.min(100, i));
        postInvalidateOnAnimation();
    }

    public void setSecondaryProgressColor(int i) {
        this.h.setColor(i);
        postInvalidateOnAnimation();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        if (i == 0) {
            c();
            return;
        }
        ValueAnimator valueAnimator = this.e;
        if (valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
    }

    public MaterialProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = ValueAnimator.ofFloat(0.0f, 3.0f);
        this.f = new Paint();
        this.g = new Paint();
        this.h = new Paint();
        b(context, attributeSet, 0);
    }

    public MaterialProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = ValueAnimator.ofFloat(0.0f, 3.0f);
        this.f = new Paint();
        this.g = new Paint();
        this.h = new Paint();
        b(context, attributeSet, i);
    }
}
