package com.android.inputmethod.latin.utils;

import android.content.Intent;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class IntentUtils {
    private static final String EXTRA_INPUT_METHOD_ID = "input_method_id";
    private static final String INPUT_METHOD_SUBTYPE_SETTINGS = "android.settings.INPUT_METHOD_SUBTYPE_SETTINGS";

    private IntentUtils() {
    }

    public static Intent getInputLanguageSelectionIntent(final String inputMethodId, final int flagsForSubtypeSettings) {
        Intent intent = new Intent(INPUT_METHOD_SUBTYPE_SETTINGS);
        if (!TextUtils.isEmpty(inputMethodId)) {
            intent.putExtra(EXTRA_INPUT_METHOD_ID, inputMethodId);
        }
        if (flagsForSubtypeSettings > 0) {
            intent.setFlags(flagsForSubtypeSettings);
        }
        return intent;
    }
}
