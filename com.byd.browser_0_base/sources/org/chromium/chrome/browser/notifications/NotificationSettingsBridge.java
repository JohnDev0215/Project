package org.chromium.chrome.browser.notifications;

import android.app.NotificationChannel;
import defpackage.xn2;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationSettingsBridge {

    public static class SiteChannel {
        public final String a;
        public final String b;
        public final long c;
        public final int d;

        public SiteChannel(int i, String str, String str2, long j) {
            this.a = str;
            this.b = str2;
            this.c = j;
            this.d = i;
        }

        @CalledByNative
        public String getId() {
            return this.a;
        }

        @CalledByNative
        public String getOrigin() {
            return this.b;
        }

        @CalledByNative
        public int getStatus() {
            return this.d;
        }

        @CalledByNative
        public long getTimestamp() {
            return this.c;
        }
    }

    @CalledByNative
    public static SiteChannel createChannel(String str, long j, boolean z) {
        return xn2.a.a.a(j, str, z);
    }

    @CalledByNative
    public static void deleteChannel(String str) {
        xn2.a.a.a.a(str);
    }

    @CalledByNative
    public static int getChannelStatus(String str) {
        NotificationChannel notificationChannelG = xn2.a.a.a.g(str);
        if (notificationChannelG == null) {
            return 2;
        }
        return notificationChannelG.getImportance() != 0 ? 0 : 1;
    }

    @CalledByNative
    public static SiteChannel[] getSiteChannels() {
        return xn2.a.a.d();
    }
}
