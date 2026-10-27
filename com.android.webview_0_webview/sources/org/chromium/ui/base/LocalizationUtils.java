package org.chromium.ui.base;

import defpackage.Is;
import java.util.Locale;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class LocalizationUtils {
    public static Boolean a;

    public static String getDisplayNameForLocale(Locale locale, Locale locale2) {
        return locale.getDisplayName(locale2);
    }

    public static Locale getJavaLocale(String str, String str2, String str3) {
        return new Locale(str, str2, str3);
    }

    public static boolean isLayoutRtl() {
        if (a == null) {
            a = Boolean.valueOf(Is.a.getResources().getConfiguration().getLayoutDirection() == 1);
        }
        return a.booleanValue();
    }
}
