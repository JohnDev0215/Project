package com.byd.mvp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.byd.mvp.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class TintCheckBox extends CheckBox implements CompoundButton.OnCheckedChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3806a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3807b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3808c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3809d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3810e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3811f;
    public int g;
    public int h;
    public int i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;

    public TintCheckBox(Context context) {
        super(context);
        this.f3806a = -2;
        this.f3807b = -2;
        this.f3808c = -2;
        this.f3809d = -2;
        this.f3810e = -2;
        this.f3811f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = false;
        this.k = true;
        this.l = true;
        this.m = false;
        h(context);
    }

    public void a(int i) {
        Drawable background = getBackground();
        if (!this.k || i <= 0) {
            setBackgroundDrawable(background);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(background.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i));
        setBackgroundDrawable(drawableWrap);
    }

    public void b(int i) {
        a(i == 1 ? this.f3807b : this.f3808c);
    }

    public void c(int i, int i2) {
        Drawable drawableF = f(i);
        if (!this.l || i2 <= 0) {
            j(i, drawableF);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(drawableF.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i2));
        j(i, drawableWrap);
    }

    public void d(int i) {
        c(this.f3806a, i == 1 ? this.f3809d : this.f3810e);
    }

    public final void e(boolean z) {
        int i;
        d(z ? 1 : 0);
        b(z ? 1 : 0);
        int i2 = this.g;
        if (i2 == -2 || (i = this.f3811f) == -2) {
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
        this.f3807b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_bg_open_state, this.f3807b);
        this.f3808c = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_bg_close_state, this.f3808c);
        this.f3809d = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_drawable_open_state, this.f3809d);
        this.f3810e = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_drawable_close_state, this.f3810e);
        this.f3811f = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_text_size_normal, this.f3811f);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_text_size_checked, this.g);
        this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_drawable_width, this.h);
        this.i = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_drawable_height, this.i);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void h(Context context) {
        setOnCheckedChangeListener(this);
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

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.m = z;
        if (this.j) {
            e(z);
            this.m = false;
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.j = false;
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        i();
        if (this.f3806a != -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        int i5 = 0;
        while (true) {
            if (i5 >= compoundDrawables.length) {
                break;
            }
            if (compoundDrawables[i5] != null) {
                this.f3806a = i5;
                break;
            }
            i5++;
        }
        this.j = true;
        e(this.m);
    }

    public void setSupportBgTint(boolean z) {
        this.k = z;
    }

    public void setSupportSrcTint(boolean z) {
        this.l = z;
    }

    public TintCheckBox(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3806a = -2;
        this.f3807b = -2;
        this.f3808c = -2;
        this.f3809d = -2;
        this.f3810e = -2;
        this.f3811f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = false;
        this.k = true;
        this.l = true;
        this.m = false;
        g(context, attributeSet);
        h(context);
    }

    public TintCheckBox(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3806a = -2;
        this.f3807b = -2;
        this.f3808c = -2;
        this.f3809d = -2;
        this.f3810e = -2;
        this.f3811f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = false;
        this.k = true;
        this.l = true;
        this.m = false;
        g(context, attributeSet);
        h(context);
    }
}
