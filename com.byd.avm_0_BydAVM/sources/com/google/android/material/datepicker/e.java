package com.google.android.material.datepicker;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;

/* JADX INFO: compiled from: MaterialDatePicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<S> extends androidx.fragment.app.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Object f2332b = "CONFIRM_BUTTON_TAG";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Object f2333c = "CANCEL_BUTTON_TAG";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final Object f2334d = "TOGGLE_BUTTON_TAG";

    static boolean a(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(c.a.a.a.t.b.c(context, c.a.a.a.b.y, MaterialCalendar.class.getCanonicalName()), new int[]{R.attr.windowFullscreen});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }
}
