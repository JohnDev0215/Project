package com.android.inputmethod.compat;

import android.provider.Settings;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public final class SettingsSecureCompatUtils {
    public static final String ACCESSIBILITY_SPEAK_PASSWORD;
    private static final Field FIELD_ACCESSIBILITY_SPEAK_PASSWORD;

    static {
        Field field = CompatUtils.getField(Settings.Secure.class, "ACCESSIBILITY_SPEAK_PASSWORD");
        FIELD_ACCESSIBILITY_SPEAK_PASSWORD = field;
        ACCESSIBILITY_SPEAK_PASSWORD = (String) CompatUtils.getFieldValue(null, null, field);
    }

    private SettingsSecureCompatUtils() {
    }
}
