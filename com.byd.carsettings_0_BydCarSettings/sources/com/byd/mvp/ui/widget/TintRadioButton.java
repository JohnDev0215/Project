package com.byd.mvp.ui.widget;

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
import com.byd.mvp.R$color;
import com.byd.mvp.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class TintRadioButton extends RadioButton {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3815a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3816b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3817c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3818d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3819e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3820f;
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
        this.f3815a = -2;
        this.f3816b = -2;
        this.f3817c = -2;
        this.f3818d = -2;
        this.f3819e = -2;
        this.f3820f = -2;
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
        h(context);
    }

    public void a(int i) {
        Drawable background = getBackground();
        if (!this.r || i <= 0) {
            setBackgroundDrawable(background);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(background.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i));
        setBackgroundDrawable(drawableWrap);
    }

    public void b(int i) {
        a(i == 1 ? this.f3816b : this.f3817c);
    }

    public void c(int i, int i2) {
        Drawable drawableF = f(i);
        if (!this.s || i2 <= 0) {
            j(i, drawableF);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(drawableF.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i2));
        j(i, drawableWrap);
    }

    public void d(int i) {
        c(this.f3815a, i == 1 ? this.f3818d : this.f3819e);
    }

    public final void e(boolean z) {
        int i;
        d(z ? 1 : 0);
        b(z ? 1 : 0);
        int i2 = this.g;
        if (i2 == -2 || (i = this.f3820f) == -2) {
            return;
        }
        setTextSize(0, z ? i2 : i);
    }

    public final Drawable f(int i) {
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

    public final void g(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.tint_rbtn);
        this.f3816b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_bg_open_state, this.f3816b);
        this.f3817c = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_bg_close_state, this.f3817c);
        this.f3818d = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_drawable_open_state, this.f3818d);
        this.f3819e = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_drawable_close_state, this.f3819e);
        this.f3820f = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_text_size_normal, this.f3820f);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_text_size_checked, this.g);
        this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_drawable_width, this.h);
        this.i = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_drawable_height, this.i);
        this.j = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_red_dot_color, this.j);
        this.k = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_width, this.k);
        this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_height, this.l);
        this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_margin_start, this.m);
        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_margin_end, this.n);
        this.o = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_margin_top, this.o);
        this.p = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_margin_bottom, this.p);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void h(Context context) {
        Paint paint = new Paint();
        this.v = paint;
        paint.setStrokeWidth(2.0f);
        this.v.setAntiAlias(true);
        int color = context.getResources().getColor(R$color.color_red_dot);
        if (this.j != -2) {
            color = context.getResources().getColor(this.j);
        }
        this.v.setColor(color);
    }

    public final void i() {
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

    public final void j(int i, Drawable drawable) {
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
        i();
        if (this.f3815a != -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        int i5 = 0;
        while (true) {
            if (i5 >= compoundDrawables.length) {
                break;
            }
            if (compoundDrawables[i5] != null) {
                this.f3815a = i5;
                break;
            }
            i5++;
        }
        this.q = true;
        e(this.u);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        this.u = z;
        super.setChecked(z);
        if (this.q) {
            e(z);
            this.u = false;
        }
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
        this.f3815a = -2;
        this.f3816b = -2;
        this.f3817c = -2;
        this.f3818d = -2;
        this.f3819e = -2;
        this.f3820f = -2;
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
        g(context, attributeSet);
        h(context);
    }

    public TintRadioButton(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3815a = -2;
        this.f3816b = -2;
        this.f3817c = -2;
        this.f3818d = -2;
        this.f3819e = -2;
        this.f3820f = -2;
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
        g(context, attributeSet);
        h(context);
    }
}
