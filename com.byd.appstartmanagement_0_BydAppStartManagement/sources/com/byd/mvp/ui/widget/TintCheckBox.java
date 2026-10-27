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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f733b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f734c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f735d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;

    public TintCheckBox(Context context) {
        super(context);
        this.f733b = -2;
        this.f734c = -2;
        this.f735d = -2;
        this.e = -2;
        this.f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = -2;
        this.k = false;
        this.l = true;
        this.m = true;
        this.n = false;
        h(context);
    }

    public void a(int i) {
        Drawable background = getBackground();
        if (!this.l || i <= 0) {
            setBackgroundDrawable(background);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(background.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i));
        setBackgroundDrawable(drawableWrap);
    }

    public void b(int i) {
        a(i == 1 ? this.f734c : this.f735d);
    }

    public void c(int i, int i2) {
        Drawable drawableF = f(i);
        if (!this.m || i2 <= 0) {
            j(i, drawableF);
            return;
        }
        Drawable drawableWrap = DrawableCompat.wrap(drawableF.mutate());
        DrawableCompat.setTint(drawableWrap, ContextCompat.getColor(getContext(), i2));
        j(i, drawableWrap);
    }

    public void d(int i) {
        c(this.f733b, i == 1 ? this.e : this.f);
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
        this.f734c = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_bg_open_state, this.f734c);
        this.f735d = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_bg_close_state, this.f735d);
        this.e = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_drawable_open_state, this.e);
        this.f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_drawable_close_state, this.f);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_text_size_normal, this.g);
        this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_text_size_checked, this.h);
        this.i = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_drawable_width, this.i);
        this.j = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_drawable_height, this.j);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void h(Context context) {
        setOnCheckedChangeListener(this);
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

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.n = z;
        if (this.k) {
            e(z);
            this.n = false;
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.k = false;
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        i();
        if (this.f733b != -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        int i5 = 0;
        while (true) {
            if (i5 >= compoundDrawables.length) {
                break;
            }
            if (compoundDrawables[i5] != null) {
                this.f733b = i5;
                break;
            }
            i5++;
        }
        this.k = true;
        e(this.n);
    }

    public void setSupportBgTint(boolean z) {
        this.l = z;
    }

    public void setSupportSrcTint(boolean z) {
        this.m = z;
    }

    public TintCheckBox(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f733b = -2;
        this.f734c = -2;
        this.f735d = -2;
        this.e = -2;
        this.f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = -2;
        this.k = false;
        this.l = true;
        this.m = true;
        this.n = false;
        g(context, attributeSet);
        h(context);
    }

    public TintCheckBox(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f733b = -2;
        this.f734c = -2;
        this.f735d = -2;
        this.e = -2;
        this.f = -2;
        this.g = -2;
        this.h = -2;
        this.i = -2;
        this.j = -2;
        this.k = false;
        this.l = true;
        this.m = true;
        this.n = false;
        g(context, attributeSet);
        h(context);
    }
}
