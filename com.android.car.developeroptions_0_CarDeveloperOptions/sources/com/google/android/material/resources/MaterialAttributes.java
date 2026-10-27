package com.google.android.material.resources;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;

/* JADX INFO: loaded from: classes.dex */
public class MaterialAttributes {
    public static TypedValue resolveAttributeOrThrow(View view, int i) {
        return resolveAttributeOrThrow(view.getContext(), i, view.getClass().getCanonicalName());
    }

    public static boolean resolveBooleanAttribute(Context context, int i) {
        TypedValue typedValueResolveAttribute = resolveAttribute(context, i);
        return (typedValueResolveAttribute == null || typedValueResolveAttribute.data == 0) ? false : true;
    }

    public static TypedValue resolveAttributeOrThrow(Context context, int i, String str) {
        TypedValue typedValueResolveAttribute = resolveAttribute(context, i);
        if (typedValueResolveAttribute != null) {
            return typedValueResolveAttribute;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(i)));
    }

    public static TypedValue resolveAttribute(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static int resolveMinimumAccessibleTouchTarget(Context context) {
        float dimension;
        TypedValue typedValueResolveAttribute = resolveAttribute(context, R$attr.minTouchTargetSize);
        if (typedValueResolveAttribute == null) {
            dimension = context.getResources().getDimension(R$dimen.mtrl_min_touch_target_size);
        } else {
            dimension = typedValueResolveAttribute.getDimension(context.getResources().getDisplayMetrics());
        }
        return (int) dimension;
    }
}
