package com.byd.secretcode.view;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.RadioButton;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public class BydRadioButton extends RadioButton {
    public Drawable b;
    public Drawable c;
    public Drawable d;
    public Drawable e;
    public int f;
    public int g;
    public int h;

    public BydRadioButton(Context context) {
        this(context, null);
    }

    public final void a(int i, int i2, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
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
        setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
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
        this.f = (int) TypedValue.applyDimension(1, 30.0f, new DisplayMetrics());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.byd.secretcode.R.styleable.BydRadioButton);
        this.b = typedArrayObtainStyledAttributes.getDrawable(0);
        this.c = typedArrayObtainStyledAttributes.getDrawable(3);
        this.d = typedArrayObtainStyledAttributes.getDrawable(2);
        this.e = typedArrayObtainStyledAttributes.getDrawable(1);
        this.g = (int) typedArrayObtainStyledAttributes.getDimension(4, this.f);
        this.h = (int) typedArrayObtainStyledAttributes.getDimension(5, this.f);
        typedArrayObtainStyledAttributes.recycle();
        a(this.h, this.g, this.d, this.b, this.c, this.e);
    }
}
