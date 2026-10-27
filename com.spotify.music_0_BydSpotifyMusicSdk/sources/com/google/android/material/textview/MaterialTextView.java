package com.google.android.material.textview;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import p.f60;
import p.fa;
import p.he1;
import p.xz1;

/* JADX INFO: loaded from: classes.dex */
public class MaterialTextView extends fa {
    public MaterialTextView(Context context, AttributeSet attributeSet) {
        super(f60.S(context, attributeSet, R.attr.textViewStyle, 0), attributeSet, R.attr.textViewStyle);
        Context context2 = getContext();
        TypedValue typedValueN = he1.N(context2, com.spotify.music.R.attr.textAppearanceLineHeightEnabled);
        if (typedValueN != null && typedValueN.type == 18 && typedValueN.data == 0) {
            return;
        }
        Resources.Theme theme = context2.getTheme();
        int[] iArr = xz1.s;
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, iArr, R.attr.textViewStyle, 0);
        int iP = p(context2, typedArrayObtainStyledAttributes, 1, 2);
        typedArrayObtainStyledAttributes.recycle();
        if (iP != -1) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes2 = theme.obtainStyledAttributes(attributeSet, iArr, R.attr.textViewStyle, 0);
        int resourceId = typedArrayObtainStyledAttributes2.getResourceId(0, -1);
        typedArrayObtainStyledAttributes2.recycle();
        if (resourceId != -1) {
            TypedArray typedArrayObtainStyledAttributes3 = theme.obtainStyledAttributes(resourceId, xz1.r);
            int iP2 = p(getContext(), typedArrayObtainStyledAttributes3, 1, 2);
            typedArrayObtainStyledAttributes3.recycle();
            if (iP2 >= 0) {
                setLineHeight(iP2);
            }
        }
    }

    public static int p(Context context, TypedArray typedArray, int... iArr) {
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

    @Override // p.fa, android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        TypedValue typedValueN = he1.N(context, com.spotify.music.R.attr.textAppearanceLineHeightEnabled);
        if (typedValueN != null && typedValueN.type == 18 && typedValueN.data == 0) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(i, xz1.r);
        int iP = p(getContext(), typedArrayObtainStyledAttributes, 1, 2);
        typedArrayObtainStyledAttributes.recycle();
        if (iP >= 0) {
            setLineHeight(iP);
        }
    }
}
