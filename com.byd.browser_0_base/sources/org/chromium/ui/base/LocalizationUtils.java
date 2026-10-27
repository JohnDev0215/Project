package org.chromium.ui.base;

import defpackage.xz;
import java.util.Locale;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class LocalizationUtils {
    @CalledByNative
    public static String getDisplayNameForLocale(Locale locale, Locale locale2) {
        return locale.getDisplayName(locale2);
    }

    @CalledByNative
    public static Locale getJavaLocale(String str, String str2, String str3) {
        return new Locale(str, str2, str3);
    }

    @CalledByNative
    public static boolean isLayoutRtl() {
        return xz.a.getResources().getConfiguration().getLayoutDirection() == 1;
    }
}
