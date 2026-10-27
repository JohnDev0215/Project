package com.android.providers.downloads;

import android.app.BroadcastOptions;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Downloads;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.providers.downloads.DownloadInfo;

/* JADX INFO: loaded from: classes.dex */
public class DownloadReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        final String action = intent.getAction();
        if ("android.intent.action.BOOT_COMPLETED".equals(action) || "android.intent.action.MEDIA_MOUNTED".equals(action)) {
            final BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
            Helpers.getAsyncHandler().post(new Runnable() { // from class: com.android.providers.downloads.-$$Lambda$DownloadReceiver$3KSuTZDSMuei3B7NgEQrFnkEHdY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onReceive$0$DownloadReceiver(context, action, pendingResultGoAsync);
                }
            });
            return;
        }
        if ("android.intent.action.UID_REMOVED".equals(action)) {
            final BroadcastReceiver.PendingResult pendingResultGoAsync2 = goAsync();
            Helpers.getAsyncHandler().post(new Runnable() { // from class: com.android.providers.downloads.-$$Lambda$DownloadReceiver$Ru8cK8OwYMuGjnD8RDzY1nT4xsc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onReceive$1$DownloadReceiver(context, intent, pendingResultGoAsync2);
                }
            });
            return;
        }
        if ("android.intent.action.DOWNLOAD_OPEN".equals(action) || "android.intent.action.DOWNLOAD_LIST".equals(action) || "android.intent.action.DOWNLOAD_HIDE".equals(action)) {
            final BroadcastReceiver.PendingResult pendingResultGoAsync3 = goAsync();
            if (pendingResultGoAsync3 == null) {
                handleNotificationBroadcast(context, intent);
                return;
            } else {
                Helpers.getAsyncHandler().post(new Runnable() { // from class: com.android.providers.downloads.-$$Lambda$DownloadReceiver$fSJaVn44GGaQJCgoqMZ4j6mlUoM
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onReceive$2$DownloadReceiver(context, intent, pendingResultGoAsync3);
                    }
                });
                return;
            }
        }
        if ("android.intent.action.DOWNLOAD_CANCEL".equals(action)) {
            ((DownloadManager) context.getSystemService("download")).remove(intent.getLongArrayExtra("com.android.providers.downloads.extra.CANCELED_DOWNLOAD_IDS"));
            ((NotificationManager) context.getSystemService("notification")).cancel(intent.getStringExtra("com.android.providers.downloads.extra.CANCELED_DOWNLOAD_NOTIFICATION_TAG"), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onReceive$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onReceive$0$DownloadReceiver(Context context, String str, BroadcastReceiver.PendingResult pendingResult) {
        handleBootCompleted(context);
        if ("android.intent.action.MEDIA_MOUNTED".equals(str)) {
            handleRemovedUidEntries(context);
        }
        pendingResult.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onReceive$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onReceive$1$DownloadReceiver(Context context, Intent intent, BroadcastReceiver.PendingResult pendingResult) {
        handleUidRemoved(context, intent);
        pendingResult.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onReceive$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onReceive$2$DownloadReceiver(Context context, Intent intent, BroadcastReceiver.PendingResult pendingResult) {
        handleNotificationBroadcast(context, intent);
        pendingResult.finish();
    }

    private void handleBootCompleted(Context context) {
        Helpers.getDownloadNotifier(context).update();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursorQuery = contentResolver.query(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, null, null, null, null);
        try {
            DownloadInfo.Reader reader = new DownloadInfo.Reader(contentResolver, cursorQuery);
            DownloadInfo downloadInfo = new DownloadInfo(context);
            while (cursorQuery.moveToNext()) {
                reader.updateFromDatabase(downloadInfo);
                Helpers.scheduleJob(context, downloadInfo);
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            DownloadIdleService.scheduleIdlePass(context);
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

    private void handleRemovedUidEntries(Context context) {
        ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient("downloads");
        try {
            Helpers.handleRemovedUidEntries(context, contentProviderClientAcquireContentProviderClient.getLocalContentProvider(), -1);
            if (contentProviderClientAcquireContentProviderClient != null) {
                contentProviderClientAcquireContentProviderClient.close();
            }
        } catch (Throwable th) {
            if (contentProviderClientAcquireContentProviderClient != null) {
                try {
                    contentProviderClientAcquireContentProviderClient.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void handleUidRemoved(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("android.intent.extra.UID", -1);
        if (intExtra == -1) {
            return;
        }
        ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient("downloads");
        try {
            Helpers.handleRemovedUidEntries(context, contentProviderClientAcquireContentProviderClient.getLocalContentProvider(), intExtra);
            if (contentProviderClientAcquireContentProviderClient != null) {
                contentProviderClientAcquireContentProviderClient.close();
            }
        } catch (Throwable th) {
            if (contentProviderClientAcquireContentProviderClient != null) {
                try {
                    contentProviderClientAcquireContentProviderClient.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void handleNotificationBroadcast(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.DOWNLOAD_LIST".equals(action)) {
            sendNotificationClickedIntent(context, intent.getLongArrayExtra("extra_click_download_ids"));
            return;
        }
        if ("android.intent.action.DOWNLOAD_OPEN".equals(action)) {
            long id = ContentUris.parseId(intent.getData());
            openDownload(context, id);
            hideNotification(context, id);
        } else if ("android.intent.action.DOWNLOAD_HIDE".equals(action)) {
            hideNotification(context, ContentUris.parseId(intent.getData()));
        }
    }

    private void hideNotification(Context context, long j) {
        Uri uriWithAppendedId = ContentUris.withAppendedId(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, j);
        Cursor cursorQuery = context.getContentResolver().query(uriWithAppendedId, null, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                int i = Helpers.getInt(cursorQuery, "status");
                int i2 = Helpers.getInt(cursorQuery, "visibility");
                cursorQuery.close();
                if (Downloads.Impl.isStatusCompleted(i)) {
                    if (i2 == 1 || i2 == 3) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("visibility", (Integer) 0);
                        context.getContentResolver().update(uriWithAppendedId, contentValues, null, null);
                        return;
                    }
                    return;
                }
                return;
            }
            Log.w("DownloadManager", "Missing details for download " + j);
        } finally {
            cursorQuery.close();
        }
    }

    private void openDownload(Context context, long j) {
        if (OpenHelper.startViewIntent(context, j, 268435456)) {
            return;
        }
        Toast.makeText(context, R.string.download_no_application_title, 0).show();
    }

    private void sendNotificationClickedIntent(Context context, long[] jArr) {
        Intent intent;
        Uri uriWithAppendedId = ContentUris.withAppendedId(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, jArr[0]);
        Cursor cursorQuery = context.getContentResolver().query(uriWithAppendedId, null, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                String string = Helpers.getString(cursorQuery, "notificationpackage");
                String string2 = Helpers.getString(cursorQuery, "notificationclass");
                boolean z = Helpers.getInt(cursorQuery, "is_public_api") != 0;
                cursorQuery.close();
                if (TextUtils.isEmpty(string)) {
                    Log.w("DownloadManager", "Missing package; skipping broadcast");
                    return;
                }
                if (z) {
                    intent = new Intent("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED");
                    intent.setPackage(string);
                    intent.putExtra("extra_click_download_ids", jArr);
                } else {
                    if (TextUtils.isEmpty(string2)) {
                        Log.w("DownloadManager", "Missing class; skipping broadcast");
                        return;
                    }
                    Intent intent2 = new Intent("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED");
                    intent2.setClassName(string, string2);
                    intent2.putExtra("extra_click_download_ids", jArr);
                    if (jArr.length == 1) {
                        intent2.setData(uriWithAppendedId);
                    } else {
                        intent2.setData(Downloads.Impl.CONTENT_URI);
                    }
                    intent = intent2;
                }
                BroadcastOptions broadcastOptionsMakeBasic = BroadcastOptions.makeBasic();
                broadcastOptionsMakeBasic.setBackgroundActivityStartsAllowed(true);
                Helpers.getSystemFacade(context).sendBroadcast(intent, null, broadcastOptionsMakeBasic.toBundle());
                return;
            }
            Log.w("DownloadManager", "Missing details for download " + jArr[0]);
        } finally {
            cursorQuery.close();
        }
    }
}
