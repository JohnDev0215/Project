package android.ext.services.autofill;

import android.os.Bundle;
import android.view.autofill.AutofillValue;
import com.google.android.textclassifier.AnnotatorModel;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
final class EditDistanceScorer {
    static float calculateScore(AutofillValue autofillValue, String str, Bundle bundle) {
        if (autofillValue == null || !autofillValue.isText() || str == null) {
            return AnnotatorModel.INVALID_LOCATION_ACCURACY_METERS;
        }
        String string = autofillValue.getTextValue().toString();
        int length = string.length();
        int length2 = str.length();
        if (length2 == 0) {
            if (length == 0) {
                return 1.0f;
            }
            return AnnotatorModel.INVALID_LOCATION_ACCURACY_METERS;
        }
        int iEditDistance = editDistance(string.toLowerCase(), str.toLowerCase());
        float fMax = Math.max(length, length2);
        return (fMax - iEditDistance) / fMax;
    }

    public static int editDistance(String str, String str2) {
        return editDistance(str, str2, Integer.MAX_VALUE);
    }

    public static int editDistance(String str, String str2, int i) {
        if (str.equals(str2)) {
            return 0;
        }
        if (Math.abs(str.length() - str2.length()) > i) {
            return Integer.MAX_VALUE;
        }
        int length = str.length();
        int length2 = str2.length();
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, length + 1, length2 + 1);
        for (int i2 = 0; i2 <= length; i2++) {
            iArr[i2][0] = i2;
        }
        for (int i3 = 0; i3 <= length2; i3++) {
            iArr[0][i3] = i3;
        }
        for (int i4 = 1; i4 <= length2; i4++) {
            for (int i5 = 1; i5 <= length; i5++) {
                int i6 = i5 - 1;
                int i7 = i4 - 1;
                if (str.charAt(i6) == str2.charAt(i7)) {
                    iArr[i5][i4] = iArr[i6][i7];
                } else {
                    iArr[i5][i4] = Math.min(iArr[i6][i4] + 1, Math.min(iArr[i5][i7] + 1, iArr[i6][i7] + 1));
                }
            }
        }
        return iArr[length][length2];
    }
}
