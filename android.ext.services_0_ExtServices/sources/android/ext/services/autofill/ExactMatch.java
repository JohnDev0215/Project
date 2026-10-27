package android.ext.services.autofill;

import android.os.Bundle;
import android.view.autofill.AutofillValue;
import com.google.android.textclassifier.AnnotatorModel;

/* JADX INFO: loaded from: classes.dex */
final class ExactMatch {
    static float calculateScore(AutofillValue autofillValue, String str, Bundle bundle) {
        if (autofillValue == null || !autofillValue.isText() || str == null) {
            return AnnotatorModel.INVALID_LOCATION_ACCURACY_METERS;
        }
        String string = autofillValue.getTextValue().toString();
        if (bundle == null) {
            if (string.equalsIgnoreCase(str)) {
                return 1.0f;
            }
            return AnnotatorModel.INVALID_LOCATION_ACCURACY_METERS;
        }
        int i = bundle.getInt("MATCH_SUFFIX", -1);
        if (i < 0) {
            throw new IllegalArgumentException("suffix argument is invalid");
        }
        if (i < string.length()) {
            string = string.substring(string.length() - i);
        }
        if (i < str.length()) {
            str = str.substring(str.length() - i);
        }
        if (string.equalsIgnoreCase(str)) {
            return 1.0f;
        }
        return AnnotatorModel.INVALID_LOCATION_ACCURACY_METERS;
    }
}
