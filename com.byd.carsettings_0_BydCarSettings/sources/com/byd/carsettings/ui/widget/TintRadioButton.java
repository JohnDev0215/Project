package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import b.b.a.j.c.a;
import com.byd.carsettings.R;
import com.byd.mvp.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class TintRadioButton extends RadioButton implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3461a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3462b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3463c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3464d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3465e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3466f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public Paint v;

    public TintRadioButton(Context context) {
        super(context);
        this.f3461a = -2;
        this.f3462b = -2;
        this.f3463c = -2;
        this.f3464d = -2;
        this.f3465e = -2;
        this.f3466f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = -2;
        this.k = -2;
        this.l = -2;
        this.m = -2;
        this.n = -2;
        this.o = -2;
        this.p = -2;
        this.q = false;
        this.r = true;
        this.s = true;
        this.t = false;
        this.u = false;
        k(context);
    }

    public void d(int i) {
        Drawable background = getBackground();
        if (!this.r || i <= 0) {
            setBackgroundDrawable(background);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(background.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i));
        setBackgroundDrawable(drawableWrap);
    }

    public void e(int i) {
        d(i == 1 ? this.f3462b : this.f3463c);
    }

    public void f(int i, int i2) {
        Drawable drawableI = i(i);
        if (!this.s || i2 <= 0) {
            m(i, drawableI);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(drawableI.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i2));
        m(i, drawableWrap);
    }

    public void g(int i) {
        f(this.f3461a, i == 1 ? this.f3464d : this.f3465e);
    }

    public final void h(boolean z) {
        int i;
        g(z ? 1 : 0);
        e(z ? 1 : 0);
        int i2 = this.g;
        if (i2 == -2 || (i = this.f3466f) == -2) {
            return;
        }
        setTextSize(0, z ? i2 : i);
    }

    public final Drawable i(int i) {
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (i == 0) {
            return compoundDrawables[0];
        }
        if (i == 1) {
            return compoundDrawables[1];
        }
        if (i == 2) {
            return compoundDrawables[2];
        }
        if (i != 3) {
            return null;
        }
        return compoundDrawables[3];
    }

    public final void j(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.tint_rbtn);
        this.f3462b = typedArrayObtainStyledAttributes.getResourceId(1, this.f3462b);
        this.f3463c = typedArrayObtainStyledAttributes.getResourceId(0, this.f3463c);
        this.f3464d = typedArrayObtainStyledAttributes.getResourceId(3, this.f3464d);
        this.f3465e = typedArrayObtainStyledAttributes.getResourceId(2, this.f3465e);
        this.f3466f = typedArrayObtainStyledAttributes.getDimensionPixelSize(14, this.f3466f);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, this.g);
        this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, this.h);
        this.i = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, this.i);
        this.j = typedArrayObtainStyledAttributes.getResourceId(6, this.j);
        this.k = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, this.k);
        this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, this.l);
        this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, this.m);
        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, this.n);
        this.o = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, this.o);
        this.p = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, this.p);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void k(Context context) {
        Paint paint = new Paint();
        this.v = paint;
        paint.setStrokeWidth(2.0f);
        this.v.setAntiAlias(true);
        int color = context.getResources().getColor(R.color.color_red_dot);
        if (this.j != -2) {
            color = context.getResources().getColor(this.j);
        }
        this.v.setColor(color);
    }

    public final void l() {
        if (this.h == -2 || this.i == -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        for (Drawable drawable : compoundDrawables) {
            if (drawable != null) {
                drawable.setBounds(0, 0, this.h, this.i);
            }
        }
        setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
    }

    public final void m(int i, Drawable drawable) {
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (i == 0) {
            setCompoundDrawables(drawable, compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
            return;
        }
        if (i == 1) {
            setCompoundDrawables(compoundDrawables[0], drawable, compoundDrawables[2], compoundDrawables[3]);
        } else if (i == 2) {
            setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], drawable, compoundDrawables[3]);
        } else {
            if (i != 3) {
                return;
            }
            setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], drawable);
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.q = false;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        if (!this.t || (i = this.k) <= 0) {
            return;
        }
        float f2 = i / 2;
        canvas.drawCircle(this.m + f2, this.o + f2, f2, this.v);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        l();
        if (this.f3461a != -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        int i5 = 0;
        while (true) {
            if (i5 >= compoundDrawables.length) {
                break;
            }
            if (compoundDrawables[i5] != null) {
                this.f3461a = i5;
                break;
            }
            i5++;
        }
        this.q = true;
        h(this.u);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        this.u = z;
        super.setChecked(z);
        if (this.q) {
            h(z);
            this.u = false;
        }
    }

    @Override // b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z) {
        super.setInvalid(z);
    }

    @Override // b.b.a.j.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z, boolean z2) {
        super.setInvalid(z, z2);
    }

    public void setRedDotVisibility(boolean z) {
        this.t = z;
        invalidate();
    }

    public void setSupportBgTint(boolean z) {
        this.r = z;
    }

    public void setSupportSrcTint(boolean z) {
        this.s = z;
    }

    public TintRadioButton(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3461a = -2;
        this.f3462b = -2;
        this.f3463c = -2;
        this.f3464d = -2;
        this.f3465e = -2;
        this.f3466f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = -2;
        this.k = -2;
        this.l = -2;
        this.m = -2;
        this.n = -2;
        this.o = -2;
        this.p = -2;
        this.q = false;
        this.r = true;
        this.s = true;
        this.t = false;
        this.u = false;
        j(context, attributeSet);
        k(context);
    }

    public TintRadioButton(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3461a = -2;
        this.f3462b = -2;
        this.f3463c = -2;
        this.f3464d = -2;
        this.f3465e = -2;
        this.f3466f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = -2;
        this.k = -2;
        this.l = -2;
        this.m = -2;
        this.n = -2;
        this.o = -2;
        this.p = -2;
        this.q = false;
        this.r = true;
        this.s = true;
        this.t = false;
        this.u = false;
        j(context, attributeSet);
        k(context);
    }
}
