package com.amazon.mp3.library.service.sync;

import android.content.ContentValues;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.library.item.TrackStatus;

/* JADX INFO: loaded from: classes3.dex */
public class SyncFilter {
    public static boolean shouldFilter(ContentValues values) {
        return filterByStatus(values) || filterByExtension(values);
    }

    private static boolean filterByExtension(ContentValues values) {
        return "wmv".equals(values.getAsString("extension"));
    }

    private static boolean filterByStatus(ContentValues values) {
        return !TrackStatus.isAvailable(values.getAsString(NotificationCompat.CATEGORY_STATUS));
    }
}
