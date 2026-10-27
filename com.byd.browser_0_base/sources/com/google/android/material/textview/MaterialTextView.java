package com.google.android.material.textview;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.R$attr;
import com.google.android.material.R$styleable;
import defpackage.cc1;
import defpackage.rc1;
import defpackage.uc1;

/* JADX INFO: loaded from: classes.dex */
public class MaterialTextView extends AppCompatTextView {
    public MaterialTextView(Context context) {
        this(context, null);
    }

    public final void q(int i, Resources.Theme theme) {
        TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(i, R$styleable.MaterialTextAppearance);
        Context context = getContext();
        int[] iArr = {R$styleable.MaterialTextAppearance_android_lineHeight, R$styleable.MaterialTextAppearance_lineHeight};
        int iC = -1;
        for (int i2 = 0; i2 < 2 && iC < 0; i2++) {
            iC = rc1.c(context, typedArrayObtainStyledAttributes, iArr[i2], -1);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (iC >= 0) {
            setLineHeight(iC);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        TypedValue typedValueA = cc1.a(context, R$attr.textAppearanceLineHeightEnabled);
        if ((typedValueA != null && typedValueA.type == 18 && typedValueA.data == 0) ? false : true) {
            q(i, context.getTheme());
        }
    }

    public MaterialTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    public MaterialTextView(Context context, AttributeSet attributeSet, int i) {
        super(uc1.a(context, attributeSet, i, 0), attributeSet, i);
        Context context2 = getContext();
        TypedValue typedValueA = cc1.a(context2, R$attr.textAppearanceLineHeightEnabled);
        if ((typedValueA != null && typedValueA.type == 18 && typedValueA.data == 0) ? false : true) {
            Resources.Theme theme = context2.getTheme();
            int[] iArr = R$styleable.MaterialTextView;
            TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, iArr, i, 0);
            int[] iArr2 = {R$styleable.MaterialTextView_android_lineHeight, R$styleable.MaterialTextView_lineHeight};
            int iC = -1;
            for (int i2 = 0; i2 < 2 && iC < 0; i2++) {
                iC = rc1.c(context2, typedArrayObtainStyledAttributes, iArr2[i2], -1);
            }
            typedArrayObtainStyledAttributes.recycle();
            if (iC != -1) {
                return;
            }
            TypedArray typedArrayObtainStyledAttributes2 = theme.obtainStyledAttributes(attributeSet, iArr, i, 0);
            int resourceId = typedArrayObtainStyledAttributes2.getResourceId(R$styleable.MaterialTextView_android_textAppearance, -1);
            typedArrayObtainStyledAttributes2.recycle();
            if (resourceId != -1) {
                q(resourceId, theme);
            }
        }
    }
}
