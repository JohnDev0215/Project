package com.facebook.react.views.text;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;

/* JADX INFO: loaded from: classes6.dex */
public final class DefaultStyleValuesUtil {
    private DefaultStyleValuesUtil() {
        throw new AssertionError("Never invoke this for an Utility class!");
    }

    public static ColorStateList getDefaultTextColorHint(Context context) {
        return getDefaultTextAttribute(context, R.attr.textColorHint);
    }

    public static ColorStateList getDefaultTextColor(Context context) {
        return getDefaultTextAttribute(context, R.attr.textColor);
    }

    public static int getDefaultTextColorHighlight(Context context) {
        return getDefaultTextAttribute(context, R.attr.textColorHighlight).getDefaultColor();
    }

    private static ColorStateList getDefaultTextAttribute(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = null;
        try {
            typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
            ColorStateList colorStateList = typedArrayObtainStyledAttributes.getColorStateList(0);
            typedArrayObtainStyledAttributes.recycle();
            return colorStateList;
        } catch (Throwable th) {
            if (typedArrayObtainStyledAttributes != null) {
                typedArrayObtainStyledAttributes.recycle();
            }
            throw th;
        }
    }
}
