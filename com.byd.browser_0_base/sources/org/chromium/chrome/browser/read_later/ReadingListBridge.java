package org.chromium.chrome.browser.read_later;

import defpackage.lh;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.components.bookmarks.BookmarkId;

/* JADX INFO: loaded from: classes2.dex */
public final class ReadingListBridge {
    @CalledByNative
    public static String getNotificationText(int i) {
        return xz.a.getResources().getQuantityString(R$plurals.reading_list_reminder_notification_subtitle, i, Integer.valueOf(i));
    }

    @CalledByNative
    public static String getNotificationTitle() {
        return xz.a.getResources().getString(R$string.reading_list_reminder_notification_title);
    }

    @CalledByNative
    public static void openReadingListPage() {
        lh.d(null, new BookmarkId(2, 0L), false);
    }
}
