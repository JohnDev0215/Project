package com.google.android.material.datepicker;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;

/* JADX INFO: loaded from: classes.dex */
public final class h<S> extends androidx.fragment.app.c {
    static final Object Z = "CONFIRM_BUTTON_TAG";
    static final Object a0 = "CANCEL_BUTTON_TAG";
    static final Object b0 = "TOGGLE_BUTTON_TAG";

    static boolean z1(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(b.a.a.a.y.b.c(context, b.a.a.a.b.r, g.class.getCanonicalName()), new int[]{R.attr.windowFullscreen});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }
}
