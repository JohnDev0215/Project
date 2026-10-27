package com.google.android.material.datepicker;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;

/* JADX INFO: compiled from: MaterialDatePicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<S> extends androidx.fragment.app.b {
    static final Object k0 = "CONFIRM_BUTTON_TAG";
    static final Object l0 = "CANCEL_BUTTON_TAG";
    static final Object m0 = "TOGGLE_BUTTON_TAG";

    static boolean t1(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(c.b.a.a.t.b.c(context, c.b.a.a.b.y, MaterialCalendar.class.getCanonicalName()), new int[]{R.attr.windowFullscreen});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }
}
