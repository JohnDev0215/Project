package com.google.android.material.textview;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.w;
import b3.b;
import b3.c;
import f3.a;
import o2.l;

/* JADX INFO: loaded from: classes.dex */
public class MaterialTextView extends w {
    public MaterialTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    private void f(Resources.Theme theme, int i7) {
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(i7, l.MaterialTextAppearance);
        int i8 = i(getContext(), typedArrayObtainStyledAttributes, l.MaterialTextAppearance_android_lineHeight, l.MaterialTextAppearance_lineHeight);
        typedArrayObtainStyledAttributes.recycle();
        if (i8 >= 0) {
            setLineHeight(i8);
        }
    }

    private static boolean g(Context context) {
        return b.b(context, o2.b.textAppearanceLineHeightEnabled, true);
    }

    private static int h(Resources.Theme theme, AttributeSet attributeSet, int i7, int i8) {
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, l.MaterialTextView, i7, i8);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(l.MaterialTextView_android_textAppearance, -1);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId;
    }

    private static int i(Context context, TypedArray typedArray, int... iArr) {
        int iC = -1;
        for (int i7 = 0; i7 < iArr.length && iC < 0; i7++) {
            iC = c.c(context, typedArray, iArr[i7], -1);
        }
        return iC;
    }

    private static boolean j(Context context, Resources.Theme theme, AttributeSet attributeSet, int i7, int i8) {
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, l.MaterialTextView, i7, i8);
        int i9 = i(context, typedArrayObtainStyledAttributes, l.MaterialTextView_android_lineHeight, l.MaterialTextView_lineHeight);
        typedArrayObtainStyledAttributes.recycle();
        return i9 != -1;
    }

    @Override // androidx.appcompat.widget.w, android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        if (g(context)) {
            f(context.getTheme(), i7);
        }
    }

    public MaterialTextView(Context context, AttributeSet attributeSet, int i7) {
        this(context, attributeSet, i7, 0);
    }

    public MaterialTextView(Context context, AttributeSet attributeSet, int i7, int i8) {
        int iH;
        super(a.c(context, attributeSet, i7, i8), attributeSet, i7);
        Context context2 = getContext();
        if (g(context2)) {
            Resources.Theme theme = context2.getTheme();
            if (j(context2, theme, attributeSet, i7, i8) || (iH = h(theme, attributeSet, i7, i8)) == -1) {
                return;
            }
            f(theme, iH);
        }
    }
}
