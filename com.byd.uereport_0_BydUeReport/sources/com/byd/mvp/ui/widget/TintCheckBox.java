package com.byd.mvp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.byd.mvp.R$styleable;
import h0.a;

/* JADX INFO: loaded from: classes2.dex */
public class TintCheckBox extends CheckBox implements CompoundButton.OnCheckedChangeListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2904d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2905e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2906f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2907g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2908h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2909i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2910j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2911k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2912l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f2913m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f2914n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f2915o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f2916p;

    public TintCheckBox(Context context) {
        super(context);
        this.f2904d = -2;
        this.f2905e = -2;
        this.f2906f = -2;
        this.f2907g = -2;
        this.f2908h = -2;
        this.f2909i = -2;
        this.f2910j = -2;
        this.f2911k = -2;
        this.f2912l = -2;
        this.f2913m = false;
        this.f2914n = true;
        this.f2915o = true;
        this.f2916p = false;
        h(context);
    }

    public void a(int i8) {
        Drawable background = getBackground();
        if (!this.f2914n || i8 <= 0) {
            setBackgroundDrawable(background);
            return;
        }
        Drawable drawableR = a.r(background.mutate());
        a.n(drawableR, e0.a.b(getContext(), i8));
        setBackgroundDrawable(drawableR);
    }

    public void b(int i8) {
        a(i8 == 1 ? this.f2905e : this.f2906f);
    }

    public void c(int i8, int i9) {
        Drawable drawableF = f(i8);
        if (!this.f2915o || i9 <= 0) {
            j(i8, drawableF);
            return;
        }
        Drawable drawableR = a.r(drawableF.mutate());
        a.n(drawableR, e0.a.b(getContext(), i9));
        j(i8, drawableR);
    }

    public void d(int i8) {
        c(this.f2904d, i8 == 1 ? this.f2907g : this.f2908h);
    }

    public final void e(boolean z7) {
        int i8;
        d(z7 ? 1 : 0);
        b(z7 ? 1 : 0);
        int i9 = this.f2910j;
        if (i9 == -2 || (i8 = this.f2909i) == -2) {
            return;
        }
        setTextSize(0, z7 ? i9 : i8);
    }

    public final Drawable f(int i8) {
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (i8 == 0) {
            return compoundDrawables[0];
        }
        if (i8 == 1) {
            return compoundDrawables[1];
        }
        if (i8 == 2) {
            return compoundDrawables[2];
        }
        if (i8 != 3) {
            return null;
        }
        return compoundDrawables[3];
    }

    public final void g(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.tint_rbtn);
        this.f2905e = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_bg_open_state, this.f2905e);
        this.f2906f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_bg_close_state, this.f2906f);
        this.f2907g = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_drawable_open_state, this.f2907g);
        this.f2908h = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_drawable_close_state, this.f2908h);
        this.f2909i = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_text_size_normal, this.f2909i);
        this.f2910j = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_text_size_checked, this.f2910j);
        this.f2911k = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_drawable_width, this.f2911k);
        this.f2912l = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_drawable_height, this.f2912l);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void h(Context context) {
        setOnCheckedChangeListener(this);
    }

    public final void i() {
        if (this.f2911k == -2 || this.f2912l == -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        for (Drawable drawable : compoundDrawables) {
            if (drawable != null) {
                drawable.setBounds(0, 0, this.f2911k, this.f2912l);
            }
        }
        setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
    }

    public final void j(int i8, Drawable drawable) {
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (i8 == 0) {
            setCompoundDrawables(drawable, compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
            return;
        }
        if (i8 == 1) {
            setCompoundDrawables(compoundDrawables[0], drawable, compoundDrawables[2], compoundDrawables[3]);
        } else if (i8 == 2) {
            setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], drawable, compoundDrawables[3]);
        } else {
            if (i8 != 3) {
                return;
            }
            setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], drawable);
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
        this.f2916p = z7;
        if (this.f2913m) {
            e(z7);
            this.f2916p = false;
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f2913m = false;
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        i();
        if (this.f2904d != -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        int i12 = 0;
        while (true) {
            if (i12 >= compoundDrawables.length) {
                break;
            }
            if (compoundDrawables[i12] != null) {
                this.f2904d = i12;
                break;
            }
            i12++;
        }
        this.f2913m = true;
        e(this.f2916p);
    }

    public void setSupportBgTint(boolean z7) {
        this.f2914n = z7;
    }

    public void setSupportSrcTint(boolean z7) {
        this.f2915o = z7;
    }

    public TintCheckBox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2904d = -2;
        this.f2905e = -2;
        this.f2906f = -2;
        this.f2907g = -2;
        this.f2908h = -2;
        this.f2909i = -2;
        this.f2910j = -2;
        this.f2911k = -2;
        this.f2912l = -2;
        this.f2913m = false;
        this.f2914n = true;
        this.f2915o = true;
        this.f2916p = false;
        g(context, attributeSet);
        h(context);
    }

    public TintCheckBox(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2904d = -2;
        this.f2905e = -2;
        this.f2906f = -2;
        this.f2907g = -2;
        this.f2908h = -2;
        this.f2909i = -2;
        this.f2910j = -2;
        this.f2911k = -2;
        this.f2912l = -2;
        this.f2913m = false;
        this.f2914n = true;
        this.f2915o = true;
        this.f2916p = false;
        g(context, attributeSet);
        h(context);
    }
}
