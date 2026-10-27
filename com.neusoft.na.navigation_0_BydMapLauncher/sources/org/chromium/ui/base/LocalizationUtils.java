package org.chromium.ui.base;

import java.util.Locale;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes4.dex */
@JNINamespace("l10n_util")
public class LocalizationUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int LEFT_TO_RIGHT = 2;
    public static final int RIGHT_TO_LEFT = 1;
    public static final int UNKNOWN_DIRECTION = 0;
    private static Boolean sIsLayoutRtl;

    private static native int nativeGetFirstStrongCharacterDirection(String str);

    private LocalizationUtils() {
    }

    private static Locale getJavaLocale(String str, String str2, String str3) {
        return new Locale(str, str2, str3);
    }

    private static String getDisplayNameForLocale(Locale locale, Locale locale2) {
        return locale.getDisplayName(locale2);
    }

    public static boolean isLayoutRtl() {
        if (sIsLayoutRtl == null) {
            sIsLayoutRtl = Boolean.valueOf(ApiCompatibilityUtils.getLayoutDirection(ContextUtils.getApplicationContext().getResources().getConfiguration()) == 1);
        }
        return sIsLayoutRtl.booleanValue();
    }

    public static void setRtlForTesting(boolean z) {
        sIsLayoutRtl = Boolean.valueOf(z);
    }

    public static int getFirstStrongCharacterDirection(String str) {
        return nativeGetFirstStrongCharacterDirection(str);
    }
}
