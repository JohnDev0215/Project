package com.google.android.material.textview;

import K4.a;
import P8.n;
import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import b5.AbstractC0997a;
import r.C2218F;

/* JADX INFO: loaded from: classes.dex */
public class MaterialTextView extends C2218F {
    public MaterialTextView(Context context, AttributeSet attributeSet) {
        super(AbstractC0997a.a(context, attributeSet, R.attr.textViewStyle, 0), attributeSet, R.attr.textViewStyle);
        Context context2 = getContext();
        TypedValue typedValueS = n.S(context2, com.stingray.drivemodedetector.R.attr.textAppearanceLineHeightEnabled);
        if (typedValueS != null && typedValueS.type == 18 && typedValueS.data == 0) {
            return;
        }
        Resources.Theme theme = context2.getTheme();
        int[] iArr = a.f3595n;
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, iArr, R.attr.textViewStyle, 0);
        int iH = h(context2, typedArrayObtainStyledAttributes, 1, 2);
        typedArrayObtainStyledAttributes.recycle();
        if (iH != -1) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes2 = theme.obtainStyledAttributes(attributeSet, iArr, R.attr.textViewStyle, 0);
        int resourceId = typedArrayObtainStyledAttributes2.getResourceId(0, -1);
        typedArrayObtainStyledAttributes2.recycle();
        if (resourceId != -1) {
            TypedArray typedArrayObtainStyledAttributes3 = theme.obtainStyledAttributes(resourceId, a.f3594m);
            int iH2 = h(getContext(), typedArrayObtainStyledAttributes3, 1, 2);
            typedArrayObtainStyledAttributes3.recycle();
            if (iH2 >= 0) {
                setLineHeight(iH2);
            }
        }
    }

    public static int h(Context context, TypedArray typedArray, int... iArr) {
        int dimensionPixelSize = -1;
        for (int i7 = 0; i7 < iArr.length && dimensionPixelSize < 0; i7++) {
            int i10 = iArr[i7];
            TypedValue typedValue = new TypedValue();
            if (typedArray.getValue(i10, typedValue) && typedValue.type == 2) {
                TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
                int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, -1);
                typedArrayObtainStyledAttributes.recycle();
                dimensionPixelSize = dimensionPixelSize2;
            } else {
                dimensionPixelSize = typedArray.getDimensionPixelSize(i10, -1);
            }
        }
        return dimensionPixelSize;
    }

    @Override // r.C2218F, android.widget.TextView
    public final void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        TypedValue typedValueS = n.S(context, com.stingray.drivemodedetector.R.attr.textAppearanceLineHeightEnabled);
        if (typedValueS != null && typedValueS.type == 18 && typedValueS.data == 0) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(i7, a.f3594m);
        int iH = h(getContext(), typedArrayObtainStyledAttributes, 1, 2);
        typedArrayObtainStyledAttributes.recycle();
        if (iH >= 0) {
            setLineHeight(iH);
        }
    }
}
