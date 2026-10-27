package org.chromium.base;

import android.os.StrictMode;
import java.util.TimeZone;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TimezoneUtils {
    public static String getDefaultTimeZoneId() {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        String id = TimeZone.getDefault().getID();
        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        return id;
    }
}
