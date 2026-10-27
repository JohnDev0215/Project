package com.android.inputmethod.compat;

import android.graphics.drawable.Drawable;
import android.widget.TextView;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public final class TextViewCompatUtils {
    private static final Method METHOD_setCompoundDrawablesRelativeWithIntrinsicBounds = CompatUtils.getMethod(TextView.class, "setCompoundDrawablesRelativeWithIntrinsicBounds", Drawable.class, Drawable.class, Drawable.class, Drawable.class);

    private TextViewCompatUtils() {
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(final TextView textView, final Drawable start, final Drawable top, final Drawable end, final Drawable bottom) {
        Method method = METHOD_setCompoundDrawablesRelativeWithIntrinsicBounds;
        if (method == null) {
            textView.setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom);
        } else {
            CompatUtils.invoke(textView, null, method, start, top, end, bottom);
        }
    }
}
