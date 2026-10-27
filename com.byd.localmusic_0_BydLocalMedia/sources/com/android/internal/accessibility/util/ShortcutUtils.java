package com.android.internal.accessibility.util;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.accessibility.common.ShortcutConstants;
import java.util.StringJoiner;

/* JADX INFO: loaded from: classes4.dex */
public final class ShortcutUtils {
    private static final TextUtils.SimpleStringSplitter sStringColonSplitter = new TextUtils.SimpleStringSplitter(ShortcutConstants.SERVICES_SEPARATOR);

    private ShortcutUtils() {
    }

    public static void optInValueToSettings(Context context, int i, String str) {
        StringJoiner stringJoiner = new StringJoiner(String.valueOf(ShortcutConstants.SERVICES_SEPARATOR));
        String strConvertToKey = convertToKey(i);
        String string = Settings.Secure.getString(context.getContentResolver(), strConvertToKey);
        if (isComponentIdExistingInSettings(context, i, str)) {
            return;
        }
        if (!TextUtils.isEmpty(string)) {
            stringJoiner.add(string);
        }
        stringJoiner.add(str);
        Settings.Secure.putString(context.getContentResolver(), strConvertToKey, stringJoiner.toString());
    }

    public static void optOutValueFromSettings(Context context, int i, String str) {
        StringJoiner stringJoiner = new StringJoiner(String.valueOf(ShortcutConstants.SERVICES_SEPARATOR));
        String strConvertToKey = convertToKey(i);
        String string = Settings.Secure.getString(context.getContentResolver(), strConvertToKey);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        sStringColonSplitter.setString(string);
        while (sStringColonSplitter.hasNext()) {
            String next = sStringColonSplitter.next();
            if (!TextUtils.isEmpty(next) && !str.equals(next)) {
                stringJoiner.add(next);
            }
        }
        Settings.Secure.putString(context.getContentResolver(), strConvertToKey, stringJoiner.toString());
    }

    public static boolean isComponentIdExistingInSettings(Context context, int i, String str) {
        String string = Settings.Secure.getString(context.getContentResolver(), convertToKey(i));
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        sStringColonSplitter.setString(string);
        while (sStringColonSplitter.hasNext()) {
            if (str.equals(sStringColonSplitter.next())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isShortcutContained(Context context, int i, String str) {
        return ((AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE)).getAccessibilityShortcutTargets(i).contains(str);
    }

    public static String convertToKey(int i) {
        if (i == 1) {
            return Settings.Secure.ACCESSIBILITY_BUTTON_TARGETS;
        }
        if (i == 2) {
            return Settings.Secure.ACCESSIBILITY_SHORTCUT_TARGET_SERVICE;
        }
        if (i == 4) {
            return Settings.Secure.ACCESSIBILITY_DISPLAY_MAGNIFICATION_ENABLED;
        }
        throw new IllegalArgumentException("Unsupported user shortcut type: " + i);
    }

    public static int convertToUserType(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        throw new IllegalArgumentException("Unsupported shortcut type:" + i);
    }
}
