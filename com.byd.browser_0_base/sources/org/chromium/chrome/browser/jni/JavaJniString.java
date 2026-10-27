package org.chromium.chrome.browser.jni;

import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$string;

/* JADX INFO: loaded from: classes.dex */
public class JavaJniString {
    @CalledByNative
    public static String blockCurrentPageString() {
        return xz.a.getString(R$string.block_web_address_current_page);
    }

    @CalledByNative
    public static String blockHitString() {
        return xz.a.getString(R$string.block_web_address_hit);
    }

    @CalledByNative
    public static String warningCloseCurrentPageString() {
        return xz.a.getString(R$string.warning_web_address_close_current_page);
    }

    @CalledByNative
    public static String warningContinueVisitingString() {
        return xz.a.getString(R$string.warning_web_address_continue_visiting_page);
    }

    @CalledByNative
    public static String warningHitString() {
        return xz.a.getString(R$string.warning_web_address_warning_hit);
    }

    @CalledByNative
    public static String warningMessageString() {
        return xz.a.getString(R$string.warning_web_address_message);
    }

    @CalledByNative
    public static String warningNoWidevineHitString() {
        return xz.a.getString(R$string.warning_web_no_widevine_warning_hit);
    }

    @CalledByNative
    public static String warningTitleString() {
        return xz.a.getString(R$string.warning_web_address_title);
    }
}
