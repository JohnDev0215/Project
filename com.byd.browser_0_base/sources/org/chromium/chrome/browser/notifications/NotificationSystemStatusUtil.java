package org.chromium.chrome.browser.notifications;

import defpackage.ro1;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationSystemStatusUtil {
    @CalledByNative
    public static int getAppNotificationStatus() {
        return new ro1(xz.a).a() ? 2 : 3;
    }
}
