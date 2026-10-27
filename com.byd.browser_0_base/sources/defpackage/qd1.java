package defpackage;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import androidx.mediarouter.R$dimen;

/* JADX INFO: compiled from: MediaRouteDialogHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class qd1 {
    public static int a(Context context) {
        float fraction;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        boolean z = displayMetrics.widthPixels < displayMetrics.heightPixels;
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(z ? R$dimen.mr_dialog_fixed_width_minor : R$dimen.mr_dialog_fixed_width_major, typedValue, true);
        int i = typedValue.type;
        if (i == 5) {
            fraction = typedValue.getDimension(displayMetrics);
        } else {
            if (i != 6) {
                return -2;
            }
            int i2 = displayMetrics.widthPixels;
            fraction = typedValue.getFraction(i2, i2);
        }
        return (int) fraction;
    }
}
