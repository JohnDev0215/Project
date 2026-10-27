package defpackage;

import java.util.Arrays;
import java.util.List;
import org.chromium.base.library_loader.a;

/* JADX INFO: compiled from: DownloadNotificationUmaHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class gd0 {
    public static final List<String> a = Arrays.asList("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED", "org.chromium.chrome.browser.download.DOWNLOAD_OPEN", "org.chromium.chrome.browser.download.DOWNLOAD_CANCEL", "org.chromium.chrome.browser.download.DOWNLOAD_PAUSE", "org.chromium.chrome.browser.download.DOWNLOAD_RESUME");

    public static void a(int i) {
        if (a.l.d()) {
            oj2.U(i, 5, "Android.DownloadManager.ServiceStopped.DownloadForeground");
        }
    }
}
