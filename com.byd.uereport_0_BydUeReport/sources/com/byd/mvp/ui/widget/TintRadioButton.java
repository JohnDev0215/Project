package com.byd.mvp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import com.byd.mvp.R$color;
import com.byd.mvp.R$styleable;
import h0.a;

/* JADX INFO: loaded from: classes2.dex */
public class TintRadioButton extends RadioButton {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2920d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2921e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2922f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2923g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2924h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2925i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2926j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2927k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2928l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2929m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2930n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f2931o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f2932p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f2933q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f2934r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f2935s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f2936t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f2937u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f2938v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f2939w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f2940x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Paint f2941y;

    public TintRadioButton(Context context) {
        super(context);
        this.f2920d = -2;
        this.f2921e = -2;
        this.f2922f = -2;
        this.f2923g = -2;
        this.f2924h = -2;
        this.f2925i = -2;
        this.f2926j = -2;
        this.f2927k = -2;
        this.f2928l = -2;
        this.f2929m = -2;
        this.f2930n = -2;
        this.f2931o = -2;
        this.f2932p = -2;
        this.f2933q = -2;
        this.f2934r = -2;
        this.f2935s = -2;
        this.f2936t = false;
        this.f2937u = true;
        this.f2938v = true;
        this.f2939w = false;
        this.f2940x = false;
        h(context);
    }

    public void a(int i8) {
        Drawable background = getBackground();
        if (!this.f2937u || i8 <= 0) {
            setBackgroundDrawable(background);
            return;
        }
        Drawable drawableR = a.r(background.mutate());
        a.n(drawableR, e0.a.b(getContext(), i8));
        setBackgroundDrawable(drawableR);
    }

    public void b(int i8) {
        a(i8 == 1 ? this.f2921e : this.f2922f);
    }

    public void c(int i8, int i9) {
        Drawable drawableF = f(i8);
        if (!this.f2938v || i9 <= 0) {
            j(i8, drawableF);
            return;
        }
        Drawable drawableR = a.r(drawableF.mutate());
        a.n(drawableR, e0.a.b(getContext(), i9));
        j(i8, drawableR);
    }

    public void d(int i8) {
        c(this.f2920d, i8 == 1 ? this.f2923g : this.f2924h);
    }

    public final void e(boolean z7) {
        int i8;
        d(z7 ? 1 : 0);
        b(z7 ? 1 : 0);
        int i9 = this.f2926j;
        if (i9 == -2 || (i8 = this.f2925i) == -2) {
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
        this.f2921e = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_bg_open_state, this.f2921e);
        this.f2922f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_bg_close_state, this.f2922f);
        this.f2923g = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_drawable_open_state, this.f2923g);
        this.f2924h = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_color_drawable_close_state, this.f2924h);
        this.f2925i = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_text_size_normal, this.f2925i);
        this.f2926j = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_text_size_checked, this.f2926j);
        this.f2927k = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_drawable_width, this.f2927k);
        this.f2928l = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_drawable_height, this.f2928l);
        this.f2929m = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_rbtn_red_dot_color, this.f2929m);
        this.f2930n = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_width, this.f2930n);
        this.f2931o = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_height, this.f2931o);
        this.f2932p = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_margin_start, this.f2932p);
        this.f2933q = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_margin_end, this.f2933q);
        this.f2934r = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_margin_top, this.f2934r);
        this.f2935s = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.tint_rbtn_red_dot_margin_bottom, this.f2935s);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void h(Context context) {
        Paint paint = new Paint();
        this.f2941y = paint;
        paint.setStrokeWidth(2.0f);
        this.f2941y.setAntiAlias(true);
        int color = context.getResources().getColor(R$color.color_red_dot);
        if (this.f2929m != -2) {
            color = context.getResources().getColor(this.f2929m);
        }
        this.f2941y.setColor(color);
    }

    public final void i() {
        if (this.f2927k == -2 || this.f2928l == -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        for (Drawable drawable : compoundDrawables) {
            if (drawable != null) {
                drawable.setBounds(0, 0, this.f2927k, this.f2928l);
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

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f2936t = false;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        int i8;
        super.onDraw(canvas);
        if (!this.f2939w || (i8 = this.f2930n) <= 0) {
            return;
        }
        float f8 = i8 / 2;
        canvas.drawCircle(this.f2932p + f8, this.f2934r + f8, f8, this.f2941y);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        i();
        if (this.f2920d != -2) {
            return;
        }
        Drawable[] compoundDrawables = getCompoundDrawables();
        int i12 = 0;
        while (true) {
            if (i12 >= compoundDrawables.length) {
                break;
            }
            if (compoundDrawables[i12] != null) {
                this.f2920d = i12;
                break;
            }
            i12++;
        }
        this.f2936t = true;
        e(this.f2940x);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z7) {
        this.f2940x = z7;
        super.setChecked(z7);
        if (this.f2936t) {
            e(z7);
            this.f2940x = false;
        }
    }

    public void setRedDotVisibility(boolean z7) {
        this.f2939w = z7;
        invalidate();
    }

    public void setSupportBgTint(boolean z7) {
        this.f2937u = z7;
    }

    public void setSupportSrcTint(boolean z7) {
        this.f2938v = z7;
    }

    public TintRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2920d = -2;
        this.f2921e = -2;
        this.f2922f = -2;
        this.f2923g = -2;
        this.f2924h = -2;
        this.f2925i = -2;
        this.f2926j = -2;
        this.f2927k = -2;
        this.f2928l = -2;
        this.f2929m = -2;
        this.f2930n = -2;
        this.f2931o = -2;
        this.f2932p = -2;
        this.f2933q = -2;
        this.f2934r = -2;
        this.f2935s = -2;
        this.f2936t = false;
        this.f2937u = true;
        this.f2938v = true;
        this.f2939w = false;
        this.f2940x = false;
        g(context, attributeSet);
        h(context);
    }

    public TintRadioButton(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2920d = -2;
        this.f2921e = -2;
        this.f2922f = -2;
        this.f2923g = -2;
        this.f2924h = -2;
        this.f2925i = -2;
        this.f2926j = -2;
        this.f2927k = -2;
        this.f2928l = -2;
        this.f2929m = -2;
        this.f2930n = -2;
        this.f2931o = -2;
        this.f2932p = -2;
        this.f2933q = -2;
        this.f2934r = -2;
        this.f2935s = -2;
        this.f2936t = false;
        this.f2937u = true;
        this.f2938v = true;
        this.f2939w = false;
        this.f2940x = false;
        g(context, attributeSet);
        h(context);
    }
}
