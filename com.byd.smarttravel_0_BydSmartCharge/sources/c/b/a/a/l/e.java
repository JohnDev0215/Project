package c.b.a.a.l;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import com.google.android.material.R$attr;
import com.google.android.material.datepicker.MaterialCalendar;

/* JADX INFO: compiled from: MaterialDatePicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<S> extends b.k.a.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f1719b = "CONFIRM_BUTTON_TAG";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f1720c = "CANCEL_BUTTON_TAG";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f1721d = "TOGGLE_BUTTON_TAG";

    public static boolean a(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(c.b.a.a.s.b.c(context, R$attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), new int[]{R.attr.windowFullscreen});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }
}
