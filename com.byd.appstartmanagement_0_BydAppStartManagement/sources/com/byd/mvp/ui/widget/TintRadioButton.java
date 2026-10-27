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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f739b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f740c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f741d;
    public int e;
    public int f;
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
    public int q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public Paint w;

    public TintRadioButton(Context context) {
        super(context);
        this.f739b = -2;
        this.f740c = -2;
        this.f741d = -2;
        this.e = -2;
        this.f = -2;
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
        this.q = -2;
        this.r = false;
        this.s = true;
        this.t = true;
        this.u = false;
        this.v = false;
        h(context);
    }

    public void a(int i) {
        Drawable background = getBackground();
        if (!this.s || i <= 0) {
            setBackgroundDrawable(background);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(background.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i));
        setBackgroundDrawable(drawableWrap);
    }

    public void b(int i) {
        a(i == 1 ? this.f740c : this.f741d);
    }

    public void c(int i, int i2) {
        Drawable drawableF = f(i);
        if (!this.t || i2 <= 0) {
            j(i, drawableF);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(drawableF.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i2));
        j(i, drawableWrap);
    }

    public void d(int i) {
        c(this.f739b, i == 1 ? this.e : this.f);
    }

    public final void e(boolean z) {
        int i;
        d(z ? 1 : 0);
        b(z ? 1 : 0);
        int i2 = this.h;
        if (i2 == -2 || (i = this.g) == -2) {
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
        this.f740c = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_bg_open_state, this.f740c);
        this.f741d = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_bg_close_state, this.f741d);
        this.e = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_drawable_open_state, this.e);
        this.f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_drawable_close_state, this.f);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_text_size_normal, this.g);
        this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_text_size_checked, this.h);
        this.i = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_drawable_width, this.i);
        this.j = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_drawable_height, this.j);
        this.k = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_red_dot_color, this.k);
        this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_width, this.l);
        this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_height, this.m);
        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_margin_start, this.n);
        this.o = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_margin_end, this.o);
        this.p = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_margin_top, this.p);
        this.q = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_margin_bottom, this.q);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void h(Context context) {
        Paint paint = new Paint();
        this.w = paint;
        paint.setStrokeWidth(2.0f);
        this.w.setAntiAlias(true);
        int color = context.getResources().getColor(R$color.color_red_dot);
        if (this.k != -2) {
            color = context.getResources().getColor(this.k);
        }
        this.w.setColor(color);
    }

    public final void i() {
        if (this.i == -2 || this.j == -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        for (Drawable drawable : compoundDrawables) {
            if (drawable != null) {
                drawable.setBounds(0, 0, this.i, this.j);
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
        this.r = false;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        if (!this.u || (i = this.l) <= 0) {
            return;
        }
        float f = i / 2;
        canvas.drawCircle(this.n + f, this.p + f, f, this.w);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        i();
        if (this.f739b != -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        int i5 = 0;
        while (true) {
            if (i5 >= compoundDrawables.length) {
                break;
            }
            if (compoundDrawables[i5] != null) {
                this.f739b = i5;
                break;
            }
            i5++;
        }
        this.r = true;
        e(this.v);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        this.v = z;
        super.setChecked(z);
        if (this.r) {
            e(z);
            this.v = false;
        }
    }

    public void setRedDotVisibility(boolean z) {
        this.u = z;
        invalidate();
    }

    public void setSupportBgTint(boolean z) {
        this.s = z;
    }

    public void setSupportSrcTint(boolean z) {
        this.t = z;
    }

    public TintRadioButton(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f739b = -2;
        this.f740c = -2;
        this.f741d = -2;
        this.e = -2;
        this.f = -2;
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
        this.q = -2;
        this.r = false;
        this.s = true;
        this.t = true;
        this.u = false;
        this.v = false;
        g(context, attributeSet);
        h(context);
    }

    public TintRadioButton(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f739b = -2;
        this.f740c = -2;
        this.f741d = -2;
        this.e = -2;
        this.f = -2;
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
        this.q = -2;
        this.r = false;
        this.s = true;
        this.t = true;
        this.u = false;
        this.v = false;
        g(context, attributeSet);
        h(context);
    }
}
