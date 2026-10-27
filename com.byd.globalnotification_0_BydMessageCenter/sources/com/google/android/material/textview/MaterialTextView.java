package com.google.android.material.textview;

import a.b.k.l;
import a.b.p.z;
import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import b.d.a.a.b;
import b.d.a.a.i0.a.a;
import b.d.a.a.k;

/* JADX INFO: loaded from: classes.dex */
public class MaterialTextView extends z {
    public MaterialTextView(Context context, AttributeSet attributeSet) {
        super(a.a(context, attributeSet, R.attr.textViewStyle, 0), attributeSet, R.attr.textViewStyle);
        Context context2 = getContext();
        if (l.i.i0(context2, b.textAppearanceLineHeightEnabled, true)) {
            Resources.Theme theme = context2.getTheme();
            TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, k.MaterialTextView, R.attr.textViewStyle, 0);
            int iG = g(context2, typedArrayObtainStyledAttributes, k.MaterialTextView_android_lineHeight, k.MaterialTextView_lineHeight);
            typedArrayObtainStyledAttributes.recycle();
            if (iG != -1) {
                return;
            }
            TypedArray typedArrayObtainStyledAttributes2 = theme.obtainStyledAttributes(attributeSet, k.MaterialTextView, R.attr.textViewStyle, 0);
            int resourceId = typedArrayObtainStyledAttributes2.getResourceId(k.MaterialTextView_android_textAppearance, -1);
            typedArrayObtainStyledAttributes2.recycle();
            if (resourceId != -1) {
                f(theme, resourceId);
            }
        }
    }

    public static int g(Context context, TypedArray typedArray, int... iArr) {
        int dimensionPixelSize = -1;
        for (int i = 0; i < iArr.length && dimensionPixelSize < 0; i++) {
            int i2 = iArr[i];
            TypedValue typedValue = new TypedValue();
            if (typedArray.getValue(i2, typedValue) && typedValue.type == 2) {
                TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
                int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, -1);
                typedArrayObtainStyledAttributes.recycle();
                dimensionPixelSize = dimensionPixelSize2;
            } else {
                dimensionPixelSize = typedArray.getDimensionPixelSize(i2, -1);
            }
        }
        return dimensionPixelSize;
    }

    public final void f(Resources.Theme theme, int i) {
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(i, k.MaterialTextAppearance);
        int iG = g(getContext(), typedArrayObtainStyledAttributes, k.MaterialTextAppearance_android_lineHeight, k.MaterialTextAppearance_lineHeight);
        typedArrayObtainStyledAttributes.recycle();
        if (iG >= 0) {
            setLineHeight(iG);
        }
    }

    @Override // a.b.p.z, android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (l.i.i0(context, b.textAppearanceLineHeightEnabled, true)) {
            f(context.getTheme(), i);
        }
    }
}
