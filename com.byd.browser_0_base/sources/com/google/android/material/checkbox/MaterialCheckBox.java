package com.google.android.material.checkbox;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.oj2;
import defpackage.r83;
import defpackage.rc1;
import defpackage.uc1;
import defpackage.yn3;

/* JADX INFO: loaded from: classes.dex */
public class MaterialCheckBox extends AppCompatCheckBox {
    public static final int l = R$style.Widget_MaterialComponents_CompoundButton_CheckBox;
    public static final int[][] m = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    public ColorStateList i;
    public boolean j;
    public boolean k;

    public MaterialCheckBox(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.i == null) {
            int iP = oj2.p(this, R$attr.colorControlActivated);
            int iP2 = oj2.p(this, R$attr.colorSurface);
            int iP3 = oj2.p(this, R$attr.colorOnSurface);
            this.i = new ColorStateList(m, new int[]{oj2.J(iP2, iP, 1.0f), oj2.J(iP2, iP3, 0.54f), oj2.J(iP2, iP3, 0.38f), oj2.J(iP2, iP3, 0.38f)});
        }
        return this.i;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.j && getButtonTintList() == null) {
            setUseMaterialThemeColors(true);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        Drawable buttonDrawable;
        if (!this.k || !TextUtils.isEmpty(getText()) || (buttonDrawable = getButtonDrawable()) == null) {
            super.onDraw(canvas);
            return;
        }
        int width = ((getWidth() - buttonDrawable.getIntrinsicWidth()) / 2) * (yn3.d(this) ? -1 : 1);
        int iSave = canvas.save();
        canvas.translate(width, 0.0f);
        super.onDraw(canvas);
        canvas.restoreToCount(iSave);
        if (getBackground() != null) {
            Rect bounds = buttonDrawable.getBounds();
            getBackground().setHotspotBounds(bounds.left + width, bounds.top, bounds.right + width, bounds.bottom);
        }
    }

    public void setCenterIfNoTextEnabled(boolean z) {
        this.k = z;
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.j = z;
        if (z) {
            setButtonTintList(getMaterialThemeColorsTintList());
        } else {
            setButtonTintList(null);
        }
    }

    public MaterialCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.checkboxStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialCheckBox(Context context, AttributeSet attributeSet, int i) {
        int i2 = l;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        TypedArray typedArrayD = r83.d(context2, attributeSet, R$styleable.MaterialCheckBox, i, i2, new int[0]);
        int i3 = R$styleable.MaterialCheckBox_buttonTint;
        if (typedArrayD.hasValue(i3)) {
            setButtonTintList(rc1.a(context2, typedArrayD, i3));
        }
        this.j = typedArrayD.getBoolean(R$styleable.MaterialCheckBox_useMaterialThemeColors, false);
        this.k = typedArrayD.getBoolean(R$styleable.MaterialCheckBox_centerIfNoTextEnabled, true);
        typedArrayD.recycle();
    }
}
