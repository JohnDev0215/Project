package android.ext.services.autofill;

import android.os.Bundle;
import android.view.autofill.AutofillValue;
import com.google.android.textclassifier.AnnotatorModel;

/* JADX INFO: loaded from: classes.dex */
final class CreditCardMatcher {
    static float calculateScore(AutofillValue autofillValue, String str, Bundle bundle) {
        if (autofillValue == null || !autofillValue.isText() || str == null || bundle == null) {
            return AnnotatorModel.INVALID_LOCATION_ACCURACY_METERS;
        }
        String string = autofillValue.getTextValue().toString();
        int i = bundle.getInt("REQUIRED_ARG_MIN_CC_LENGTH", -1);
        int i2 = bundle.getInt("REQUIRED_ARG_MAX_CC_LENGTH", -1);
        if (i < 0 || i2 < i) {
            throw new IllegalArgumentException("bad length arguments");
        }
        int length = string.length();
        int length2 = str.length();
        int i3 = bundle.getInt("OPTIONAL_ARG_SUFFIX_LENGTH", 4);
        if (i3 <= 0) {
            throw new IllegalArgumentException("bad suffix length argument");
        }
        if (length != i3 || length2 < i || length2 > i2 || length2 < length || !string.equalsIgnoreCase(str.substring(length2 - i3))) {
            return AnnotatorModel.INVALID_LOCATION_ACCURACY_METERS;
        }
        return 1.0f;
    }
}
