package org.chromium.ui.widget;

import android.R;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatButton;
import defpackage.bc2;
import defpackage.bz;
import org.chromium.ui.R$color;
import org.chromium.ui.R$dimen;
import org.chromium.ui.R$style;
import org.chromium.ui.R$styleable;

/* JADX INFO: loaded from: classes3.dex */
public class ButtonCompat extends AppCompatButton {
    public final bc2 h;

    public ButtonCompat() {
        throw null;
    }

    public ButtonCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$style.FilledButtonThemeOverlay);
    }

    private void setRaised(boolean z) {
        if (!z) {
            setElevation(0.0f);
            setStateListAnimator(null);
        } else {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, new int[]{R.attr.stateListAnimator}, 0, R.style.Widget.Material.Button);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
            typedArrayObtainStyledAttributes.recycle();
            setStateListAnimator(resourceId != 0 ? AnimatorInflater.loadStateListAnimator(getContext(), resourceId) : null);
        }
    }

    public void setButtonColor(ColorStateList colorStateList) {
        bc2 bc2Var = this.h;
        if (colorStateList == bc2Var.a) {
            return;
        }
        bc2Var.a = colorStateList;
        bc2Var.c.setColor(colorStateList);
    }

    public ButtonCompat(Context context, AttributeSet attributeSet, int i) {
        float[] fArr;
        super(new ContextThemeWrapper(context, i), attributeSet, R.attr.buttonStyle);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ButtonCompat, R.attr.buttonStyle, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ButtonCompat_buttonColor, R$color.blue_when_enabled_list);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ButtonCompat_rippleColor, -1);
        if (resourceId2 == -1) {
            resourceId2 = getContext().getColor(resourceId) == 0 ? R$color.text_button_ripple_color_list_baseline : R$color.filled_button_ripple_color;
        }
        int i2 = resourceId2;
        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ButtonCompat_borderColor, R.color.transparent);
        int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ButtonCompat_borderWidth, R$dimen.default_ripple_background_border_size);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ButtonCompat_buttonRaised, true);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ButtonCompat_verticalInset, getResources().getDimensionPixelSize(R$dimen.button_bg_vertical_inset));
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.button_compat_corner_radius);
        int dimensionPixelSize3 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ButtonCompat_rippleCornerRadiusTopStart, dimensionPixelSize2);
        int dimensionPixelSize4 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ButtonCompat_rippleCornerRadiusTopEnd, dimensionPixelSize2);
        int dimensionPixelSize5 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ButtonCompat_rippleCornerRadiusBottomStart, dimensionPixelSize2);
        int dimensionPixelSize6 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ButtonCompat_rippleCornerRadiusBottomEnd, dimensionPixelSize2);
        int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ButtonCompat_buttonTextColor, -1);
        if (resourceId5 != -1) {
            setTextColor(bz.b(getContext(), resourceId5));
        }
        if (getLayoutDirection() == 1) {
            float f = dimensionPixelSize4;
            float f2 = dimensionPixelSize3;
            float f3 = dimensionPixelSize5;
            float f4 = dimensionPixelSize6;
            fArr = new float[]{f, f, f2, f2, f3, f3, f4, f4};
        } else {
            float f5 = dimensionPixelSize3;
            float f6 = dimensionPixelSize4;
            float f7 = dimensionPixelSize6;
            float f8 = dimensionPixelSize5;
            fArr = new float[]{f5, f5, f6, f6, f7, f7, f8, f8};
        }
        typedArrayObtainStyledAttributes.recycle();
        this.h = new bc2(this, resourceId, i2, fArr, resourceId3, resourceId4, dimensionPixelSize);
        setRaised(z);
    }
}
