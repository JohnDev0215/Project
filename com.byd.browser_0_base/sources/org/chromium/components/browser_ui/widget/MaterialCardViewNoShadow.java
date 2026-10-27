package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.material.elevation.ElevationOverlayProvider;
import defpackage.yp;

/* JADX INFO: loaded from: classes2.dex */
public class MaterialCardViewNoShadow extends FrameLayout {
    public MaterialCardViewNoShadow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialCardViewNoShadow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialCardViewNoShadow, i, R$style.MaterialCardStyle);
        float dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.MaterialCardViewNoShadow_cornerRadius, 0);
        float dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.MaterialCardViewNoShadow_cardElevation, 0);
        typedArrayObtainStyledAttributes.recycle();
        setBackgroundResource(R$drawable.card_with_corners_background);
        GradientDrawable gradientDrawable = (GradientDrawable) getBackground();
        gradientDrawable.setCornerRadius(dimensionPixelSize);
        Context context2 = getContext();
        String str = yp.a;
        ElevationOverlayProvider elevationOverlayProvider = new ElevationOverlayProvider(context2);
        gradientDrawable.setColor(elevationOverlayProvider.b(dimensionPixelSize2, elevationOverlayProvider.d));
    }
}
