package u5;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import com.google.android.material.R$attr;
import com.google.android.material.datepicker.MaterialCalendar;

/* JADX INFO: compiled from: MaterialDatePicker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e<S> extends v0.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f8262d = "CONFIRM_BUTTON_TAG";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f8263e = "CANCEL_BUTTON_TAG";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Object f8264f = "TOGGLE_BUTTON_TAG";

    public static boolean a(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(b6.b.c(context, R$attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), new int[]{R.attr.windowFullscreen});
        boolean z7 = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z7;
    }
}
