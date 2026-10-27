package com.google.android.material.resources;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import com.google.android.material.R;

/* JADX INFO: loaded from: classes8.dex */
public class MaterialAttributes {
    public static TypedValue resolveAttributeOrThrow(View componentView, int attributeResId) {
        return resolveAttributeOrThrow(componentView.getContext(), attributeResId, componentView.getClass().getCanonicalName());
    }

    public static boolean resolveBooleanAttributeOrThrow(Context context, int attributeResId, String errorMessageComponent) {
        return resolveAttributeOrThrow(context, attributeResId, errorMessageComponent).data != 0;
    }

    public static boolean resolveBooleanAttribute(Context context, int attributeResId) {
        TypedValue typedValue = resolveAttribute(context, attributeResId);
        return (typedValue == null || typedValue.data == 0) ? false : true;
    }

    public static TypedValue resolveAttributeOrThrow(Context context, int attributeResId, String errorMessageComponent) {
        TypedValue typedValue = resolveAttribute(context, attributeResId);
        if (typedValue == null) {
            throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", errorMessageComponent, context.getResources().getResourceName(attributeResId)));
        }
        return typedValue;
    }

    public static TypedValue resolveAttribute(Context context, int attributeResId) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(attributeResId, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static int resolveMinimumAccessibleTouchTarget(Context context) {
        TypedValue minTouchTargetSizeValue = resolveAttribute(context, R.attr.minTouchTargetSize);
        if (minTouchTargetSizeValue == null) {
            return (int) context.getResources().getDimension(R.dimen.mtrl_min_touch_target_size);
        }
        return (int) minTouchTargetSizeValue.getDimension(context.getResources().getDisplayMetrics());
    }
}
