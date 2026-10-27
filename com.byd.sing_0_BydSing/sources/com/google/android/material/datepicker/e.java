package com.google.android.material.datepicker;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;

/* JADX INFO: compiled from: MaterialDatePicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<S> extends androidx.fragment.app.b {
    static final Object Z = "CONFIRM_BUTTON_TAG";
    static final Object a0 = "CANCEL_BUTTON_TAG";
    static final Object b0 = "TOGGLE_BUTTON_TAG";

    static boolean m1(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(d.b.a.a.t.b.c(context, d.b.a.a.b.y, MaterialCalendar.class.getCanonicalName()), new int[]{R.attr.windowFullscreen});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }
}
