package com.android.inputmethod.compat;

import android.view.View;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public final class ViewCompatUtils {
    private static final Method METHOD_getPaddingEnd = CompatUtils.getMethod(View.class, "getPaddingEnd", new Class[0]);
    private static final Method METHOD_setPaddingRelative = CompatUtils.getMethod(View.class, "setPaddingRelative", Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);
    private static final Method METHOD_setTextAlignment = CompatUtils.getMethod(View.class, "setTextAlignment", Integer.TYPE);
    public static final int TEXT_ALIGNMENT_CENTER = 4;
    public static final int TEXT_ALIGNMENT_GRAVITY = 1;
    public static final int TEXT_ALIGNMENT_INHERIT = 0;
    public static final int TEXT_ALIGNMENT_TEXT_END = 3;
    public static final int TEXT_ALIGNMENT_TEXT_START = 2;
    public static final int TEXT_ALIGNMENT_VIEW_END = 6;
    public static final int TEXT_ALIGNMENT_VIEW_START = 5;

    private ViewCompatUtils() {
    }

    public static int getPaddingEnd(final View view) {
        Method method = METHOD_getPaddingEnd;
        if (method == null) {
            return view.getPaddingRight();
        }
        return ((Integer) CompatUtils.invoke(view, 0, method, new Object[0])).intValue();
    }

    public static void setPaddingRelative(final View view, final int start, final int top, final int end, final int bottom) {
        Method method = METHOD_setPaddingRelative;
        if (method == null) {
            view.setPadding(start, top, end, bottom);
        } else {
            CompatUtils.invoke(view, null, method, Integer.valueOf(start), Integer.valueOf(top), Integer.valueOf(end), Integer.valueOf(bottom));
        }
    }

    public static void setTextAlignment(final View view, final int textAlignment) {
        CompatUtils.invoke(view, null, METHOD_setTextAlignment, Integer.valueOf(textAlignment));
    }
}
