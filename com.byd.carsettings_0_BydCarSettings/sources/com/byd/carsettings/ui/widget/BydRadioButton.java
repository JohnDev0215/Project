package com.byd.carsettings.ui.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.RadioButton;

/* JADX INFO: loaded from: classes2.dex */
public class BydRadioButton extends RadioButton {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Drawable f3361a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Drawable f3362b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Drawable f3363c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Drawable f3364d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f3365e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f3366f;
    public int g;
    public int h;
    public int i;

    public BydRadioButton(Context context) {
        this(context, null);
    }

    public final void a(int i, int i2, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (drawable != null) {
            drawable.setBounds(0, 0, i, i2);
        }
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, i, i2);
        }
        if (drawable3 != null) {
            drawable3.setBounds(0, 0, i, i2);
        }
        if (drawable4 != null) {
            drawable4.setBounds(0, 0, i, i2);
        }
        if (drawable5 != null) {
            drawable5.setBounds(0, 0, i, i2);
        }
        if (drawable6 != null) {
            drawable6.setBounds(0, 0, i, i2);
        }
        if (isLayoutRtl()) {
            if (drawable6 != null) {
                drawable3 = drawable6;
            }
            if (drawable5 != null) {
                drawable = drawable5;
            }
            setCompoundDrawables(drawable3, drawable2, drawable, drawable4);
            return;
        }
        if (drawable5 != null) {
            drawable = drawable5;
        }
        if (drawable6 != null) {
            drawable3 = drawable6;
        }
        setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    public boolean isLayoutRtl() {
        return getLayoutDirection() == 1;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        a(this.i, this.h, this.f3363c, this.f3361a, this.f3362b, this.f3366f, this.f3364d, this.f3365e);
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.widget.RadioButton, android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        if (isChecked()) {
            return;
        }
        super.toggle();
    }

    public BydRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.radioButtonStyle);
    }

    public BydRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = (int) TypedValue.applyDimension(1, 30.0f, new DisplayMetrics());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.byd.carsettings.R.styleable.BydRadioButton);
        this.f3361a = typedArrayObtainStyledAttributes.getDrawable(0);
        this.f3362b = typedArrayObtainStyledAttributes.getDrawable(3);
        this.f3363c = typedArrayObtainStyledAttributes.getDrawable(2);
        this.f3364d = typedArrayObtainStyledAttributes.getDrawable(4);
        this.f3365e = typedArrayObtainStyledAttributes.getDrawable(5);
        this.f3366f = typedArrayObtainStyledAttributes.getDrawable(1);
        this.h = (int) typedArrayObtainStyledAttributes.getDimension(7, this.g);
        this.i = (int) typedArrayObtainStyledAttributes.getDimension(8, this.g);
        typedArrayObtainStyledAttributes.recycle();
    }
}
