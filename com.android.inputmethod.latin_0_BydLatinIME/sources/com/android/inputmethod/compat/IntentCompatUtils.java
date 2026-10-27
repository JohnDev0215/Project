package com.android.inputmethod.compat;

import android.content.Intent;

/* JADX INFO: loaded from: classes2.dex */
public final class IntentCompatUtils {
    private static final String ACTION_USER_INITIALIZE = (String) CompatUtils.getFieldValue(null, null, CompatUtils.getField(Intent.class, "ACTION_USER_INITIALIZE"));

    private IntentCompatUtils() {
    }

    public static boolean is_ACTION_USER_INITIALIZE(final String action) {
        String str = ACTION_USER_INITIALIZE;
        return str != null && str.equals(action);
    }
}
