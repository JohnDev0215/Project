package com.facebook.react.modules.i18nmanager;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.core.text.TextUtilsCompat;
import java.util.Locale;

/* JADX INFO: loaded from: classes6.dex */
public class I18nUtil {
    private static I18nUtil sharedI18nUtilInstance;

    private I18nUtil() {
    }

    public static I18nUtil getInstance() {
        if (sharedI18nUtilInstance == null) {
            sharedI18nUtilInstance = new I18nUtil();
        }
        return sharedI18nUtilInstance;
    }

    public boolean isRTL(Context context) {
        if (isRTLForced(context)) {
            return true;
        }
        return isRTLAllowed(context) && isDevicePreferredLanguageRTL();
    }

    private boolean isRTLAllowed(Context context) {
        return isPrefSet(context, "RCTI18nUtil_allowRTL", true);
    }

    public void allowRTL(Context context, boolean z) {
        setPref(context, "RCTI18nUtil_allowRTL", z);
    }

    public boolean doLeftAndRightSwapInRTL(Context context) {
        return isPrefSet(context, "RCTI18nUtil_makeRTLFlipLeftAndRightStyles", true);
    }

    public void swapLeftAndRightInRTL(Context context, boolean z) {
        setPref(context, "RCTI18nUtil_makeRTLFlipLeftAndRightStyles", z);
    }

    private boolean isRTLForced(Context context) {
        return isPrefSet(context, "RCTI18nUtil_forceRTL", false);
    }

    public void forceRTL(Context context, boolean z) {
        setPref(context, "RCTI18nUtil_forceRTL", z);
    }

    private boolean isDevicePreferredLanguageRTL() {
        return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }

    private boolean isPrefSet(Context context, String str, boolean z) {
        return context.getSharedPreferences("com.facebook.react.modules.i18nmanager.I18nUtil", 0).getBoolean(str, z);
    }

    private void setPref(Context context, String str, boolean z) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.facebook.react.modules.i18nmanager.I18nUtil", 0).edit();
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
    }
}
