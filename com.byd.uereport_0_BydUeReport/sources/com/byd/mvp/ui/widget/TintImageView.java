package com.byd.mvp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.byd.mvp.R$styleable;
import h0.a;

/* JADX INFO: loaded from: classes2.dex */
public class TintImageView extends ImageView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2917d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2918e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2919f;

    public TintImageView(Context context) {
        super(context);
        this.f2917d = -1;
        this.f2918e = -1;
        this.f2919f = -1;
        d(context);
    }

    public void a(int i8) {
        Drawable background = getBackground();
        if (i8 == -1) {
            setBackgroundDrawable(background);
            return;
        }
        Drawable drawableR = a.r(background.mutate());
        a.n(drawableR, e0.a.b(getContext(), i8));
        setBackgroundDrawable(drawableR);
    }

    public void b(int i8) {
        if (i8 == -1) {
            a(this.f2919f);
        } else if (i8 == 0) {
            a(this.f2918e);
        } else {
            if (i8 != 1) {
                return;
            }
            a(this.f2917d);
        }
    }

    public final void c(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.tint_imageview);
        this.f2917d = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_imageview_color_open_state, this.f2917d);
        this.f2918e = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_imageview_color_close_state, this.f2918e);
        this.f2919f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.tint_imageview_color_err_state, this.f2919f);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void d(Context context) {
    }

    public TintImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2917d = -1;
        this.f2918e = -1;
        this.f2919f = -1;
        c(context, attributeSet);
        d(context);
    }

    public TintImageView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2917d = -1;
        this.f2918e = -1;
        this.f2919f = -1;
        c(context, attributeSet);
        d(context);
    }
}
