package com.android.inputmethod.latin.utils;

import com.android.inputmethod.latin.SuggestedWords;

/* JADX INFO: loaded from: classes2.dex */
public final class AutoCorrectionUtils {
    private static final boolean DBG = false;
    private static final String TAG = "AutoCorrectionUtils";

    private AutoCorrectionUtils() {
    }

    public static boolean suggestionExceedsThreshold(final SuggestedWords.SuggestedWordInfo suggestion, final String consideredWord, final float threshold) {
        if (suggestion != null) {
            if (suggestion.isKindOf(3)) {
                return true;
            }
            if (!suggestion.isAprapreateForAutoCorrection()) {
                return false;
            }
            if (BinaryDictionaryUtils.calcNormalizedScore(consideredWord, suggestion.mWord, suggestion.mScore) >= threshold) {
                return true;
            }
        }
        return false;
    }
}
