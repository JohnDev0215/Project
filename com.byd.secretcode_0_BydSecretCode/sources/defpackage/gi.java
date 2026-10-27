package defpackage;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import com.google.android.material.R$attr;
import com.google.android.material.datepicker.MaterialCalendar;

/* JADX INFO: compiled from: MaterialDatePicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class gi<S> extends ib {
    public static final Object b = "CONFIRM_BUTTON_TAG";
    public static final Object c = "CANCEL_BUTTON_TAG";
    public static final Object d = "TOGGLE_BUTTON_TAG";

    public static boolean a(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(rj.c(context, R$attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), new int[]{R.attr.windowFullscreen});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }
}
