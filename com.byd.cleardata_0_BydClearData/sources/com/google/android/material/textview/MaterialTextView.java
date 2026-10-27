package com.google.android.material.textview;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.y;
import b.a.a.a.l;
import b.a.a.a.y.b;
import b.a.a.a.y.c;
import com.google.android.material.theme.a.a;

/* JADX INFO: loaded from: classes.dex */
public class MaterialTextView extends y {
    public MaterialTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public MaterialTextView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public MaterialTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        int iH;
        super(a.c(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        if (g(context2)) {
            Resources.Theme theme = context2.getTheme();
            if (j(context2, theme, attributeSet, i, i2) || (iH = h(theme, attributeSet, i, i2)) == -1) {
                return;
            }
            f(theme, iH);
        }
    }

    private void f(Resources.Theme theme, int i) {
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(i, l.w2);
        int i2 = i(getContext(), typedArrayObtainStyledAttributes, l.x2, l.y2);
        typedArrayObtainStyledAttributes.recycle();
        if (i2 >= 0) {
            setLineHeight(i2);
        }
    }

    private static boolean g(Context context) {
        return b.b(context, b.a.a.a.b.A, true);
    }

    private static int h(Resources.Theme theme, AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, l.z2, i, i2);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(l.A2, -1);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId;
    }

    private static int i(Context context, TypedArray typedArray, int... iArr) {
        int iC = -1;
        for (int i = 0; i < iArr.length && iC < 0; i++) {
            iC = c.c(context, typedArray, iArr[i], -1);
        }
        return iC;
    }

    private static boolean j(Context context, Resources.Theme theme, AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, l.z2, i, i2);
        int i3 = i(context, typedArrayObtainStyledAttributes, l.B2, l.C2);
        typedArrayObtainStyledAttributes.recycle();
        return i3 != -1;
    }

    @Override // androidx.appcompat.widget.y, android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (g(context)) {
            f(context.getTheme(), i);
        }
    }
}
