package defpackage;

import android.widget.TextView;
import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutAutofillProfile;

/* JADX INFO: compiled from: AutofillProfileItemViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hb {
    public static String a(String str) {
        return !str.isEmpty() ? str.concat(",") : "";
    }

    public static String b(FastCheckoutAutofillProfile fastCheckoutAutofillProfile) {
        StringBuilder sb = new StringBuilder();
        sb.append(fastCheckoutAutofillProfile.getLocality());
        if (!fastCheckoutAutofillProfile.getLocality().isEmpty() && !fastCheckoutAutofillProfile.getPostalCode().isEmpty()) {
            sb.append(", ");
        }
        sb.append(fastCheckoutAutofillProfile.getPostalCode());
        return sb.toString();
    }

    public static void c(TextView textView) {
        textView.setVisibility(textView.length() == 0 ? 8 : 0);
    }
}
