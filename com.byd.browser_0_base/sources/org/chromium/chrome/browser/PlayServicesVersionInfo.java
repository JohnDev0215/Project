package org.chromium.chrome.browser;

import defpackage.zi0;
import java.util.Locale;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class PlayServicesVersionInfo {
    @CalledByNative
    public static String getGmsInfo() {
        zi0.b.getClass();
        return String.format(Locale.US, "SDK=%s; Installed=%s; Access=%s", 12451000L, Long.valueOf(0), "none");
    }
}
