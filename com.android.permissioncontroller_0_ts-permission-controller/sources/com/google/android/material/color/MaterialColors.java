package com.google.android.material.color;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.resources.MaterialAttributes;

/* JADX INFO: loaded from: classes8.dex */
public class MaterialColors {
    public static final float ALPHA_DISABLED = 0.38f;
    public static final float ALPHA_DISABLED_LOW = 0.12f;
    public static final float ALPHA_FULL = 1.0f;
    public static final float ALPHA_LOW = 0.32f;
    public static final float ALPHA_MEDIUM = 0.54f;

    public static int getColor(View view, int colorAttributeResId) {
        return MaterialAttributes.resolveAttributeOrThrow(view, colorAttributeResId).data;
    }

    public static int getColor(Context context, int colorAttributeResId, String errorMessageComponent) {
        return MaterialAttributes.resolveAttributeOrThrow(context, colorAttributeResId, errorMessageComponent).data;
    }

    public static int getColor(View view, int colorAttributeResId, int defaultValue) {
        return getColor(view.getContext(), colorAttributeResId, defaultValue);
    }

    public static int getColor(Context context, int colorAttributeResId, int defaultValue) {
        TypedValue typedValue = MaterialAttributes.resolveAttribute(context, colorAttributeResId);
        if (typedValue != null) {
            return typedValue.data;
        }
        return defaultValue;
    }

    public static int layer(View view, int backgroundColorAttributeResId, int overlayColorAttributeResId) {
        return layer(view, backgroundColorAttributeResId, overlayColorAttributeResId, 1.0f);
    }

    public static int layer(View view, int backgroundColorAttributeResId, int overlayColorAttributeResId, float overlayAlpha) {
        int backgroundColor = getColor(view, backgroundColorAttributeResId);
        int overlayColor = getColor(view, overlayColorAttributeResId);
        return layer(backgroundColor, overlayColor, overlayAlpha);
    }

    public static int layer(int backgroundColor, int overlayColor, float overlayAlpha) {
        int computedAlpha = Math.round(Color.alpha(overlayColor) * overlayAlpha);
        int computedOverlayColor = ColorUtils.setAlphaComponent(overlayColor, computedAlpha);
        return layer(backgroundColor, computedOverlayColor);
    }

    public static int layer(int backgroundColor, int overlayColor) {
        return ColorUtils.compositeColors(overlayColor, backgroundColor);
    }
}
