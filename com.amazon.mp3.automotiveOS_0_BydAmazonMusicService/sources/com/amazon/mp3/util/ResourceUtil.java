package com.amazon.mp3.util;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes4.dex */
public class ResourceUtil {
    public static int getLoadingNotificationIconId() {
        return R.drawable.notification_icn_loading;
    }

    public static int getLowspaceErrorMessageStringId() {
        return R.string.dmusic_lowspace_error_generic;
    }

    public static int getLowspaceNotificationDescStringId() {
        return R.string.dmusic_lowspace_notification_desc_generic;
    }

    public static int getLowspaceWarningMessageStringId() {
        return R.string.dmusic_lowspace_warning_generic;
    }

    public static int getPlayingNotificationIconId() {
        return R.drawable.notification_icn_playing;
    }

    public static int getWarningNotificationIconId() {
        return R.drawable.notification_icn_warning;
    }

    public static int getNoConnectionStringId() {
        return ConnectivityUtil.isWifiOnlyDevice() ? R.string.dmusic_no_connection_desc_wifi_only : R.string.dmusic_no_connection_desc_generic;
    }

    public static int getGenericSyncNotificationMessageId() {
        return AmazonApplication.getCapabilities().shouldCloudBeSupported() ? R.string.dmusic_sync_first_time_notification : R.string.dmusic_sync_progress_notification_local_songs;
    }
}
