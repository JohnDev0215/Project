package com.byd.mvp.ui.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.RadioButton;
import com.byd.mvp.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class BydRadioButton extends RadioButton {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Drawable f2850d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f2851e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f2852f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f2853g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f2854h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Drawable f2855i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2856j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2857k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2858l;

    public BydRadioButton(Context context) {
        this(context, null);
    }

    public final void a(int i8, int i9, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (drawable != null) {
            drawable.setBounds(0, 0, i8, i9);
        }
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, i8, i9);
        }
        if (drawable3 != null) {
            drawable3.setBounds(0, 0, i8, i9);
        }
        if (drawable4 != null) {
            drawable4.setBounds(0, 0, i8, i9);
        }
        if (drawable5 != null) {
            drawable5.setBounds(0, 0, i8, i9);
        }
        if (drawable6 != null) {
            drawable6.setBounds(0, 0, i8, i9);
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
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        a(this.f2858l, this.f2857k, this.f2852f, this.f2850d, this.f2851e, this.f2855i, this.f2853g, this.f2854h);
        super.onLayout(z7, i8, i9, i10, i11);
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

    public BydRadioButton(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2856j = (int) TypedValue.applyDimension(1, 30.0f, new DisplayMetrics());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BydRadioButton);
        this.f2850d = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BydRadioButton_android_drawableTop);
        this.f2851e = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BydRadioButton_android_drawableRight);
        this.f2852f = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BydRadioButton_android_drawableLeft);
        this.f2853g = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BydRadioButton_android_drawableStart);
        this.f2854h = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BydRadioButton_android_drawableEnd);
        this.f2855i = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BydRadioButton_android_drawableBottom);
        this.f2857k = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydRadioButton_drawableHeight, this.f2856j);
        this.f2858l = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydRadioButton_drawableWidth, this.f2856j);
        typedArrayObtainStyledAttributes.recycle();
    }
}
