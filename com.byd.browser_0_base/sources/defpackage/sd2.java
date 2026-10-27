package defpackage;

import android.content.Context;
import android.widget.TextView;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.safety_check.SafetyCheckSettingsFragment;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SafetyCheckViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sd2 {
    public static void a(PropertyModel propertyModel, SafetyCheckSettingsFragment safetyCheckSettingsFragment) {
        String quantityString;
        long jI = propertyModel.i(pd2.i);
        long jCurrentTimeMillis = System.currentTimeMillis();
        TextView textView = safetyCheckSettingsFragment.i0;
        Context contextK = safetyCheckSettingsFragment.K();
        if (jI == 0) {
            quantityString = "";
        } else {
            long j = jCurrentTimeMillis - jI;
            if (j < 60000) {
                quantityString = contextK.getString(R$string.safety_check_timestamp_after);
            } else if (j < 3600000) {
                int i = (int) (j / 60000);
                quantityString = contextK.getResources().getQuantityString(R$plurals.safety_check_timestamp_after_mins, i, Integer.valueOf(i));
            } else if (j < 86400000) {
                int i2 = (int) (j / 3600000);
                quantityString = contextK.getResources().getQuantityString(R$plurals.safety_check_timestamp_after_hours, i2, Integer.valueOf(i2));
            } else if (j < 172800000) {
                quantityString = contextK.getString(R$string.safety_check_timestamp_after_yesterday);
            } else {
                int i3 = (int) (j / 86400000);
                quantityString = contextK.getResources().getQuantityString(R$plurals.safety_check_timestamp_after_days, i3, Integer.valueOf(i3));
            }
        }
        textView.setText(quantityString);
    }
}
