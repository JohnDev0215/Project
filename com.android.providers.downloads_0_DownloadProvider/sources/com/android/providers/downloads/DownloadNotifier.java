package com.android.providers.downloads;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.SystemClock;
import android.provider.Downloads;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.IntArray;
import android.util.LongSparseLongArray;

/* JADX INFO: loaded from: classes.dex */
public class DownloadNotifier {
    private final Context mContext;
    private final NotificationManager mNotifManager;
    private final ArrayMap<String, Long> mActiveNotifs = new ArrayMap<>();
    private final LongSparseLongArray mDownloadSpeed = new LongSparseLongArray();
    private final LongSparseLongArray mDownloadTouch = new LongSparseLongArray();

    private interface UpdateQuery {
        public static final String[] PROJECTION = {"_id", "status", "visibility", "notificationpackage", "current_bytes", "total_bytes", "destination", "title", "description"};
    }

    private static boolean isActiveAndVisible(int i, int i2) {
        return i == 192 && (i2 == 0 || i2 == 1);
    }

    private static boolean isQueuedAndVisible(int i, int i2) {
        return i == 196 && (i2 == 0 || i2 == 1);
    }

    public DownloadNotifier(Context context) {
        this.mContext = context;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        this.mNotifManager = notificationManager;
        notificationManager.createNotificationChannel(new NotificationChannel("active", context.getText(R.string.download_running), 1));
        this.mNotifManager.createNotificationChannel(new NotificationChannel("waiting", context.getText(R.string.download_queued), 3));
        this.mNotifManager.createNotificationChannel(new NotificationChannel("complete", context.getText(android.R.string.config_satellite_gateway_service_package), 3));
    }

    public void notifyDownloadSpeed(long j, long j2) {
        synchronized (this.mDownloadSpeed) {
            if (j2 != 0) {
                this.mDownloadSpeed.put(j, j2);
                this.mDownloadTouch.put(j, SystemClock.elapsedRealtime());
            } else {
                this.mDownloadSpeed.delete(j);
                this.mDownloadTouch.delete(j);
            }
        }
    }

    public void update() {
        Cursor cursorQuery = this.mContext.getContentResolver().query(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, UpdateQuery.PROJECTION, "deleted == '0'", null, null);
        try {
            synchronized (this.mActiveNotifs) {
                updateWithLocked(cursorQuery);
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateWithLocked(android.database.Cursor r28) {
        /*
            Method dump skipped, instruction units count: 856
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.DownloadNotifier.updateWithLocked(android.database.Cursor):void");
    }

    private static CharSequence getDownloadTitle(Resources resources, Cursor cursor) {
        String string = cursor.getString(7);
        return !TextUtils.isEmpty(string) ? string : resources.getString(R.string.download_unknown_title);
    }

    private long[] getDownloadIds(Cursor cursor, IntArray intArray) {
        long[] jArr = new long[intArray.size()];
        for (int i = 0; i < intArray.size(); i++) {
            cursor.moveToPosition(intArray.get(i));
            jArr[i] = cursor.getLong(0);
        }
        return jArr;
    }

    private static String buildNotificationTag(Cursor cursor) {
        long j = cursor.getLong(0);
        int i = cursor.getInt(1);
        int i2 = cursor.getInt(2);
        String string = cursor.getString(3);
        if (isQueuedAndVisible(i, i2)) {
            return "2:" + string;
        }
        if (isActiveAndVisible(i, i2)) {
            return "1:" + string;
        }
        if (!isCompleteAndVisible(i, i2)) {
            return null;
        }
        return "3:" + j;
    }

    private static int getNotificationTagType(String str) {
        return Integer.parseInt(str.substring(0, str.indexOf(58)));
    }

    private static boolean isCompleteAndVisible(int i, int i2) {
        return Downloads.Impl.isStatusCompleted(i) && (i2 == 1 || i2 == 3);
    }
}
